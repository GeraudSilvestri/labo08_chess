package engine.moves;

import engine.pieces.Piece;

import static java.lang.Math.abs;

public class LimitedMoves extends Movement{
    public LimitedMoves(int offsetX, int offsetY) {
        super(offsetX, offsetY);
    }

    /**
     * check if a move is valid according to the offset specified in the constructor
     * @param startX initial X position
     * @param startY initial Y position
     * @param endX final X position
     * @param endY final Y position
     * @return wether the move is valid or not
     */
    public boolean canMove(int startX, int startY, int endX, int endY){
        int x = startX;
        int y = startY;

        if(x != endX || y != endY) {
            x += offsetX;
            y += offsetY;
        }
        return x == endX && y == endY;
    }
}
