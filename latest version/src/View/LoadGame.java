package View;

import Model.Game;
import Model.ResourceManager;
import Model.SaveData;
import static View.view.controller_$;
import static View.view.model_$;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import static latest.version.LatestVersion.HEIGHT;
import static latest.version.LatestVersion.WIDTH;
import static latest.version.LatestVersion.Windows;

public class LoadGame {

    Scene scene;
    Pane root;
    ListView list;

    public LoadGame() {
        ImageView imageview = (new PublicMethod()).addBackground("war.png");
        root = new Pane();
        Titles load = new Titles("Load Game", Color.WHITE);
        list = new ListView<>();
        // list.getItems().addAll("roulla", "mohammad");
        HBox h = new HBox();
        Button loaad = new Button("L o a d");
        loaad.setFont(new Font("Times New Roman", 20));
        loaad.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");
        Button delete = new Button("D e l e t e");
        delete.setFont(new Font("Times New Roman", 20));
        delete.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");
        Button Back = new Button("B a c k");
        Back.setFont(new Font("Times New Roman", 20));
        Back.setStyle("-fx-font: 30 ariel; -fx-base: #aa5533");
        h.getChildren().addAll(Back, delete, loaad);
        h.setSpacing(50);
        VBox v = new VBox(load, list, h);

        v.setTranslateX(170);
        v.setTranslateY(65);
        v.setSpacing(50);
        v.setAlignment(Pos.CENTER);

        root.getChildren().addAll(imageview, v);
        scene = new Scene(root, WIDTH, HEIGHT);

        fillList();

        Back.setOnAction(e -> {
            new MainMenu();
        });
        if (!list.getItems().isEmpty()) {
            list.getSelectionModel().selectFirst();
        }

        loaad.setOnAction(e -> {
            controller_$.LoadGame((String) list.getSelectionModel().getSelectedItem());
            model_$.getBaseGame().Restore();
        });

        delete.setOnAction(e -> {
            File folder = new File("src/save/");
            File[] listOfFiles = folder.listFiles();

            // System.out.println(listOfFiles.length + " ::: " + list.getItems().size());
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles.length == 0) {
                    continue;
                }
                if (listOfFiles[i].getName() == null ? (String) list.getSelectionModel().getSelectedItem() == null : listOfFiles[i].getName().equals((String) list.getSelectionModel().getSelectedItem())) {

                    list.getItems().remove(i);
                    if (!list.getItems().isEmpty()) {
                        list.getSelectionModel().selectFirst();
                    }
                    listOfFiles[i].delete();
                    
                    
                }
            }

        });

    }

    public void setScene() {
        getAllLoad();
        Windows.setScene(scene);
    }

    public void getAllLoad() {
        list.getItems().clear();
        fillList();
    }

    private void fillList() {
        File folder = new File("src/save/");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (!"QuickSaved".equals(listOfFiles[i].getName())) {
                list.getItems().add(listOfFiles[i].getName());
            }
        }
    }

}
