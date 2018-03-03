package View;

import static View.view.controller_$;
import static latest.version.LatestVersion.HEIGHT;
import static latest.version.LatestVersion.WIDTH;
import static latest.version.LatestVersion.Windows;
import static latest.version.LatestVersion.publicMethod;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SettingShipBoard {

    Scene scene;
    Pane root = new Pane();
    ComboBox W = new ComboBox<>();
    ComboBox H = new ComboBox<>();
    ComboBox NumberOfShip = new ComboBox<>();
    ComboBox ship_W = new ComboBox<>();
    ComboBox ship_H = new ComboBox<>();
    ComboBox[] WShip = new ComboBox[8];
    ComboBox[] HShip = new ComboBox[8];

    GridPane Grid = new GridPane();

    Text Width1 = new Text("Width");
    Text Height1 = new Text("Height");

    public SettingShipBoard() {
        ImageView imageview = publicMethod.addBackground("war6.jpg");

        /// Board Setting
        Titles Board = new Titles("Board Setting", Color.WHITE);
        Board.setTranslateX(370);
        Board.setTranslateY(60);

        Width1.setFont(new Font("Verdana", 25));
        Height1.setFont(new Font("Verdana", 25));

        for (int i = 2; i <= 13; i++) {
            W.getItems().add(i);
            H.getItems().add(i);
        }
        W.getSelectionModel().select(8);
        H.getSelectionModel().select(8);

        W.valueProperty().addListener((observable, oldvalue, newvalue)
                -> {
            controller_$.editDimensionsWidth((int) W.getValue());
        });
        H.valueProperty().addListener((observable, oldvalue, newvalue)
                -> {
            controller_$.editDimensionsHeight((int) W.getValue());
        });

        /// Ship Setting ///////////////////////////////////////////
        Titles ShipSetting = new Titles("Ships Setting", Color.WHITE);
        ShipSetting.setTranslateX(370);
        ShipSetting.setTranslateY(60);
        Text NumShip = new Text("Number of Ships");
        NumShip.setFont(new Font("Verdana", 25));

        for (int i = 1; i <= 5; i++) {
            NumberOfShip.getItems().add(i);
        }
        NumberOfShip.getSelectionModel().selectLast();
     //   NumberOfShip.getSelectionModel().select(1);

        W.valueProperty().addListener((observable, oldvalue, newvalue)
                -> {
            setComboBoxNumberOfShip();
        });
        H.valueProperty().addListener(e -> setComboBoxNumberOfShip());
        NumberOfShip.valueProperty().addListener((observable, oldvalue, newvalue) -> setComboBoxGroupShip());

        /// Setting Grid for every Ship
        Grid.setTranslateX(350);
        Grid.setHgap(20);
        Grid.setVgap(20);
        setComboBoxGroupShip();
        /// Add All Childern
        Button Back = new Button("Back");
        Button Reset = new Button("Reset");
        Back.setFont(new Font("Verdana", 25));
        Reset.setFont(new Font("Verdana", 25));
        Back.setOnAction(e -> new GameOptions());
        Reset.setOnAction(e
                -> {
            W.getSelectionModel().select(8);
            H.getSelectionModel().select(8);
            NumberOfShip.getSelectionModel().select(4);
            setComboBoxGroupShip();
            setComboBoxNumberOfShip();
        });
        HBox h1 = new HBox();
        HBox h2 = new HBox();
        HBox h3 = new HBox();
        VBox v1 = new VBox();

        h1.setTranslateX(430);
        h1.setSpacing(20);
        h2.setTranslateX(430);
        h2.setSpacing(20);
        v1.setSpacing(20);
        h3.setTranslateX(350);
        h3.setTranslateY(20);
        h3.setSpacing(400);
        h1.getChildren().addAll(Width1, W, Height1, H);
        h2.getChildren().addAll(NumShip, NumberOfShip);
        h3.getChildren().addAll(Back, Reset);

        v1.getChildren().addAll(Board, h1, ShipSetting, h2, Grid, h3);

        root.getChildren().addAll(imageview, v1);

        scene = new Scene(root, WIDTH, HEIGHT);

    }

    public void setComboBoxGroupShip() {
        Grid.getChildren().clear();

        if (NumberOfShip.getItems().isEmpty()) {
            return;
        }

        int x3 = (int) NumberOfShip.getValue();

        for (int i = 1; i <= x3; i++) {
            Text countShip = new Text("Ship " + i);
            countShip.setFont(new Font("Verdana", 25));

            Text Width2 = new Text("Width");
            Text Height2 = new Text("Height");
            Width2.setFont(new Font("Verdana", 25));
            Height2.setFont(new Font("Verdana", 25));
            Width2.setFill(Color.WHITESMOKE);
            Height2.setFill(Color.WHITESMOKE);
            int x2 = (int) W.getValue();
            int y2 = (int) H.getValue();
            WShip[i] = new ComboBox();
            HShip[i] = new ComboBox();
            for (int j = 1; j <= Math.min(x2 / 2, y2 / 2); j++) {
                WShip[i].getItems().add(j);
                HShip[i].getItems().add(j);
            }
            HShip[i].getSelectionModel().selectFirst();
            WShip[i].getSelectionModel().select(i - 1);
            HBox h3 = new HBox(countShip, Width2, WShip[i], Height2, HShip[i]);
            h3.setSpacing(40);
            Grid.addRow(i, h3);
        }
    }

    public void setComboBoxNumberOfShip() {
        NumberOfShip.getItems().remove(1, NumberOfShip.getItems().size());/// here It (was) wrong 
        int x2 = (int) W.getValue();
        int y2 = (int) H.getValue();
        for (int i = 2; i <= Math.min(x2, y2) / 2; i++) {
            NumberOfShip.getItems().add(i);
        }
//        NumberOfShip.getSelectionModel().select(Math.min(4,Math.min(x2, y2)/2 - 1));
        NumberOfShip.getSelectionModel().select(0);

        setComboBoxGroupShip();

    }

    public void setScene() {
        Windows.setScene(scene);

    }

}
