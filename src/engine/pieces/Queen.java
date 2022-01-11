package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Movement;
import engine.moves.OrthogonalMove;

/**
 * Implémentation de la classe Piece pour la reine
 *
 * @author Géraud Silvestri
 * @author Loïc Rosset
 */
public class Queen extends Piece{

    /**
     * crée une reine avec ses différents déplacements possible
     * @param color couleur de la pièce
     * @param board échiquier sur lequel est la pièce
     */
    public Queen(PlayerColor color, Board board) {
        super(color, PieceType.QUEEN, board,  new Movement[] {
                new OrthogonalMove(1,1),
                new OrthogonalMove(1,-1),
                new OrthogonalMove(-1,1),
                new OrthogonalMove(-1,-1),
                new OrthogonalMove(0, 1),
                new OrthogonalMove(1, 0),
                new OrthogonalMove(0, -1),
                new OrthogonalMove(-1, 0)
        });
    }
}
