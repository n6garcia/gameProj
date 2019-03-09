import javafx.application.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.io.*;
import java.lang.*;

public class Game extends Application {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    GameThread game;

    Pane root = new Pane();
    Scene scene = new Scene(root);

    @Override
    public void start(Stage primaryStage) {

        //primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);

        primaryStage.setScene(scene);

        primaryStage.show();

        Rectangle bg = new Rectangle();
        bg.setFill(Color.BLACK);
        bg.setX(0); bg.setY(0);
        bg.setWidth(scene.getWidth());
        bg.setHeight(scene.getHeight());
        root.getChildren().add(bg);

        game = new GameThread(this);
        game.start();

        scene.setOnKeyPressed(new KeyPress(game.handler));
        scene.setOnKeyReleased(new KeyRelease(game.handler));

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override public void handle(WindowEvent t) {
                game.stopThread();
            }
        });

    }

    public static void main(String[] args){
        Application.launch(args);
    }

}
