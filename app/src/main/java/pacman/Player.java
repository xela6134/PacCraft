package pacman;

import java.awt.Color;
import java.awt.Graphics2D;

import pacman.components.GamePanel;
import pacman.components.KeyHandler;
import pacman.entities.Entity;

public class Player extends Entity {

    public Player(int x, int y, int speed) {
        super(x, y, speed);
    }
    
    public void update(KeyHandler handler) {
        if (handler.getUpPressed()) {
            setY(getY() - getSpeed());
        } else if (handler.getDownPressed()) {
            setY(getY() + getSpeed());
        } else if (handler.getLeftPressed()) {
            setX(getX() - getSpeed());
        } else if (handler.getRightPressed()) {
            setX(getX() + getSpeed());
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(getX(), getY(), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }
}
