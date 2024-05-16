package dev.marfien.mazegenerator.impl.grid;

import dev.marfien.mazegenerator.MazeGenerator;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class InfiniteGridMazeGenerator<T extends GridMazeTile> implements MazeGenerator<T, GridCoordinate> {

    private final Map<GridCoordinate, T> gridMazeTiles = new HashMap<>();
    private final GridMazeTile.Generator<T> generator;

    public InfiniteGridMazeGenerator(GridMazeTile.Generator<T> generator) {
        this.generator = generator;
    }

    @Override
    public Optional<T> getMazeTile(GridCoordinate coordinate) {
        return Optional.ofNullable(this.gridMazeTiles.get(coordinate));
    }

    @Override
    public T getOrGenerateMazeTile(GridCoordinate coordinate) {
        return this.getMazeTile(coordinate).orElse(this.generate(coordinate));
    }

    @Override
    public boolean isTileGenerated(GridCoordinate coordinate) {
        return this.gridMazeTiles.containsKey(coordinate);
    }

    @Override
    public T generate(GridCoordinate coordinate) {
        Map<GridTileFace, Boolean> doors = new EnumMap<>(GridTileFace.class);
        for (GridTileFace face : GridTileFace.values()) {
            GridMazeTile neighbor = this.gridMazeTiles.get(coordinate.getNeighbor(face));
            if (neighbor == null) {
                doors.put(face, ThreadLocalRandom.current().nextBoolean());
            } else {
                doors.put(face, neighbor.isOpen(face.opposite()));
            }
        }

        T generated = this.generator.generate(
                coordinate,
                doors.get(GridTileFace.IN_FRONT),
                doors.get(GridTileFace.LEFT),
                doors.get(GridTileFace.BEHIND),
                doors.get(GridTileFace.RIGHT)
        );

        this.gridMazeTiles.put(coordinate, generated);
        return generated;
    }

}
