package grid.board;


import grid.cell.CellType;
import grid.cell.CellTypeChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SimpleBoardBuilder implements BoardBuilder {

    @Autowired
    CellTypeChooser cellTypeChooser;

    @Override
    public CellType[][] build(int size) {
        CellType[][] cells = new CellType[size][size];
        Random r = new Random();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                cells[x][y] = cellTypeChooser.getCellType();
            }
        }
        return cells;
    }

}
