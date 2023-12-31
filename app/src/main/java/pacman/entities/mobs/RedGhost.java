package pacman.entities.mobs;

import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.components.GameMap;
import pacman.entities.Player;
import pacman.entities.mobs.movementState.RedGhostMovement;

public class RedGhost extends Mob {
    public static final int REDGHOST_SPEED = 3;
    public static final int REDGHOST_HEALTH = 5;
    public static final int REDGHOST_DAMAGE = 3;

    public RedGhost(int x, int y, int speed, Direction direction, GameMap map) {
        super(x, y, speed, direction, map, REDGHOST_HEALTH, REDGHOST_DAMAGE);
        setMovement(new RedGhostMovement());
        setMobImage();
    }

    @Override
    public void setMobImage() {
        try {
            mobImageLeft = ImageIO.read(getClass().getResourceAsStream("/mobs/redghost_left.png"));
            mobImageRight = ImageIO.read(getClass().getResourceAsStream("/mobs/redghost_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInteract(Player player) {
        if (!player.getPosition().equals(getPosition())) return;
    }
    
}
