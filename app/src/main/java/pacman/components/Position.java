package pacman.components;

/**
 * Unlike the position set in Entity, (worldX, worldY)
 * which is used for the X and Y coordinates for pixel drawing
 * these are the 'coordinates' of the location of every entity.
 */
public class Position {
    private int mapX;
    private int mapY;

    public Position(int mapX, int mapY) {
        this.mapX = mapX;
        this.mapY = mapY;
    }

    public int getWorldX() {
        return mapX;
    }

    public int getWorldY() {
        return mapY;
    }

    public void setWorldX(int mapX) {
        this.mapX = mapX;
    }

    public void setWorldY(int mapY) {
        this.mapY = mapY;
    }
}
