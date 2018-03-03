package View;

import Model.Game;
import Model.model;
import static View.view.controller_$;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.text.StyledEditorKit;

public class SaveGame {

    Stage Save = new Stage();
    Scene scene;

    public SaveGame() {
        Save.initModality(Modality.APPLICATION_MODAL);
        Save.setTitle("Login");
        Save.setMinWidth(250);

        AnchorPane root = new AnchorPane();
        root.setPrefSize(360, 420);

        root.setStyle("-fx-background-color: #222222");

        Text SaveGame = new Text("Save Game");
        Text NameFile = new Text("Name File");

        SaveGame.setTranslateX(100);
        SaveGame.setTranslateY(50);
        SaveGame.setFill(Color.WHITESMOKE);
        SaveGame.setFont(new Font(31));

        NameFile.setTranslateX(90);
        NameFile.setTranslateY(180);
        NameFile.setFont(new Font(25));
        NameFile.setFill(Color.WHITE);

        TextField textField = new TextField();

        textField.setTranslateX(90);
        textField.setTranslateY(197);

        Button Back = new Button("Back");
        Button Done = new Button("Done");

        Back.setPrefSize(70, 50);

        Done.setPrefSize(70, 50);

        Button DefaultSave = new Button("Default Save");

        HBox h = new HBox(30, Back, Done);

        h.setTranslateX(100);
        h.setTranslateY(250);

        DefaultSave.setTranslateX(100);
        DefaultSave.setTranslateY(270);

        VBox v = new VBox(h, DefaultSave);

        /// enabel and disable button when text field is empty
        BooleanBinding b = new BooleanBinding() {
            {
                super.bind(textField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return textField.getText().isEmpty();
            }
        };
        Done.disableProperty().bind(b);

        Done.setOnAction(e -> {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    controller_$.SaveGame("save/" + textField.getText());
                }
            }).start();
            Game.Pause = false;
            Save.close();
        });

        DefaultSave.setOnAction(e -> {
            controller_$.SaveGame("save/" + String.valueOf(model.BaseGame.getInformation().getIdGame()));
            Save.close();
        });

        Back.setOnAction(e -> {
            Save.close();
        });

        root.getChildren().addAll(SaveGame, NameFile, textField, v);

        scene = new Scene(root, 360, 420);

        Save.setScene(scene);
        Save.showAndWait();
    }

}
