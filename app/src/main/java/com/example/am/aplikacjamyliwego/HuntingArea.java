package com.example.am.aplikacjamyliwego;

/**
 * Created by pawel on 09.05.2016.
 */
public class HuntingArea {
    private int id;
    private String name;
    private String description;
    private double topLeftCorner;
    private double topRightCorner;
    private double bottomLeftCorner;
    private double bottomRightCorner;

    public HuntingArea(){
        this.id = -1;
        this.name = "";
        this.description = "";
        this.topLeftCorner = 0.0;
        this.topRightCorner = 0.0;
        this.bottomLeftCorner = 0.0;
        this.bottomRightCorner = 0.0;
    }

    void setId(int id){ this.id = id; }
    void setName(String name){ this.name = name; }
    void setDescription(String description){ this.description = description; }
    void setTopLeftCorner(double topLeftCorner) { this.topLeftCorner = topLeftCorner; }
    void setTopRightCorner(double topRightCorner){ this.topRightCorner = topRightCorner; }
    void setBottomLeftCorner(double bottomLeftCorner){ this.bottomLeftCorner = bottomLeftCorner; }
    void setBottomRightCorner(double bottomRightCorner){ this.bottomRightCorner = bottomRightCorner; }

    int getId(){ return this.id; }
    String getName(){ return this.name; }
    String getDescription(){ return  this.description; }
    double getTopLeftCorner(){ return  this.topLeftCorner; }
    double getTopRightCorner(){ return  this.topRightCorner; }
    double getBottomLeftCorner(){ return  this.bottomLeftCorner; }
    double getBottomRightCorner(){ return  this.bottomRightCorner; }

}
