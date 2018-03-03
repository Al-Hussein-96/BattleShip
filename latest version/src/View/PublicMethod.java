package View;


import static latest.version.LatestVersion.HEIGHT;
import static latest.version.LatestVersion.WIDTH;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PublicMethod
{


    public ImageView addBackground(String nameImage)
    {
        ImageView imageview = new ImageView(new Image(getClass().getResource("res/" + nameImage).toExternalForm()));
        imageview.setFitWidth(WIDTH);
        imageview.setFitHeight(HEIGHT);
        return imageview;
    }

    public Titles addTitle(String text)
    {
        Titles title = new Titles(text,Color.WHITE);
        return title;
    }

    public void startAnimation(VBox list)
    {
        ScaleTransition st = new ScaleTransition();
        st.setToY(0);
        st.setOnFinished(e ->
        {

            for (int i = 0; i < list.getChildren().size(); i++)
            {
                Node n = list.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

}
