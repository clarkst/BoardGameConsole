package grid;

import grid.board.Board2D;
import grid.board.SimpleBoardBuilder;
import grid.cell.SimpleRandomMineChooser;
import grid.position.XYPosition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Game.class,XYPosition.class, Board2D.class, SimpleBoardBuilder.class,
                                SimpleRandomMineChooser.class})
public class GameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Autowired
    Game game;


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        game.resetPosition();
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void shouldWinOrLose() {
        ByteArrayInputStream in = new ByteArrayInputStream("u u u u u u quit".getBytes());
        game.play(in);
        assertThat(outContent.toString(), either(containsString("lose")).or(containsString("won")));
    }

    @Test
    public void shouldDetectInvalidEntry() {
        ByteArrayInputStream in = new ByteArrayInputStream("x quit".getBytes());
        game.play(in);
        assertThat(outContent.toString(), containsString("invalid entry"));
    }

    @Test
    public void shouldMoveUp() {
        ByteArrayInputStream in = new ByteArrayInputStream("u q".getBytes());
        game.play(in);
        assertThat(outContent.toString(), containsString("Position : 0,1"));
    }

    @Test
    public void shouldNotMoveOffGrid() {
        ByteArrayInputStream in = new ByteArrayInputStream("l q".getBytes());
        game.play(in);
        assertThat(outContent.toString(), not(containsString("Position : -1,0")));
    }

}
