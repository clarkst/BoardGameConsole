package grid.board;

import grid.Game;
import grid.position.Position;
import grid.cell.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Board2D implements Board {

    @Autowired
    BoardBuilder boardBuilder;

    private CellType[][] grid;

    public Board2D() {
    }

    @Override
    public void setup(int size) {
        grid = boardBuilder.build(size);
    }

    @Override
    public boolean isSquareMined(Position currentPosition) {
        return grid[currentPosition.getX()][currentPosition.getY()]==CellType.MINE;
    }

    @Override
    public boolean isSquareWinner(Position currentPosition) {
        return currentPosition.getY()>= Game.BOARD_SIZE-1;
    }
}
