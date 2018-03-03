package View;

import Model.SmartComputerPlayer;
import static View.view.controller_$;
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
import static latest.version.LatestVersion.publicMethod;

public class SingleGame {

    private Scene scene;

    private final List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<>("Random Computer Player", ()
                    -> {
                controller_$.AddHuman();
                controller_$.AddRnadom();
                controller_$.setTypeComputer("Random");
                controller_$.StartGame();
                

                new InitializeShipOnBoard();

            }),
            new Pair<>("Smart Computer Player", ()
                    -> {
                controller_$.AddHuman();
                controller_$.AddSmart();
                controller_$.setTypeComputer("Smart");
                controller_$.StartGame();

                new InitializeShipOnBoard();

            }),
            new Pair<>("Back To Main Menu", ()
                    -> {
                controller_$.ClearEveryThing();
                new MainMenu();
            })
    );

    public SingleGame() {
        Pane root = new Pane();
        VBox list = new VBox();
        ImageView imageview = publicMethod.addBackground("war4.jpg");
        Titles title = publicMethod.addTitle("Single Player");
        title.setTranslateX(400);
        title.setTranslateY(120);
        list.setTranslateX(420);
        list.setTranslateY(200);

        menuData.forEach(data
                -> {
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

//    public void setScene()
//    {
//        publicMethod.startAnimation(this.list);
//        scene = new Scene(this.root, WIDTH, HEIGHT);
//    }
}
