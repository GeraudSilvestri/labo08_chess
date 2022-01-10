package engine.moves;

import engine.Board;

import static java.lang.Math.abs;

public class OrthogonalMove extends Movement{
    public OrthogonalMove(int offsetX, int offsetY) {
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
        boolean move = false;
        int x = startX;
        int y = startY;

        if(x != endX || y != endY) {
            while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                x += offsetX;
                y += offsetY;

                if (x == endX && y == endY) {
                    move = true;
                    break;
                }
            }
        }
        return move;
    }

    @Override
    public boolean checkCollisions(int startX, int startY, int endX, int endY, Board board) {
        int x = startX;
        int y = startY;


        if(board.at(endX,endY) != null && board.at(x,y).getColor() == board.at(endX,endY).getColor())
            return false;

        // calcul the distance between 2 tiles
        int distance = abs(startY-endY) > 0 ? abs(startY-endY) : abs(startX-endX);

        for(int i = 0; i < distance; ++i){
            x += offsetX;
            y += offsetY;


            // Vérification qu'il n'y a pas de collisions sauf à la dernière case
            if (board.at(x,y) != null && (x != endX || y != endY)) {
                return false;
            }
        }
        return true;
    }
}
