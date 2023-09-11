package pacman.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.entities.Entity;
import pacman.entities.Player;

public class DirtTile extends Tile {

    public DirtTile(boolean overlappable, int mapX, int mapY) {
        super(overlappable, mapX, mapY);
        setTileImage();
    }

    @Override
    public void setTileImage() {
        try {
            tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/dirttile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOverlap(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (!player.getBlueOrbInEffect()) {
                entity.setSpeed(entity.getDefaultSpeed());
            }
        } else {
            entity.setSpeed(entity.getDefaultSpeed());
        }
    }
}
