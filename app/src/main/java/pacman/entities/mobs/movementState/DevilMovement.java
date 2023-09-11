package pacman.entities.mobs.movementState;

import pacman.components.GamePanel;
import pacman.components.Position;
import pacman.entities.mobs.Mob;

public class DevilMovement implements Movement {
    private MovementHelper helper = new MovementHelper();
    private int ticks = 0;

    @Override
    public void update(Mob mob) {
        if (ticks < 20) {
            ticks++;
            return;
        }

        Position nextDestination = helper.getNextAstarPosition(mob.getPosition(), mob.getPlayer().getPosition(), mob.getGameMap());
        mob.setWorldX(nextDestination.getMapX() * GamePanel.TILE_SIZE);
        mob.setMapX(nextDestination.getMapX());
        mob.setWorldY(nextDestination.getMapY() * GamePanel.TILE_SIZE);
        mob.setMapY(nextDestination.getMapY());
        ticks = 0;
    }
}
