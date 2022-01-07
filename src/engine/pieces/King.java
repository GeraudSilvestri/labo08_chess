package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.LimitedMoves;
import engine.moves.Movement;
import engine.moves.OrthogonalMove;

import static java.lang.Math.abs;

public class King extends SpecialPiece{
    public King(PlayerColor color, Piece[][] board) {
        super(color, PieceType.KING, board,  new Movement[] {
                new LimitedMoves(0,1),
                new LimitedMoves(0,-1),
                new LimitedMoves(-1,0),
                new LimitedMoves(-1,0),
                new LimitedMoves(-1,-1),
                new LimitedMoves(-1,1),
                new LimitedMoves(1,-1),
                new LimitedMoves(1,1)
        });
    }

    public boolean canMove(int fromX, int fromY, int toX, int toY) {
        if(abs(toY-fromY) <= 1 || abs(toX-fromY) <= 1 ){
            return super.canMove(fromX, fromY, toX, toY);
        }
        return false;
    }
}
