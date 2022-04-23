package com.example.crime;

public class Data {
    private int id;
    private String name;
    private int age;
    private String detail, detailU;
    private int delete;

    public Data(int id, String name, int age, String detail, String detailU, int delete) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.detail = detail;
        this.detailU = detailU;
        this.delete = delete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetailU() {
        return detailU;
    }

    public void setDetailU(String detailU) {
        this.detailU = detailU;
    }

    public int isDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }
}
