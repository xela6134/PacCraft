package pacman.tiles;

import java.awt.image.BufferedImage;

import pacman.components.Position;
import pacman.entities.Entity;

public abstract class Tile {
    protected BufferedImage tileImage;
    private boolean overlappable;
    private Position position;

    public Tile(boolean overlappable, int mapX, int mapY) {
        this.overlappable = overlappable;
        position = new Position(mapX, mapY);
    }

    public BufferedImage getTileImage() {
        return tileImage;
    }

    public boolean getOverlappable() {
        return overlappable;
    }

    public Position getPosition() {
        return position;
    }

    public abstract void setTileImage();

    public abstract void onOverlap(Entity entity);
}
