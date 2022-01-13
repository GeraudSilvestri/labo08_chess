package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.LimitedMoves;
import engine.moves.Movement;

import static java.lang.Math.abs;

/**
 * Implémentation de la classe SpecialPiece pour le pion
 *
 * @author Géraud Silvestri
 * @author Loïc Rosset
 */
public class Pawn extends SpecialPiece{
    boolean enPassantAble;

    public boolean isEnPassantAble() {
        return enPassantAble;
    }

    /**
     * crée un pion avec ses différents déplacements possible
     * @param color couleur de la pièce
     * @param board échiquier sur lequel est la pièce
     */
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
        // check le manger en diagonal
        if(toX != fromX) {
            if (board.at(toX, toY) != null) {
                if (board.at(toX, toY).getColor() == board.at(fromX, fromY).getColor()) {
                    return false;
                }
            }else{ // check en passant
                return board.checkEnPassant(toX, toY + (fromY-toY > 0 ? 1 : -1));
            }
        }else{ // check le manger forward
            if(board.at(toX, toY) != null){
                return false;
            }
        }
        // check le déplacement initial de 2
        if(abs(toY-fromY) == 2){
            // check déplacement est valide
            if(hasMoved || board.at(toX, toY + (toY-fromY > 0 ? -1 : 1)) != null)
                return false;
            else
                enPassantAble = true;
        }

        return super.canMove(fromX, fromY, toX, toY);
    }
}
