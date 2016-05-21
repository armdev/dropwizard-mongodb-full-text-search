package com.web.jsf.beans;

import com.web.jsf.dto.ArticleData;

import com.web.rest.client.RESTClientBean;
import java.io.Serializable;
import java.util.List;
import java.util.PropertyResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Home
 */
@ManagedBean(name = "articleBean")
@ViewScoped
public class ArticleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{restClient}")
    private RESTClientBean restClient;

    private ArticleData articleData = new ArticleData();

    private String searchQuery;

    public ArticleBean() {

    }

    @PostConstruct
    public void init() {

    }

    public void doSearch() {

    }

    public List<ArticleData> getArticleList() {
        if (searchQuery == null) {
            return null;
        }

        return restClient.getArticleList(searchQuery);
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
    
    

    public String saveArticle() {
        restClient.saveArticle(articleData);
        return "index";

    }

    public ArticleData getArticleData() {
        return articleData;
    }

    public void setArticleData(ArticleData articleData) {
        this.articleData = articleData;
    }

    public void setRestClient(RESTClientBean restClient) {
        this.restClient = restClient;
    }

    public PropertyResourceBundle getBundle() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().evaluateExpressionGet(context, "#{i18n}", PropertyResourceBundle.class);
    }

}
