package engine.pieces;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Movement;
import engine.moves.OrthogonalMove;

public abstract class Piece implements ChessView.UserChoice{
    private final PlayerColor color;
    private final PieceType type;
    private final Movement[] moves;
    protected Board board;

    public Piece(PlayerColor color, PieceType type, Board board, Movement[] moves){
        this.color = color;
        this.type = type;
        this.board = board;
        this.moves = moves;
    }

    @Override
    public String textValue(){
        return getClass().getSimpleName();
    }

    public String toString(){
        return textValue();
    }

    public PlayerColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public boolean canMove(int fromX, int fromY, int toX, int toY){
        Movement valid = null;

        // Vérifie que les coordonnées entrées par l'utilisateur permettent un mouvement plausible pour une pièce donnée
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
