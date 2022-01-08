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
        // castling check left
        if(fromX-toX == 2 && fromY-toY == 0){
            // check rook is castlingable
            if(board[0][toY] != null
                && board[0][toY].getType() == PieceType.ROOK
                && ((SpecialPiece)board[0][toY]).getHasMoved() == 0) {
                if (board[0][toY].canMove(0, fromY, 3, fromY)) {
                    ((SpecialPiece)board[0][toY]).moved(1);
                    board[3][toY] = board[0][toY];
                    board[0][toY] = null;
                    return true;
                }
            }
            return false;
        }
        // right check castling
        else if(fromX-toX == -2 && fromY-toY == 0){
            if(board[7][toY] != null
                    && board[7][toY].getType() == PieceType.ROOK
                    && ((SpecialPiece)board[7][toY]).getHasMoved() == 0) {
                if (board[7][toY].canMove(7, fromY, 5, fromY)) {
                    ((SpecialPiece)board[7][toY]).moved(1);
                    board[5][toY] = board[7][toY];
                    board[7][toY] = null;
                    return true;
                }
            }
            return false;
        }
        else {
            if (abs(toY - fromY) <= 1 || abs(toX - fromY) <= 1) {
                return super.canMove(fromX, fromY, toX, toY);
            }
        }
        return false;
    }
}
