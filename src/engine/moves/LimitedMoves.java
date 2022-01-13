package engine.moves;

import engine.Board;

/**
 * gère les mouvements limités
 * Mouvement limités : mouvement non infini tel ceux du roi, pion et le cavalier
 *
 * les mouvements limités n'ont pas besoin de check de collisions, soit c'est un cavalier, soit
 * c'est une pièce se déplaçant que de 1 et par conséquent mangeant la pièce finale
 *
 * @author Géraud Silvestri
 * @author Loïc Rosset
 */
public class LimitedMoves extends Movement{
    public LimitedMoves(int offsetX, int offsetY) {
        super(offsetX, offsetY);
    }

    /**
     * vérifie qu'un déplacement est valide en fonction de l'offset spécifié dans le constructeur
     * @param startX position X initial
     * @param startY position Y initial
     * @param endX position X final
     * @param endY position Y final
     * @return le déplacement est-il valide
     */
    public boolean canMove(int startX, int startY, int endX, int endY, Board board){
        int x = startX;
        int y = startY;

        if(board.at(endX, endY) != null && board.at(startX, startY).getColor() == board.at(endX, endY).getColor())
            return false;

        x += offsetX;
        y += offsetY;

        return x == endX && y == endY;
    }
}
