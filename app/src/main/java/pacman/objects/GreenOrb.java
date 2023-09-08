package pacman.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.entities.Entity;
import pacman.entities.Player;

public class GreenOrb extends Object {

    public GreenOrb(boolean overlappable, int mapX, int mapY) {
        super(overlappable, mapX, mapY);
        setObjectImage();
    }

    @Override
    public void onOverlap(Entity entity) {
        if (!(entity instanceof Player)) return;
    }

    @Override
    public void setObjectImage() {
        try {
            tileImage = ImageIO.read(getClass().getResourceAsStream("/objects/blueorb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
