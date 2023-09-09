package pacman.entities.mobs.movementState;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import pacman.components.GameMap;
import pacman.components.GamePanel;
import pacman.components.Position;
import pacman.entities.Entity.Direction;
import pacman.entities.mobs.Mob;

public class MovementHelper {

    public void changeDirection(Mob mob) {
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

    public boolean checkDestinationBlocked(Mob mob, Direction direction) {
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

    public Position getNextAstarPosition(Position src, Position dest, GameMap map) {
        Comparator<GraphNode> compareByF = Comparator.comparingInt(GraphNode::getF);
        List<GraphNode> closed = new ArrayList<GraphNode>();
        PriorityQueue<GraphNode> open = new PriorityQueue<GraphNode>(compareByF);

        GraphNode startNode = new GraphNode(src, null, 0, 0, 0);
        open.add(startNode);

        GraphNode currentNode;
        while (!open.isEmpty()) {
            currentNode = open.poll();
            closed.add(currentNode);

            if (currentNode.getPosition().equals(dest)) {
                return getNextPosition(currentNode, src);
            }
            
            List<Position> neighbours = currentNode.getPosition().getCardinallyAdjacentPositions();
            for (Position neighbour : neighbours) {

                if (!map.getTile(neighbour.getMapX(), neighbour.getMapY()).getOverlappable()) continue;

                if (checkNeighbourExists(open, closed, neighbour, src)) {
                    continue;
                }



                int gCost = Math.abs(neighbour.getMapX() - src.getMapX()) + Math.abs(neighbour.getMapY() - src.getMapY());
                int hCost = Math.abs(neighbour.getMapX() - dest.getMapX()) + Math.abs(neighbour.getMapY() - dest.getMapY());
                int fCost = gCost + hCost;
                GraphNode newNode = new GraphNode(neighbour, currentNode, fCost, gCost, hCost);
            
                
                open.add(newNode);
            }
        }

        return null;
    }

    private boolean checkNeighbourExists(PriorityQueue<GraphNode> open, List<GraphNode> closed, Position wantedNode, Position src) {
        if (wantedNode.equals(src)) {
            return true;
        }
        
        for (GraphNode node : open) {
            if (node.getPosition().equals(wantedNode)) {
                return true;
            }
        }

        for (GraphNode node : closed) {
            if (node.getPosition().equals(wantedNode)) {
                return true;
            }
        }

        return false;
    }

    private Position getNextPosition(GraphNode node, Position src) {
        GraphNode curr = node;
        
        if (node.getParent() == null) return node.getPosition();
        
        while (!curr.getParent().getPosition().equals(src)) {
            curr = curr.getParent();
            if (curr.getParent() == null) return curr.getPosition();
        }
        return curr.getPosition();
    }
}
