//package View;
//
//import Model.BattleshipGame;
//import Model.BattleshipMove;
//import Model.BattleshipPlayer;
//import Model.ComputerPlayer;
//import Model.HumanPlayer;
//import Model.Square;
//import static View.view.controller_$;
//import static View.view.model_$;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import static latest.version.LatestVersion.HEIGHT;
//import static latest.version.LatestVersion.RunningGame;
//import static latest.version.LatestVersion.WIDTH;
//import static latest.version.LatestVersion.Windows;
//import static latest.version.LatestVersion.publicMethod;
//import static latest.version.LatestVersion.settingShipBoard;
//
//public class GUIPlayer2 {
//
//    private final Scene scene;
//    private final Pane root = new Pane();
//    private BoardAndCell myBoard, myDraft, myOppentBoard;
//    BattleshipGame TheGame = (BattleshipGame) model_$.getGame();
//    HumanPlayer human = TheGame.getHuman();
//    ComputerPlayer computer = TheGame.getComputer();
//    Thread thread1, thread2;
//
//    public GUIPlayer2() {
//        myBoard = new BoardAndCell((int) settingShipBoard.W.getValue(), (int) settingShipBoard.H.getValue());
//        myOppentBoard = new BoardAndCell((int) settingShipBoard.W.getValue(), (int) settingShipBoard.H.getValue());
//
//        myDraft = new BoardAndCell((int) settingShipBoard.W.getValue(), (int) settingShipBoard.H.getValue(), (event)
//                -> {
//
//            if (!RunningGame) {
//                return;
//            }
//            BattleshipGame TheGame = (BattleshipGame) model_$.getGame();
//            HumanPlayer human = TheGame.getHuman();
//            ComputerPlayer computer = TheGame.getComputer();
//
//            BoardAndCell.Cell cell = (BoardAndCell.Cell) event.getSource();
//
//            if (human.getStateSquareDraft(new BattleshipMove(cell.x, cell.y)) != Square.SquareStatus.UNKNOWN) {
//                System.out.println("Repeat");
//                return;
//            }
//
//            model_$.RunGame(cell.x, cell.y, 0);
//            cell.Shoot(human.getStateSquareDraft(new BattleshipMove(cell.x, cell.y)));
//
//            if (!RunningGame) {
//                return;
//            }
//
//            model_$.RunGame(-1, -1, 1);
//            BattleshipMove curmove = ((BattleshipPlayer) computer).getcurrentMove();
//
//            BoardAndCell.Cell cell2 = myBoard.getCell(curmove.getY(), curmove.getX());
//            cell2.Shoot(computer.getStateSquareDraft(new BattleshipMove(curmove.getX(), curmove.getY())));
//
//            if (((BattleshipPlayer) computer).getAttackBomb()) {
//                ((BattleshipPlayer) computer).setAttackBomb(false);
//                //thread1.interrupt();
//                int dx[] = {1, -1, 1, -1, 0, 0, 1, -1};
//                int dy[] = {1, -1, -1, 1, 1, -1, 0, 0};
//                for (int i = 0; i < 8; i++) {
//                    int nx = curmove.getX() + dx[i];
//                    int ny = curmove.getY() + dy[i];
//
//                    if ((nx >= 0 && nx < (int) settingShipBoard.W.getValue()) && (ny >= 0 && ny < (int) settingShipBoard.H.getValue())) //         this.DraftGrid.setSquare(square);
//                    {
//                        cell2 = myBoard.getCell(nx, ny);
//                        cell2.Shoot(computer.getStateSquareDraft(new BattleshipMove(nx, ny)));
//                    }
//                }
//
//            }
//
//        }, event
//                -> {
//            BoardAndCell.Cell cell = (BoardAndCell.Cell) event.getSource();
//            cell.ChangeColor1();
//
//        }, event
//                -> {
//            BoardAndCell.Cell cell = (BoardAndCell.Cell) event.getSource();
//            cell.ChangeColor2();
//
//        });
//        for (int x = 0; x < (int) settingShipBoard.W.getValue(); x++) {
//            for (int y = 0; y < (int) settingShipBoard.H.getValue(); y++) {
//                BoardAndCell.Cell cell = myBoard.getCell(y, x);
//                Square.SquareStatus state = controller_$.getStateMy(x, y);
//                cell.set(state);
//
//                cell = myOppentBoard.getCell(y, x);
//                state = controller_$.getStateOppent(x, y);
//                cell.set(state);
//            }
//        }
//        ImageView imageview = publicMethod.addBackground("war.png");
//        Titles NamePlayer = new Titles("Player", Color.WHITE);
//        NamePlayer.setTranslateX(470 - NamePlayer.getWidth());
//        NamePlayer.setTranslateY(80);
//        Button Exit = new Button("Exit");
//
//        Exit.setTranslateX(50);
//        Exit.setTranslateY(650);
//        Exit.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");
//
//        Exit.setOnAction(e
//                -> {
//            controller_$.ClearEveryThing();
//            new MainMenu();
//        });
//        Button displayboard = new Button("Cheat");
//        displayboard.setStyle("-fx-font: 25 arial; -fx-base: #11eeee;");
//        displayboard.setTranslateX(50);
//        displayboard.setTranslateY(250);
//
//        displayboard.setOnAction(e -> new Cheat());
//
//        HBox h = new HBox();
//        h.getChildren().addAll(myBoard, myDraft);
//        h.setSpacing(40);
//        h.setTranslateX(200);
//        h.setTranslateY(150);
//        root.getChildren().addAll(imageview, NamePlayer, h, Exit, displayboard);
//        scene = new Scene(root, WIDTH, HEIGHT);
//        Windows.setScene(scene);
//
//        thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        int x = human.MyGrid.getBomb().getX();
//                        int y = human.MyGrid.getBomb().getY();
//                        human.MyGrid.moveBomb();
//                        System.out.println(human.MyGrid.getBomb().getX() + " :: " + human.MyGrid.getBomb().getY());
//                        BoardAndCell.Cell c = myBoard.getCell(y, x);
//                        c.set(Square.SquareStatus.WATER);
//                        x = human.MyGrid.getBomb().getX();
//                        y = human.MyGrid.getBomb().getY();
//                        c = myBoard.getCell(y, x);
//                        c.set(Square.SquareStatus.HAVE_BOMB);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        break;
//                    }
//                    try {
//                        Thread.sleep(1500);
//                    } catch (InterruptedException ex) {
//                        break;
//                    }
//                }
//            }
//        });
//        thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        int x = computer.MyGrid.getBomb().getX();
//                        int y = computer.MyGrid.getBomb().getY();
//                        computer.MyGrid.moveBomb();
//                        BoardAndCell.Cell c = myOppentBoard.getCell(y, x);
//                        c.set(Square.SquareStatus.WATER);
//                        x = computer.MyGrid.getBomb().getX();
//                        y = computer.MyGrid.getBomb().getY();
//                        c = myOppentBoard.getCell(y, x);
//                        c.set(Square.SquareStatus.HAVE_BOMB);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        break;
//                    }
//                    try {
//                        Thread.sleep(1500);
//                    } catch (InterruptedException ex) {
//                        break;
//                    }
//                }
//            }
//        });
//        thread1.start();
//        thread2.start();
//
//        Exit.setOnAction(e
//                -> {
//            controller_$.ClearEveryThing();
//            thread1.interrupt();
//            thread2.interrupt();
//            new MainMenu();
//        }
//        );
//
//    }
//
//    public void setScene() {
//        Windows.setScene(scene);
//    }
//
//    private class Cheat {
//
//        public Cheat() {
//
//            Stage AlertBox = new Stage();
//            AlertBox.initModality(Modality.APPLICATION_MODAL);
//            AlertBox.setTitle("Winner");
//            AlertBox.setMinWidth(250);
//
//            Pane root = new Pane();
//            ImageView imageview = publicMethod.addBackground("Win.jpg");
//            imageview.setFitWidth(450);
//            imageview.setFitHeight(450);
//
//            Button Finish = new Button("Finish");
//
//            VBox v = new VBox(Finish);
//            v.setAlignment(Pos.CENTER);
//            v.setTranslateY(150);
//            v.setTranslateX(80);
//            v.setSpacing(50);
//            Finish.setTranslateY(100);
//            Finish.setStyle("-fx-font: 35 arial; -fx-base: #ee2211;");
//
//            Finish.setOnAction(e
//                    -> {
//                AlertBox.close();
//            });
//            root.getChildren().addAll(imageview, v, myOppentBoard);
//            Scene scene = new Scene(root, 450, 450);
//            AlertBox.setScene(scene);
//            AlertBox.showAndWait();
//
//        }
//    }
//}
