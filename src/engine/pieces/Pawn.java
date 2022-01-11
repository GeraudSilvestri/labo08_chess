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
        int distance = abs(toY-fromY);

        // si il n'y a pas de déplacements
        if(fromX == toX && fromY == toY){
            return false;
        }
        if(distance == 1) {
            // vérifie qu'on ne mange rien
            if (toX == fromX && board.at(toX, toY) != null)
                return false;

            // vérifie que lors d'un déplacement en diagonal, on mange une pièce via en passant ou non
            else if (toX != fromX && board.at(toX, toY) == null) {
                // check en passant
                Piece temp = board.at(toX, toY + (getColor() == PlayerColor.WHITE ? -1 : 1));
                if (temp instanceof Pawn && temp.getColor() != getColor()) {
                    return board.checkEnPassant((Pawn)temp);
                }
                else {
                    return false;
                }
            }
        }
        else if(distance == 2) {
            // mets que la pièce peut être mangée en passant
            if(!this.getHasMoved() && board.at(fromX, fromY + (toY-fromY)/2) == null){
                enPassantAble = true;
            }else{
                return false;
            }
        }

        return super.canMove(fromX, fromY, toX, toY);
    }
}
