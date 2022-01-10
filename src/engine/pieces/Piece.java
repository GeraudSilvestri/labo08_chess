package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.Movement;
import engine.moves.OrthogonalMove;

public abstract class Piece {
    private final PlayerColor color;
    private final PieceType type;
    private Movement[] moves;
    protected Piece[][] board;

    public Piece(PlayerColor color, PieceType type, Piece[][] board, Movement[] moves){
        this.color = color;
        this.type = type;
        this.board = board;
        this.moves = moves;
    }

    public PlayerColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public boolean canMove(int fromX, int fromY, int toX, int toY){
        Movement valid = null;
        for(Movement m : moves){
            if(m.canMove(fromX, fromY, toX, toY)){
                valid = m;
                break;
            }
        }
        if(valid == null)
            return false;

        if(valid instanceof OrthogonalMove){
            return valid.checkCollisions(fromX, fromY, toX, toY, board);
        }
        return true;
    }
}
