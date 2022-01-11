package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Movement;
import engine.moves.OrthogonalMove;

/**
 * Implémentation de la classe Piece pour le fou
 *
 * @author Géraud Silvestri
 * @author Loïc Rosset
 */
public class Bishop extends Piece{

    /**
     * crée un fou avec ses différents déplacements possible
     * @param color couleur de la pièce
     * @param board échiquier sur lequel est la pièce
     */
    public Bishop(PlayerColor color, Board board) {
        super(color, PieceType.BISHOP,board,  new Movement[] {
                new OrthogonalMove(1,1),
                new OrthogonalMove(1,-1),
                new OrthogonalMove(-1,1),
                new OrthogonalMove(-1,-1)
        });
    }
}
