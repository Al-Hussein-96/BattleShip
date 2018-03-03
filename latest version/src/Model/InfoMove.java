package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoMove implements Serializable {

    private int Number;
    private String Coord;
    private AttackStatus ResultMove;
    private String TimeMove;
    private int Mark;

    public InfoMove(BattleshipMove Coordinates, AttackStatus ResultMove, Date TimeMove,int Mark) {
        this.Coord = "(" + String.valueOf(Coordinates.getX()) + " , " + String.valueOf(Coordinates.getY()) + ")";
        System.out.println(Coord);
        this.ResultMove = ResultMove;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        this.TimeMove = formatter.format(TimeMove);
        this.Mark = Mark;
    }

    public String getCoord() {
        return Coord;
    }

    public void setCoord(String Coord) {
        this.Coord = Coord;
    }

//    public int getX() {
//        return x;
//    }
//
//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }
    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public String getTimeMove() {
        return TimeMove;
    }

    public void setTimeMove(Date TimeMove) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        this.TimeMove = formatter.format(TimeMove);
    }

    public AttackStatus getResultMove() {
        return ResultMove;
    }

    public void setResultMove(AttackStatus ResultMove) {
        this.ResultMove = ResultMove;
    }

    public int getMark() {
        return Mark;
    }

    public void setMark(int Mark) {
        this.Mark = Mark;
    }
    

}
