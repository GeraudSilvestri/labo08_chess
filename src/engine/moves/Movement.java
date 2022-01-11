package engine.moves;

import engine.Board;

/**
 * gère les déplacements possibles des pièces
 *
 * @author Géraud Silvestri
 * @author Loïc Rosset
 */
public abstract class Movement {
    protected int offsetX, offsetY;

    /**
     * constructeur
     * un mouvement rectiligne vertical a un offset X de 0 et un offset Y de 1 ou -1
     * un mouvement diagonal vers en haut à gauche à une offset X de -1 et un offset Y de 1     *
     * @param offsetX offset de X
     * @param offsetY offset de Y
     */
    public Movement(int offsetX, int offsetY){this.offsetX = offsetX; this.offsetY = offsetY;}

    /**
     * vérifie qu'un déplacement est valide
     * @param startX position X de départ
     * @param startY position Y de départ
     * @param endX position X d'arrivée
     * @param endY position Y d'arrivée
     * @return le déplacement est-il valide
     */
    public abstract boolean canMove(int startX, int startY, int endX, int endY);

    /**
     * vérifie que les cases empruntées par un déplacement soient libres
     * @param startX position X de départ
     * @param startY position Y de départ
     * @param endX position X d'arrivée
     * @param endY position Y d'arrivée
     * @param board l'échiquier sur lequel la pièce se déplace
     * @return le passage est-il vide ou non
     */
    public boolean checkCollisions(int startX, int startY, int endX, int endY, Board board){
        return true;
    }
}
