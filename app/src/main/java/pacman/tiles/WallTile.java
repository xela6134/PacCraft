package pacman.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

public class WallTile extends Tile {

    public WallTile(boolean overlappable, int mapX, int mapY) {
        super(overlappable, mapX, mapY);
        setTileImage();
    }

    @Override
    public void setTileImage() {
        try {
            tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/walltile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
