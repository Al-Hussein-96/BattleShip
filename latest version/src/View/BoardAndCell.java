package View;

import Model.BattleshipMove;
import Model.BattleshipPlayer;
import Model.HumanPlayer;
import Model.Ship;
import Model.Square;
import Model.Square.SquareStatus;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BoardAndCell extends Parent {

    private int w, h;
    private final VBox rows = new VBox();

    public BoardAndCell(StatusBoard state, int w, int h, EventHandler<MouseEvent> handler1, EventHandler<MouseEvent> handler2, EventHandler<MouseEvent> handler3) {
        this.w = w;
        this.h = h;
        for (int x = 0; x < w; x++) {
            HBox row = new HBox();
            for (int y = 0; y < h; y++) {
                SquareStatus temp = SquareStatus.UNKNOWN;

                Cell c = new Cell(x, y, temp);

                c.setOnMouseClicked(handler1);
                c.setOnMouseEntered(handler2);
                c.setOnMouseExited(handler3);

                row.getChildren().add(c);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);
    }

    public BoardAndCell(int w, int h, EventHandler<MouseEvent> handler1, EventHandler<MouseEvent> handler2, EventHandler<MouseEvent> handler3, HumanPlayer human) {
        this.w = w;
        this.h = h;
        for (int x = 0; x < w; x++) {
            HBox row = new HBox();
            for (int y = 0; y < h; y++) {
                Square.SquareStatus state = human.getStateSquareDraft(new BattleshipMove(x, y));
                Cell c = new Cell(x, y, state);

                c.setOnMouseClicked(handler1);
                c.setOnMouseEntered(handler2);
                c.setOnMouseExited(handler3);

                row.getChildren().add(c);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);
    }

    public BoardAndCell(int w, int h) {
        for (int x = 0; x < w; x++) {
            HBox row = new HBox();
            for (int y = 0; y < h; y++) {
                Cell c = new Cell(x, y, SquareStatus.WATER);
                row.getChildren().add(c);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);

    }

    public BoardAndCell() {

    }

    public void PlaceShip(Ship newShip) {

        for (int i = newShip.getX(); i < newShip.getX() + newShip.getWidth(); i++) {
            for (int j = newShip.getY(); j < newShip.getY() + newShip.getHeight(); j++) {
                Cell cell = getCell(j, i);
                cell.set(SquareStatus.PART_OF_SHIP);
                cell.state = SquareStatus.PART_OF_SHIP;

            }
        }

    }

    public Cell getCell(int x, int y) {
        return (Cell) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
    }

    public enum StatusBoard {
        MYBOARD, MYDRAFT, INITIALIZE;
    }

    public class Cell extends Rectangle {

        public int x, y;
        public SquareStatus state;

        public Cell(int x, int y, SquareStatus state) {
            super(45, 45);
            this.x = x;
            this.y = y;
            this.state = state;
            set(state);
            if (state == SquareStatus.WATER) {
                setFill(Color.GREEN);
            }
//            setFill(Color.LIGHTGRAY);
//            setStroke(Color.BLACK);
        }

        public void ChangeColor1() {
            if ("Water".equals(state)) {
                setFill(Color.GREEN);
                setStroke(Color.BLACK);

            }
        }

        public void ChangeColor2() {
            if ("Water".equals(state)) {
                setFill(Color.LIGHTGRAY);
                setStroke(Color.BLACK);
            }
        }

        public void ChangeColor3(BattleshipPlayer computer) {
            ImagePattern imagePattern1 = new ImagePattern(new Image(getClass().getResource("res/ship.jpg").toExternalForm()));
            ImagePattern imagePattern2 = new ImagePattern(new Image(getClass().getResource("res/water.jpg").toExternalForm()));
            ImagePattern imagePattern3 = new ImagePattern(new Image(getClass().getResource("res/Bomb.PNG").toExternalForm()));

            if ("Water".equals(state)) {
                if (computer.getStateSquareMy(new BattleshipMove(x, y)) == Square.SquareStatus.PART_OF_SHIP) {
                    setFill(imagePattern1);
                    setStroke(Color.BLACK);
                } else if (computer.getStateSquareMy(new BattleshipMove(x, y)) == Square.SquareStatus.WATER) {
                    System.out.println("ChangeColor3");
                    setFill(imagePattern2);
                    setStroke(Color.BLACK);
                } else {
                    setFill(imagePattern3);
                    setStroke(Color.BLACK);
                }
            }

        }

        void Shoot(Square.SquareStatus state) {

            if (null != state) {
                switch (state) {
                    case WATER:
                        System.out.println("Thread2 : " + Thread.currentThread().getName());
                        setFill(Color.CHOCOLATE);
                        setStroke(Color.BLACK);
                        this.state = SquareStatus.DESTROYED;
                        break;
                    case DESTROYED_PART_OF_SHIP:
                    case DESTROYED:
                        setFill(Color.YELLOW);
                        setStroke(Color.BLACK);
                        this.state = SquareStatus.DESTROYED_PART_OF_SHIP;
                        break;
                    default:
                        setFill(Color.CRIMSON);
                        setStroke(Color.BLACK);
                        break;
                }
            }
            this.state = SquareStatus.DESTROYED;
        }

        public void set(Square.SquareStatus state) {
            ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("res/Bomb.PNG").toExternalForm()));
            ImagePattern imagePattern2 = new ImagePattern(new Image(getClass().getResource("res/water2.PNG").toExternalForm()));

            if (null != state) {
                switch (state) {
                    case WATER:
                        setFill(Color.BLUE);
                        setStroke(Color.BLACK);
                        break;
                    case PART_OF_SHIP:
                        setFill(Color.BLACK);
                        setStroke(Color.BLACK);
                        break;
                    case DESTROYED:
                        setFill(Color.GREEN);
                        setStroke(Color.BLACK);
                        break;
                    case DESTROYED_PART_OF_SHIP:
                        setFill(Color.YELLOW);
                        setStroke(Color.BLACK);
                        break;
                    case DESTROYED_BOMB:
                        setFill(Color.CRIMSON);
                        setStroke(Color.BLACK);
                        break;
                    case UNKNOWN:
                        setFill(Color.LIGHTGREY);
                        setStroke(Color.BLACK);
                        break;
                    case HAVE_BOMB:
                        setFill(imagePattern);
                        setStroke(Color.BLACK);
                        break;
                }
            }
            this.state = SquareStatus.DESTROYED;

        }

    }
}
