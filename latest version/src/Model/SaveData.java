package Model;

import java.io.Serializable;

public class SaveData implements Serializable {

    Game TheGame;

    public SaveData(Game TheGame) {
        this.TheGame = TheGame;
    }
    public Game getGame() {
        return TheGame;
    }

}
