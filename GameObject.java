import javafx.scene.input.*;

public abstract class GameObject {
    protected int x, y, z;
    protected ID id;
    protected int velX, velY, velZ;
    protected GameThread game;

    public GameObject(int x, int y, int z, ID id, GameThread game){
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
        this.game = game;
    }

    public abstract void tick();
    public abstract void keyPressed(KeyCode key);
    public abstract void keyReleased(KeyCode key);
    public abstract void render();

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setId(ID id){
        this.id = id;
    }

    public ID getId(){
        return id;
    }

    public void setVelX(int velX){
        this.velX = velX;
    }

    public void setVelY(int velY){
        this.velY = velY;
    }

    public int getVelX(){
        return velX;
    }

    public int getVelY(){
        return velY;
    }
}
