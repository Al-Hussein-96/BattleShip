package View;

import static View.view.controller_$;
import static View.view.model_$;
import static latest.version.LatestVersion.HEIGHT;
import static latest.version.LatestVersion.WIDTH;
import static latest.version.LatestVersion.Windows;
import javafx.scene.Scene;
import java.util.Arrays;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import static latest.version.LatestVersion.loadGame;

public class MainMenu {

    Pane root = new Pane();

    Scene scene;

    private final List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<>("New Game", ()
                    -> {
                controller_$.setIdgame();
                new Login();
                // new SingleGame();
            }),
            new Pair<>("Load Game", ()
                    -> {
                loadGame.setScene();

            }),
            new Pair<>("Quick Load", ()
                    -> {
                try {
                    controller_$.LoadGame("QuickSaved");
                    model_$.getBaseGame().Restore();

                } catch (Exception ex) {
                    

                }

            }),
            new Pair<>("Result Game", ()
                    -> {
                new ResultGame();

            }),
            new Pair<>("Game Options", ()
                    -> {
                new GameOptions();
            }),
            new Pair<>("Credits", ()
                    -> {
                new Thanks();
            }),
            new Pair<>("Exit to Desktop", Platform::exit)
    );
    private VBox list = new VBox();

    public MainMenu() {
        ImageView imageview = (new PublicMethod()).addBackground("war.png");
        Titles title = (new PublicMethod()).addTitle("Battle Ship War I");

        title.setTranslateX(300);
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
        (new PublicMethod()).startAnimation(list);
        scene = new Scene(root, WIDTH, HEIGHT);
        Windows.setScene(scene);

    }

}
