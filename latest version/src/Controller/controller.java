package Controller;
/// to bind (Classes Controller) with (Model)

import Model.BattleshipGame;
import Model.BattleshipMove;
import Model.BattleshipPlayer;
import Model.HumanPlayer;
import Model.ComputerPlayer;
import Model.InfoGame;
import Model.Ship;
import Model.Square.SquareStatus;
import Model.model;
import java.util.Date;

public class controller
{

    private final model model_$;
    BattleshipMove MoveMouse;

    public controller(model model_$)
    {
        this.model_$ = model_$;
    }

    public void addShipHuman(HumanPlayer human, Ship newShip)
    {
        human.BattleshipPlayerAddShip(newShip);
    }

    public void editDimensionsWidth(int newWidth)
    {
        ((BattleshipGame) model_$.getBaseGame()).setWidth(newWidth);
    }

    public void editDimensionsHeight(int newHeight)
    {
        ((BattleshipGame) model_$.getBaseGame()).setHeight(newHeight);
    }

    public void StartGame()
    {
        model_$.Start();
    }

    public void resetHuman(HumanPlayer human)
    {
        human.bulid_Grid();
    }

    public void resetComputer(ComputerPlayer computer)
    {
        computer.bulid_Grid();
    }

    public void addShipComputer(ComputerPlayer computer, int w, int h)
    {
        computer.SetUpShip(w, h);
    }

    public void AddHuman()
    {
        model_$.AddHumanPlayer();
    }

    public void AddRnadom()
    {
        model_$.AddRandomComputerPlayer();
    }

    public void AddSmart()
    {
        model_$.AddSmartComputerPlayer();
    }

    public SquareStatus getStateMy(int x, int y)
    {
        BattleshipGame TheGame = (BattleshipGame) model_$.getBaseGame();
        HumanPlayer human = TheGame.getHuman();
        return human.getStateSquareMy(new BattleshipMove(x, y));
    }

    public void ClearEveryThing()
    {
        ((BattleshipGame) model_$.getBaseGame()).CleareveryThing();
    }

    public SquareStatus getStateOppent(int x, int y)
    {
        BattleshipGame TheGame = (BattleshipGame) model_$.getBaseGame();
        ComputerPlayer computer = TheGame.getComputer();
        return computer.getStateSquareMy(new BattleshipMove(x,y));

    }

    public void SaveGame(String fileName) {
        model_$.SaveGame(fileName);
    }

    public void LoadGame(String fileName) {
        model_$.LoadGame(fileName);
    }
    
    /**************************** Information Game ***********************/
    public void setNameHuman(String string) {
        model_$.setNameHuman(string);
    }

    public void setTypeComputer(String smart) {
        model_$.setTypeComuter(smart);
    }

    public void setPlayerGrid() {
        model_$.setPlayerGrid();
    }

    public void setWinner(BattleshipPlayer win) {
        model_$.setWinner(win);
    }

    public void setStartTime(Date date) {
        model_$.setStartTime(date);
    }

    public InfoGame LoadInfoGame(String name) {
        return model_$.LoadInfoGame(name);
    }

    public void SaveInfoGame() {
        model_$.SaveInfoGame("previous/");
    }

    public void setIdgame() {
        model_$.setIdgame();
    }

    public void setPlayerMove() {
        model_$.setPlayerMove();
    }

    public void setScoring(BattleshipPlayer win) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void MarkPlayers() {
        model_$.setMarksPlayers();
    }
    
}
