package View;

import Model.AttackStatus;
import Model.BattleshipGame;
import Model.BattleshipGame.StatusTurn;
import Model.BattleshipMove;
import Model.BattleshipPlayer;
import Model.ComputerPlayer;
import Model.HumanPlayer;
import Model.Square;
import View.BoardAndCell.Cell;
import static View.view.controller_$;
import static View.view.model_$;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class GUIPlayer4 extends GUI {

    private final Scene scene;
    private final Pane root = new Pane();
    private boolean ShutDown = false;
    private BattleshipGame TheGame = (BattleshipGame) model_$.getBaseGame();
    private HumanPlayer human = TheGame.getHuman();
    private ComputerPlayer computer = TheGame.getComputer();
    private Button Pass, Save, Exit;
    private myTimer countDown;

    Text p1 = new Text("0");
    Text p2 = new Text("0");

    public GUIPlayer4() {
        countDown = new myTimer("1");

        myBoard = new BoardAndCell((int) settingShipBoard.W.getValue(), (int) settingShipBoard.H.getValue());
        myDraft = new BoardAndCell((int) settingShipBoard.W.getValue(), (int) settingShipBoard.H.getValue(), (event)
                -> {
            if (TheGame.Turn != StatusTurn.HUMAN || TheGame.RunningGame == false) {
                return;
            }
            Cell cell = (Cell) event.getSource();

            HumanTurn(cell);

        },
                event
                -> {
            if (TheGame.Turn != StatusTurn.HUMAN || TheGame.RunningGame == false) {
                return;
            }
            Cell cell = (Cell) event.getSource();
            cell.ChangeColor3(computer);

        },
                event
                -> {
            if (TheGame.Turn != StatusTurn.HUMAN) {
                return;
            }
            Cell cell = (Cell) event.getSource();
            cell.ChangeColor2();

        }, human
        );
        for (int x = 0; x < (int) settingShipBoard.W.getValue(); x++) {
            for (int y = 0; y < (int) settingShipBoard.H.getValue(); y++) {
                Cell cell = myBoard.getCell(y, x);
                Square.SquareStatus state = human.getStateSquareMy(new BattleshipMove(x, y));
                cell.set(state);

//                cell = myDraft.getCell(y, x);
//                state = human.getStateSquareDraft(new BattleshipMove(x, y));
//                cell.set(state);
            }
        }
        ImageView imageview = publicMethod.addBackground("war5.jpg");

        Titles NamePlayer = new Titles("Running Game", Color.WHITE);
        NamePlayer.setTranslateX(440 - NamePlayer.getWidth());
        NamePlayer.setTranslateY(60);

        Exit = new Button("Exit");
        Exit.setTranslateX(50);
        Exit.setTranslateY(650);
        Exit.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");

        Pass = new Button("Pass");
        Pass.setTranslateX(200);
        Pass.setTranslateY(650);
        Pass.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");

        Save = new Button("Save");
        Save.setTranslateX(50);
        Save.setTranslateY(580);
        Save.setStyle("-fx-font: 25 arial; -fx-base: #ee2211;");

        Button QuickSave = new Button("Quick Save");
        QuickSave.setTranslateX(40);
        QuickSave.setTranslateY(500);
        QuickSave.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");

        Text t1 = new Text("Human Player");
        Text t2 = new Text("Computer Player");
        t1.setFont(new Font(23));
        t2.setFont(new Font(23));
        t1.setTranslateX(150);
        t2.setTranslateX(150);
        t1.setFill(Color.RED);
        t2.setFill(Color.RED);

        Text k1 = new Text("Points : ");
        Text k2 = new Text("Points : ");
        k1.setFont(new Font(18));
        k2.setFont(new Font(18));

        p1.setFont(new Font(18));
        p2.setFont(new Font(18));

        HBox h = new HBox();
        VBox v1 = new VBox();
        VBox v2 = new VBox();
        HBox h1 = new HBox(k1, p1);
        HBox h2 = new HBox(k2, p2);

        v1.getChildren().addAll(t1, h1, myBoard);
        v2.getChildren().addAll(t2, h2, myDraft);

        h.getChildren().addAll(v1, v2);
        h.setSpacing(40);
        h.setTranslateX(200);
        h.setTranslateY(130);

        countDown.setFont1(40);
        countDown.setTranslateX(650);
        countDown.setTranslateY(140);

        root.getChildren().addAll(imageview, NamePlayer, h, QuickSave, Save, Exit, Pass, countDown);
        scene = new Scene(root, WIDTH, HEIGHT);
        Windows.setScene(scene);
        countDown.setTimer(String.valueOf(TheGame.getCurrentTimer()));
        countDown.StartThreadTimer();

        if (TheGame.Turn == StatusTurn.COMPUTER) {
            ComputerTurn();
        }

        /**
         * *************************************** Event
         * ******************************************
         */
        Exit.setOnAction(e
                -> {
            controller_$.ClearEveryThing();
            countDown.StopThreadTimer();
            new MainMenu();
        }
        );

        Pass.setOnAction(e -> {
            TheGame.Turn = StatusTurn.COMPUTER;
            Pass.setDisable(true);
            ComputerTurn();
            countDown.ResetTimer();
        });
        Save.setOnAction(e -> {
            new SaveGame();

        });
        QuickSave.setOnAction(e -> {
            controller_$.SaveGame("save/" + "QuickSaved");
        });
    }

    private void HumanTurn(Cell c) {

        if (!TheGame.RunningGame) {
            new Win(TheGame.Winner);
            return;
        }
        Cell cell = c;

        if (human.getStateSquareDraft(new BattleshipMove(cell.x, cell.y)) != Square.SquareStatus.UNKNOWN) {
            return;
        }
        model_$.RunGame(cell.x, cell.y, 0);
        if (!TheGame.RunningGame) {
            new Win(TheGame.Winner);
            return;
        }
        int Po11 = ((BattleshipPlayer) human).getMyPoints().getCurrentPoints();
        int Po22 = ((BattleshipPlayer) computer).getMyPoints().getCurrentPoints();
        p1.setText("" + Po11);
        p2.setText("" + Po22);
        cell.Shoot(human.getStateSquareDraft(new BattleshipMove(cell.x, cell.y)));

        if (((BattleshipPlayer) human).getLastResult() == AttackStatus.DESTROYED_BOMB) {

            int dx[] = {1, -1, 1, -1, 0, 0, 1, -1};
            int dy[] = {1, -1, -1, 1, 1, -1, 0, 0};

            Cell last = cell;

            for (int i = 0; i < 8; i++) {
                int nx = last.x + dx[i];
                int ny = last.y + dy[i];

                if ((nx >= 0 && nx < (int) settingShipBoard.W.getValue()) && (ny >= 0 && ny < (int) settingShipBoard.H.getValue())) //         this.DraftGrid.setSquare(square);
                {
                    cell = myDraft.getCell(ny, nx);
                    cell.Shoot(human.getStateSquareDraft(new BattleshipMove(nx, ny)));
                }
            }

        }
        Pass.setDisable(true);
        TheGame.Turn = StatusTurn.COMPUTER;

        ComputerTurn();

        countDown.ResetTimer();
    }

    private void ComputerTurn() {

        if (TheGame.RunningGame == false) {
            return;
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //     int Think = (new Random()).nextInt((int)displaySetting.limitTime.getValue() + 4);
                int Think = (new Random()).nextInt(12);
                int cnt = 0;
                while (!ShutDown) {
                    try {
                        Thread.sleep(1000);
                        cnt++;
                        //  if (cnt >= (int)displaySetting.Boomvolicity.getValue() || cnt > Think) 
                        if (cnt >= 10 || cnt > Think) {
                            break;
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUIPlayer3.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                    }
                }
                if (cnt < 10) {

                    model_$.RunGame(-1, -1, 1);

                    int Po11 = ((BattleshipPlayer) human).getMyPoints().getCurrentPoints();
                    int Po22 = ((BattleshipPlayer) computer).getMyPoints().getCurrentPoints();
                    p1.setText("" + Po11);
                    p2.setText("" + Po22);

                    BattleshipMove curmove = ((BattleshipPlayer) computer).getcurrentMove();

                    Cell cell2 = myBoard.getCell(curmove.getY(), curmove.getX());
                    cell2.Shoot(computer.getStateSquareDraft(new BattleshipMove(curmove.getX(), curmove.getY())));

                    if (((BattleshipPlayer) computer).getLastResult() == AttackStatus.DESTROYED_BOMB) {
                        int dx[] = {1, -1, 1, -1, 0, 0, 1, -1};
                        int dy[] = {1, -1, -1, 1, 1, -1, 0, 0};
                        for (int i = 0; i < 8; i++) {

                            int nx = curmove.getX() + dx[i];
                            int ny = curmove.getY() + dy[i];

                            if ((nx >= 0 && nx < (int) settingShipBoard.W.getValue()) && (ny >= 0 && ny < (int) settingShipBoard.H.getValue())) //         this.DraftGrid.setSquare(square);
                            {
                                cell2 = myBoard.getCell(ny, nx);
                                cell2.Shoot(computer.getStateSquareDraft(new BattleshipMove(nx, ny)));
                            }
                        }

                    }

                }
                TheGame.Turn = StatusTurn.HUMAN;
                //   ShutDown = true;
                Pass.setDisable(false);
                countDown.ResetTimer();
            }
        });
        t.start();

        if (!TheGame.RunningGame) {
            new Win(TheGame.Winner);
            return;
        }

    }

    @Override
    public void setScene() {
        Windows.setScene(scene);
    }

}
