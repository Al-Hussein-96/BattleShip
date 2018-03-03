package Model;

import java.io.Serializable;

public abstract class Player implements Serializable {

    protected IPlayable currentGame;

    public void LeaveGame() {
        currentGame = null;
    }

    public void SubscribeTo(IPlayable game) throws PlayerNotCompatableException {
        if (!((game instanceof BattleshipGame) && (this instanceof BattleshipPlayer))) {
            throw new PlayerNotCompatableException();
        }
        currentGame = game;
    }
}
