package View;

import Model.InfoGame;
import Model.InfoMove;
import Model.ResourceManager;
import Model.Scoring;
import static View.view.controller_$;
import java.io.File;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static latest.version.LatestVersion.Windows;

public class ScoreBoard extends GUI {

    private Stage AlertBox;
    private Scene scene;
    private Pane root;
    TableView<Scoring> table;

    public ScoreBoard() {
        AlertBox = new Stage();
        root = new Pane();

        table = new TableView<>();
        table.setEditable(true);
        table.setMinSize(500, 700);

        TableColumn<Scoring, String> c1 = new TableColumn<>("Name Player");
        c1.setMinWidth(200);
        c1.setMaxWidth(200);
        TableColumn<Scoring, String> c2 = new TableColumn<>("Win");
        c2.setMinWidth(100);
        c2.setMaxWidth(100);
        TableColumn<Scoring, String> c3 = new TableColumn<>("Lose");
        c3.setMinWidth(100);
        c3.setMaxWidth(100);
        TableColumn<Scoring, String> c4 = new TableColumn<>("Marks");
        c4.setMinWidth(100);
        c4.setMaxWidth(100);

        c1.setCellValueFactory(new PropertyValueFactory<>("Name"));
        c2.setCellValueFactory(new PropertyValueFactory<>("win"));
        c3.setCellValueFactory(new PropertyValueFactory<>("lose"));
        c4.setCellValueFactory(new PropertyValueFactory<>("marks"));
        table.getColumns().addAll(c1, c2, c3, c4);

//c4.setSortType(TableColumn.SortType.ASCENDING);
        FillTabel();

        root.getChildren().add(table);
        scene = new Scene(root, 500, 700);
        AlertBox.setScene(scene);
        AlertBox.showAndWait();
    }

    private void FillTabel() {
        File folder = new File("src/scoreboard/");
        File[] listOfFiles = folder.listFiles();

        ObservableList<Scoring> data = FXCollections.observableArrayList();

        for (File listOfFile : listOfFiles) {

            try {
                Scoring x = (Scoring) ResourceManager.load("scoreboard/" + listOfFile.getName());
                data.add(x);
            } catch (Exception ex) {
                Logger.getLogger(ScoreBoard.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        Comparator<Scoring> comparator = Comparator.comparingDouble(Scoring::getMarks);
        comparator = comparator.reversed();

        FXCollections.sort(data, comparator);

        table.setItems(data);

    }

    @Override
    public void setScene() {
        Windows.setScene(scene);
    }

}
