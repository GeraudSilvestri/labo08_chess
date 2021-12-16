package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Tile;

public abstract class Piece {
    private final PlayerColor color;
    private final PieceType type;

    public Piece(PlayerColor color, PieceType type){
        this.color = color;
        this.type = type;
    }

    public PlayerColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public abstract void move(int fromX, int fromY, int toX, int toY);
}
