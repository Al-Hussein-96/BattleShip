package View;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Titles extends Pane
{

    private final Text text;

    public Titles(String name,Color color)
    {
        String spread = "";
        for (char c : name.toCharArray())
            spread += c + " ";

        text = new Text(spread);
        text.setFont(Font.font(50));
        text.setFill(color);
        text.setEffect(new DropShadow(30, Color.BLACK));

        getChildren().addAll(text);
    }

    public double getTitleWidth()
    {
        return text.getLayoutBounds().getWidth();
    }

    public double getTitleHeight()
    {
        return text.getLayoutBounds().getHeight();
    }

}
