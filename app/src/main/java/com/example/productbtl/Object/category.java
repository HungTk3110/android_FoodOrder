package com.example.productbtl.Object;

import java.util.List;

public class category {
    private String name;
    private List<product> list;

    public category() {
    }

    public category(String name, List<product> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<product> getList() {
        return list;
    }

    public void setList(List<product> list) {
        this.list = list;
    }
}
