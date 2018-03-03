package View;

import Model.BattleshipGame;
import Model.BattleshipGame.StatusTurn;
import Model.HumanPlayer;
import Model.ComputerPlayer;
import Model.Ship;
import View.BoardAndCell.Cell;
import static View.view.controller_$;
import static View.view.model_$;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static latest.version.LatestVersion.HEIGHT;
import static latest.version.LatestVersion.WIDTH;
import static latest.version.LatestVersion.Windows;
import static latest.version.LatestVersion.publicMethod;
import static latest.version.LatestVersion.settingShipBoard;
import static latest.version.LatestVersion.displaySetting;

public class InitializeShipOnBoard {

    Scene scene;
    Pane root = new Pane();
    BoardAndCell board;
    ComboBox TypeOfShip = new ComboBox<>();
    RadioButton horizal = new RadioButton("Horizal");
    RadioButton vertical = new RadioButton("Vertical");

    BattleshipGame TheGame = (BattleshipGame) model_$.getBaseGame();
    HumanPlayer human = TheGame.getHuman();
    ComputerPlayer computer = TheGame.getComputer();

    public InitializeShipOnBoard() {
        ImageView imageview = publicMethod.addBackground("war4.jpg");

        /// Board name
        Titles InitializeBoard = new Titles("Initialize Ship On Board", Color.WHITE);
        InitializeBoard.setTranslateX(230 - InitializeBoard.getWidth());
        InitializeBoard.setTranslateY(80);

        board = new BoardAndCell(BoardAndCell.StatusBoard.INITIALIZE, (int) settingShipBoard.W.getValue(), (int) settingShipBoard.H.getValue(),
                event
                -> {
            if (!TypeOfShip.getItems().isEmpty()) {
                Cell cell = (Cell) event.getSource();

                String temp = (String) TypeOfShip.getValue();
                int wship = (int) (temp.charAt(0) - '0');
                int hship = (int) (temp.charAt(4) - '0');
                if (horizal.isSelected()) {
                    int t = wship;
                    wship = hship;
                    hship = t;
                }
                if (human.CheckAddShip(new Ship(cell.x, cell.y, wship, hship, Ship.ShipStatus.GOOD))) {
                    board.PlaceShip(new Ship(cell.x, cell.y, wship, hship, Ship.ShipStatus.GOOD));
                    controller_$.addShipHuman(human, new Ship(cell.x, cell.y, wship, hship, Ship.ShipStatus.GOOD));
                    controller_$.addShipComputer(computer, wship, hship);

                    TypeOfShip.getItems().remove(TypeOfShip.getValue());
                    TypeOfShip.getSelectionModel().selectFirst();
                }
            }
        },
                (event)
                -> {
            Cell cell = (Cell) event.getSource();
            cell.ChangeColor1();
        }, event
                -> {
            Cell cell = (Cell) event.getSource();
            cell.ChangeColor2();
        }
        );
        board.setTranslateX(380);
        board.setTranslateY(130);

        Button Back = new Button("Back");
        Button Start = new Button("Start");
        Back.setFont(new Font("Verdana", 25));
        Start.setFont(new Font("Verdana", 25));
        Start.setDisable(true);
        TypeOfShip.valueProperty().addListener(e
                -> {
            // if (TypeOfShip.getItems().isEmpty()) 
            {
                Start.setDisable(false);
            }
        });

        horizal.setStyle("-fx-font: 25 arial; -fx-base: #ee2211;");
        vertical.setStyle("-fx-font: 25 arial; -fx-base: #ee2211;");

        HBox h3 = new HBox();
        h3.setTranslateX(330);
        h3.setTranslateY(620);
        h3.setSpacing(400);
        h3.getChildren().addAll(Back, Start);

        /// Type of Ships
        Text Type = new Text("Type of Ship");
        Type.setFill(Color.WHITE);
        Type.setFont(new Font("Verdana", 25));

        int x = (int) settingShipBoard.NumberOfShip.getValue();
        for (Node child : settingShipBoard.Grid.getChildren()) {
            HBox hbox = (HBox) child;
            ComboBox combow = (ComboBox) hbox.getChildren().get(2);
            ComboBox comboh = (ComboBox) hbox.getChildren().get(4);
            int w = (int) combow.getValue();
            int h = (int) comboh.getValue();
            TypeOfShip.getItems().add(w + " * " + h);
        }
        TypeOfShip.getSelectionModel().selectFirst();
        VBox v = new VBox();
        v.setSpacing(20);

        v.setTranslateX(100);
        v.setTranslateY(170);

        ToggleGroup group = new ToggleGroup();
        horizal.setToggleGroup(group);
        vertical.setToggleGroup(group);
        HBox h = new HBox(horizal, vertical);

        horizal.setSelected(true);
        v.getChildren().addAll(Type, TypeOfShip, h);
        root.getChildren().addAll(imageview, InitializeBoard, board, h3, v);
        scene = new Scene(root, WIDTH, HEIGHT);
        Windows.setScene(scene);

        Back.setOnAction(e
                -> {
            controller_$.resetHuman(human);
            controller_$.resetComputer(computer);
            controller_$.ClearEveryThing();
            new SingleGame();
        });
        Start.setOnAction((ActionEvent e)
                -> {
            TheGame.RunningGame = true;
            TheGame.Turn = StatusTurn.HUMAN;

            controller_$.setPlayerGrid(); /// for Information
            controller_$.setStartTime(new Date());

            human.BattleshipPlayerAddBomb(1500);
//            human.BattleshipPlayerAddBomb(1500);
////            human.BattleshipPlayerAddBomb(1500);
////            human.BattleshipPlayerAddBomb(1500);
////            human.BattleshipPlayerAddBomb(1500);
            computer.BattleshipPlayerAddBomb(1500);
//            computer.BattleshipPlayerAddBomb(1500);

            // computer.draw();
            if ("ON".equals(displaySetting.text.getText())) {
                human.DisplayGUI(new GUIPlayer2());
            } else {
                human.DisplayGUI(new GUIPlayer3());
            }
            human.MoveAllBomb();
            computer.MoveAllBomb();
        });
    }

}
