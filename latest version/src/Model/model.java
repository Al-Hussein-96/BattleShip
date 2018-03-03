package Model;
/// to bind all Classes Model

import View.view;
import static View.view.model_$;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class model {

    static public Game BaseGame;
    static public BattleshipPlayer Winner;

    public model() {
        BaseGame = new BattleshipGame();
        BaseGame.Information = new InfoGame();
    }

    public void SetBaseGane(Game BaseGame) {
        this.BaseGame = BaseGame;
    }

    public void AddHumanPlayer() {
        BattleshipPlayer one = new HumanPlayer();

        try {
            BaseGame.Subscribe(one);
        } catch (PlayerNotCompatableException ex) {
            System.out.println("the player not Battle Ship Player");
            Logger.getLogger(model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AddRandomComputerPlayer() {
        ComputerPlayer two = new RandomComputerPlayer();
        try {
            BaseGame.Subscribe(two);
        } catch (PlayerNotCompatableException ex) {
            System.out.println("the player not Battle Ship Player");
            Logger.getLogger(model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AddSmartComputerPlayer() {
        ComputerPlayer two = new SmartComputerPlayer();
        try {
            BaseGame.Subscribe(two);
        } catch (PlayerNotCompatableException ex) {
            System.out.println("the player not Battle Ship Player");
            Logger.getLogger(model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Game getBaseGame() {
        return BaseGame;
    }

    public void Start() {
        ((BattleshipGame) BaseGame).Start();
    }

    public void RunGame(int x, int y, int id) {
        ((BattleshipGame) BaseGame).RunGame(new BattleshipMove(x, y), id);
    }

//    public void draw() {
//        BattleshipPlayer x1 = (BattleshipPlayer) BaseGame.players.get(0);
//        BattleshipPlayer x2 = (BattleshipPlayer) BaseGame.players.get(1);
//        System.out.println(x1);
//        x1.draw();
//        System.out.println("||||||||||||||||||||||||||||||");
//        System.out.println(x2);
//        x2.draw();
//
//    }

    public void SaveGame(String fileName) {
        SaveData data = new SaveData(BaseGame);
        try {
            ResourceManager.save(data, fileName);
        } catch (Exception e1) {
            System.out.println("Can't Save : " + e1.getMessage());
        }

    }

    public void LoadGame(String fileName) {
        try {
            System.out.println("size: " + BaseGame.players.size());

            SaveData data = (SaveData) ResourceManager.load("save/" + fileName);
            BaseGame = data.getGame();
            BaseGame.RunningGame = true;
        } catch (Exception e1) {
            System.out.println("Can't Load : " + e1.getMessage());
        }
    }

    /**
     * *************************** Information about Game
     * ********************************
     */
    public void setNameHuman(String string) {
        this.BaseGame.Information.setNameHuman(string);
    }

    public void setTypeComuter(String smart) {
        this.BaseGame.Information.setTypeComputer(smart);
    }

    public void setPlayerGrid() {
        BaseGame.Information.setHumanGrid((Grid) ((BattleshipGame) BaseGame).getHuman().getMyGrid().Copy());
        BaseGame.Information.setComputerGrid((Grid) ((BattleshipGame) BaseGame).getComputer().getMyGrid().Copy());

    }

    public void setWinner(BattleshipPlayer win) {
        if (win instanceof HumanPlayer) {
            this.BaseGame.Information.setWinner(this.getBaseGame().Information.getNameHuman());
        } else {
            this.BaseGame.Information.setWinner("Computer");
        }
    }

    public void setStartTime(Date date) {
        this.BaseGame.Information.setStartGame(date);
    }

    public void setIdgame() {
        int ID = 0;
        File folder = new File("src/previous/");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            ID = Math.max(ID, Integer.valueOf(listOfFiles[i].getName()));
        }
        this.BaseGame.Information.setIdGame(ID + 1);
    }

    public void SaveInfoGame(String fileName) {
        SaveInfo data = new SaveInfo((BaseGame.Information));
        // SaveInfo data = new SaveInfo(new InfoGame(1, "Mohammad", "SMART", "4234", "4234234", "Mohammad", 2));
        try {
            ResourceManager.save(data, fileName + data.getInformation().getIdGame());
        } catch (Exception e1) {
            System.out.println("Can't Save Info: " + e1.getMessage());
        }

    }

    public InfoGame LoadInfoGame(String fileName) {
        try {
            SaveInfo data = (SaveInfo) ResourceManager.load(fileName);
            return data.getInformation();
            // BaseGame.RunningGame = true;
        } catch (Exception e1) {
            System.out.println("Can't Load Info: " + e1.getMessage());
            return null;
        }
    }

    public void setPlayerMove() {
        BaseGame.Information.setHumanMove(((BattleshipGame) BaseGame).getHuman().getListMove());
        BaseGame.Information.setComputerMove(((BattleshipGame) BaseGame).getComputer().getListMove());
    }

    public void setMarksPlayers() {
        BaseGame.Information.setMarks(((BattleshipGame) BaseGame).getHuman().getMyPoints(),((BattleshipGame) BaseGame).getComputer().getMyPoints());
    }

}
