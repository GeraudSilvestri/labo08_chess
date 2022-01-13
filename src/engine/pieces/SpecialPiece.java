package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Movement;

/**
 * Implémentation des pièces spéciales
 * Les pièces spécial sont celle dont il est utile de savoir si elles ont déjà bougée
 *
 * @author Géraud Silvestri
 * @author Loïc Rosset
 */
public abstract class SpecialPiece extends Piece {
    boolean hasMoved;

    /**
     * constructeur
     * @param color couleur de la pièce
     * @param type type de la pièce
     * @param board échiquier sur lequel est la pièce
     * @param moves tableau des déplacements possible pour la pièce
     */
    public SpecialPiece(PlayerColor color, PieceType type, Board board, Movement[] moves) {
        super(color, type, board, moves);
        hasMoved = false;
    }

    /**
     * indique que la pièce a bougé
     */
    public void moved(){
        hasMoved =  true;
    }

    /**
     * retourne si la pièce a déjà bougé
     * @return l'état de la pièce
     */
    public boolean getHasMoved(){
        return hasMoved;
    }

    @Override
    public boolean canMove(int fromX, int fromY, int toX, int toY){
        if(super.canMove(fromX, fromY, toX, toY)){
            hasMoved = true;
            return true;
        }

        return false;
    }

}
