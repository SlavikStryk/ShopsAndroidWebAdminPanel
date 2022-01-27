package com.example.shopsandroidwebadminpanel.dto;

import lombok.Data;

@Data
public class ProductAddDTO {
    private String name;
    private String description;
    private double price;
    private String image;
    private String type_product;
    private String link;
    private String IdentityANDROID;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType_product(String type_product) {
        this.type_product = type_product;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setIdentityANDROID(String identityANDROID) {
        IdentityANDROID = identityANDROID;
    }
}
