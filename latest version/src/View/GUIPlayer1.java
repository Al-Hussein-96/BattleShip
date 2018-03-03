//package View;
//
//import Model.AttackStatus;
//import Model.BattleshipGame;
//import Model.BattleshipMove;
//import Model.BattleshipPlayer;
//import Model.Bomb;
//import Model.ComputerPlayer;
//import Model.HumanPlayer;
//import Model.Square;
//import Model.model;
//import View.BoardAndCell.Cell;
//import static View.view.controller_$;
//import static View.view.model_$;
//import java.util.Random;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.animation.Timeline;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import latest.version.LatestVersion;
//import static latest.version.LatestVersion.HEIGHT;
//import static latest.version.LatestVersion.RunningGame;
//import static latest.version.LatestVersion.WIDTH;
//import static latest.version.LatestVersion.Windows;
//import static latest.version.LatestVersion.displaySetting;
//import static latest.version.LatestVersion.publicMethod;
//import static latest.version.LatestVersion.settingShipBoard;
//
//public class GUIPlayer1 {
//
//    private final Scene scene;
//    private final Pane root = new Pane();
//    private final BoardAndCell myBoard;
//    private final BoardAndCell myDraft;
//    Button Pass = new Button("Pass");
//    Thread CountThread, t = null;
//    myTimer countDown = new myTimer("1");
//    BattleshipGame TheGame = (BattleshipGame) model_$.getGame();
//    HumanPlayer human = TheGame.getHuman();
//    ComputerPlayer computer = TheGame.getComputer();
//    Text p1 = new Text("0");
//    Text p2 = new Text("0");
//    boolean actionAllow = false;
//    boolean ok = false;
//    int i;
//    Thread thread1, thread2;
//
//    public BoardAndCell getBoard() {
//        return myBoard;
//    }
//
//    private void HumanTurn(Cell c) {
//        if (!RunningGame) {
//            new Win(TheGame.Winner);
//            return;
//        }
//        Cell cell = c;
//        if (human.getStateSquareDraft(new BattleshipMove(cell.x, cell.y)) != Square.SquareStatus.UNKNOWN) {
//            return;
//        }
//        model_$.RunGame(cell.x, cell.y, 0);
//        if (!RunningGame) {
//            new Win(TheGame.Winner);
//            return;
//        }
//        int Po11 = ((BattleshipPlayer) human).getMyPoints().getCurrentPoints();
//        int Po22 = ((BattleshipPlayer) computer).getMyPoints().getCurrentPoints();
//        p1.setText("" + Po11);
//        p2.setText("" + Po22);
//        cell.Shoot(human.getStateSquareDraft(new BattleshipMove(cell.x, cell.y)));
//
//        if (((BattleshipPlayer) human).getAttackBomb()) {
//            /**
//             * *
//             */
//            ((BattleshipPlayer) human).setAttackBomb(false);
//            int dx[] = {1, -1, 1, -1, 0, 0, 1, -1};
//            int dy[] = {1, -1, -1, 1, 1, -1, 0, 0};
//
//            Cell last = cell;
//
//            for (int i = 0; i < 8; i++) {
//                int nx = last.y + dx[i];
//                int ny = last.x + dy[i];
//
//                if ((nx >= 0 && nx < (int) settingShipBoard.W.getValue()) && (ny >= 0 && ny < (int) settingShipBoard.H.getValue())) //         this.DraftGrid.setSquare(square);
//                {
//                    cell = myDraft.getCell(nx, ny);
//                    cell.Shoot(human.getStateSquareDraft(new BattleshipMove(nx, ny)));
//                }
//            }
//
//        }
//        Pass.setDisable(true);
//        view.Turn = view.StatusTurn.COMPUTER;
//
//        ComputerTurn();
//
//        StopCountDown();
//
//    }
//
//    private void ComputerTurn() {
//
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //     int Think = (new Random()).nextInt((int)displaySetting.limitTime.getValue() + 4);
//                int Think = (new Random()).nextInt(1);
//                int cnt = 0;
//                while (true) {
//                    try {
//                        Thread.sleep(1000);
//                        cnt++;
//                        //  if (cnt >= (int)displaySetting.Boomvolicity.getValue() || cnt > Think) 
//                        if (cnt >= 10 || cnt > Think) {
//                            break;
//                        }
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(GUIPlayer1.class.getName()).log(Level.SEVERE, null, ex);
//                        break;
//                    }
//                }
//                if (cnt < 10) {
//                    model_$.RunGame(-1, -1, 1);
//                    int Po11 = ((BattleshipPlayer) human).getMyPoints().getCurrentPoints();
//                    int Po22 = ((BattleshipPlayer) computer).getMyPoints().getCurrentPoints();
//                    p1.setText("" + Po11);
//                    p2.setText("" + Po22);
//
//                    BattleshipMove curmove = ((BattleshipPlayer) computer).getcurrentMove();
//
//                    Cell cell2 = myBoard.getCell(curmove.getY(), curmove.getX());
//                    cell2.Shoot(computer.getStateSquareDraft(new BattleshipMove(curmove.getX(), curmove.getY())));
//
//                    if (((BattleshipPlayer) computer).getAttackBomb()) {
//                        ((BattleshipPlayer) computer).setAttackBomb(false);
//                        //thread1.interrupt();
//                        int dx[] = {1, -1, 1, -1, 0, 0, 1, -1};
//                        int dy[] = {1, -1, -1, 1, 1, -1, 0, 0};
//                        for (int i = 0; i < 8; i++) {
//                            int nx = curmove.getX() + dx[i];
//                            int ny = curmove.getY() + dy[i];
//
//                            if ((nx >= 0 && nx < (int) settingShipBoard.W.getValue()) && (ny >= 0 && ny < (int) settingShipBoard.H.getValue())) //         this.DraftGrid.setSquare(square);
//                            {
//                                cell2 = myBoard.getCell(nx, ny);
//                                cell2.Shoot(computer.getStateSquareDraft(new BattleshipMove(nx, ny)));
//                            }
//                        }
//
//                    }
//
//                }
//                view.Turn = view.StatusTurn.HUMAN;
//                Pass.setDisable(false);
//                StopCountDown();
//            }
//        });
//        t.start();
//
//        if (!RunningGame) {
//            new Win(TheGame.Winner);
//            return;
//        }
//
//    }
//
//    public void startCountDown() {
//        CountThread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                countDown.setTimer("1");
//                i = 1;
//                try {
//                    while (true) {
//                        countDown.setTimer(String.valueOf(i));
//                        i++;
//                        Thread.sleep(1000);
//                    }
//                } catch (Exception e) {
//
//                }
//                countDown.setTimer("");
//            }
//
//        });
//        CountThread.start();
//
//        if (!RunningGame) {
//            new Win(TheGame.Winner);
//            return;
//        }
//
//    }
//
//    public void StopCountDown() {
//        i = 1;
//        countDown.setTimer("1");
//    }
//
//    public GUIPlayer1() {
//        myBoard = new BoardAndCell((int) settingShipBoard.W.getValue(), (int) settingShipBoard.H.getValue());
//        myDraft = new BoardAndCell((int) settingShipBoard.W.getValue(), (int) settingShipBoard.H.getValue(), (event)
//                -> {
//
//            if (view.Turn != view.StatusTurn.HUMAN) {
//                return;
//            }
//
//            // System.out.println("Number: " + java.lang.Thread.activeCount());
//            Cell cell = (Cell) event.getSource();
//            HumanTurn(cell);
//
//        },
//                event
//                -> {
//            if (view.Turn != view.StatusTurn.HUMAN) {
//                return;
//            }
//            Cell cell = (Cell) event.getSource();
//            cell.ChangeColor1();
//
//        },
//                event
//                -> {
//            if (view.Turn != view.StatusTurn.HUMAN) {
//                return;
//            }
//            Cell cell = (Cell) event.getSource();
//            cell.ChangeColor2();
//
//        }
//        );
//        for (int x = 0; x < (int) settingShipBoard.W.getValue(); x++) {
//            for (int y = 0; y < (int) settingShipBoard.H.getValue(); y++) {
//                Cell cell = myBoard.getCell(y, x);
//                Square.SquareStatus state = controller_$.getStateMy(x, y);
//                cell.set(state);
//            }
//        }
//        ImageView imageview = publicMethod.addBackground("war5.jpg");
//        Titles NamePlayer = new Titles("Running Game", Color.WHITE);
//
//        NamePlayer.setTranslateX(440 - NamePlayer.getWidth());
//        NamePlayer.setTranslateY(60);
//        Button Exit = new Button("Exit");
//
//        Exit.setTranslateX(50);
//        Exit.setTranslateY(650);
//        Exit.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");
//
//        Pass.setTranslateX(200);
//        Pass.setTranslateY(650);
//        Pass.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");
//
//        Text t1 = new Text("Human Player");
//        Text t2 = new Text("Computer Player");
//
//        t1.setFont(new Font(23));
//        t2.setFont(new Font(23));
//        t1.setTranslateX(150);
//        t2.setTranslateX(150);
//        t1.setFill(Color.RED);
//
//        t2.setFill(Color.RED);
//
//        Text k1 = new Text("Points : ");
//        Text k2 = new Text("Points : ");
//
//        k1.setFont(new Font(18));
//        k2.setFont(new Font(18));
//        p1.setFont(new Font(18));
//        p2.setFont(new Font(18));
//
//        HBox h = new HBox();
//        VBox v1 = new VBox();
//        VBox v2 = new VBox();
//        HBox h1 = new HBox(k1, p1);
//        HBox h2 = new HBox(k2, p2);
//
//        v1.getChildren().addAll(t1, h1, myBoard);
//        v2.getChildren().addAll(t2, h2, myDraft);
//        h.getChildren().addAll(v1, v2);
//
//        h.setSpacing(40);
//        h.setTranslateX(200);
//        h.setTranslateY(130);
//        countDown.setFont1(40);
//        //h.getChildren().add(countDown);
//        countDown.setTranslateX(650);
//        countDown.setTranslateY(140);
//        root.getChildren().addAll(imageview, NamePlayer, h, Exit, Pass, countDown);
//        scene = new Scene(root, WIDTH, HEIGHT);
//
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
//                        Cell c = myBoard.getCell(y, x);
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
////            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        computer.MyGrid.moveBomb();
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
//        Exit.setOnAction(e
//                -> {
//            controller_$.ClearEveryThing();
//            thread1.interrupt();
//            thread2.interrupt();
//            new MainMenu();
//        }
//        );
//
//        Pass.setOnAction(e -> {
//            view.Turn = view.StatusTurn.COMPUTER;
//            Pass.setDisable(true);
//            ComputerTurn();
//            StopCountDown();
//        });
//        startCountDown();
//
//    }
//
//    public void setScene() {
//        Windows.setScene(scene);
//
//    }
//
//}
