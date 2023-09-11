package pacman.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.entities.Player;

public class Gold extends Object {

    public Gold(int mapX, int mapY) {
        super(mapX, mapY);
        setObjectImage();
    }

    @Override
    public void onInteract(Player player) {
        if (!player.getPosition().equals(getPosition())) return;

        if (getExists()) {
            player.addGold();
            setExists(false);
        }
    }

    @Override
    public void setObjectImage() {
        try {
            objectImage = ImageIO.read(getClass().getResourceAsStream("/objects/gold.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
