package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Tile;

public class King extends SpecialPiece{
    public King(PlayerColor color) {
        super(color, PieceType.KING);
    }

    public void move(Tile begin, Tile end){}
}
