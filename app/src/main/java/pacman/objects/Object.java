package pacman.objects;

import java.awt.image.BufferedImage;

import pacman.components.Position;
import pacman.interfaces.Overlappable;

public abstract class Object implements Overlappable {
    protected BufferedImage tileImage;
    private boolean overlappable;
    private Position position;

    public Object(boolean overlappable, int mapX, int mapY) {
        this.overlappable = overlappable;
        position = new Position(mapX, mapY);
    }

    public BufferedImage getObjectImage() {
        return tileImage;
    }

    public boolean getOverlappable() {
        return overlappable;
    }

    public Position getPosition() {
        return position;
    }

    public abstract void setObjectImage();
}
