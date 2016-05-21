package com.web.rest.client;

import com.web.jsf.dto.ArticleData;
import com.web.jsf.utils.ParamUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

@ManagedBean(eager = true, name = "restClient")
@ApplicationScoped
public class RESTClientBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public RESTClientBean() {

    }

    @PostConstruct
    public void init() {

    }

    public Integer saveArticle(ArticleData articleData) {
        HttpClient httpClient = new DefaultHttpClient();
        Integer userId = 0;
        try {
            HttpPost request = new HttpPost("http://localhost:8080/article/publish");
            JSONObject json = new JSONObject();
            json.put("title", articleData.getTitle());
            json.put("content", articleData.getContent());
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.addHeader("charset", "utf8");
            request.setEntity(params);
            HttpResponse response = (HttpResponse) httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            userId = ParamUtil.integerValue(EntityUtils.toString(entity));
            // System.out.println("Returned " + EntityUtils.toString(entity));
        } catch (IOException | ParseException ex) {

        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return userId;
    }
    
    public List<ArticleData> getArticleList(String query) {
     
        List<ArticleData> list = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://localhost:8080/article/fetch/"+query);
            HttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();

            ObjectMapper mapper = new ObjectMapper();
           
            list = mapper.readValue(EntityUtils.toString(entity),List.class);
       
        } catch (Exception e) {

            e.printStackTrace();

        }
        return list;

    }

}
