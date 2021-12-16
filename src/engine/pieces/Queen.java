package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Tile;

public class Queen extends Piece{
    public Queen(PlayerColor color) {
        super(color, PieceType.QUEEN);
    }

    public void move(Tile begin, Tile end){}
}
