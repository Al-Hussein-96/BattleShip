package Model;

import Model.BattleshipGame.StatusTurn;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Game implements IPlayable, Serializable {

    protected List< Player> players = new ArrayList<>();
    protected InfoGame Information;

    public InfoGame getInformation() {
        return Information;
    }
    public StatusTurn Turn;
    public boolean RunningGame;
    public static boolean Pause = false;

    @Override
    public void Subscribe(Player player) throws PlayerNotCompatableException {
        if (!(player instanceof BattleshipPlayer)) {
            throw new PlayerNotCompatableException();
        }
        this.players.add(player);
    }

    @Override
    public void Leave(Player player) {
        this.players.remove(player);
    }

    public abstract void Start();

    public abstract void Stop();

    public abstract void Restore();

}
