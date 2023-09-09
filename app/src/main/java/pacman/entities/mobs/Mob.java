package pacman.entities.mobs;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pacman.components.GameMap;
import pacman.components.GamePanel;
import pacman.entities.Entity;
import pacman.entities.mobs.movementState.Movement;
import pacman.interfaces.Interactable;

public abstract class Mob extends Entity implements Interactable {
    private Movement movement;
    protected BufferedImage mobImageLeft, mobImageRight;

    public Mob(int x, int y, int speed, Direction direction, GameMap map, int health, int damage) {
        super(x, y, speed, direction, map, health, damage);
    }
    
    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public void update() {
        movement.update(this);
        updateSprites();
    }

    public BufferedImage getMobImageLeft() {
        return this.mobImageLeft;
    }

    public BufferedImage getMobImageRight() {
        return this.mobImageRight;
    }

    public abstract void setMobImage();

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (getSpriteNum() == 1) {
            image = mobImageLeft;
        } else if (getSpriteNum() == 2) {
            image = mobImageRight;
        } 
        g2.drawImage(image, getWorldX(), getWorldY(), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
