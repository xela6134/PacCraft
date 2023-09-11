package pacman.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.components.GamePanel;
import pacman.entities.Player;

/**
 * Dramatically increases movement speed for 7 seconds
 */
public class BlueOrb extends Object {
    private int ticks = 0;
    private boolean activated = false;
    private int limit = GamePanel.FPS * 7;

    public BlueOrb(int mapX, int mapY) {
        super(mapX, mapY);
        setObjectImage();
    }

    @Override
    public void onInteract(Player player) {
        if (player.getPosition().equals(getPosition()) || activated) {
            setExists(false);
            applyBuff(player);
        }
    }

    @Override
    public void setObjectImage() {
        try {
            objectImage = ImageIO.read(getClass().getResourceAsStream("/objects/blueorb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void applyBuff(Player player) {
        if (ticks == 0) {
            player.setSpeed(Player.DEFAULT_PLAYER_SPEED + 2);
            player.setBlueOrbInEffect(true);
            activated = true;
            ticks++;
        } else if (ticks < limit) {
            player.setSpeed(Player.DEFAULT_PLAYER_SPEED + 2);
            ticks++;
        } else if (ticks == limit) {
            player.setSpeed(Player.DEFAULT_PLAYER_SPEED);
            player.setBlueOrbInEffect(false);
            activated = false;
        } else {
            return;
        }
    }
}
