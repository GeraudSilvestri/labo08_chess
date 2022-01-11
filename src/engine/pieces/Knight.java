package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.LimitedMoves;
import engine.moves.Movement;

/**
 * Implémentation de la classe Piece pour le cavalier
 *
 * @author Géraud Silvestri
 * @author Loïc Rosset
 */
public class Knight extends Piece{

    /**
     * crée un cavalier avec ses différents déplacements possible
     * @param color couleur de la pièce
     * @param board échiquier sur lequel est la pièce
     */
    public Knight(PlayerColor color, Board board) {
        super(color, PieceType.KNIGHT, board,  new Movement[] {
                new LimitedMoves(1,2),
                new LimitedMoves(-1,2),
                new LimitedMoves(1,-2),
                new LimitedMoves(-1,-2),
                new LimitedMoves(2,1),
                new LimitedMoves(2,-1),
                new LimitedMoves(-2,1),
                new LimitedMoves(-2,-1)
        });
    }
}
