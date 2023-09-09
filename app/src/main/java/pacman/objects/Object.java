package pacman.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pacman.components.GamePanel;
import pacman.components.Position;
import pacman.interfaces.Interactable;

public abstract class Object implements Interactable {
    protected BufferedImage objectImage;
    private Position position;
    private boolean exists = true;

    public Object(int mapX, int mapY) {
        position = new Position(mapX, mapY);
    }

    public BufferedImage getObjectImage() {
        return objectImage;
    }

    public Position getPosition() {
        return position;
    }

    public boolean getExists() {
        return exists;
    }

    public abstract void setObjectImage();

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public void draw(Graphics2D g2) {
        if (exists) {
            g2.drawImage(objectImage, 
                position.getMapX() * GamePanel.TILE_SIZE + 8, 
                position.getMapY() * GamePanel.TILE_SIZE + 8, 
                GamePanel.PIXEL_NUM, GamePanel.PIXEL_NUM, null);
        }
    }
}
