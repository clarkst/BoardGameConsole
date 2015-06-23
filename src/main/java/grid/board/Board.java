package grid.board;

import grid.position.Position;

/**
 * Created by sharky on 23/06/15.
 */
public interface Board {
    void setup(int size);

    boolean isSquareMined(Position currentPosition);

    boolean isSquareWinner(Position currentPosition);
}
