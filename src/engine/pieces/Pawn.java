package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.LimitedMoves;
import engine.moves.Movement;
import engine.moves.OrthogonalMove;

import static java.lang.Math.abs;

public class Pawn extends SpecialPiece{
    public Pawn(PlayerColor color, Board board) {
            super(color, PieceType.PAWN, board, color == PlayerColor.WHITE ?
                    new Movement[]{
                            new LimitedMoves(0, 1),
                            new LimitedMoves(1, 1),
                            new LimitedMoves(-1, 1),
                            new LimitedMoves(0, 2)
                    } :
                    new Movement[]{
                            new LimitedMoves(0, -1),
                            new LimitedMoves(1, -1),
                            new LimitedMoves(-1, -1),
                            new LimitedMoves(0, -2)
            });

    }

    /**
     * check if a move is valid, if it is proceeds to move the piece
     * it isn't a "normal" move as it is limited in range
     * @param fromX starting X position
     * @param fromY starting Y position
     * @param toX final X position
     * @param toY final Y position
     */
    @Override
    public boolean canMove(int fromX, int fromY, int toX, int toY) {
        if(abs(toY-fromY) == 1 || this.getHasMoved() == 0 && abs(toY-fromY) == 2){
            // check if diagonal without adversary
            if(abs(toX-fromX) == 1){
                if(board.at(toX, toY) == null)
                    return false;
            }else{
                // check if forward with adversary
                if(board.at(toX, toY) != null)
                    return false;
            }
            if(abs(toY-fromY) == 2 && board.at(fromX, fromY + (toY-fromY)/2) != null)
                return false;

            return super.canMove(fromX, fromY, toX, toY);
        }
        return false;
    }
}
