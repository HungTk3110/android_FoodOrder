package com.example.productbtl.Object;

import java.io.Serializable;

public class product implements Serializable {
    String name  ,img , information ;
    Long price;

    public product() {
    }

    public product(String name, String img, String information, Long price) {
        this.name = name;
        this.img = img;
        this.information = information;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
