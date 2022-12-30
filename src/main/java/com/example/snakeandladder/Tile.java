package com.example.snakeandladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


//Rectangle is a Shape class to generate different kind of shape
public class Tile extends Rectangle {


    //when we create object of tile then this constructor will call and inside constructer
    //we have declared the size and fill the color of one particular tile
    public Tile(int size){
        setWidth(size);
        setHeight(size);
        setFill(Color.RED);
        setStroke(Color.BLACK);


    }
}
