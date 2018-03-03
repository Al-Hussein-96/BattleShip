package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InfoGame implements Serializable {

    private int IdGame;
    private String NameHuman;
    private String TypeComputer;
    private String StartGame;
    private String EndGame;
    private String Winner;
    private String Marks;
    private Grid HumanGrid;
    private Grid ComputerGrid;
    private List<InfoMove> HumanMove = new ArrayList<>();
    private List<InfoMove> ComputerMove = new ArrayList<>();

    public InfoGame() {
    }

    public InfoGame(int IdGame, String NameHuman, String TypeComputer, String StartGame, String EndGame, String Winner, String Marks) {
        this.IdGame = IdGame;
        this.NameHuman = NameHuman;
        this.TypeComputer = TypeComputer;
        this.StartGame = StartGame;
        this.EndGame = EndGame;
        this.Winner = Winner;
        this.Marks = Marks;
    }

    public void setIdGame(int IdGame) {
        this.IdGame = IdGame;
    }

    public void setNameHuman(String NameHuman) {
        this.NameHuman = NameHuman;
    }

    public void setHumanMove(List<InfoMove> HumanMove) {
        this.HumanMove = HumanMove;
    }

    public void setComputerMove(List<InfoMove> ComputerMove) {
        this.ComputerMove = ComputerMove;
    }

    public void setTypeComputer(String TypeComputer) {
        this.TypeComputer = TypeComputer;
    }

    public void setStartGame(Date StartGame) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.StartGame = formatter.format(StartGame);
    }

    public void setEndGame(Date EndGame) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.EndGame = formatter.format(EndGame);
    }

    public void setWinner(String Winner) {
        this.Winner = Winner;
    }

    public void setMarks(String Marks) {
        this.Marks = Marks;
    }

    public int getIdGame() {
        return IdGame;
    }

    public String getNameHuman() {
        return NameHuman;
    }

    public String getTypeComputer() {
        return TypeComputer;
    }

    public String getStartGame() {
        return StartGame;
    }

    public String getEndGame() {
        return EndGame;
    }

    public String getWinner() {
        return Winner;
    }

    public String getMarks() {
        return Marks;
    }

    public List<InfoMove> getHumanMove() {
        return HumanMove;
    }

    public List<InfoMove> getComputerMove() {
        return ComputerMove;
    }

    public void drawShip() {
        System.out.println("***************************Human************************************");
        for (int i = 0; i < this.HumanGrid.getWidth(); i++) {
            for (int j = 0; j < this.HumanGrid.getHeight(); j++) {
                System.out.print(HumanGrid.getSquare(i, j).getState() + " ");
            }
            System.out.println("");
        }
//        System.out.println("***************************Computr************************************");
//        for (int i = 0; i < this.HumanGrid.getWidth(); i++) {
//            for (int j = 0; j < HumanGrid.getHeight(); j++) {
//                System.out.print(ComputerGrid.getSquare(i, j).getState() + " ");
//            }
//            System.out.println("");
//        }

    }

    public Grid getHumanGrid() {
        return HumanGrid;
    }

    public Grid getComputerGrid() {
        return ComputerGrid;
    }

    public void setStartGame(String StartGame) {
        this.StartGame = StartGame;
    }

    public void setEndGame(String EndGame) {
        this.EndGame = EndGame;
    }

    public void setHumanGrid(Grid HumanGrid) {
        this.HumanGrid = HumanGrid;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(InfoGame.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    drawShip();
//                }
//            }
//        }).start();
    }

    public void setComputerGrid(Grid ComputerGrid) {
        this.ComputerGrid = ComputerGrid;
    }

    public void setMarks(BattleshipPlayer.CalculatePoints myPoints, BattleshipPlayer.CalculatePoints myPoints0) {
        this.Marks = ("" + myPoints.getCurrentPoints()) + " / " + ("" + myPoints0.getCurrentPoints());
    }

}
