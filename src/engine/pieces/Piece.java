package engine.pieces;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Movement;

/**
 * gère les différents types de pièces d'un jeu d'échec
 *
 * @author Géraud Silvestri
 * @author Loïc Rosset
 */
public abstract class Piece implements ChessView.UserChoice{
    private final PlayerColor color;
    private final PieceType type;
    private final Movement[] moves;
    protected Board board;

    /**
     * constructeur
     * @param color couleur de la pièce (blanc ou noir)
     * @param type type de pièce
     * @param board échiquier sur lequel est la pièce
     * @param moves tableau des mouvements possible pour une pièce
     */
    public Piece(PlayerColor color, PieceType type, Board board, Movement[] moves){
        this.color = color;
        this.type = type;
        this.board = board;
        this.moves = moves;
    }

    /**
     * retourne le nom de la pièce en String
     * @return nom de la pièce en String
     */
    @Override
    public String textValue(){
        return getClass().getSimpleName();
    }

    /**
     * retourne le nom de la pièce en String
     * @return nom de la pièce en String
     */
    public String toString(){
        return textValue();
    }

    /**
     * retourne la couleur de la pièce
     * @return couleur de la pièce
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * retourne le type de la pièce
     * @return type de la pièce
     */
    public PieceType getType() {
        return type;
    }

    /**
     * vérifie qu'une pièce peu bouger en fonction de la liste de mouvements passée
     * @param fromX position X initiale
     * @param fromY position Y initiale
     * @param toX position X finale
     * @param toY position Y finale
     * @return le mouvement est-il valide ou non
     */
    public boolean canMove(int fromX, int fromY, int toX, int toY){
        Movement valid = null;

        // Vérifie que les coordonnées entrées par l'utilisateur permettent un mouvement plausible pour une pièce donnée
        for(Movement m : moves){
            if(m.canMove(fromX, fromY, toX, toY, board)){
                valid = m;
                break;
            }
        }
        return valid != null;
    }

    public boolean canMove(int fromX, int fromY, int toX, int toY, Board board){
        return canMove(fromX, fromY, toX, toY);
    }
}
