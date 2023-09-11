package pacman.entities;

import pacman.components.GameMap;
import pacman.components.GamePanel;
import pacman.components.Position;
import pacman.tiles.Tile;

public abstract class Entity {
    private int worldX, worldY;
    private int speed;
    private int defaultSpeed;
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private Position position;
    private Direction direction;
    private GameMap map;
    private int health;
    private int damage;
    private boolean alive = true;

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public Entity(int x, int y, int speed, Direction direction, GameMap map, int health, int damage) {
        this.worldX = x * 32;
        this.worldY = y * 32;
        this.speed = speed;
        this.defaultSpeed = speed;
        this.position = new Position(x, y);
        this.direction = direction;
        this.map = map;
        this.health = health;
        this.damage = damage;
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

    public int getDefaultSpeed() {
        return defaultSpeed;
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

    public Tile getMapTile(int x, int y) {
        return map.getTile(x, y);
    }

    public Player getPlayer() {
        return map.getPlayer();
    }

    public GameMap getGameMap() {
        return map;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setWorldX(int x) {
        this.worldX = x;
    }

    public void setWorldY(int y) {
        this.worldY = y;
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

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Updates the animation of entities automatically.  
     * Default FPS is 60, and animation updates itself every 10 ticks
     */
    public void updateSprites() {
        setSpriteCounter(getSpriteCounter() + 1);
        if (getSpriteCounter() > 5) {
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
            if (getWorldY() % GamePanel.TILE_SIZE <= speed) {
                position.setMapY(getMapY() - 1);
                if (getMapY() <= -1) return;
                getMapTile(getMapX(), getMapY()).onOverlap(this);
            }
        } else if (direction == Direction.DOWN) {
            if (getWorldY() % GamePanel.TILE_SIZE >= GamePanel.TILE_SIZE - speed) {
                position.setMapY(getMapY() + 1);
                if (getMapY() >= GamePanel.HEIGHT_NUM) return;
                getMapTile(getMapX(), getMapY()).onOverlap(this);
            }
        } else if (direction == Direction.LEFT) {
            if (getWorldX() % GamePanel.TILE_SIZE <= speed) {
                position.setMapX(getMapX() - 1);
                if (getMapX() <= -1) return;
                getMapTile(getMapX(), getMapY()).onOverlap(this);
            }
        } else if (direction == Direction.RIGHT) {
            if (getWorldX() % GamePanel.TILE_SIZE >= GamePanel.TILE_SIZE - speed) {
                position.setMapX(getMapX() + 1);
                if (getMapX() >= GamePanel.WIDTH_NUM) return;
                getMapTile(getMapX(), getMapY()).onOverlap(this);
            }
        }
    }

    public int nearest32X() {
        if (getWorldX() % 32 == 0) return getWorldX();

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

    public int nearest32Y() {
        if (getWorldY() % 32 == 0) return getWorldY();

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

    public void moveUp(boolean toNearest) {
        setDirection(Direction.UP);

        // Wall collision
        if (checkWallCollision(getDirection())) {
            if (getWorldY() % 32 != 0) {
                setWorldY(nearest32Y());
            }
            updateMapCoordinates();
            return;
        }

        if (toNearest == false) {
            setWorldY(getWorldY() - getSpeed());
        } else {
            setWorldY(nearest32Y());
        }

        updateMapCoordinates();
    }

    public void moveDown(boolean toNearest) {
        setDirection(Direction.DOWN);

        // Wall collision
        if (checkWallCollision(getDirection())) {
            if (getWorldY() % 32 != 0) {
                setWorldY(nearest32Y());
            }
            updateMapCoordinates();
            return;
        }

        if (toNearest == false) {
            setWorldY(getWorldY() + getSpeed());
        } else {
            setWorldY(nearest32Y());
        }

        updateMapCoordinates();
    }

    public void moveLeft(boolean toNearest) {
        setDirection(Direction.LEFT);

        // Wall collision
        if (checkWallCollision(getDirection())) {
            if (getWorldX() % 32 != 0) {
                setWorldX(nearest32X());
            }
            updateMapCoordinates();
            return;
        }

        if (toNearest == false) {
            setWorldX(getWorldX() - getSpeed());
        } else {
            setWorldX(nearest32X());
        }

        updateMapCoordinates();
    }

    public void moveRight(boolean toNearest) {
        setDirection(Direction.RIGHT);

        // Wall collision
        if (checkWallCollision(getDirection())) {
            if (getWorldX() % 32 != 0) {
                setWorldX(nearest32X());
            }
            updateMapCoordinates();
            return;
        }

        if (toNearest == false) {
            setWorldX(getWorldX() + getSpeed());
        } else {
            setWorldX(nearest32X());
        }

        updateMapCoordinates();
    }

    /**
     * Checks if a certain entity is about to collide with a wall with its current direction. 
     * Returns true when entity is at the end of the map, or there is a wall in front 
     * in front of the player.
     */
    public boolean checkWallCollision(Direction direction) {
        switch (direction) {
            case UP:
                return (getMapY() <= 0) 
                || (getMapY() != 0 && !getMapTile(getMapX(), getMapY() - 1).getOverlappable());
            case DOWN:
                return (getMapY() >= GamePanel.HEIGHT_NUM - 1) 
                || (getMapY() != GamePanel.HEIGHT_NUM - 1 && !getMapTile(getMapX(), getMapY() + 1).getOverlappable());
            case LEFT:
                return (getMapX() <= 0) 
                || (getMapX() != 0 && !getMapTile(getMapX() - 1, getMapY()).getOverlappable());
            case RIGHT:
                return (getMapX() >= GamePanel.WIDTH_NUM - 1) 
                || (getMapX() != GamePanel.WIDTH_NUM - 1 && !getMapTile(getMapX() + 1, getMapY()).getOverlappable());
        }
        return false;
    }
}
