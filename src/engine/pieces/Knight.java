package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Tile;

public class Knight extends Piece{
    public Knight(PlayerColor color) {
        super(color, PieceType.KNIGHT);
    }

    public void move(Tile begin, Tile end){}
}
