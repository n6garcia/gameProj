import javafx.scene.input.*;
import javafx.event.*;

public class KeyRelease implements EventHandler<KeyEvent> {
    Handler handler;

    public KeyRelease(Handler handler){
        this.handler = handler;
    }

    @Override
    public void handle (KeyEvent e) {
        KeyCode key = e.getCode();
        handler.keyRelease(key);
    }
}
