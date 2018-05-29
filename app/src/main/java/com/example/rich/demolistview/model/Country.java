package com.example.rich.demolistview.model;

public class Country {
    String name;
    int imgFlag;

    public Country(String name, int imgFlag) {
        this.name = name;
        this.imgFlag = imgFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgFlag() {
        return imgFlag;
    }

    public void setImgFlag(int imgFlag) {
        this.imgFlag = imgFlag;
    }
}
