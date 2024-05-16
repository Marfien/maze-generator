package dev.marfien.mazegenerator.impl.grid;

import dev.marfien.mazegenerator.MazeTileCoordinate;

import java.util.Objects;

public record GridCoordinate(
    int x,
    int y
) implements MazeTileCoordinate {

    @Override
    public int hash() {
        return this.hashCode();
    }

    @Override
    public GridCoordinate[] getNeighbors() {
        GridCoordinate[] neighbors = new GridCoordinate[4];
        for (int i = 0; i < GridTileFace.values().length; i++) {
            neighbors[i] = this.getNeighbor(GridTileFace.values()[i]);
        }
        return neighbors;
    }

    public GridCoordinate getNeighbor(GridTileFace face) {
        return new GridCoordinate(this.x + face.getdX(), this.y + face.getdY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        GridCoordinate that = (GridCoordinate) o;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
