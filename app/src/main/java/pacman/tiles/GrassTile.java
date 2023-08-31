package pacman.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class GrassTile extends Tile {

    public GrassTile(boolean overlappable) {
        super(overlappable);
        setTileImage();
    }

    @Override
    public void setTileImage() {
        try {
            tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/grasstile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
