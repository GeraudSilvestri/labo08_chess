package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Tile;

public class Rook extends SpecialPiece{

    public Rook(PlayerColor color) {
        super(color, PieceType.ROOK);
    }

    public void move(Tile begin, Tile end){}
}
