package grid.position;

import grid.exception.InvalidMoveException;

public interface Position {

    void makeMove(String move) throws InvalidMoveException;

    void reset();

    int getX();
    int getY();

}
