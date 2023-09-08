package pacman.entities.mobs.movementState;

import java.util.Random;

import pacman.entities.Entity.Direction;
import pacman.entities.mobs.Mob;

public class RedGhostMovement implements Movement {
    private int length;
    private int ticks = 0;
    private MovementHelper movementHelper = new MovementHelper();

    @Override
    public void update(Mob mob) {
        if (mob.getDirection() == Direction.UP) {
            if (mob.getWorldY() - mob.getSpeed() < mob.nearest32Y()) {
                ticks++;
            }
            if (movementHelper.checkDestinationBlocked(mob, Direction.UP) || ticks == length) {
                mob.moveUp(true);
                movementHelper.changeDirection(mob);
                updateLength();
            } else {
                mob.moveUp(false);
            }
        } else if (mob.getDirection() == Direction.DOWN) {
            if (mob.getWorldY() + mob.getSpeed() > mob.nearest32Y()) {
                ticks++;
            }
            if (movementHelper.checkDestinationBlocked(mob, Direction.DOWN) || ticks == length) {
                mob.moveDown(true);
                movementHelper.changeDirection(mob);
                updateLength();
            } else {
                mob.moveDown(false);
            }
        } else if (mob.getDirection() == Direction.LEFT) {
            if (mob.getWorldX() - mob.getSpeed() < mob.nearest32X()) {
                ticks++;
            }
            if (movementHelper.checkDestinationBlocked(mob, Direction.LEFT) || ticks == length) {
                mob.moveLeft(true);
                movementHelper.changeDirection(mob);
                updateLength();
            } else {
                mob.moveLeft(false);
            }
        } else if (mob.getDirection() == Direction.RIGHT) {
            if (mob.getWorldX() + mob.getSpeed() > mob.nearest32X()) {
                ticks++;
            }
            if (movementHelper.checkDestinationBlocked(mob, Direction.RIGHT) || ticks == length) {
                mob.moveRight(true);
                movementHelper.changeDirection(mob);
                updateLength();
            } else {
                mob.moveRight(false);
            }
        }
    }

    public void updateLength() {
        length = new Random().nextInt(9) + 4;
        ticks = 0;
    }
}
