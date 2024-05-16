package dev.marfien.mazegenerator.impl.grid;

import dev.marfien.mazegenerator.MazeGenerator;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class BoxedGridMazeGenerator<T extends GridMazeTile> implements MazeGenerator<T, GridCoordinate> {

    private final T[][] gridMazeTiles;
    private final GridMazeTile.Generator<T> generator;
    private final EdgeStrategy edgeStrategy;

    public BoxedGridMazeGenerator(int width, int height, EdgeStrategy edgeStrategy, GridMazeTile.Generator<T> generator) {
        this.gridMazeTiles = (T[][]) new GridMazeTile[width][height];
        this.generator = generator;
        this.edgeStrategy = edgeStrategy;
    }

    @Override
    public Optional<T> getMazeTile(GridCoordinate coordinate) {
        this.checkBounds(coordinate);
        return Optional.ofNullable(this.gridMazeTiles[coordinate.x()][coordinate.y()]);
    }

    @Override
    public T getOrGenerateMazeTile(GridCoordinate coordinate) {
        return this.getMazeTile(coordinate).orElse(this.generate(coordinate));
    }

    @Override
    public boolean isTileGenerated(GridCoordinate coordinate) {
        this.checkBounds(coordinate);
        return this.gridMazeTiles[coordinate.x()][coordinate.y()] != null;
    }

    public int getWidth() {
        return this.gridMazeTiles.length;
    }

    public int getHeight() {
        return this.gridMazeTiles[0].length;
    }

    @Override
    public T generate(GridCoordinate coordinate) {
        this.checkBounds(coordinate);

        Map<GridTileFace, Boolean> doors = new EnumMap<>(GridTileFace.class);
        for (GridTileFace face : GridTileFace.values()) {
            GridCoordinate neighborCoordinate = coordinate.getNeighbor(face);

            if (!this.isInBounds(neighborCoordinate)) {
                doors.put(face, this.edgeStrategy.getEdgeCase());
            } else {
                T neighbor = this.gridMazeTiles[neighborCoordinate.x()][neighborCoordinate.y()];
                // If the neighbor is not generated yet, just choose a random door state
                doors.put(face, neighbor == null ? ThreadLocalRandom.current().nextBoolean() : neighbor.isOpen(face.opposite()));
            }
        }

        T generated = this.generator.generate(
                coordinate,
                doors.get(GridTileFace.IN_FRONT),
                doors.get(GridTileFace.LEFT),
                doors.get(GridTileFace.BEHIND),
                doors.get(GridTileFace.RIGHT)
        );

        this.gridMazeTiles[coordinate.x()][coordinate.y()] = generated;
        return generated;
    }

    private void checkBounds(GridCoordinate coordinate) {
        if (!this.isInBounds(coordinate)) {
            throw new IndexOutOfBoundsException("Invalid coordinate: %s for bounds [%d %d]".formatted(coordinate, this.gridMazeTiles.length, this.gridMazeTiles[0].length));
        }
    }

    public boolean isInBounds(GridCoordinate coordinate) {
        return coordinate.x() >= 0 && coordinate.x() < this.gridMazeTiles.length
                && coordinate.y() >= 0 && coordinate.y() < this.gridMazeTiles[0].length;
    }

    public enum EdgeStrategy {

        OPEN,
        CLOSED,
        NONE;

        public boolean getEdgeCase() {
            return switch (this) {
                case NONE -> ThreadLocalRandom.current().nextBoolean();
                case OPEN -> true;
                case CLOSED -> false;
            };
        }

    }

}
