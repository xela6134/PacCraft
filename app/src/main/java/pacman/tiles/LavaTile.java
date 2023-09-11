package pacman.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.entities.Entity;
import pacman.entities.Player;

public class LavaTile extends Tile {

    public LavaTile(boolean overlappable, int mapX, int mapY) {
        super(overlappable, mapX, mapY);
        setTileImage();
    }

    @Override
    public void setTileImage() {
        try {
            tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/lavatile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOverlap(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (player.getBlueOrbInEffect()) {
                player.setSpeed(player.getDefaultSpeed());
            } else {
                player.setAlive(false);
            }
        } else {
            entity.setSpeed(entity.getDefaultSpeed());
        }
    }
    
}
