package pacman.interfaces;

import pacman.entities.Entity;

public interface Overlappable {
    public void onOverlap(Entity entity);
}
