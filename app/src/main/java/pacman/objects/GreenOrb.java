package pacman.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.components.GamePanel;
import pacman.entities.Player;

/**
 * Makes player invulnerable for 7 seconds
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
            objectImage = ImageIO.read(getClass().getResourceAsStream("/objects/greenorb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void applyBuff(Player player) {
        if (ticks == 0) {
            System.out.println("GreenOrb Activated!");
            player.setGreenOrbInEffect(true);
            activated = true;
            ticks++;
        } else if (ticks < GamePanel.FPS * 7) {
            System.out.println("GreenOrb Tick: " + ticks);
            player.setGreenOrbInEffect(true);
            ticks++;
        } else if (ticks == GamePanel.FPS * 7) {
            System.out.println("GreenOrb Deactivated!");
            player.setGreenOrbInEffect(false);
            activated = false;
        } else {
            return;
        }
    }
}
