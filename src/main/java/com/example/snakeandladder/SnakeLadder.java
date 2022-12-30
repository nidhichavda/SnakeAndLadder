package com.example.snakeandladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {

    public static final int tileSize = 40, height=10, width=10 ; // make global variable to use anywhere
    int lowerLine = tileSize*height;
    public int diceValue;
    Label rolledDiceValueLable;
    Button startGameButton;

    boolean firstPlayerTurn =true, secondPlayerTurn = false, gameStarted =false;

    Player firstPlayer = new Player(tileSize-10, Color.BLACK, "Player1");
    Player secondPlayer = new Player(tileSize-10,Color.BEIGE,"Player2");//-10 becz if they go on the same position we can see them

    public Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+100);//(width,height of Pane)

        //create 10*10 tiles by calling Tile constructor
        for(int i=0; i<width; i++) {
            for(int j=0; j<height; j++) {
                Tile tile = new Tile(tileSize);// it only creates the tiles
                tile.setTranslateX(i*tileSize); //we have to set its positing where we want to place it.
                tile.setTranslateY(j*tileSize);
                root.getChildren().add(tile);  //now we have to add this to Pane
            }
        }

        firstPlayer.setCoinInTile(10,380);
        secondPlayer.setCoinInTile(30,380);

        //to give path righr click on image file on left side pannel then copy path reference and then select absolute path
        //it copied and then paste in ""
        Image img = new Image("F:\\workspace\\SnakeAndLadder\\src\\main\\java\\snakeladderimage.jpg");//Image is already a class of javafx
        ImageView boardImage = new ImageView();// ImageView is a class of javafx
        boardImage.setImage(img);//set image
        boardImage.setFitHeight(tileSize*height);// set height
        boardImage.setFitWidth(tileSize*width);// set width
        root.getChildren().add(boardImage); // add this to panel


        //create buttons

        //player one button
        Button playerOneButton = new Button("Player One");
        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(lowerLine+20);

        //action on player one
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(firstPlayerTurn){
                        setDiceValue();
                        firstPlayer.movePlayer(diceValue);
                        if(firstPlayer.playerWon()!=null)
                        {
                            rolledDiceValueLable.setText(firstPlayer.playerWon());
                            firstPlayerTurn=true;
                            secondPlayerTurn=false;
                            gameStarted=false;
                        }
                        firstPlayerTurn=false;
                        secondPlayerTurn=true;
                    }
                }


            }
        });

//write code to make snake and ladder effective


        //player two button
        Button playerTwoButton = new Button("Player Two");
        playerTwoButton.setTranslateX(250);
        playerTwoButton.setTranslateY(lowerLine+20);


        startGameButton = new Button("Start");
        startGameButton.setTranslateX(130);
        startGameButton.setTranslateY(lowerLine+50);
        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted=true;
                startGameButton.setText("Ongoing Game");
                startGameButton.setDisable(true);
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(secondPlayerTurn){
                        setDiceValue();
                        secondPlayer.movePlayer(diceValue);
                        if(secondPlayer.playerWon()!=null)
                        {
                            rolledDiceValueLable.setText(secondPlayer.playerWon());
                            firstPlayerTurn=true;
                            secondPlayerTurn=false;
                            gameStarted=false;
                            startGameButton.setDisable(false);
                            startGameButton.setText("Start Game");
                        }
                        secondPlayerTurn=false;
                        firstPlayerTurn=true;
                    }
                }

            }
        });






        rolledDiceValueLable = new Label("Start the Game");
        rolledDiceValueLable.setTranslateY(lowerLine+20);
        rolledDiceValueLable.setTranslateX(130);


        root.getChildren().addAll(playerOneButton,playerTwoButton);
        root.getChildren().addAll(firstPlayer.getCoin(),rolledDiceValueLable,startGameButton,secondPlayer.getCoin());


        return root;
    }

    private void setDiceValue(){

        //random() generate the value between 0 to 1 and 0 and 1 are excluded
        //then we are multiplying it with 6 suppose 0.2 *6 +1 = 1.2+1=2.2==>in int it is 2;

        diceValue = (int)(Math.random()*6+1);
        rolledDiceValueLable.setText("Dice Value:"+ diceValue);

    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
