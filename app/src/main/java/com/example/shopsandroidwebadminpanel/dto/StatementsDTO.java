package com.example.shopsandroidwebadminpanel.dto;

public class StatementsDTO {
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String email;
    private String product;

    public StatementsDTO(){}

    public StatementsDTO(String firstname, String lastname, String phonenumber, String email, String product){
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.product = product;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
