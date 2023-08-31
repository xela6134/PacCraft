package pacman.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.entities.Entity;

public class SandTile extends Tile {

    public SandTile(boolean overlappable, int mapX, int mapY) {
        super(overlappable, mapX, mapY);
        setTileImage();
    }

    @Override
    public void setTileImage() {
        try {
            tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/sandtile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOverlap(Entity entity) {
        entity.setSpeed(entity.getDefaultSpeed());
    }
    
}
