package pacman.entities.mobs.movementState;

import java.util.Random;

import pacman.components.GamePanel;
import pacman.entities.Entity.Direction;
import pacman.entities.mobs.Mob;

public class RedGhostMovement implements Movement {
    private int length;
    private int ticks = 0;

    @Override
    public void update(Mob mob) {
        if (mob.getDirection() == Direction.UP) {
            if (mob.getWorldY() - mob.getSpeed() < mob.nearest32Y()) {
                ticks++;
            }
            if (checkDestinationBlocked(mob, Direction.UP) || ticks == length) {
                mob.moveUp(true);
                changeDirection(mob);
                updateLength();
            } else {
                mob.moveUp(false);
            }
        } else if (mob.getDirection() == Direction.DOWN) {
            if (mob.getWorldY() + mob.getSpeed() > mob.nearest32Y()) {
                ticks++;
            }
            if (checkDestinationBlocked(mob, Direction.DOWN) || ticks == length) {
                mob.moveDown(true);
                changeDirection(mob);
                updateLength();
            } else {
                mob.moveDown(false);
            }
        } else if (mob.getDirection() == Direction.LEFT) {
            if (mob.getWorldX() - mob.getSpeed() < mob.nearest32X()) {
                ticks++;
            }
            if (checkDestinationBlocked(mob, Direction.LEFT) || ticks == length) {
                mob.moveLeft(true);
                changeDirection(mob);
                updateLength();
            } else {
                mob.moveLeft(false);
            }
        } else if (mob.getDirection() == Direction.RIGHT) {
            if (mob.getWorldX() + mob.getSpeed() > mob.nearest32X()) {
                ticks++;
            }
            if (checkDestinationBlocked(mob, Direction.RIGHT) || ticks == length) {
                mob.moveRight(true);
                changeDirection(mob);
                updateLength();
            } else {
                mob.moveRight(false);
            }
        }
    }

    private void changeDirection(Mob mob) {
        int direction = new Random().nextInt(4);
        while (true) {
            if (direction % 4 == 0) {
                if (!checkDestinationBlocked(mob, Direction.UP)) {
                    mob.setDirection(Direction.UP);
                    break;
                }
            } else if (direction % 4 == 1) {
                if (!checkDestinationBlocked(mob, Direction.DOWN)) {
                    mob.setDirection(Direction.DOWN);
                    break;
                }
            } else if (direction % 4 == 2) {
                if (!checkDestinationBlocked(mob, Direction.LEFT)) {
                    mob.setDirection(Direction.LEFT);
                    break;
                }
            } else if (direction % 4 == 3) {
                if (!checkDestinationBlocked(mob, Direction.RIGHT)) {
                    mob.setDirection(Direction.RIGHT);
                    break;
                }
            }
            direction++;
        }
    }

    private void updateLength() {
        length = new Random().nextInt(9) + 4;
        ticks = 0;
    }

    private boolean checkDestinationBlocked(Mob mob, Direction direction) {
        if (direction == Direction.UP) {
            if (mob.getMapY() <= 0) {
                return true;
            } else if (!mob.getMapTile(mob.getMapX(), mob.getMapY() - 1).getOverlappable()) {
                return true;
            } else {
                return false;
            }
        } else if (direction == Direction.DOWN) {
            if (mob.getMapY() >= GamePanel.HEIGHT_NUM - 1) {
                return true;
            } else if (!mob.getMapTile(mob.getMapX(), mob.getMapY() + 1).getOverlappable()) {
                return true;
            } else {
                return false;
            }
        } else if (direction == Direction.LEFT) {
            if (mob.getMapX() <= 0) {
                return true;
            } else if (!mob.getMapTile(mob.getMapX() - 1, mob.getMapY()).getOverlappable()) {
                return true;
            } else {
                return false;
            }
        } else if (direction == Direction.RIGHT) {
            if (mob.getMapX() >= GamePanel.WIDTH_NUM - 1) {
                return true;
            } else if (!mob.getMapTile(mob.getMapX() + 1, mob.getMapY()).getOverlappable()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
