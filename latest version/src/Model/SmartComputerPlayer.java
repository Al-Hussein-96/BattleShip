package Model;

import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmartComputerPlayer extends ComputerPlayer implements Serializable {

    public SmartComputerPlayer() {
        super();
        this.currentStrategy = new SmartComputerStrategy();
    }

    public SmartComputerStrategy mohammad() {
        return new SmartComputerStrategy();
    }

    public class SmartComputerStrategy extends abstractComputerStrategy implements Serializable {

        int w = ((BattleshipGame) model.BaseGame).getWidth();
        int h = ((BattleshipGame) model.BaseGame).getHeight();
     //   BattleshipMove BestMove = new BattleshipMove((new Random()).nextInt(w), (new Random()).nextInt(h));
        BattleshipMove BestMove = new BattleshipMove(1, 1);

        @Override
        public BattleshipMove GetNextMove() {

            int dx[] = {0, 0, 1, -1};
            int dy[] = {1, -1, 0, 0};
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    if (getStateSquareDraft(new BattleshipMove(i, j)) == Square.SquareStatus.DESTROYED_PART_OF_SHIP) {
                        for (int d = 0; d < 4; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];

                            if (nx >= 0 && nx < w && ny >= 0 && ny < h && getStateSquareDraft(new BattleshipMove(nx, ny)) == Square.SquareStatus.UNKNOWN) {
                                {
                                    BestMove = new BattleshipMove(nx, ny);
                                }
                            }

                        }
                    }
                }
            }
            while (getStateSquareDraft(BestMove) != Square.SquareStatus.UNKNOWN) {
                BestMove = new BattleshipMove((new Random()).nextInt(w), (new Random()).nextInt(h));
            }
            return BestMove;
        }
    }

}
