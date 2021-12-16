package engine;

import engine.pieces.Piece;

public class Tile {
    Piece piece = null;
    int x = 0;
    int y = 0;

    public Tile(int x, int y, Piece piece){
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public void setPiece(Piece p){
        this.piece = p;
    }
}
