package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Movement;
import engine.moves.OrthogonalMove;

/**
 * Implémentation de la classe SpecialPiece pour la tour
 * La tour est une pièce spéciale utilisée pour le roque
 *
 * @author Géraud Silvestri
 * @author Loïc Rosset
 */
public class Rook extends SpecialPiece{

    /**
     * crée une tour avec ses différents déplacements possible
     * @param color couleur de la pièce
     * @param board échiquier sur lequel est la pièce
     */
    public Rook(PlayerColor color, Board board) {
        super(color, PieceType.ROOK, board,  new Movement[] {
                new OrthogonalMove(0,1),
                new OrthogonalMove(0,-1),
                new OrthogonalMove(1,0),
                new OrthogonalMove(-1,0)
        });
    }
}
