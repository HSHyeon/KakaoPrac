package com.example.practicesns.view;

public class Board {
    int id;
    String imgURL;
    String title;
    String text;
    String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Board(int id, String imgURL, String title, String text,String host){
        this.id=id;
        this.imgURL=imgURL;
        this.title=title;
        this.text=text;
        this.host=host;
    }
}
