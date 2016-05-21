package com.web.jsf.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



import java.util.UUID;

public class ArticleData {

    private String id = UUID.randomUUID().toString();

  
    private String title;

   
    private String content;

   
    private List<String> tags = new ArrayList<>();

    public ArticleData() {

    }

   
    public String getId() {
        return id;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.title);
        hash = 71 * hash + Objects.hashCode(this.content);
        hash = 71 * hash + Objects.hashCode(this.tags);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        return Objects.equals(this.tags, other.tags);
    }

    @Override
    public String toString() {
        return "ArticleData{" + "id=" + id + ", title=" + title + ", content=" + content + ", tags=" + tags + '}';
    }

}
