package com.example.shopsandroidwebadminpanel.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private String price;
    private String image;
    private String type_product;
    private String link;
    private String IdentityANDROID;

    public ProductDTO(){}

    public ProductDTO(String name, String description, String price, String image, String type_product, String link, String IdentityANDROID) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.type_product = type_product;
        this.link = link;
        this.IdentityANDROID = IdentityANDROID;
    }

    public String getIdentityANDROID() {
        return IdentityANDROID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getTypeproduct() {
        return type_product;
    }

    public String getLink() {
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
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
