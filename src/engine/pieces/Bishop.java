package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Tile;

public class Bishop extends Piece{
    public Bishop(PlayerColor color) {
        super(color, PieceType.BISHOP);
    }

    public void move(Tile begin, Tile end){}
}
