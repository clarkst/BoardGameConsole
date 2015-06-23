package grid.position;

import grid.Game;
import grid.exception.InvalidMoveException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XYPosition.class)
public class XYPositionTest {

    @Autowired
    private XYPosition position;

    private int oldX;
    private int oldY;

    @Before
    public void setUp() throws Exception {
        setPosition(0,0);
    }

    @Test
    public void shouldIncrementYWhenMoveIsUp() throws Exception {
        position.makeMove("u");
        assertThat("X should be unchanged ", position.getX(), equalTo(oldX));
        assertThat("Y should be incremented by one ", position.getY(), is(both(greaterThan(oldY)).and(equalTo(oldY + 1))));
    }

    @Test
    public void shouldIncrementXWhenMoveIsRight() throws Exception {
        position.makeMove("r");
        assertThat("Y should be unchanged ", position.getY(), equalTo(oldY));
        assertThat("X should be incremented by one ", position.getX(), is(both(greaterThan(oldX)).and(equalTo(oldX + 1))));
    }

    @Test
    public void shouldDecrementXWhenMoveIsLeft() throws Exception {
        setPosition(1,1); //move away from edge of grid
        position.makeMove("l");
        assertThat("Y should be unchanged ", position.getY(), equalTo(oldY));
        assertThat("X should be decremented by one ", position.getX(), is(both(lessThan(oldX)).and(equalTo(oldX - 1))));
    }

    @Test
    public void shouldDecrementYWhenMoveIsDown() throws Exception {
        setPosition(1, 1); //move away from edge of grid
        position.makeMove("d");
        assertThat("X should be unchanged ", position.getX(), equalTo(oldX));
        assertThat("Y should be decremented by one ", position.getY(), is(both(lessThan(oldY)).and(equalTo(oldY - 1))));
    }

    @Test(expected = InvalidMoveException.class)
    public void shouldThrowExceptionForInvalidMoveEntry() throws Exception {
        position.makeMove("z");
    }

    @Test
    public void shouldIgnoreInvalidMoveOffTheGridLeft() throws Exception {
        position.makeMove("l");
        assertThat("X should be unchanged ", position.getX(), equalTo(oldX));
        assertThat("Y should be unchanged ", position.getY(), equalTo(oldY));
    }

    @Test
    public void shouldIgnoreInvalidMoveOffTheGridDown() throws Exception {
        position.setY(0);
        oldY = position.getY();
        position.makeMove("d");
        assertThat("X should be unchanged ", position.getX(), equalTo(oldX));
        assertThat("Y should be unchanged ", position.getY(), equalTo(oldY));
    }

    @Test
    public void shouldIgnoreInvalidMoveOffTheGridUp() throws Exception {
        for (int i = 0; i < Game.BOARD_SIZE; i++) {
            position.makeMove("u");
        }
        int oldX = position.getX();
        int oldY = position.getY();
        position.makeMove("u");
        assertThat("X should be unchanged ", position.getX(), equalTo(oldX));
        assertThat("Y should be unchanged ", position.getY(), equalTo(oldY));
    }

    @Test
    public void shouldIgnoreInvalidMoveOffTheGridRight() throws Exception {
        for (int i = 0; i < Game.BOARD_SIZE; i++) {
            position.makeMove("r");
        }
        int oldX = position.getX();
        int oldY = position.getY();
        position.makeMove("r");
        assertThat("X should be unchanged ", position.getX(), equalTo(oldX));
        assertThat("Y should be unchanged ", position.getY(), equalTo(oldY));
    }

    private void setPosition(int x, int y) {
        position.setY(x);
        position.setX(y);
        oldX = position.getX();
        oldY = position.getY();
    }

}