package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Tile;

public class Pawn extends SpecialPiece{
    public Pawn(PlayerColor color) {
        super(color, PieceType.PAWN);
    }

    public void move(Tile begin, Tile end){}
}
