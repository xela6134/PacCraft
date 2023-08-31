package pacman.entities;

import pacman.components.GamePanel;

public abstract class Entity {
    private int worldX, worldY;
    private int speed;
    private int spriteCounter = 0;
    private int spriteNum = 1;

    public Entity(int worldX, int worldY, int speed) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.speed = speed;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setWorldX(int x) {
        if (x < 0) {
            this.worldX = 0;
        } else if (x > GamePanel.SCREEN_WIDTH - GamePanel.TILE_SIZE) {
            this.worldX = GamePanel.SCREEN_WIDTH - GamePanel.TILE_SIZE;
        } else {
            this.worldX = x;
        }
    }

    public void setWorldY(int y) {
        if (y < 0) {
            this.worldY = 0;
        } else if (y > GamePanel.SCREEN_HEIGHT - GamePanel.TILE_SIZE) {
            this.worldY = GamePanel.SCREEN_HEIGHT - GamePanel.TILE_SIZE;
        } else {
            this.worldY = y;
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    } 

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    /**
     * Updates the animation of entities automatically
     * Default FPS is 60, and animation updates itself every 10 ticks
     */
    public void updateSprites() {
        setSpriteCounter(getSpriteCounter() + 1);
        if (getSpriteCounter() > 10) {
            if (getSpriteNum() == 1) {
                setSpriteNum(2);
            } else if (getSpriteNum() == 2) {
                setSpriteNum(1);
            }
            setSpriteCounter(0);
        }
    }
}
