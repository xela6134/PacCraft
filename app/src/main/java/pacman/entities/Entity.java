package pacman.entities;

import pacman.components.GamePanel;
import pacman.components.Position;

public abstract class Entity {
    private int worldX, worldY;
    private int speed;
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private Position position;
    private Direction direction;

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public Entity(int x, int y, int speed, Direction direction) {
        this.worldX = x * 32;
        this.worldY = y * 32;
        this.speed = speed;
        this.position = new Position(x, y);
        this.direction = direction;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getMapX() {
        return position.getMapX();
    }

    public int getMapY() {
        return position.getMapY();
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

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
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

    public void setMapX(int mapX) {
        position.setMapX(mapX);
    }

    public void setMapY(int mapY) {
        position.setMapY(mapY);
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

    public void setDirection(Direction direction) {
        this.direction = direction;
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

    /**
     * Sets map position right before (or when exactly arriving)
     * on the coordinate directly
     */
    public void updateMapCoordinates() {
        if (getWorldX() % 32 == 0 && getWorldY() % 32 == 0) {
            position.setMapX(getWorldX() / 32);
            position.setMapY(getWorldY() / 32);
            return;
        }

        if (direction == Direction.UP) {
            if (getWorldY() - getSpeed() <= nearest16Y()) {
                position.setMapY(getMapY() - 1);
            }
        } else if (direction == Direction.DOWN) {
            if (getWorldY() + getSpeed() >= nearest16Y()) {
                position.setMapY(getMapY() + 1);
            }
        } else if (direction == Direction.LEFT) {
            if (getWorldX() - getSpeed() <= nearest16X()) {
                position.setMapX(getMapX() - 1);
            }
        } else if (direction == Direction.RIGHT) {
            if (getWorldX() + getSpeed() >= nearest16X()) {
                position.setMapX(getMapX() + 1);
            }
        }
    }

    public int nearest16X() {
        if (getDirection() == Direction.RIGHT) {
            for (int i = getWorldX(); ; i++) {
                if (i % GamePanel.TILE_SIZE == 0) {
                    return i;
                }
            }
        } else {
            for (int i = getWorldX(); ; i--) {
                if (i % GamePanel.TILE_SIZE == 0) {
                    return i;
                }
            }
        }
    }

    public int nearest16Y() {
        if (getDirection() == Direction.DOWN) {
            for (int i = getWorldY(); ; i++) {
                if (i % GamePanel.TILE_SIZE == 0) {
                    return i;
                }
            }
        } else {
            for (int i = getWorldY(); ; i--) {
                if (i % GamePanel.TILE_SIZE == 0) {
                    return i;
                }
            }
        }
    }
}
