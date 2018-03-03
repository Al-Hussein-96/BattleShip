package latest.version;


import Controller.*;
import Model.*;
import View.LoadGame;
import View.PreviousGame;
import View.PublicMethod;
import View.ScoreBoard;
import View.SettingShipBoard;
import View.view;
import View.displaysetting;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LatestVersion extends Application {

    public static Stage Windows;
    public static int WIDTH = 1280;
    public static int HEIGHT = 720;
    public static PublicMethod publicMethod = new PublicMethod();
    public static SettingShipBoard settingShipBoard = new SettingShipBoard();
    public static displaysetting displaySetting = new displaysetting();
    public static LoadGame loadGame = new LoadGame();
    public model Model_X = new model();
    public controller Controller_X = new controller(Model_X);
    public view View_X = new view(Model_X, Controller_X);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Windows = primaryStage;

        Windows.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });

        //    Model_X.SaveInfoGame("1");
        //   SaveInfo
        View_X.RunMainMenu();
      //  new PreviousGame();
        Windows.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

//    Stage window;
//
//    TextField nameInput, priceInput, quantityInput;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        window = primaryStage;
//        window.setTitle("thenewboston - JavaFX");
//
//        TableView<Product> table = new TableView<>();
//
//        //Name column
//        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
//        nameColumn.setMinWidth(200);
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//        //Price column
//        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
//        priceColumn.setMinWidth(100);
//        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
//
//        //Quantity column
//        TableColumn<Product, String> quantityColumn = new TableColumn<>("Quantity");
//        quantityColumn.setMinWidth(100);
//        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//
//
//
//
//
//        ObservableList<Product> products = FXCollections.observableArrayList();
//        products.add(new Product("Laptop", 859.00, 20));
//
//        //table = new TableView<>();
//        table.setItems(products);
//        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);
//
//        Scene scene = new Scene(new Pane(table));
//        window.setScene(scene);
//        window.show();
//    }
}
