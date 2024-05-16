package dev.marfien.mazegenerator.impl.grid;

public enum GridTileFace {

    // Order is important
    IN_FRONT(0, 1),
    LEFT(-1, 0),
    BEHIND(0, -1),
    RIGHT(1, 0);

    private final int dX;
    private final int dY;

    GridTileFace(final int dX, final int dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public int getdX() {
        return dX;
    }

    public int getdY() {
        return dY;
    }

    public GridTileFace opposite() {
        return switch (this) {
            case IN_FRONT -> BEHIND;
            case BEHIND -> IN_FRONT;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }

}
