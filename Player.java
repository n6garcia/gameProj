import java.lang.Math;
import javafx.scene.input.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.application.*;

public class Player extends GameObject{
  public Player(int x, int y, int z, ID id, GameThread game){
    super(x, y, z, id, game);

    ShapeProj sp = new ShapeProj(game.game);
    sp.getCube();
    this.sp = sp;

  }

    private ShapeProj sp;
    private static int right, left, up , down;
    private float fTheta = 1;
    private static final int speed = 3;

  public void tick(){
    /*
    velX = right + left;
    velY = up + down;

    if (velX !=0 && velY !=0){
      velX = (int)Math.round(velX*Math.sqrt(2)/2*speed);
      velY = (int)Math.round(velY*Math.sqrt(2)/2*speed);
    } else {
      velX = velX * speed;
      velY = velY * speed;
    } */

    fTheta+= 0.01f;

    Platform.runLater(() -> {
        sp.rotate(fTheta);
    });

  }

  public void keyPressed(KeyCode key){
      /*
    if(key == KeyCode.UP) up = 1;
    if(key == KeyCode.DOWN) down = -1;
    if(key == KeyCode.RIGHT) right = 1;
    if(key == KeyCode.LEFT) left = -1;
    */
  }

  public void keyReleased(KeyCode key){
      /*
    if(key == KeyCode.UP) up = 0;
    if(key == KeyCode.DOWN) down = 0;
    if(key == KeyCode.RIGHT) right = 0;
    if(key == KeyCode.LEFT) left = 0;
    */
  }

  public void render(){}

}
