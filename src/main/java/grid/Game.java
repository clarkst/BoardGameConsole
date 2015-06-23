package grid;

import grid.board.Board;
import grid.exception.InvalidMoveException;
import grid.position.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;

@Component
public class Game implements CommandLineRunner {

    public static final int BOARD_SIZE = 8;
    public static final int MINE_LIMIT = 2;

    @Autowired
    Position currentPosition;

    @Autowired
    Board board;

    @Override
    public void run(String... args) throws Exception {
        play(System.in);
    }

    public void play(InputStream in) {

        board.setup(BOARD_SIZE);

        Scanner scanStr = new Scanner(in);

        int mineCount = 0;

        do {
            System.out.println("Position : " + currentPosition.getX() + "," + currentPosition.getY());
            System.out.println("Mine count:" + mineCount+" / "+MINE_LIMIT);
            System.out.print("Your move: U/D/L/R ?");

            String move = scanStr.next();

            if (move.startsWith("q")) {
                System.out.println("Bye bye");
                return;
            } else {
                try {
                    currentPosition.makeMove(move);

                    if(board.isSquareMined(currentPosition)){
                        System.out.println("Boom!");
                        mineCount++;
                    }

                    if(mineCount>=MINE_LIMIT){
                        System.out.println("You hit too many mines - You lose");
                        return;
                    }

                    if(board.isSquareWinner(currentPosition)) {
                        System.out.println("You won !");
                        return;
                    }

                } catch (InvalidMoveException e) {
                    System.out.println(e.getMessage());
                }

            }

        } while (true);

    }

    public void resetPosition(){
        currentPosition.reset();
    }

}
