package grid.cell;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SimpleRandomMineChooser implements CellTypeChooser {

    public CellType getCellType() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return CellType.MINE;
        } else {
            return CellType.NORMAL;
        }
    }
}
