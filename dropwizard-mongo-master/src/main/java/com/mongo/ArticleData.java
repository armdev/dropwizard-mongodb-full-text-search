package com.mongo;

import java.util.Objects;


import org.bson.types.ObjectId;


public class ArticleData {

    
    private ObjectId _id;  

   
    private String title;

   
    private String content;

   

    public ArticleData() {

    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.title);
        hash = 83 * hash + Objects.hashCode(this.content);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArticleData other = (ArticleData) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return Objects.equals(this.content, other.content);
    }

    @Override
    public String toString() {
        return "ArticleData{" + "title=" + title + ", content=" + content + '}';
    }

 

    

}
