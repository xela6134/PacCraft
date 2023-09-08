package pacman.components;

import java.util.ArrayList;
import java.util.List;

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

    public int getMapX() {
        return mapX;
    }

    public int getMapY() {
        return mapY;
    }

    public void setMapX(int mapX) {
        this.mapX = mapX;
    }

    public void setMapY(int mapY) {
        this.mapY = mapY;
    }

    public List<Position> getCardinallyAdjacentPositions() {
        List<Position> adjacentPositions = new ArrayList<>();

        if (mapX != 0) {
            adjacentPositions.add(new Position(mapX - 1, mapY));
        } if (mapY != 0) {
            adjacentPositions.add(new Position(mapX, mapY - 1));
        } if (mapX != GamePanel.WIDTH_NUM - 1) {
            adjacentPositions.add(new Position(mapX + 1, mapY));
        } if (mapY != GamePanel.HEIGHT_NUM - 1) {
            adjacentPositions.add(new Position(mapX, mapY + 1));
        }
        return adjacentPositions;
    }

    public boolean equals(Position position) {
        return mapX == position.getMapX() && mapY == position.getMapY();
    }
}
