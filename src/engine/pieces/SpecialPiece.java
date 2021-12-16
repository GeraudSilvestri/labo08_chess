package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;

public abstract class SpecialPiece extends Piece{
    boolean hasMoved = false;

    public SpecialPiece(PlayerColor color, PieceType type) {
        super(color, type);
    }
}
