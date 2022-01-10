package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Movement;

public abstract class SpecialPiece extends Piece{
    boolean hasMoved;

    public SpecialPiece(PlayerColor color, PieceType type, Board board, Movement[] moves) {
        super(color, type, board, moves);
        hasMoved = false;
    }

    public void moved(){
        hasMoved =  true;
    }

    public boolean getHasMoved(){
        return hasMoved;
    }
}
