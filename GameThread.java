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

public class GameThread extends Thread{

    public Handler handler;
    private boolean running = true;
    Game game;

    public GameThread(Game game){
        this.game = game;
        handler = new Handler();
        handler.addObject(new Player(100,100,100, ID.Player, this));
    }

    public synchronized void stopThread(){
        running = false;
    }

    @Override
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running){

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1){
                tick();
                delta--;
            }

            if(running) render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }

        System.out.println("GOODBYE :)");
        return;
    }


    private void tick(){
        handler.tick();
    }

    private void render(){
        handler.render();
    }

}
