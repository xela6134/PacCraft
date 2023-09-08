package pacman.entities.mobs.movementState;

import pacman.components.Position;

public class GraphNode {
    private Position position;
    private GraphNode parent;
    private int fCost;
    private int gCost;
    private int hCost;

    public GraphNode(Position position, GraphNode parent, int fCost, int gCost, int hCost) {
        this.position = position;
        this.parent = parent;
        this.fCost = fCost;
        this.gCost = gCost;
        this.hCost = hCost;
    }

    public Position getPosition() {
        return position;
    }

    public GraphNode getParent() {
        return parent;
    }

    public int getF() {
        return fCost;
    }

    public int getG() {
        return gCost;
    }

    public int getH() {
        return hCost;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setParent(GraphNode parent) {
        this.parent = parent;
    }

    public void setF(int fCost) {
        this.fCost = fCost;
    }

    public void setG(int gCost) {
        this.gCost = gCost;
    }

    public void setH(int hCost) {
        this.hCost = hCost;
    }
}
