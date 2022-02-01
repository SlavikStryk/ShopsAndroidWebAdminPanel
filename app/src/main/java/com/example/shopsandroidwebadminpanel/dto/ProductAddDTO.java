package com.example.shopsandroidwebadminpanel.dto;

import java.util.IdentityHashMap;

import lombok.Data;

@Data
public class ProductAddDTO {
    private String name;
    private String description;
    private double price;
    private String image;
    private String typeproduct;
    private String link;
    private String indentityANDROID;

    public ProductAddDTO(String name, String description, double price, String image, String type_product, String link, String IdentityANDROID) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.typeproduct = type_product;
        this.link = link;
        this.indentityANDROID = IdentityANDROID;
    }

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
        this.typeproduct = type_product;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setIdentityANDROID(String identityANDROID) {
        indentityANDROID = identityANDROID;
    }
}
