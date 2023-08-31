package pacman.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class GrassTile extends Tile {

    public GrassTile(boolean overlappable, int mapX, int mapY) {
        super(overlappable, mapX, mapY);
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
