package pacman.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.components.GamePanel;
import pacman.entities.Player;

/**
 * Makes player invulnerable for 5 seconds
 */
public class GreenOrb extends Object {
    private int ticks = 0;
    private boolean activated = false;

    public GreenOrb(int mapX, int mapY) {
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
            player.setInvulnerable(true);
            activated = true;
            ticks++;
        } else if (ticks < GamePanel.FPS * 5) {
            ticks++;
        } else if (ticks == GamePanel.FPS) {
            player.setInvulnerable(false);
            activated = false;
        } else {
            return;
        }
    }
}
