package pacman.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.entities.Entity;

public class WaterTile extends Tile {

    public WaterTile(boolean overlappable, int mapX, int mapY) {
        super(overlappable, mapX, mapY);
        setTileImage();
    }

    @Override
    public void setTileImage() {
        try {
            tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/watertile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOverlap(Entity entity) {
        entity.setSpeed(entity.getDefaultSpeed() - 1);
    }
    
}
