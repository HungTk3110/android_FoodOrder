package com.example.productbtl.Object;

import java.io.Serializable;

public class menu implements Serializable {
    private String name;
    private int img;

    public menu() {
    }

    public menu(String name, int img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
