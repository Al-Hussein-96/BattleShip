package View;

import static latest.version.LatestVersion.HEIGHT;
import static latest.version.LatestVersion.WIDTH;
import static latest.version.LatestVersion.Windows;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import static latest.version.LatestVersion.displaySetting;
//import static latest.version.LatestVersion.displaySetting;
import static latest.version.LatestVersion.publicMethod;
import static latest.version.LatestVersion.settingShipBoard;
public class GameOptions
{

    private Scene scene;

    private final List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Setting Ships and Board", () ->
            {
                settingShipBoard.setScene();

            }),
            new Pair<String, Runnable>("Display Settings", () ->
            {
                displaySetting.setScene();
                
            }),
            new Pair<String, Runnable>("Back To Main Menu", () ->
            {
                new MainMenu();
            })
    );

    public GameOptions()
    {
        Pane root = new Pane();
        ImageView imageview = publicMethod.addBackground("war2.jpg");
        Titles title = publicMethod.addTitle("Game Options");
        VBox list = new VBox(-5);
        title.setTranslateX(300);
        title.setTranslateY(120);
        list.setTranslateX(420);
        list.setTranslateY(200);

        menuData.forEach(data ->
        {
            MenuItems item = new MenuItems(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-450);
            Rectangle clip = new Rectangle(450, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());
            item.setClip(clip);
            list.getChildren().add(item);
        });
        root.getChildren().addAll(imageview, title, list);
        publicMethod.startAnimation(list);
        scene = new Scene(root, WIDTH, HEIGHT);
        Windows.setScene(scene);

    }


}
