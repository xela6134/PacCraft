package pacman.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class SandTile extends Tile {

    public SandTile(boolean overlappable) {
        super(overlappable);
        setTileImage();
    }

    @Override
    public void setTileImage() {
        try {
            tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/sandtile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
