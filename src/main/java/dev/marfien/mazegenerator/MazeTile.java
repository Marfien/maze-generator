package dev.marfien.mazegenerator;

public interface MazeTile {

    boolean[] getOpenEdges();

    boolean isOpen(int edge);

}
