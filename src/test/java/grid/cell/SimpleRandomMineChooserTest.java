package grid.cell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isIn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleRandomMineChooser.class)
public class SimpleRandomMineChooserTest {

    @Autowired
    CellTypeChooser cellTypeChooser;


    @Test
    public void testGetCellType() throws Exception {
        CellType cellType = cellTypeChooser.getCellType();
        assertThat(cellType, isIn(CellType.values()));
    }


}