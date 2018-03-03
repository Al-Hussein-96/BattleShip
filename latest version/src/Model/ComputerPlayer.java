package Model;

import Model.SmartComputerPlayer.SmartComputerStrategy;
import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComputerPlayer extends BattleshipPlayer implements Serializable {

    protected abstractComputerStrategy currentStrategy;

    public ComputerPlayer() {
        super();
    }

    public void SetUpShip(int w, int h) {
        Random rand = new Random();
        int x = 1;
        int y = 1;
        boolean flag = false;

        do {
            x = rand.nextInt(((BattleshipGame) model.BaseGame).getWidth());
            y = rand.nextInt(((BattleshipGame) model.BaseGame).getHeight());

        } while (!CheckAddShip(new Ship(x, y, w, h, Ship.ShipStatus.GOOD)));

        BattleshipPlayerAddShip(new Ship(x, y, w, h, Ship.ShipStatus.GOOD));

    }

    @Override
    public BattleshipMove GetNextMove() {
        return this.currentStrategy.GetNextMove();

    }

}
