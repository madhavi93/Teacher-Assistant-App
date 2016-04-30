package com.example.madhaviruwandika.teacher_assistant.Model.Util;

import java.security.PrivateKey;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public class ItemSlideMenu {


    private String title;
    private int imgId;

    public ItemSlideMenu(String title) {
        this.title = title;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
