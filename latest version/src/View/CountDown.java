package View;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CountDown {

    private Thread t;
    private Text Count;

    public CountDown() {
        Count.setText("10");
    }
    
    public void Set(String text) {
        Count.setText(text);
        if (Integer.parseInt(text) <= 5) {
            Count.setFill(Color.ORANGE);
        } else {
            Count.setFill(Color.WHITE);
        }
    }

}
