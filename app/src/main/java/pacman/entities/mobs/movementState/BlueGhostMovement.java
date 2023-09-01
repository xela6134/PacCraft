package pacman.entities.mobs.movementState;

import pacman.entities.Entity.Direction;
import pacman.entities.mobs.Mob;

public class BlueGhostMovement implements Movement {
    private int ticks = 0;

    @Override
    public void update(Mob mob) {
        if (mob.getDirection() == Direction.UP) {
            if (mob.getWorldY() - mob.getSpeed() < mob.nearest32Y() && ticks < 2) {
                mob.moveUp(false);
                ticks++;
            } else if (mob.getWorldY() - mob.getSpeed() < mob.nearest32Y() && ticks == 2) {
                mob.moveUp(true);
                ticks = 0;
                mob.setDirection(Direction.LEFT);
            } else {
                mob.moveUp(false);
            }
        } else if (mob.getDirection() == Direction.DOWN) {
            if (mob.getWorldY() + mob.getSpeed() > mob.nearest32Y() && ticks < 2) {
                mob.moveDown(false);
                ticks++;
            } else if (mob.getWorldY() + mob.getSpeed() > mob.nearest32Y() && ticks == 2) {
                mob.moveDown(true);
                ticks = 0;
                mob.setDirection(Direction.RIGHT);
            } else {
                mob.moveDown(false);
            }
        } else if (mob.getDirection() == Direction.LEFT) {
            if (mob.getWorldX() - mob.getSpeed() < mob.nearest32X() && ticks < 2) {
                mob.moveLeft(false);
                ticks++;
            } else if (mob.getWorldX() - mob.getSpeed() < mob.nearest32X() && ticks == 2) {
                mob.moveLeft(true);
                ticks = 0;
                mob.setDirection(Direction.DOWN);
            } else {
                mob.moveLeft(false);
            }
        } else if (mob.getDirection() == Direction.RIGHT) {
            if (mob.getWorldX() + mob.getSpeed() > mob.nearest32X() && ticks < 2) {
                mob.moveRight(false);
                ticks++;
            } else if (mob.getWorldX() + mob.getSpeed() > mob.nearest32X() && ticks == 2) {
                mob.moveRight(true);
                ticks = 0;
                mob.setDirection(Direction.UP);
            } else {
                mob.moveRight(false);
            }
        }
    }
}
