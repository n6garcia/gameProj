import java.util.LinkedList;
import javafx.scene.input.*;
import javafx.event.*;


public class Handler{

  LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void keyPress(KeyCode key){
        for(int i = 0; i < object.size(); i++){

            GameObject tempObject = object.get(i);
            tempObject.keyPressed(key);
        }
    }

    public void keyRelease(KeyCode key){
        for(int i = 0; i < object.size(); i++){

            GameObject tempObject = object.get(i);
            tempObject.keyReleased(key);
        }
    }

  public void tick(){
    for(int i = 0; i < object.size(); i++){
      GameObject tempObject = object.get(i);

      tempObject.tick();
    }
  }

  public void render(){
    for(int i = 0; i < object.size(); i++){
      GameObject tempObject = object.get(i);

      tempObject.render();
    }
  }

  public void addObject(GameObject object){
    this.object.add(object);
  }

  public void removeObject(GameObject object){
    this.object.remove(object);
  }
}
