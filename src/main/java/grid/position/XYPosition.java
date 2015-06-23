package grid.position;

import grid.Game;
import grid.exception.InvalidMoveException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class XYPosition implements Position {

    private int x, y = 0;

    public enum Direction {
        UP("U"),
        DOWN("D"),
        LEFT("L"),
        RIGHT("R");

        private final List<String> values;

        Direction(String... values) {
            this.values = Arrays.asList(values);
        }

        public List<String> getValues() {
            return values;
        }

    }

    @Override
    public void makeMove(String move) throws InvalidMoveException {

        Direction direction = getDirection(move.toUpperCase());

        switch (direction) {
            case UP:
                if (y < Game.BOARD_SIZE - 1) y++;
                break;
            case DOWN:
                if (y > 0) y--;
                break;
            case LEFT:
                if (x > 0) x--;
                break;
            case RIGHT:
                if (x < Game.BOARD_SIZE - 1) x++;
                break;
            default:
        }
    }

    @Override
    public void reset() {
        x=0;
        y=0;
    }

    private Direction getDirection(String move) throws InvalidMoveException {
        for (Direction direction : Direction.values()) {
            if (direction.getValues().contains(move)) {
                return direction;
            }
        }
        throw new InvalidMoveException("invalid entry, please try again");
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
