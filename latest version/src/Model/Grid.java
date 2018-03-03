package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grid implements Serializable,Cloneable {

    private int Width, Height, NumberShip, NumberDestroyedShip;
    private GridStatus State;
    private Square ArraySquare[][];
    private List<Ship> ListShip = new ArrayList<>();
    private List<Bomb> ListBomb = new ArrayList<>();

    Grid() {
    }
    
    public Grid Copy()
    {
        Grid temp = new Grid(this.Width,this.Height,GridStatus.MINE);
        
        for(int i=0;i<temp.Width;i++)
            for(int j=0;j<temp.Height;j++)
                try {
                    temp.ArraySquare[i][j] = (Square) this.ArraySquare[i][j].clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
            }
        return temp;
    }
    
    
//    @Override
//    protected Object clone() throws CloneNotSupportedException
//    {
//        Grid temp = new Grid();
//        return  super.clone();
//    }
//
//    public Grid(Grid another) {
//        this.ArraySquare = another.ArraySquare;
//        this.Height = another.Height;
//        this.ListBomb = another.ListBomb;
//        this.ListShip = another.ListShip;
//        this.NumberDestroyedShip = another.NumberDestroyedShip;
//        this.NumberShip = another.NumberShip;
//        this.State = another.State;
//        this.Width = another.Width;
//    }

//    public void draw() {
//        for (int i = 0; i < Width; i++) {
//            for (int j = 0; j < Height; j++) {
//                System.out.print(ArraySquare[i][j].getState() + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("End Grid");
////        for (Ship u : ListShip) {
////            System.out.println("Ship: ");
////            u.DrawSquareList();
////            System.out.println("EndShip");
////        }
//    }

    public List<Ship> getListShip() {
        return ListShip;
    }

    public Grid(int Width, int Height, GridStatus State) {
        this.NumberShip = 0;
        this.NumberDestroyedShip = 0;
        this.Width = Width;
        this.Height = Height;
        this.State = State;
        this.ArraySquare = new Square[Width][Height];
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Height; j++) {
                if (State == GridStatus.DRAFT) {
                    ArraySquare[i][j] = new Square(i, j, Square.SquareStatus.UNKNOWN);
                } else {
                    ArraySquare[i][j] = new Square(i, j, Square.SquareStatus.WATER);
                }
            }
        }
    }

    public void setWidth(int Width) {
        this.Width = Width;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public void setState(GridStatus State) {
        this.State = State;
    }

    public int getWidth() {
        return this.Width;
    }

    public int getHeight() {
        return this.Height;
    }

    public GridStatus getState() {
        return this.State;
    }

    public Square getSquare(int X, int Y) {
        return ArraySquare[X][Y];
    }

    public void setSquare(Square square) {
        ArraySquare[square.getX()][square.getY()] = square;
    }

    public Ship getShip(int id) {
        if (id > NumberShip) {
            return null;
        }
        return ListShip.get(id);
    }

    public void addShip(Ship ship) {
        for (int i = ship.getX(); i < ship.getX() + ship.getWidth(); i++) {
            for (int j = ship.getY(); j < ship.getY() + ship.getHeight(); j++) {
                ArraySquare[i][j].setState(Square.SquareStatus.PART_OF_SHIP);
            }
        }
        NumberShip++;
        this.ListShip.add(ship);
    }

    public boolean CheckGrid(Ship ship) {
        int dx[]
                = {
                    0, 0, 1, -1
                };
        int dy[]
                = {
                    1, -1, 0, 0
                };
        for (int i = ship.getX(); i < ship.getX() + ship.getWidth(); i++) {
            for (int j = ship.getY(); j < ship.getY() + ship.getHeight(); j++) {
                if (i >= Width || j >= Height || !(Square.SquareStatus.WATER == ArraySquare[i][j].getState())) {
                    return false;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if ((nx >= 0 && nx < Width && ny >= 0 && ny < Height) && Square.SquareStatus.WATER != ArraySquare[nx][ny].getState()) {
                        return false;
                    }
                }

            }
        }

        return true;
    }

    public AttackStatus AttackSequare(BattleshipMove Destination) {
        AttackStatus Result = AttackStatus.FAILURE;
        Square NewSquare = new Square(Destination.getX(), Destination.getY(), Square.SquareStatus.WATER);

        NewSquare.setState(Square.SquareStatus.DESTROYED); /// By default
        //   System.out.println(ArraySquare[Destination.getX()][Destination.getY()].getState() + " ::::: ");
        if (ArraySquare[Destination.getX()][Destination.getY()].getState() == Square.SquareStatus.HAVE_BOMB) {
            Result = AttackStatus.DESTROYED_BOMB;
            ArraySquare[NewSquare.getX()][NewSquare.getY()].setState(Square.SquareStatus.DESTROYED_BOMB);
            for (int i = 0; i < ListBomb.size(); i++) {
                Bomb temp = ListBomb.get(i);
                if (temp.getX() == Destination.getX() && temp.getY() == Destination.getY()) {
                    {
                        temp.setState(Bomb.StatusBomb.DESTROYED);
                        temp.StopThread();
                    }
                }
            }
            return Result;
        }

        ArraySquare[NewSquare.getX()][NewSquare.getY()].setState(Square.SquareStatus.DESTROYED);

        for (int i = 0; i < ListShip.size(); i++) {
            Ship temp = ListShip.get(i);
            if (temp.inShip(NewSquare)) {
                Result = AttackStatus.PARTIAL_DAMAGE_OF_SHIP;
                ArraySquare[NewSquare.getX()][NewSquare.getY()].setState(Square.SquareStatus.DESTROYED_PART_OF_SHIP);
                temp.ShipAttack(NewSquare);
                if (temp.ShipIsDestroyed()) {
                    NumberDestroyedShip++;
                    Result = AttackStatus.DESTROYED_SHIP;
                    if (NumberDestroyedShip == ListShip.size()) {
                        Result = AttackStatus.DESTROYED_ALL_SHIPS;
                    }
                }
                break;
            }
        }
        return Result;
    }

    public void addBomb(int Speed, BattleshipPlayer Father) {
        boolean ok = true;
        Bomb newBomb = new Bomb(-1, -1, Speed, Father);
        for (int i = 0; i < Width && ok; i++) {
            for (int j = 0; j < Height && ok; j++) {
                if (ArraySquare[i][j].getState() == Square.SquareStatus.WATER && SafeDist(i, j)) {
                    ArraySquare[i][j].setState(Square.SquareStatus.HAVE_BOMB);
                    newBomb = new Bomb(i, j, Speed, Father);
                    ok = false;
                }
            }
        }

        this.ListBomb.add(newBomb);
    }

    public List getListBomb() {
        return ListBomb;
    }

    public void MoveAllBomb() {

        for (int i = 0; i < ListBomb.size(); i++) {
            Bomb bomb = ListBomb.get(i);
            bomb.StartThread();
        }
    }

    void StopAllBomb() {
        for (int i = 0; i < ListBomb.size(); i++) {
            Bomb bomb = ListBomb.get(i);
            bomb.StopThread();
        }
    }

    private boolean SafeDist(int x, int y) {
        List<Bomb> temp = ListBomb;

        for (int i = 0; i < temp.size(); i++) {
            System.out.println(temp.size());
            Bomb k = temp.get(i);
            int r1 = x - k.getX();
            int r2 = y - k.getY();
            r1 *= r1;
            r2 *= r2;

            if (Math.sqrt(r1 + r2) <= 4) {
                return false;
            }
        }
        return true;
    }

    void RunThread() {
        for (int i = 0; i < ListBomb.size(); i++) {
            Bomb bomb = ListBomb.get(i);
            bomb.RunThread();
        }
    }

    public enum GridStatus {
        MINE, DRAFT;
    }

}
