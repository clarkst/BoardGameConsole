package grid.board;

import grid.cell.CellType;
import grid.cell.SimpleRandomMineChooser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SimpleBoardBuilder.class, SimpleRandomMineChooser.class})
public class SimpleBoardBuilderTest {

    public static final int TEST_GRID_SIZE = 8;

    @Autowired
    private BoardBuilder boardBuilder;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldBuildGridOfCells() throws Exception {

        CellType[][] grid = boardBuilder.build(TEST_GRID_SIZE);

        assertThat("Grid should not be null",grid, is(notNullValue()));

        for(CellType[] gridColumnCellArray : Arrays.asList(grid)){
            List<CellType> cellTypeList = Arrays.asList(gridColumnCellArray);
            assertThat("Every cell should be a CellType ", cellTypeList, everyItem(isA(CellType.class)));
        }
    }
}