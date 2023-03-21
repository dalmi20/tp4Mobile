package com.example.takennotes;

import java.io.Serializable;

public class Item implements Serializable {
    public String userName;
    public String content;
    public int imgSrc;
    public Item(String n,String c,int i) {
        this.userName=n;
        this.content=c;
        this.imgSrc=i;

    }
}
