package pacman.tiles;

import java.awt.image.BufferedImage;

public abstract class Tile {
    protected BufferedImage tileImage;
    private boolean overlappable;

    public Tile(boolean overlappable) {
        this.overlappable = overlappable;
    }

    public BufferedImage getTileImage() {
        return tileImage;
    }

    public boolean getOverlappable() {
        return overlappable;
    }

    public abstract void setTileImage();
}
