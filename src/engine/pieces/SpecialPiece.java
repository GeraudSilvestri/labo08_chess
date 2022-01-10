package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Movement;

public abstract class SpecialPiece extends Piece{
    int hasMoved;

    public SpecialPiece(PlayerColor color, PieceType type, Board board, Movement[] moves) {
        super(color, type, board, moves);
        hasMoved = 0;
    }

    public void moved(int value){
        hasMoved =  value;
    }

    public int getHasMoved(){
        return hasMoved;
    }
}
