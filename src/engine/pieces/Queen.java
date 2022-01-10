package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Movement;
import engine.moves.OrthogonalMove;

public class Queen extends Piece{
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
