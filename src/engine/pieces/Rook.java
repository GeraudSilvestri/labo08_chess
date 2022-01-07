package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.Movement;
import engine.moves.OrthogonalMove;

public class Rook extends SpecialPiece{

    public Rook(PlayerColor color, Piece[][] board) {
        super(color, PieceType.ROOK, board,  new Movement[] {
                new OrthogonalMove(0,1),
                new OrthogonalMove(0,-1),
                new OrthogonalMove(1,0),
                new OrthogonalMove(-1,0)
        });
    }
}
