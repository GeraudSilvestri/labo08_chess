package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Movement;
import engine.moves.OrthogonalMove;

public class Bishop extends Piece{

    public Bishop(PlayerColor color, Board board) {
        super(color, PieceType.BISHOP,board,  new Movement[] {
                new OrthogonalMove(1,1),
                new OrthogonalMove(1,-1),
                new OrthogonalMove(-1,1),
                new OrthogonalMove(-1,-1)
        });
    }
}
