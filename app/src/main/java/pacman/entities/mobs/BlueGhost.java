package pacman.entities.mobs;

import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.components.GameMap;
import pacman.entities.Player;
import pacman.entities.mobs.movementState.BlueGhostMovement;

public class BlueGhost extends Mob {
    public static final int BLUEGHOST_SPEED = 2;
    public static final int BLUEGHOST_HEALTH = 3;
    public static final int BLUEGHOST_DAMAGE = 1;

    public BlueGhost(int x, int y, int speed, Direction direction, GameMap map) {
        super(x, y, speed, direction, map, BLUEGHOST_HEALTH, BLUEGHOST_DAMAGE);
        setMovement(new BlueGhostMovement());
        setMobImage();
    }

    @Override
    public void setMobImage() {
        try {
            mobImageLeft = ImageIO.read(getClass().getResourceAsStream("/mobs/blueghost_left.png"));
            mobImageRight = ImageIO.read(getClass().getResourceAsStream("/mobs/blueghost_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInteract(Player player) {
        if (!player.getPosition().equals(getPosition())) return;
    }
    
}
