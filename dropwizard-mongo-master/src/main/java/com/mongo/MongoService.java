package com.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class MongoService extends Service<MongoConfiguration> {

    public static void main(String[] args) throws Exception {
        new MongoService().run(args);
    }

    @Override
    public void initialize(Bootstrap<MongoConfiguration> bootstrap) {
        bootstrap.setName("mongo");
    }

    @Override
    public void run(MongoConfiguration configuration, Environment environment) throws Exception {

        //Create Mongo instance
        Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
        //Add Managed for managing the Mongo instance
        MongoManaged mongoManaged = new MongoManaged(mongo);
        environment.manage(mongoManaged);
     
        environment.addHealthCheck(new MongoHealthCheck(mongo));
       
        DB db = mongo.getDB(configuration.mongodb);
        
        DBCollection articleCollection = db.getCollection("article");
        
        environment.addResource(new ArticleResource(articleCollection, db));

    }
}
