package engine.moves;

import engine.Board;

import static java.lang.Math.abs;

/**
 *  gère les mouvements non limités
 *  les mouvements non limités sont ceux qui s'arrêtent au bord de l'échiquier
 *
 * @author Géraud Silvestri
 * @author Loïc Rosset
 */
public class OrthogonalMove extends Movement{
    public OrthogonalMove(int offsetX, int offsetY) {
        super(offsetX, offsetY);
    }

    /**
     * vérifie qu'un déplacement est valide
     * les vérifications sont faites via les offsets, jusqu'au bord de l'échiquier
     * @param startX initial X position
     * @param startY initial Y position
     * @param endX final X position
     * @param endY final Y position
     * @return wether the move is valid or not
     */
    public boolean canMove(int startX, int startY, int endX, int endY, Board board){
        boolean move = false;
        int x = startX;
        int y = startY;
        int distance = abs(startX-endX) > 0 ? abs(startX-endX) : abs(startY-endY);

        if(board.at(endX, endY) != null && board.at(startX, startY).getColor() == board.at(endX, endY).getColor())
            return false;

        for(int i = 0; i < distance; ++i) {
            x += offsetX;
            y += offsetY;

            if (x == endX && y == endY) {
                move = true;
                break;
            }

            if(x < 0 || x > 7 || y < 0 || y > 7 || board.at(x,y) != null){
                break;
            }
        }

        return move;
    }

    /**
     * vérifie que le chemin emprunté par une pièce lors d'un déplacement est vide
     * @param startX position X de départ
     * @param startY position Y de départ
     * @param endX position X d'arrivée
     * @param endY position Y d'arrivée
     * @param board échiquier sur lequel la pièce se déplace
     * @return le passage est-il libre ou non
     */
    /*@Override
    public boolean checkCollisions(int startX, int startY, int endX, int endY, Board board) {
        int x = startX;
        int y = startY;

        // la case d'arrivée à une pièce de la même couleur
        if(board.at(endX,endY) != null && board.at(x,y).getColor() == board.at(endX,endY).getColor())
            return false;

        // calcul la distance entre 2 cases
        int distance = abs(startY-endY) > 0 ? abs(startY-endY) : abs(startX-endX);

        for(int i = 0; i < distance; ++i){
            x += offsetX;
            y += offsetY;

            // Vérification qu'il n'y a pas de collisions sauf à la dernière case
            if (board.at(x,y) != null) {
                return false;
            }
        }
        return true;
    }*/
}
