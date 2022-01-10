package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.LimitedMoves;
import engine.moves.Movement;

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

        if(fromX == toX && fromY == toY)
            return false;
        if (abs(fromX - toX) == 2 && fromY - toY == 0) {
            boolean isLeft = fromX - toX > 0;
            int rookX = isLeft ? 0 : board.length - 1;
            int newRookX = fromX + (isLeft ? -1 : 1);

            // check rook is castlingable
            if (board[rookX][toY] != null
                    && board[rookX][toY].getType() == PieceType.ROOK
                    && ((SpecialPiece) board[rookX][toY]).getHasMoved() == 0) {
                if (board[rookX][toY].canMove(rookX, fromY, newRookX, fromY)) {
                    ((SpecialPiece) board[rookX][toY]).moved(1);
                    board[newRookX][toY] = board[rookX][toY];
                    board[rookX][toY] = null;
                    return true;
                }
            }
        }
        return super.canMove(fromX,fromY,toX,toY);
    }
}
