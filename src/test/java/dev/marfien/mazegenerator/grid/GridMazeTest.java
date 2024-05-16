package dev.marfien.mazegenerator.grid;

import dev.marfien.mazegenerator.impl.grid.BoxedGridMazeGenerator;
import dev.marfien.mazegenerator.impl.grid.GridCoordinate;

public class GridMazeTest {

    public static void main(String[] args) {
        BoxedGridMazeGenerator<CharTile> generator = new BoxedGridMazeGenerator<>(50, 8, BoxedGridMazeGenerator.EdgeStrategy.OPEN, (coordinate, front, left, behind, right) -> new CharTile(front, left, behind, right));
        char[][] charGrid = new char[8][50];
        for (int i = 0; i < charGrid.length; i++) {
            for (int j = 0; j < charGrid[0].length; j++) {
                charGrid[i][j] = generator.getOrGenerateMazeTile(new GridCoordinate(j, i)).getChar();
            }
        }

        printGrid(charGrid);
    }

    private static void printGrid(char[][] charGrid) {
        for (int i = charGrid.length - 1; i >= 0; i--) {
            for (int j = 0; j < charGrid[0].length; j++) {
                System.out.print(charGrid[i][j]);
            }
            System.out.println();
        }
    }

}
