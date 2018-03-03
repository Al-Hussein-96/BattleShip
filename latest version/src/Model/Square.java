package Model;

import java.io.Serializable;

public class Square implements Serializable, Cloneable {

    private int X, Y;
    private SquareStatus Status;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //   Grid temp = new Grid();
        return super.clone();
    }

    public Square(int X, int Y, SquareStatus Status) {
        this.X = X;
        this.Y = Y;
        this.Status = Status;
    }

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public void setState(SquareStatus Status) {
        this.Status = Status;
    }

    public int getX() {
        return this.X;
    }

    public int getY() {
        return this.Y;
    }

    public SquareStatus getState() {
        return this.Status;
    }

    public Square getSquare() {
        return new Square(this.X, this.Y, this.Status);
    }

    public enum SquareStatus {
        UNKNOWN, WATER, PART_OF_SHIP, DESTROYED_PART_OF_SHIP, DESTROYED, HAVE_BOMB, DESTROYED_BOMB;
    }

}
