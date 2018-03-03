package Model;

import java.io.Serializable;

public class BattleshipMove implements Serializable
{

    private int x, y;

    public BattleshipMove()
    {

    }

    public BattleshipMove(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public BattleshipMove getMove()
    {
        return new BattleshipMove(x, y);
    }
    public BattleShipMoveResult getBattleShipMoveResult()
    {
        return new BattleShipMoveResult();
    }

    void SetMove(BattleshipMove battleshipMove) {
        this.x = battleshipMove.x;
        this.y = battleshipMove.y;
    }

    public class BattleShipMoveResult implements Serializable
    {

        private AttackStatus Result;

        public BattleShipMoveResult()
        {
        }
        
        public BattleShipMoveResult(AttackStatus Result)
        {
            this.Result = Result;
        }
        
        public void SetResult(AttackStatus Result)
        {
            this.Result = Result;
        }

        public AttackStatus getResult()
        {
            return Result;
        }

    }

}
