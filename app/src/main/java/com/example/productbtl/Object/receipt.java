package com.example.productbtl.Object;

public class receipt {
    private String   address, date, id , name , phonenumber ,price , product, status;

    public receipt() {
    }

    public receipt(String address, String date, String id, String name, String phonenumber, String price, String product, String status) {
        this.address = address;
        this.date = date;
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.price = price;
        this.product = product;
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
