package Model;

import static Model.model.BaseGame;
import View.GUIPlayer4;
import static View.view.controller_$;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BattleshipGame extends Game implements Serializable {

    BattleshipPlayer currentPlayer;
    private int CurrentTimer;
    private static int Width = 10;
    private static int Height = 10;
    public BattleshipPlayer Winner;

    public int getCurrentTimer() {
        return CurrentTimer;
    }

    public void setCurrentTimer(int CurrentTimer) {
        this.CurrentTimer = CurrentTimer;
    }

    @Override
    public void Start() {
        if (players.size() == 2) {
            Player one = (players.get(0));
            Player two = (players.get(1));

            try {
                one.SubscribeTo(this);
            } catch (PlayerNotCompatableException ex) {
                Logger.getLogger(BattleshipGame.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                two.SubscribeTo(this);
            } catch (PlayerNotCompatableException ex) {
                Logger.getLogger(BattleshipGame.class.getName()).log(Level.SEVERE, null, ex);
            }

            ((BattleshipPlayer) one).bulid_Grid();
            ((BattleshipPlayer) two).bulid_Grid();

            RunningGame = true;
        }
    }

    public void RunGame(BattleshipMove newMove, int id) {
        if (players.isEmpty()) {
            return;
        }
        currentPlayer = (BattleshipPlayer) players.get(id);
        BattleshipPlayer NextPlayer = (BattleshipPlayer) players.get(1 - id);
        BattleshipMove NewBattleshipMove;

        if (currentPlayer instanceof HumanPlayer) {
            NewBattleshipMove = newMove;
        } else {
            NewBattleshipMove = currentPlayer.GetNextMove();
        }

        currentPlayer.set_currentMove(NewBattleshipMove);

        BattleshipMove.BattleShipMoveResult NewAttackResult = NextPlayer.AcceptAttack(NewBattleshipMove);

        if (NewAttackResult.getResult() == AttackStatus.DESTROYED_BOMB) {
            currentPlayer.getMyPoints().IncreasePoints(1);
        } else if (NewAttackResult.getResult() != AttackStatus.FAILURE) {
            System.out.println("CurrentPlayer " + currentPlayer);
            currentPlayer.getMyPoints().IncreasePoints(0);
        }

        if (NewAttackResult.getResult() == AttackStatus.DESTROYED_ALL_SHIPS) {
            (this.Information).setEndGame(new Date());
            Result(currentPlayer);
            RunningGame = false;
            Stop();
        }
        currentPlayer.AcceptAttackResult(NewAttackResult, NewBattleshipMove);
        currentPlayer.SetLastResult(NewAttackResult.getResult());

        if (NewAttackResult.getResult() == AttackStatus.DESTROYED_BOMB) {

            int dx[] = {1, -1, 1, -1, 0, 0, 1, -1};
            int dy[] = {1, -1, -1, 1, 1, -1, 0, 0};
            for (int i = 0; i < 8; i++) {
                int nx = NewBattleshipMove.getX() + dx[i];
                int ny = NewBattleshipMove.getY() + dy[i];
                if ((nx >= 0 && nx < Width) && (ny >= 0 && ny < Height)) //         this.DraftGrid.setSquare(square);
                {
                    NewAttackResult = NextPlayer.AcceptAttack(new BattleshipMove(nx, ny));

                    if (NewAttackResult.getResult() == AttackStatus.DESTROYED_ALL_SHIPS) {
                        (this.Information).setEndGame(new Date());
                        Result(currentPlayer);
                        RunningGame = false;
                        Stop();
                    }
                    currentPlayer.AcceptAttackResult(NewAttackResult, new BattleshipMove(nx, ny));
                }

            }

        }
    }

    public void SetcurrentPlayer(BattleshipPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public void Stop() {
        if (players.isEmpty()) {
            return;
        }
        controller_$.setPlayerMove();
        controller_$.MarkPlayers();
        RunningGame = false;
        BattleshipPlayer one = ((BattleshipPlayer) players.get(0));
        BattleshipPlayer two = ((BattleshipPlayer) players.get(1));
        one.LeaveGame();
        two.LeaveGame();
        model.BaseGame.Leave(one);
        model.BaseGame.Leave(two);
    }

    public void Result(BattleshipPlayer win) {
        Winner = win;
        File folder = new File("src/scoreboard/");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            try {
                Scoring x = (Scoring) ResourceManager.load("scoreboard/" + listOfFiles[i].getName());
                if (x.getName() == null ? BaseGame.Information.getNameHuman() == null : x.getName().equals(BaseGame.Information.getNameHuman())) {
                    listOfFiles[i].delete();
                    int w = x.getWin();
                    int l = x.getLose();
                    if (win instanceof HumanPlayer) {
                        x.setWin(w + 1);
                    } else {
                        x.setLose(l + 1);
                    }
                    x.setMarks();
                    ResourceManager.save(x, "scoreboard/" + x.getName());
                    return;
                }

            } catch (Exception ex) {
                Logger.getLogger(BattleshipGame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        int w = 0;
        int l = 0;
        if (win instanceof HumanPlayer) {
            w++;
        } else {
            l++;
        }
        Scoring x = new Scoring(BaseGame.Information.getNameHuman(), w, l);
        try {
            ResourceManager.save(x, "scoreboard/" + x.getName());
        } catch (Exception ex) {
            Logger.getLogger(BattleshipGame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getWidth() {
        return this.Width;
    }

    public int getHeight() {
        return this.Height;
    }

    public void setWidth(int Width) {
        this.Width = Width;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public BattleshipPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public HumanPlayer getHuman() {
        if ((BattleshipPlayer) players.get(0) instanceof HumanPlayer) {
            return (HumanPlayer) players.get(0);
        } else {
            return (HumanPlayer) players.get(1);
        }
    }

    public ComputerPlayer getComputer() {
        if(players.isEmpty())
            return null;
        if ((BattleshipPlayer) players.get(0) instanceof ComputerPlayer) {
            return (ComputerPlayer) players.get(0);
        } else {
            return (ComputerPlayer) players.get(1);
        }
    }

    public void CleareveryThing() {
        System.out.println("All Stop Clear Every Thing");
        if (!players.isEmpty()) {
            BattleshipPlayer x = (BattleshipPlayer) players.get(0);
            x.StopAllBomb();
        }
        players.clear();

    }

    @Override
    public void Restore() {
        BattleshipPlayer human = (BattleshipPlayer) players.get(0);
        human.DisplayGUI(new GUIPlayer4());
        human.RunThread();
    }

    public enum StatusTurn {
        HUMAN, COMPUTER, STOPED;
    }

}
