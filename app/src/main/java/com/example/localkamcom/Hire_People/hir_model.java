package com.example.localkamcom.Hire_People;

public class hir_model {
    String name,phone,age,amount,expertise,address;


    public hir_model(String name, String phone, String age, String amount, String expertise, String address) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.amount = amount;
        this.expertise = expertise;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
