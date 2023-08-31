package pacman.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class WaterTile extends Tile {

    public WaterTile(boolean overlappable) {
        super(overlappable);
        setTileImage();
    }

    @Override
    public void setTileImage() {
        try {
            tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/watertile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
