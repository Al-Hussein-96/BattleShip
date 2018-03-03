package View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import static latest.version.LatestVersion.HEIGHT;
import static latest.version.LatestVersion.WIDTH;
import static latest.version.LatestVersion.Windows;

public class ResultGame {

    Scene scene;
    Pane root;

    public ResultGame() {
        
        Titles ResultGame = new Titles("Result Game", Color.WHITE);
        ResultGame.setTranslateX(390 - ResultGame.getWidth());
        ResultGame.setTranslateY(100);
        root = new Pane();
        ImageView imageview = (new PublicMethod()).addBackground("war.png");

        Button Score = new Button("Score Board");
        Button Previous = new Button("Previous Games");
        Button Back = new Button("Back");
        Score.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        Previous.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        Back.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");

        HBox h = new HBox(50,Back, Previous,Score);
        
        Score.setPrefSize(200, 50);
        Previous.setPrefSize(200, 50);
        Back.setPrefSize(200, 50);
        h.setTranslateX(300);
        h.setTranslateY(300);
        
        root.getChildren().addAll(imageview,ResultGame,h);

        scene = new Scene(root, WIDTH, HEIGHT);
        Windows.setScene(scene);

        /**
         * ****** Event *******
         */
        Previous.setOnAction(e -> {

            new PreviousGame();

        });

        Score.setOnAction(e -> {
            new ScoreBoard();
        });
        Back.setOnAction(e -> {
            new MainMenu();
        });

    }

}
