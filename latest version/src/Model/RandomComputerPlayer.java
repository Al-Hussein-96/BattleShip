package Model;

import java.io.Serializable;
import java.util.Random;

public class RandomComputerPlayer extends ComputerPlayer
{

    public RandomComputerPlayer()
    {
        super();
        this.currentStrategy = new RandomComputerStrategy();
    }

    public class RandomComputerStrategy extends abstractComputerStrategy implements Serializable
    {

        @Override
        public BattleshipMove GetNextMove()
        {
            Random rand = new Random();
            int a = rand.nextInt(((BattleshipGame) model.BaseGame).getWidth());
            int b = rand.nextInt(((BattleshipGame) model.BaseGame).getHeight());
            return (new BattleshipMove(a, b));
        }

    }

}
