/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Al-Hussein
 */
public class Scoring implements Serializable {

    private String Name;
    private int win, lose;
    private double marks;

    public void setMarks() {
        this.marks = (win * 100.0) / (win + lose);
        this.marks = BigDecimal.valueOf(marks).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public Scoring(String Name, int win, int lose) {
        this.Name = Name;
        this.win = win;
        this.lose = lose;
        this.marks = win * 100.0 / (win + lose);
        this.marks = BigDecimal.valueOf(marks).setScale(2, RoundingMode.HALF_UP).doubleValue();

    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

}
