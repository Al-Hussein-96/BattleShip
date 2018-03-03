package View;

import Model.InfoGame;
import Model.Scoring;
import static View.view.controller_$;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import static latest.version.LatestVersion.HEIGHT;
import static latest.version.LatestVersion.WIDTH;
import static latest.version.LatestVersion.Windows;

public class PreviousGame {

    Scene scene;
    Pane root;
    TableView<InfoGame> tabel;
    ObservableList<InfoGame> data = FXCollections.observableArrayList();

    public PreviousGame() {
        root = new Pane();
        tabel = new TableView<>();

        tabel.setMinSize(1100, 400);
        // t.setEditable(true);
        ImageView imageview = (new PublicMethod()).addBackground("war.png");
        Titles Previousgame = new Titles("Previous Game", Color.WHITE);
        Previousgame.setTranslateX(330 - Previousgame.getWidth());
        Previousgame.setTranslateY(60);

        TableColumn<InfoGame, String> c1 = new TableColumn<>("ID Game");
        c1.setMinWidth(100);
        c1.setMaxWidth(100);
        TableColumn<InfoGame, String> c2 = new TableColumn<>("Name Human");
        c2.setMinWidth(150);
        c2.setMaxWidth(150);
        TableColumn<InfoGame, String> c3 = new TableColumn<>("Type Computer");
        c3.setMinWidth(150);
        c3.setMaxWidth(150);

        TableColumn<InfoGame, String> c4 = new TableColumn<>("Start Time");
        c4.setMinWidth(200);
        c4.setMaxWidth(200);
        TableColumn<InfoGame, String> c5 = new TableColumn<>("End Time");
        c5.setMinWidth(200);
        c5.setMaxWidth(200);
        TableColumn<InfoGame, String> c6 = new TableColumn<>("Winner");
        c6.setMinWidth(150);
        c6.setMaxWidth(150);
        TableColumn<InfoGame, String> c7 = new TableColumn<>("Marks");
        c7.setMinWidth(100);
        c7.setMaxWidth(100);
        TableColumn<InfoGame, String> c8 = new TableColumn<>("Restore");
        c8.setMinWidth(100);
        c8.setMaxWidth(100);
        tabel.getColumns().addAll(c1, c2, c3, c4, c5, c6, c7, c8);

        c1.setCellValueFactory(new PropertyValueFactory<>("IdGame"));
        c2.setCellValueFactory(new PropertyValueFactory<>("NameHuman"));
        c3.setCellValueFactory(new PropertyValueFactory<>("TypeComputer"));
        c4.setCellValueFactory(new PropertyValueFactory<>("StartGame"));
        c5.setCellValueFactory(new PropertyValueFactory<>("EndGame"));
        c6.setCellValueFactory(new PropertyValueFactory<>("Winner"));
        c7.setCellValueFactory(new PropertyValueFactory<>("Marks"));
        c8.setCellValueFactory(new PropertyValueFactory<>("Restore"));

        Callback<TableColumn<InfoGame, String>, TableCell<InfoGame, String>> cellFactory
                = //
                new Callback<TableColumn<InfoGame, String>, TableCell<InfoGame, String>>() {
            @Override
            public TableCell call(final TableColumn<InfoGame, String> param) {
                final TableCell<InfoGame, String> cell = new TableCell<InfoGame, String>() {

                    final Button btn = new Button("Restore");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                InfoGame SelecetGame = getTableView().getItems().get(getIndex());
                                new AnalysisGame(SelecetGame);
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        c8.setCellFactory(cellFactory);

        TextField textField = new TextField();
        ComboBox comboBox = new ComboBox<>();
        comboBox.getItems().addAll("ID Game", "Name Human", "Type Computer", "Start Time", "End Time", "Winner", "Marks");
        comboBox.getSelectionModel().selectFirst();
        Button Search = new Button("Search");
        Search.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        Button Back = new Button("Back");
        Back.setStyle("-fx-font: 25 arial; -fx-base: #ee2211;");

        HBox h = new HBox(20, textField, comboBox, Search);
        VBox v = new VBox(30, Previousgame, h, tabel, Back);
        v.setAlignment(Pos.CENTER);
        v.setTranslateX(60);

        root.getChildren().addAll(imageview, v);

        FillTabel();

        System.out.println("Data: " + data.size());

        FilteredList<InfoGame> filteredData = new FilteredList<>(data, e -> true);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(object -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String t = (String) textField.getText();
                boolean ok = false;
                if (null != t) {
                    switch ((String) comboBox.getValue()) {
                        case "ID Game":
                            ok = (String.valueOf(object.getIdGame()).contains(t.toLowerCase()));
                            break;
                        case "Name Human":
                            System.out.println("HELLO ID " + object.getIdGame() + " : " + t);
                            ok = (object.getNameHuman().contains(t));
                            break;
                        case "Type Computer":
                            ok = (object.getTypeComputer().contains(t));
                            break;
                        case "Start Time":
                            ok = (object.getStartGame().contains(t));
                            break;
                        case "End Time":
                            ok = (object.getEndGame().contains(t));
                            break;
                        case "Winner":
                            ok = (object.getWinner().contains(t));
                            break;
                        case "Marks":
                            ok = (String.valueOf(object.getMarks()) == t);
                            break;
                        default:
                            break;
                    }
                }
                System.out.println("OK: " + ok);
                return ok;
            });
        });

        SortedList<InfoGame> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabel.comparatorProperty());
        tabel.setItems(sortedData);

        scene = new Scene(root, WIDTH, HEIGHT);
        Windows.setScene(scene);

        Search.setOnAction(e -> {
            //  tabel.getItems().clear();

        });

        Back.setOnAction(e -> {
            new ResultGame();
        });

    }

    private void FillTabel() {
        File folder = new File("src/previous/");
        File[] listOfFiles = folder.listFiles();

        data.clear();

        for (File listOfFile : listOfFiles) {
            InfoGame x = controller_$.LoadInfoGame("previous/" + listOfFile.getName());
            // x.drawShip();

            data.add(x);
        }

    }

}
