package com.example.heriana.javaaplikasi.model;

import java.io.Serializable;

/**
 * Created by AKM on 7/1/2019.
 */
public class Lesson implements Serializable {

    private String name;
    private String content;

    public Lesson () {

    }

    public Lesson (String name, String content) {
        this.name = name;
        this.content = content;
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

}
