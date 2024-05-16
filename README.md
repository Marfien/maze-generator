# Maze-Generator
This is a library to generate mazes. Currently, only two-dimensional grid based mazes are supported.

## Usage
There are currently two implementations for maze generation:

### BoxedGridMazeGenerator
This implementation generates mazes with a finite size. All tiles will be stored in a two-dimensional
array. This will allocate the memory at instantiation of the generator, but hat fast access to every tile.
```java
public static void main(String[] args) {
    BoxedGridMazeGenerator<CharTile> generator = new BoxedGridMazeGenerator<>(50, 8, BoxedGridMazeGenerator.EdgeStrategy.OPEN, (coordinate, front, left, behind, right) -> new CharTile(front, left, behind, right));
    // Get a tile if already generated or generate it if not present yet.
    CharTile tile = generator.getOrGenerate(new GridCoordinate(0, 0));
    // Regenerates a tile fitting perfectly into the maze
    tile = generator.generate(new GridCoordinate(0, 0));
    
    // This will throw an exception
    tile = generator.getTile(new GridCoordinate(0, 1000));
    ...
}
```

### InfiniteGridMazeGenerator
This implementation generates mazes with an infinite size.
All tiles will be stored into a `HashMap` upon generation.
```java
public static void main(String[] args) {
    InfiniteGridMazeGenerator<CharTile> generator = new InfiniteGridMazeGenerator<>((coordinate, front, left, behind, right) -> new CharTile(front, left, behind, right));
    // Get a tile if already generated or generate it if not present yet.
    CharTile tile = generator.getOrGenerate(new GridCoordinate(0, 0));
    // Regenerates a tile fitting perfectly into the maze
    // We can generate any tile in integer range
    tile = generator.generate(new GridCoordinate(100000, -456875));
    ...
}
```