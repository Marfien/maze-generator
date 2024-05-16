package dev.marfien.mazegenerator.grid;

import dev.marfien.mazegenerator.impl.grid.GridMazeTile;
import dev.marfien.mazegenerator.impl.grid.GridTileFace;

public class CharTile extends GridMazeTile {

    public CharTile(boolean[] openEdges) {
        super(openEdges);
    }

    public CharTile(boolean front, boolean left, boolean behind, boolean right) {
        super(front, left, behind, right);
    }

    public char getChar() {
        if (super.isOpen(GridTileFace.IN_FRONT) && super.isOpen(GridTileFace.LEFT) && super.isOpen(GridTileFace.RIGHT) && super.isOpen(GridTileFace.BEHIND)) {
            return '┼';
        }
        if (!super.isOpen(GridTileFace.IN_FRONT) && super.isOpen(GridTileFace.LEFT) && super.isOpen(GridTileFace.RIGHT) && super.isOpen(GridTileFace.BEHIND)) {
            return '┬';
        }
        if (super.isOpen(GridTileFace.IN_FRONT) && !super.isOpen(GridTileFace.LEFT) && super.isOpen(GridTileFace.RIGHT) && super.isOpen(GridTileFace.BEHIND)) {
            return '├';
        }
        if (super.isOpen(GridTileFace.IN_FRONT) && super.isOpen(GridTileFace.LEFT) && !super.isOpen(GridTileFace.RIGHT) && super.isOpen(GridTileFace.BEHIND)) {
            return '┤';
        }
        if (super.isOpen(GridTileFace.IN_FRONT) && super.isOpen(GridTileFace.LEFT) && super.isOpen(GridTileFace.RIGHT) && !super.isOpen(GridTileFace.BEHIND)) {
            return '┴';
        }
        if (!super.isOpen(GridTileFace.IN_FRONT) && !super.isOpen(GridTileFace.LEFT) && super.isOpen(GridTileFace.RIGHT) && super.isOpen(GridTileFace.BEHIND)) {
            return '┌';
        }
        if (!super.isOpen(GridTileFace.IN_FRONT) && super.isOpen(GridTileFace.LEFT) && !super.isOpen(GridTileFace.RIGHT) && super.isOpen(GridTileFace.BEHIND)) {
            return '┐';
        }
        if (!super.isOpen(GridTileFace.IN_FRONT) && super.isOpen(GridTileFace.LEFT) && super.isOpen(GridTileFace.RIGHT) && !super.isOpen(GridTileFace.BEHIND)) {
            return '─';
        }
        if (super.isOpen(GridTileFace.IN_FRONT) && !super.isOpen(GridTileFace.LEFT) && !super.isOpen(GridTileFace.RIGHT) && super.isOpen(GridTileFace.BEHIND)) {
            return '│';
        }
        if (super.isOpen(GridTileFace.IN_FRONT) && !super.isOpen(GridTileFace.LEFT) && super.isOpen(GridTileFace.RIGHT) && !super.isOpen(GridTileFace.BEHIND)) {
            return '└';
        }
        if (super.isOpen(GridTileFace.IN_FRONT) && super.isOpen(GridTileFace.LEFT) && !super.isOpen(GridTileFace.RIGHT) && !super.isOpen(GridTileFace.BEHIND)) {
            return '┘';
        }
        if (super.isOpen(GridTileFace.IN_FRONT) && !super.isOpen(GridTileFace.LEFT) && !super.isOpen(GridTileFace.RIGHT) && !super.isOpen(GridTileFace.BEHIND)) {
            return '╵';
        }
        if (!super.isOpen(GridTileFace.IN_FRONT) && super.isOpen(GridTileFace.LEFT) && !super.isOpen(GridTileFace.RIGHT) && !super.isOpen(GridTileFace.BEHIND)) {
            return '╴';
        }
        if (!super.isOpen(GridTileFace.IN_FRONT) && !super.isOpen(GridTileFace.LEFT) && super.isOpen(GridTileFace.RIGHT) && !super.isOpen(GridTileFace.BEHIND)) {
            return '╶';
        }
        if (!super.isOpen(GridTileFace.IN_FRONT) && !super.isOpen(GridTileFace.LEFT) && !super.isOpen(GridTileFace.RIGHT) && super.isOpen(GridTileFace.BEHIND)) {
            return '╷';
        }
        return '·';
    }

}
