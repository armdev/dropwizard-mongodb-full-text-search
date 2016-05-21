# dropwizard-mongodb-full-text-search
dropwizard mongodb full text search, JSF2.2 as front end

dropwizard will run in the port 8080, so you should change tomcat port for running the frontend.

just add text index in the mongo db

//    db.article.createIndex(
//   {
//      title: "text",
//      content: "text"
//    }
1. start mongodb
2. deploy and start dropwizard(right click and click run on the project), start frontend , from web from add articles and do search.
3. used NetBeans, Apache Tomcat 8
4. enjoy and extend
