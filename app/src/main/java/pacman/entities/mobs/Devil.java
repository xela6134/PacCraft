package pacman.entities.mobs;

import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.components.GameMap;
import pacman.entities.Player;
import pacman.entities.mobs.movementState.DevilMovement;

public class Devil extends Mob {
    public static final int DEVIL_SPEED = 4;
    public static final int DEVIL_HEALTH = 5;
    public static final int DEVIL_DAMAGE = 15;

    public Devil(int x, int y, int speed, Direction direction, GameMap map) {
        super(x, y, speed, direction, map, DEVIL_HEALTH, DEVIL_DAMAGE);
        setMovement(new DevilMovement());
        setMobImage();
    }

    @Override
    public void setMobImage() {
        try {
            mobImageLeft = ImageIO.read(getClass().getResourceAsStream("/mobs/devil_left.png"));
            mobImageRight = ImageIO.read(getClass().getResourceAsStream("/mobs/devil_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInteract(Player player) {
        if (!player.getPosition().equals(getPosition())) return;
    }
    
}
