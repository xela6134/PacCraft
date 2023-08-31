package pacman.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.components.GamePanel;
import pacman.components.KeyHandler;

public class Player extends Entity {
    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public Player(int x, int y, int speed, Direction direction) {
        super(x, y, speed, direction);
        getPlayerImage();
    }
    
    public void update(KeyHandler handler) {
        if (handler.getUpPressed() && !playerIsHorizontallyBetween()) {
            setWorldY(getWorldY() - getSpeed());
            setDirection(Direction.UP);
        } else if (handler.getDownPressed() && !playerIsHorizontallyBetween()) {
            setWorldY(getWorldY() + getSpeed());
            setDirection(Direction.DOWN);
        } else if (handler.getLeftPressed() && !playerIsVerticallyBetween()) {
            setWorldX(getWorldX() - getSpeed());
            setDirection(Direction.LEFT);
        } else if (handler.getRightPressed() && !playerIsVerticallyBetween()) {
            setWorldX(getWorldX() + getSpeed());
            setDirection(Direction.RIGHT);
        } else if (playerIsHorizontallyBetween()) {
            if (getDirection() == Direction.LEFT) {
                if (getWorldX() - getSpeed() < nearest16X()) {
                    setWorldX(nearest16X());
                } else {
                    setWorldX(getWorldX() - getSpeed());
                }
            } else if (getDirection() == Direction.RIGHT) { 
                if (getWorldX() + getSpeed() > nearest16X()) {
                    setWorldX(nearest16X());
                } else {
                    setWorldX(getWorldX() + getSpeed());
                }
            }
        } else if (playerIsVerticallyBetween()) {
            if (getDirection() == Direction.UP) {
                if (getWorldY() - getSpeed() < nearest16Y()) {
                    setWorldY(nearest16Y());
                } else {
                    setWorldY(getWorldY() - getSpeed());
                }
            } else if (getDirection() == Direction.DOWN) {
                if (getWorldY() + getSpeed() > nearest16Y()) {
                    setWorldY(nearest16Y());
                } else {
                    setWorldY(getWorldY() + getSpeed());
                }
            }
        }

        updateMapCoordinates();

        System.out.println(getMapX() + " " + getMapY());

        updateSprites();
    }

    /**
     * Assume there are tiles such as
     * A B
     * C D
     * If a player is between A and B
     * playerIsHorizontallyBetween() returns true
     * If a player is between A and C
     * playerIsVerticallyBetween() returns true
     * If a player is exactly on a tile
     * playerIsOnTile() returns true
     */

    private boolean playerIsHorizontallyBetween() {
        return (getWorldX() % GamePanel.TILE_SIZE != 0);
    }

    private boolean playerIsVerticallyBetween() {
        return (getWorldY() % GamePanel.TILE_SIZE != 0);
    }

    private boolean playerIsOnTile() {
        return (!playerIsHorizontallyBetween() && !playerIsVerticallyBetween());
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/UpBig.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/UpSmall.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/DownBig.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/DownSmall.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/LeftBig.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/LeftSmall.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/RightBig.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/RightSmall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (getDirection()) {
            case UP:
                if (getSpriteNum() == 1) {
                    image = up1;
                } else if (getSpriteNum() == 2) {
                    image = up2;
                } break;
            case DOWN:
                if (getSpriteNum() == 1) {
                    image = down1;
                } else if (getSpriteNum() == 2) {
                    image = down2;
                } break;
            case LEFT:
                if (getSpriteNum() == 1) {
                    image = left1;
                } else if (getSpriteNum() == 2) {
                    image = left2;
                } break;    
            case RIGHT:
                if (getSpriteNum() == 1) {
                    image = right1;
                } else if (getSpriteNum() == 2) {
                    image = right2;
                } break;
        }
        g2.drawImage(image, getWorldX(), getWorldY(), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
