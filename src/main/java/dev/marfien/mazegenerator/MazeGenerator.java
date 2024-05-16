package dev.marfien.mazegenerator;

import java.util.Optional;

public interface MazeGenerator<T extends MazeTile, C extends MazeTileCoordinate> {

    Optional<T> getMazeTile(C coordinate);

    T getOrGenerateMazeTile(C coordinate);

    boolean isTileGenerated(C coordinate);

    T generate(C coordinate);

}
