package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.LimitedMoves;
import engine.moves.Movement;

import static java.lang.Math.abs;

/**
 * Implémentation de la classe SpecialPiece pour le roi
 * Le roi est une pièce spéciale utilisée pour le roque
 *
 * @author Géraud Silvestri
 * @author Loïc Rosset
 */
public class King extends SpecialPiece{

    /**
     * crée un roi avec ses différents déplacements possible
     * @param color couleur de la pièce
     * @param board échiquier sur lequel est la pièce
     */
    public King(PlayerColor color, Board board) {
        super(color, PieceType.KING, board,  new Movement[] {
                new LimitedMoves(0,1),
                new LimitedMoves(0,-1),
                new LimitedMoves(1,0),
                new LimitedMoves(-1,0),
                new LimitedMoves(-1,-1),
                new LimitedMoves(-1,1),
                new LimitedMoves(1,-1),
                new LimitedMoves(1,1)
        });
    }

    /**
     * vérifie que le déplacement du roi est valable
     * @param fromX position X initiale
     * @param fromY position Y initiale
     * @param toX position X finale
     * @param toY position Y finale
     * @return le déplacement est-il valide
     */
    public boolean canMove(int fromX, int fromY, int toX, int toY) {

        // vérifie si un roque est faisable
        if (abs(fromX - toX) == 2 && fromY - toY == 0 && !hasMoved) {
            boolean isLeft = fromX - toX > 0;
            int rookX = isLeft ? 0 : board.getWidth() - 1;
            int newRookX = fromX + (isLeft ? -1 : 1);

            // vérifie que la tour est roquable
            if (board.at(rookX,toY) != null
                    && board.at(rookX,toY).getType() == PieceType.ROOK
                    && !((SpecialPiece) board.at(rookX, toY)).getHasMoved()) {
                if (board.at(rookX,toY).canMove(rookX, fromY, newRookX, fromY)) {
                    //vérifie que le roi ne passe pas en échecs
                    for (int i = 0; i <= 2; ++i) {
                        if (board.isCellChecked(fromX + (isLeft ? -i : i), fromY, board.at(fromX,fromY).getColor()))
                            return false;
                    }
                    // bouge la tour
                    ((SpecialPiece) board.at(rookX,toY)).moved();
                    board.move(rookX, fromY, newRookX, fromY, getColor());
                    return true;
                }
            }
        }
        return super.canMove(fromX,fromY,toX,toY, board);
    }
}
