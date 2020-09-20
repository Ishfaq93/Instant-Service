package com.example.instantservice.logIn_SignUp;

public class UserDataClass {
    String name, username, email, phoneNo,adress, password;

    public UserDataClass() {
    }
    public UserDataClass(String name) {
        this.name=name;
    }

    public UserDataClass(String name, String username, String email, String phoneNo, String adress, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phoneNo = phoneNo;
        this.adress = adress;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
