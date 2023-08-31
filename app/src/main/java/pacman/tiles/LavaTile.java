package pacman.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class LavaTile extends Tile {

    public LavaTile(boolean overlappable) {
        super(overlappable);
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
    
}