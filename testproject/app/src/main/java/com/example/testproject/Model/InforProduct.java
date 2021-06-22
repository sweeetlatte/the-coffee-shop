package com.example.testproject.Model;

import java.io.Serializable;

public class InforProduct implements Serializable {
    private String name;
    private String src;

    public InforProduct(String name, String src) {
        this.name = name;
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
