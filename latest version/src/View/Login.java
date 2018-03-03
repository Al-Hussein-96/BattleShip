package View;

import Model.BattleshipGame;
import Model.Game;
import Model.InfoGame;
import static Model.model.BaseGame;
import static View.view.controller_$;
import java.io.File;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.BreakNode;

public class Login {

    Stage login = new Stage();
    Scene scene;

    public Login() {
        login.initModality(Modality.APPLICATION_MODAL);
        login.setTitle("Login");
        login.setMinWidth(250);

        AnchorPane root = new AnchorPane();
        root.setPrefSize(360, 420);

        int idgame = ((Game) BaseGame).getInformation().getIdGame();
        Text IdGame = new Text("ID Game : " + String.valueOf(idgame));
        Text YourName = new Text("Your Name");

        IdGame.setTranslateX(90);
        IdGame.setTranslateY(80);
        IdGame.setFont(new Font(31));

        YourName.setTranslateX(90);
        YourName.setTranslateY(180);
        YourName.setFont(new Font(25));

        TextField textField = new TextField();

        textField.setTranslateX(90);
        textField.setTranslateY(197);

        Button Sign = new Button("Sign in");

        Sign.setTranslateX(120);
        Sign.setTranslateY(300);
        Sign.setPrefSize(120, 50);

        BooleanBinding b = new BooleanBinding() {
            {
                super.bind(textField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return textField.getText().isEmpty() ;
            }

        };
        Sign.disableProperty().bind(b);

        Sign.setOnAction(e -> {
            //if (nameNotFound(textField.getText())) {
                login.close();

                controller_$.setNameHuman(textField.getText());
                new SingleGame();
            //}
        });

        root.getChildren().addAll(IdGame, YourName, textField, Sign);

        scene = new Scene(root, 360, 420);

        login.setScene(scene);
        login.showAndWait();

    }

    private boolean nameNotFound(String text) {
        File folder = new File("src/scoreboard/");
        File[] listOfFiles = folder.listFiles();

        for (File listOfFile : listOfFiles) {
            if (listOfFile.getName() == null ? text == null : listOfFile.getName().equals(text)) {
                return false;
            }
        }
        return true;

    }

}
