package grid.board;

import grid.cell.CellType;

public interface BoardBuilder {

    CellType[][] build(int size);
}
