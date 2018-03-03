package View;

import Model.BattleshipGame;
import Model.InfoGame;
import Model.InfoMove;
import static Model.model.BaseGame;
import View.BoardAndCell.Cell;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static latest.version.LatestVersion.HEIGHT;
import static latest.version.LatestVersion.WIDTH;
import static latest.version.LatestVersion.Windows;
import static latest.version.LatestVersion.publicMethod;

public class AnalysisGame {

    InfoGame Information;
    Scene scene;
    Pane root;

    public AnalysisGame(InfoGame Information) {
        this.Information = Information;
        ImageView imageview = publicMethod.addBackground("war.png");
        Titles analysisGame = new Titles("Analysis Game", Color.WHITE);
        analysisGame.setTranslateX(370);
        analysisGame.setTranslateY(60);
        root = new Pane(imageview, analysisGame);
        int w = ((BattleshipGame) BaseGame).getWidth();
        int h = ((BattleshipGame) BaseGame).getHeight();
        BoardAndCell HumanBoard = new BoardAndCell(w, h);
        BoardAndCell ComputerBoard = new BoardAndCell(w, h);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Cell cell1 = HumanBoard.getCell(j, i);
                cell1.set(this.Information.getHumanGrid().getSquare(i, j).getState());
                Cell cell2 = ComputerBoard.getCell(j, i);
                cell2.set(this.Information.getComputerGrid().getSquare(i, j).getState());
            }
        }
        Text t1 = new Text("Human Player");
        Text t2 = new Text("Computer Player");
        t1.setFont(new Font(23));
        t2.setFont(new Font(23));
        t1.setTranslateX(150);
        t2.setTranslateX(150);
        t1.setFill(Color.WHITE);
        t2.setFill(Color.WHITE);

        Button HumanMove = new Button("Human Move");
        HumanMove.setStyle("-fx-font: 25 arial; -fx-base: #ee2211;");
        HumanMove.setTranslateX(100);

        Button ComputerMove = new Button("Computer Move");
        ComputerMove.setStyle("-fx-font: 25 arial; -fx-base: #ee2211;");
        ComputerMove.setTranslateX(100);

        VBox v1 = new VBox(15);
        VBox v2 = new VBox(15);

        v1.getChildren().addAll(t1, HumanBoard, HumanMove);
        v2.getChildren().addAll(t2, ComputerBoard, ComputerMove);

        HBox h1 = new HBox(20, v1, v2);
        h1.setTranslateX(200);
        h1.setTranslateY(130);
        
        Button Back = new Button("Back");
        Back.setStyle("-fx-font: 25 arial; -fx-base: #ee2211;");
        Back.setTranslateX(20);
        Back.setTranslateY(660);
        
        root.getChildren().addAll(h1,Back);
        scene = new Scene(root, WIDTH, HEIGHT);
        Windows.setScene(scene);
        
        Back.setOnAction(e -> {
            new PreviousGame();
        });

        HumanMove.setOnAction(e -> {
            DisplayMove(Information.getHumanMove());
        });

        ComputerMove.setOnAction(e -> {
            DisplayMove(Information.getComputerMove());
        });

    }

    private void DisplayMove(List<InfoMove> playerMove) {
        Stage AlertBox = new Stage();
        AlertBox.initModality(Modality.APPLICATION_MODAL);
        AlertBox.setTitle("Winner");
        //   AlertBox.setMinWidth(250);
        Pane root1 = new Pane();

        TableView<InfoMove> table = new TableView<>();
        table.setMinSize(520, 700);

        TableColumn<InfoMove, String> c1 = new TableColumn<>("Number");
        c1.setMinWidth(100);
        c1.setMaxWidth(100);
        TableColumn<InfoMove, String> c2 = new TableColumn<>("Coordinates");
        c2.setMinWidth(100);
        c2.setMaxWidth(100);
        TableColumn<InfoMove, String> c3 = new TableColumn<>("Time");
        c3.setMinWidth(100);
        c3.setMaxWidth(100);
        TableColumn<InfoMove, String> c4 = new TableColumn<>("Result");
        c4.setMinWidth(200);
        c4.setMaxWidth(200);
        TableColumn<InfoMove, String> c5 = new TableColumn<>("Mark");
        c4.setMinWidth(120);
        c4.setMaxWidth(120);

        c1.setCellValueFactory(new PropertyValueFactory<>("Number"));
        c2.setCellValueFactory(new PropertyValueFactory<>("Coord"));
        c3.setCellValueFactory(new PropertyValueFactory<>("TimeMove"));
        c4.setCellValueFactory(new PropertyValueFactory<>("ResultMove"));
        c5.setCellValueFactory(new PropertyValueFactory<>("Mark"));

        table.getColumns().addAll(c1, c2, c3, c4,c5);
        ObservableList<InfoMove> data = FXCollections.observableArrayList();

        for (int i = 0; i < playerMove.size(); i++) {
            InfoMove x = playerMove.get(i);
            x.setNumber(i + 1);
            data.add(x);
        }
        table.setItems(data);
        
        root1.getChildren().add(table);
        Scene scene1 = new Scene(root1, 520, 700);
        AlertBox.setScene(scene1);
        AlertBox.showAndWait();
    }

}
