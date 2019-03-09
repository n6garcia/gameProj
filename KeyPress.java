import javafx.scene.input.*;
import javafx.event.*;

public class KeyPress implements EventHandler<KeyEvent> {
    Handler handler;

    public KeyPress(Handler handler){
        this.handler = handler;
    }

    @Override
    public void handle (KeyEvent e) {
        KeyCode key = e.getCode();
        handler.keyPress(key);
    }
}
