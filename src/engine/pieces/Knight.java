package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.LimitedMoves;
import engine.moves.Movement;

public class Knight extends Piece{
    public Knight(PlayerColor color, Piece[][] board) {
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
