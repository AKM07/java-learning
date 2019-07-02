package com.example.heriana.javaaplikasi.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by AKM on 7/2/2019.
 */
public class PDF extends SugarRecord {

    private String url;
    private String path;
    private Date createdDate;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
