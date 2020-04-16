package com.example.lab3_fragments;

import android.net.Uri;

public class Item
{
    private String titile;
    private int img;
    private  String info;
    private Uri ph;

    public Item(String titile, String info, Uri ph)
    {
        //int img
        this.titile = titile;
        //this.img = img;
        this.info = info;
        this.ph = ph;
    }

    public String getTitile()
    {
        return this.titile;
    }

    public int getImg()
    {
        return this.img;
    }

    public String getInfo()
    {
        return this.info;
    }

    public  Uri getPh()
    {
        return this.ph;
    }
}
