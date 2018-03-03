package Model;

import View.GUI;

public class HumanPlayer extends BattleshipPlayer
{
    private String MyName;
    

    HumanPlayer()
    {
        super();
    }
    @Override
    public BattleshipMove GetNextMove()
    {
        BattleshipMove move = new BattleshipMove(0,0);
        return move;   
    }
    

    

}
