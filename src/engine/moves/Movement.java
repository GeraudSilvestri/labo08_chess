package engine.moves;

import engine.Board;

public abstract class Movement {
    protected int offsetX, offsetY;
    public Movement(int offsetX, int offsetY){this.offsetX = offsetX; this.offsetY = offsetY;}
    public abstract boolean canMove(int startX, int startY, int endX, int endY);
    public boolean checkCollisions(int startX, int startY, int endX, int endY, Board board){
        return true;
    }
}
