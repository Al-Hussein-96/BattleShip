package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ship implements Serializable {

    private int topX, topY, Width, Height;
    private List<Square> ShipSquare;
    private int NumberOfDestroyedSquare;
    private ShipStatus State;

    public Ship(int topX, int topY, int Width, int Height, ShipStatus State) {
        this.topX = topX;
        this.topY = topY;
        this.Width = Width;
        this.Height = Height;
        this.State = State;
        this.NumberOfDestroyedSquare = 0;
        ShipSquare = new ArrayList<>();
        for (int x = topX; x < topX + Width; x++) {
            for (int y = topY; y < topY + Height; y++) {
                ShipSquare.add(new Square(x, y, Square.SquareStatus.PART_OF_SHIP));
            }
        }
    }

    public void setWidth(int Width) {
        this.Width = Width;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public void setX(int topX) {
        this.topX = topX;
    }

    public void setY(int topY) {
        this.topY = topY;
    }

    public void setState(ShipStatus State) {
        this.State = State;
    }

    public int getWidth() {
        return this.Width;
    }

    public int getHeight() {
        return this.Height;
    }

    public int getX() {
        return this.topX;
    }

    public int getY() {
        return this.topY;
    }

    public ShipStatus getState() {
        return this.State;
    }

    public boolean inShip(Square Goal) {
        for (Square u : ShipSquare) {
            if (u.getX() == Goal.getX() && u.getY() == Goal.getY()) {
                return true;
            }
        }
        return false;
    }

    public void ShipAttack(Square Goal) {

        for (Square u : ShipSquare) {
            if (u.getX() == Goal.getX() && u.getY() == Goal.getY()) {
                NumberOfDestroyedSquare++;
                break;
            }
        }

        if (NumberOfDestroyedSquare == ShipSquare.size()) {
            this.State = ShipStatus.TOTALLY_DESTROYED;
        } else {
            this.State = ShipStatus.PARTIALLY_DESTROYED;
        }

    }

    public boolean ShipIsDestroyed() {
        return (this.State == ShipStatus.TOTALLY_DESTROYED);
    }

    public void DrawSquareList() {
        for (Square sq : ShipSquare) {
            System.out.println(sq.getX() + " " + sq.getY());
        }

    }

    public enum ShipStatus {
        GOOD, PARTIALLY_DESTROYED, TOTALLY_DESTROYED;
    }
}
