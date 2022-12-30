package com.example.snakeandladder;

import java.util.ArrayList;
import javafx.util.Pair;

public class Board {
    private ArrayList<Pair<Integer,Integer>> positionCoordinates;
    private ArrayList<Integer> snackLadderPosition;

    public Board(){
        populatePositionCoordinates();
        populateSnackLadderPosition();
    }
    //it will create coordinates to store ina map
    private void populatePositionCoordinates(){
        positionCoordinates = new ArrayList<>();
        positionCoordinates.add(new Pair<Integer,Integer>(0,0)); // dummy values at index 0


        int x =1 ,y=10 ,xPos,yPos;

        for(int i = 0; i< SnakeLadder.height; i++) // for y coordinate
        {
            x =1; // for every y the x value will be 1
            for(int j = 0; j< SnakeLadder.width; j++)// for x coordinate
            {
                if(y%2==0) // for moving 1 to 10 x will have to increment when we move from 11 to 20 it should decreement
                {
                  xPos = x* SnakeLadder.tileSize- SnakeLadder.tileSize/2; // 1*40 - (40/2)=20 === 40-20 ===20
                }
                else {
                    xPos = SnakeLadder.width* SnakeLadder.tileSize - (x* SnakeLadder.tileSize- SnakeLadder.tileSize/2); // 1*40 - (40/2)=20 === 40-20 ===20


                }
                yPos = y* SnakeLadder.tileSize- SnakeLadder.tileSize/2; //10*40-20 ==400-20 ==380
                positionCoordinates.add(new Pair<Integer,Integer>(xPos,yPos));
                x++; //x increment becz we want to increment the size
            }
            y-- ; // y decrement becz we starts from 380

        }

    }


    private void populateSnackLadderPosition(){
        snackLadderPosition = new ArrayList<>();
        for(int i=0; i<101; i++)
        {
            snackLadderPosition.add(i);
        }
        snackLadderPosition.set(4,25);
        snackLadderPosition.set(13,46);
        snackLadderPosition.set(27,5);
        snackLadderPosition.set(33,49);
        snackLadderPosition.set(40,3);
        snackLadderPosition.set(42,63);
        snackLadderPosition.set(43,18);
        snackLadderPosition.set(50,69);
        snackLadderPosition.set(54,31);
        snackLadderPosition.set(62,81);
        snackLadderPosition.set(66,45);
        snackLadderPosition.set(76,58);
        snackLadderPosition.set(74,92);
        snackLadderPosition.set(89,53);
        snackLadderPosition.set(99,41);

    }

    //when someone gives a valid index then return the x coordinates
    public int getXCoordinate(int position)
    {
        return positionCoordinates.get(position).getKey();
    }
    public int getYCoordinate(int position)
    {
        return positionCoordinates.get(position).getValue();
    }

    public int getNextPosition(int position){
        if(position >=1 && position<=100 )
        return snackLadderPosition.get(position);
        else return -1;

    }

//    public static void main(String[] args) {
//        Board board = new Board();
//        board.populatePositionCoordinates();
//        for(int i=0; i<board.positionCoordinates.size(); i++)
//        {
//            System.out.println(i + "x: "+ board.positionCoordinates.get(i).getKey()+"y:" +
//                    board.positionCoordinates.get(i).getValue());
//        }
//    }

}
