package View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static latest.version.LatestVersion.Windows;
import static latest.version.LatestVersion.publicMethod;

public class Thanks
{

    public Thanks()
    {
        Scene scene;
        Pane root = new Pane();
        ImageView imageview = publicMethod.addBackground("war.png");
        Titles name1 = new Titles("Created By: ", Color.CORAL);
        Titles name2 = new Titles("Mohammad Al-Hussein", Color.ALICEBLUE);
        Titles name3 = new Titles("Engineer: Faten Zahra", Color.DARKGRAY);
        Button Back = new Button("Back");
        Back.setStyle("-fx-font: 35 arial; -fx-base: #ee2211;");
        VBox v = new VBox(name1, name2, name3,Back);
        name2.setTranslateX(20);
        name3.setTranslateY(10);
        v.setTranslateX(200);
        v.setTranslateY(120);

        root.getChildren().addAll(imageview, v);
        scene = new Scene(root, 1200, 720);
        
        
        Back.setOnAction(e -> new MainMenu());
        
        Windows.setScene(scene);

    }

}
