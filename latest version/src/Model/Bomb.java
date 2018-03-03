package Model;

import View.BoardAndCell.Cell;
import View.GUIPlayer2;
import View.GUIPlayer3;
import View.GUIPlayer4;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bomb implements Serializable {

    private transient Thread ThreadBomb;
    private int X, Y, Speed;
    private StatusBomb state;
    private BattleshipPlayer Father;
    private Grid grid;
    private boolean ShutDown = false;
//    BattleshipPlayer Father;
    int dx[] = {1, -1, 0, 0};
    int dy[] = {0, 0, 1, -1};
    private List< Square> ListSquer = new ArrayList<>();

    public Bomb(int X, int Y, int Speed, BattleshipPlayer Father) {
        this.X = X;
        this.Y = Y;
        this.Speed = Speed;
        this.state = StatusBomb.STOPPED;
        this.Father = Father;
        this.grid = Father.getMyGrid();

        this.ThreadBomb = new Thread(new Runnable() {
            @Override
            public void run() {

                while (!ShutDown) {
                    System.out.println("");
                    if (!Game.Pause) {
                        move();

                        try {
                            Thread.sleep(1500);
                        } catch (Exception e) {
                            break;
                        }
                    }
                }
            }
        });
    }

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getX() {
        return this.X;
    }

    public int getY() {
        return this.Y;
    }

    public void move() {
        if (state == StatusBomb.DESTROYED) {
            return;
        }
        int w = ((BattleshipGame) model.BaseGame).getWidth();
        int h = ((BattleshipGame) model.BaseGame).getHeight();

        ListSquer = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int u, v;
            u = X + dx[i];
            v = Y + dy[i];
            if ((u >= 0 && u < w) && (v >= 0 && v < h) && grid.getSquare(u, v).getState() == Square.SquareStatus.WATER) {
                ListSquer.add(new Square(u, v, Square.SquareStatus.WATER));
            }
        }
        if (ListSquer.size() > 0) {
            Random rand = new Random();
            int SZ = ListSquer.size();
            int a = rand.nextInt(SZ);
            int NextX = ListSquer.get(a).getX();
            int NextY = ListSquer.get(a).getY();

            if (Father instanceof HumanPlayer) {
                Cell cell = (Father.GUI).myBoard.getCell(Y, X);
                cell.set(Square.SquareStatus.WATER);

                cell = (Father.GUI).myBoard.getCell(NextY, NextX);
                cell.set(Square.SquareStatus.HAVE_BOMB);
            }

            grid.setSquare(new Square(X, Y, Square.SquareStatus.WATER));
            grid.setSquare(new Square(NextX, NextY, Square.SquareStatus.HAVE_BOMB));

            X = NextX;
            Y = NextY;
        }
    }

    public void StartThread() {
        this.ThreadBomb.start();
    }

    public void StopThread() {
//        if (this.ThreadBomb.isAlive()) {
//            ThreadBomb.interrupt();
//        }
        ShutDown = true;
    }

    void setState(StatusBomb state) {
        this.state = state;
    }

    private boolean SafeDist() {
        List<Bomb> temp = Father.getMyGrid().getListBomb();

        for (int i = 0; i < temp.size(); i++) {
            Bomb k = temp.get(i);
            int r1 = this.X - k.getX();
            int r2 = this.Y - k.getY();
            r1 *= r1;
            r2 *= r2;

            if (Math.sqrt(r1 + r2) <= 3) {
                return false;
            }
        }
        return true;
    }

    void RunThread() {
        this.ThreadBomb = new Thread(new Runnable() {
            @Override
            public void run() {

                while (!ShutDown) {
                    System.out.println("");
                    if (!Game.Pause) {
                        if (state == StatusBomb.DESTROYED) {
                            return;
                        }
                        int w = ((BattleshipGame) model.BaseGame).getWidth();
                        int h = ((BattleshipGame) model.BaseGame).getHeight();

                        ListSquer = new ArrayList<>();
                        for (int i = 0; i < 4; i++) {
                            int u, v;
                            u = X + dx[i];
                            v = Y + dy[i];
                            if ((u >= 0 && u < w) && (v >= 0 && v < h) && grid.getSquare(u, v).getState() == Square.SquareStatus.WATER) {
                                ListSquer.add(new Square(u, v, Square.SquareStatus.WATER));
                            }
                        }
                        if (ListSquer.size() > 0) {
                            Random rand = new Random();
                            int SZ = ListSquer.size();
                            int a = rand.nextInt(SZ);
                            int NextX = ListSquer.get(a).getX();
                            int NextY = ListSquer.get(a).getY();

                            if (Father instanceof HumanPlayer) {
                                Cell cell = ((GUIPlayer4) Father.GUI).myBoard.getCell(Y, X);
                                cell.set(Square.SquareStatus.WATER);

                                cell = ((GUIPlayer4) Father.GUI).myBoard.getCell(NextY, NextX);
                                cell.set(Square.SquareStatus.HAVE_BOMB);
                            }

                            grid.setSquare(new Square(X, Y, Square.SquareStatus.WATER));
                            grid.setSquare(new Square(NextX, NextY, Square.SquareStatus.HAVE_BOMB));

                            X = NextX;
                            Y = NextY;
                        }

                        try {
                            Thread.sleep(1500);
                        } catch (Exception e) {
                            break;
                        }
                    }
                }
            }
        });
        ThreadBomb.start();
    }

    public enum StatusBomb {
        DESTROYED, RUNNING, STOPPED;
    }
}
