package dev.marfien.mazegenerator.impl.grid;

import dev.marfien.mazegenerator.MazeTile;

public class GridMazeTile implements MazeTile {

    private final boolean[] openEdges;

    public GridMazeTile(boolean[] openEdges) {
        this.checkBounds(openEdges.length - 1);

        this.openEdges = openEdges;
    }

    public GridMazeTile(boolean front, boolean left, boolean behind, boolean right) {
        this.openEdges = new boolean[] {
                front, left, behind, right
        };
    }

    @Override
    public boolean[] getOpenEdges() {
        return this.openEdges.clone();
    }

    public boolean isOpen(GridTileFace face) {
        return isOpen(face.ordinal());
    }

    @Override
    public boolean isOpen(int edge) {
        this.checkBounds(edge);
        return this.openEdges[edge];
    }

    private void checkBounds(int edge) {
        if (edge < 0 || edge >= 4)
            throw new IndexOutOfBoundsException("GridMazeTiles only have four edges. Edge requested: " + edge);
    }

    public interface Generator<T extends GridMazeTile> {

        T generate(GridCoordinate coordinate, boolean front, boolean left, boolean behind, boolean right);

    }

}
