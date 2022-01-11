package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.LimitedMoves;
import engine.moves.Movement;
import engine.moves.OrthogonalMove;

import static java.lang.Math.abs;

public class Pawn extends SpecialPiece{
    boolean enPassantAble;

    public boolean isEnPassantAble() {
        return enPassantAble;
    }

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
        enPassantAble = false;
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
        int distance = abs(toY-fromY);

        if(fromX == toX && fromY == toY){
            return false;
        }
        if(distance == 1) {
            // check no one forward
            if (toX == fromX && board.at(toX, toY) != null)
                return false;

                // check ennemy on diagonal
            else if (toX != fromX && board.at(toX, toY) == null) {
                // check en passant
                Piece temp = board.at(toX, toY + (getColor() == PlayerColor.WHITE ? -1 : 1));
                if (temp instanceof Pawn && temp.getColor() != getColor()) {
                    return board.checkEnPassant((Pawn)temp);
                }
                // si pas en passant alors false
                else {
                    return false;
                }
            }
        }
        else if(distance == 2) {
            if(!this.getHasMoved() && board.at(fromX, fromY + (toY-fromY)/2) == null){
                enPassantAble = true;
            }else{
                return false;
            }
        }

        return super.canMove(fromX, fromY, toX, toY);
    }
}
