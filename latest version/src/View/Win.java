package View;

import Model.BattleshipPlayer;
import Model.HumanPlayer;
import static View.view.controller_$;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static latest.version.LatestVersion.publicMethod;

public class Win {

    public Win(BattleshipPlayer win) {
        
        controller_$.setWinner(win);

        Stage AlertBox = new Stage();
        AlertBox.initModality(Modality.APPLICATION_MODAL);
        AlertBox.setTitle("Winner");
        AlertBox.setMinWidth(250);

        Pane root = new Pane();
        ImageView imageview = publicMethod.addBackground("Win.jpg");
        imageview.setFitWidth(450);
        imageview.setFitHeight(450);
        String name;
        if (win instanceof HumanPlayer) {
            name = "You Win";
        } else {
            name = "You Lose";
        }

        Titles x = new Titles(name, Color.YELLOW);

        Button Finish = new Button("Finish");

        VBox v = new VBox(x, Finish);
        v.setAlignment(Pos.CENTER);
        v.setTranslateY(150);
        v.setTranslateX(80);
        v.setSpacing(50);
        Finish.setTranslateY(100);
        Finish.setStyle("-fx-font: 35 arial; -fx-base: #ee2211;");

        Finish.setOnAction(e -> {
            AlertBox.close();
            controller_$.SaveInfoGame();
            controller_$.ClearEveryThing();
            new MainMenu();

        });

        root.getChildren().addAll(imageview, v);

        Scene scene = new Scene(root, 450, 450);
        AlertBox.setScene(scene);
        AlertBox.showAndWait();

    }

}
