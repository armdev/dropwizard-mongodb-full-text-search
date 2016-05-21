package com.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.yammer.metrics.annotation.Timed;
import java.util.ArrayList;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.PathParam;
import net.karmafiles.ff.core.tool.dbutil.converter.Converter;

@Path("/article")
public class ArticleResource {

    //db.article.find(     {$text : {$search : "\"armenia\" keyword"}})
//    db.article.createIndex(
//   {
//      title: "text",
//      content: "text"
//    }
//  )
    private final DBCollection collection;
    private final DB database;

    public ArticleResource(DBCollection collection, DB database) {
        this.collection = collection;
        this.database = database;
    }

    @Path("/publish")
    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Timed
    public Response publishArticleData(@Valid ArticleData data) {
        // System.out.println("Received data " + data.getContent());
        DBObject dbObject = Converter.toDBObject(data);
        collection.save(dbObject, WriteConcern.SAFE);
        return Response.ok().entity("\"Success\"").type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/fetch/{searchQuery}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response fetchArticleData(@PathParam("searchQuery") String searchQuery) {
        System.out.println("searchQuery " + searchQuery);

        List<ArticleData> articles = this.doAdvancedSearch(searchQuery);

        return Response
                .ok(articles)
                .build();
    }

    public List<ArticleData> doAdvancedSearch(String searchString) {
        List<ArticleData> list = new ArrayList<>();

        DBCursor cursor = collection.find(new BasicDBObject("$text", new BasicDBObject("$search", searchString)));
        while (cursor.hasNext()) {
            DBObject document = cursor.next();
            ArticleData data = new ArticleData();
            data.setContent((String) document.get("content"));
            data.setTitle((String) document.get("title"));
            list.add(data);
        }

        return list;
    }

}
