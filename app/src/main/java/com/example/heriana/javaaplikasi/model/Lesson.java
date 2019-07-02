package com.example.heriana.javaaplikasi.model;

import java.io.Serializable;

/**
 * Created by AKM on 7/1/2019.
 */
public class Lesson implements Serializable {

    private String name;
    private String content;
    private String fileUrl;

    public Lesson () {

    }

    public Lesson (String name, String content, String fileUrl) {
        this.name = name;
        this.content = content;
        this.fileUrl = fileUrl;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}
