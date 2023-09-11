package pacman.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import pacman.components.GameMap;
import pacman.components.GamePanel;
import pacman.components.KeyHandler;
import pacman.entities.mobs.Mob;

public class Player extends Entity {
    public static final int DEFAULT_PLAYER_SPEED = 3;
    public static final int PLAYER_HEALTH = 20;
    public static final int PLAYER_DAMAGE = 5;

    private boolean greenOrbInEffect = false;
    private boolean blueOrbInEffect = false;
    private int goldNum = 0;

    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public Player(int x, int y, int speed, Direction direction, GameMap map) {
        super(x, y, speed, direction, map, PLAYER_HEALTH, PLAYER_DAMAGE);
        getPlayerImage();
        map.addPlayer(this);
    }

    public void addGold() {
        goldNum++;
    }

    public int getGold() {
        return goldNum;
    }

    public boolean getGreenOrbInEffect() {
        return greenOrbInEffect;
    }

    public boolean getBlueOrbInEffect() {
        return blueOrbInEffect;
    }

    public void setGreenOrbInEffect(boolean greenOrbInEffect) {
        this.greenOrbInEffect = greenOrbInEffect;
    }

    public void setBlueOrbInEffect(boolean blueOrbInEffect) {
        this.blueOrbInEffect = blueOrbInEffect;
    }
    
    public void update(KeyHandler handler) {
        if (handler.getUpPressed() && !playerIsHorizontallyBetween()) {
            moveUp(false);
        } else if (handler.getDownPressed() && !playerIsHorizontallyBetween()) {
            moveDown(false);
        } else if (handler.getLeftPressed() && !playerIsVerticallyBetween()) {
            moveLeft(false);
        } else if (handler.getRightPressed() && !playerIsVerticallyBetween()) {
            moveRight(false);
        } else if (playerIsHorizontallyBetween()) {
            if (getDirection() == Direction.LEFT) {
                if (getWorldX() - getSpeed() < nearest32X()) {
                    moveLeft(true);
                } else {
                    moveLeft(false);
                }
            } else if (getDirection() == Direction.RIGHT) { 
                if (getWorldX() + getSpeed() > nearest32X()) {
                    moveRight(true);
                } else {
                    moveRight(false);
                }
            }
        } else if (playerIsVerticallyBetween()) {
            if (getDirection() == Direction.UP) {
                if (getWorldY() - getSpeed() < nearest32Y()) {
                    moveUp(true);
                } else {
                    moveUp(false);
                }
            } else if (getDirection() == Direction.DOWN) {
                if (getWorldY() + getSpeed() > nearest32Y()) {
                    moveDown(true);
                } else {
                    moveDown(false);
                }
            }
        }

        updateSprites();
    }

    /*
     * Assume there are tiles such as
     * A B
     * C D
     * If a player is between A and B
     * playerIsHorizontallyBetween() returns true
     * If a player is between A and C
     * playerIsVerticallyBetween() returns true
     */

    private boolean playerIsHorizontallyBetween() {
        return (getWorldX() % GamePanel.TILE_SIZE != 0);
    }

    private boolean playerIsVerticallyBetween() {
        return (getWorldY() % GamePanel.TILE_SIZE != 0);
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

    public void battle(List<Mob> mobList) {
        for (Mob mob : mobList) {
            if (mob.getPosition().equals(getPosition())) {
                battleMob(this, mob);
            }
        }
    }

    private void battleMob(Player player, Mob mob) {
        if (player.getGreenOrbInEffect()) {
            mob.setAlive(false);
            mob.setHealth(0);
            return;
        }

        int newPlayerHealth = player.getHealth() - mob.getDamage();
        int newMobHealth = mob.getHealth() - player.getDamage();

        if (!mob.getAlive()) return;
        
        if (newPlayerHealth <= 0) {
            player.setAlive(false);
            player.setHealth(0);
        } else {
            player.setHealth(newPlayerHealth);
        }

        if (newMobHealth <= 0) {
            mob.setAlive(false);
            mob.setHealth(0);
        } else {
            mob.setHealth(newMobHealth);
        }
    }
}
