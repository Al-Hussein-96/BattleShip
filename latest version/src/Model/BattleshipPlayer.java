package Model;

import Model.Square.SquareStatus;
import View.GUI;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BattleshipPlayer extends Player implements Serializable {

    public transient GUI GUI;
    private Grid MyGrid;
    private Grid DraftGrid;
    protected BattleshipMove currentMove;
    private CalculatePoints MyPoints;
    private AttackStatus LastResult;
    private List<InfoMove> ListMove = new ArrayList<>();

    public void DisplayGUI(GUI GUI) {
        this.GUI = GUI;
        GUI.setScene();

    }

    public void DisplayGUI2() {
        GUI.setScene();
    }

    public BattleshipPlayer() {
        MyPoints = new CalculatePoints();
    }

    public void bulid_Grid() {
        // Exception
        int Width = ((BattleshipGame) currentGame).getWidth();
        int Height = ((BattleshipGame) currentGame).getHeight();
        MyGrid = new Grid(Width, Height, Grid.GridStatus.MINE);
        DraftGrid = new Grid(Width, Height, Grid.GridStatus.DRAFT);

    }

    public SquareStatus getStateSquareDraft(BattleshipMove AttackSquare) {
        return this.DraftGrid.getSquare(AttackSquare.getX(), AttackSquare.getY()).getState();
    }

    public SquareStatus getStateSquareMy(BattleshipMove goal) {
        return this.MyGrid.getSquare(goal.getX(), goal.getY()).getState();
    }

    public void set_currentMove(BattleshipMove currentMove) {
        this.currentMove = currentMove;
    }

    public BattleshipMove getcurrentMove() {
        return currentMove;
    }

//    public void draw() {
//        System.out.println("MyGrid");
//        MyGrid.draw();
//        System.out.println("DraftGrid");
//        DraftGrid.draw();
//    }
    public void BattleshipPlayerAddShip(Ship NewShip) {
        MyGrid.addShip(NewShip);
    }

    public boolean CheckAddShip(Ship NewShip) {
        return MyGrid.CheckGrid(NewShip);
    }

    public void BattleshipPlayerAddBomb(int Speed) {
        MyGrid.addBomb(Speed, this);
    }

    public void MoveAllBomb() {
        MyGrid.MoveAllBomb();
    }

    void StopAllBomb() {
        MyGrid.StopAllBomb();
    }

    
    public List<InfoMove> getListMove() {
        return ListMove;
    }

    public abstract BattleshipMove GetNextMove();

    public BattleshipMove.BattleShipMoveResult AcceptAttack(BattleshipMove NewBattleshipMove) {
        AttackStatus temp = this.MyGrid.AttackSequare(NewBattleshipMove);
        BattleshipMove.BattleShipMoveResult res = NewBattleshipMove.getBattleShipMoveResult();
        res.SetResult(temp);
        return res;

    }

    public void AcceptAttackResult(BattleshipMove.BattleShipMoveResult NewAttackResult, BattleshipMove currentMove) {


        ListMove.add(new InfoMove(currentMove.getMove(), NewAttackResult.getResult(), new Date(),getMyPoints().getLastPoints()));
        getMyPoints().setLastPoints(0);

        switch (NewAttackResult.getResult()) {
            case FAILURE:
                this.DraftGrid.setSquare(new Square(currentMove.getX(), currentMove.getY(), SquareStatus.WATER));
                break;
            case DESTROYED_BOMB:
                this.DraftGrid.setSquare(new Square(currentMove.getX(), currentMove.getY(), SquareStatus.DESTROYED_BOMB));
                break;
            default:
                this.DraftGrid.setSquare(new Square(currentMove.getX(), currentMove.getY(), SquareStatus.DESTROYED_PART_OF_SHIP));
                break;
        }
    }

    public AttackStatus getLastResult() {
        return LastResult;
    }

    public void SetLastResult(AttackStatus LastResult) {
        this.LastResult = LastResult;
    }

    public Grid getMyGrid() {
        return this.MyGrid;
    }

    public CalculatePoints getMyPoints() {
        return MyPoints;
    }

    void RunThread() {
        MyGrid.RunThread();
    }

    public class CalculatePoints implements Serializable {

        private int CurrentPoints;
        private int NumberOfattack;
        private int LastPoints;

        public CalculatePoints() {
            this.CurrentPoints = 0;
            this.NumberOfattack = 0;
            this.LastPoints = 0;
        }

        public int getCurrentPoints() {
            return CurrentPoints;
        }

        public int getLastPoints() {
            return LastPoints;
        }

        public void setLastPoints(int LastPoints) {
            this.LastPoints = LastPoints;
        }
        

        public void IncreasePoints(int k) {
            IncreaseNumberOfattck();
            int Width = ((BattleshipGame) currentGame).getWidth();
            int Height = ((BattleshipGame) currentGame).getHeight();
            int m = Width * Height;
            CurrentPoints += (m - NumberOfattack + 1) * (k == 0 ? 1 : 3);
            this.LastPoints = (m - NumberOfattack + 1) * (k == 0 ? 1 : 3);

        }

        private void IncreaseNumberOfattck() {
            NumberOfattack++;
        }
    }

}
