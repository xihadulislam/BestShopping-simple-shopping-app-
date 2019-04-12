package com.example.bestshopping.Model;

public class UserData {

    String username, address, email,password,imageuri,id;

    public UserData() {
    }

    public UserData(String username, String address, String email, String password, String imageuri, String id) {
        this.username = username;
        this.address = address;
        this.email = email;
        this.password = password;
        this.imageuri = imageuri;
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
