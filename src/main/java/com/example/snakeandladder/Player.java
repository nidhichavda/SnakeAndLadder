package com.example.snakeandladder;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;// Circle is a javafx class , created object coin of circle class
    private int coinPosition;
    private  String name;

    private static Board gameBoard =new Board();

    public Player(int tileSize, Color coinColor, String playerName){
        coinPosition=1;
        name = playerName;
        coin = new Circle(tileSize/4);
        coin.setFill(coinColor);
//        coin.setTranslateX(10);
//        coin.setTranslateY(380);
    }

    //move dice
    public void movePlayer(int diceValue)
    {
        if(coinPosition + diceValue <=100){ //it should not excceed the limit, if i am at 97 and my dice value is 4 then i need to stay at my position
            coinPosition += diceValue;
//            coin.setTranslateX(gameBoard.getXCoordinate(coinPosition));//move on x position
//            coin.setTranslateY(gameBoard.getYCoordinate(coinPosition)); // move coin on y position
            translatePlayer();
            int newPosition = gameBoard.getNextPosition(coinPosition);
            if(newPosition != coinPosition){
                coinPosition = newPosition;
                translatePlayer();
            }
        }

    }


    public String playerWon(){
        if(coinPosition == 100){
            return name + "Won the Game";
        }
        return null;
    }

    //animation to move our circle smoothly
    private void translatePlayer(){
        //the duration: 1000 time to take to move our circle 1000milisec =1sec //the piece that we want apply on it
        TranslateTransition move = new TranslateTransition(Duration.millis(1000), this.coin);

        //where i want to move our coin
        move.setToX(gameBoard.getXCoordinate(coinPosition));
        move.setToY(gameBoard.getYCoordinate(coinPosition));
        move.setAutoReverse(false);
        move.play();

    }



    //we create getter method becaz Circle coin object is private so we have to access it through getter method
    public Circle getCoin() {
        return coin;
    }

    //we create getter method becaz int coinPosition variable is private so we have to access it through getter method
    public int getCoinPosition() {
        return coinPosition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoinInTile(int x, int y) {
        this.coin.setTranslateX(x);
         this.coin.setTranslateY(y);
    }
}

