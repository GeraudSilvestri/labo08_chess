package engine;

import chess.ChessView;
import chess.PlayerColor;
import engine.pieces.*;

import javax.swing.text.View;

public class Board {
    private Piece[][] board;
    private int width;
    private ChessView view;

    public Board(int width, ChessView view){
        this.width = width;
        view = view;
    }

    /**
     * crée les pièces au lancement d'une partie
     */
    public void initBoard(){
        board = new Piece[width][width];
        PlayerColor color = PlayerColor.WHITE;

        //création des pièces
        for(int i = 0; i < 2; ++i) {
            board[0][i*(width-1)] = new Rook(color, board);
            board[1][i*(width-1)] = new Knight(color, board);
            board[2][i*(width-1)] = new Bishop(color, board);
            // création des rois/reines
            board[3][i*(width-1)] = new Queen(color, board);
            board[4][i*(width-1)] = new King(color, board);
            board[5][i*(width-1)] = new Bishop(color, board);
            board[6][i*(width-1)] = new Knight(color, board);
            board[7][i*(width-1)] = new Rook(color, board);
            color = PlayerColor.BLACK;
        }

        color = PlayerColor.WHITE;
        for(int j = 1; j < width; j+=5) {
            for (int i = 0; i < width; ++i) {
                board[i][j] = new Pawn(color, board);
            }
            color = PlayerColor.BLACK;
        }
    }

    public void drawBoard(){
        for(int i = 0; i < width; ++i){
            for(int j = 0; j < width; ++j){
                if(board[i][j] == null){
                    view.removePiece(i,j);
                }else{
                    view.putPiece(board[i][j].getType(),board[i][j].getColor(),i,j);
                }
            }
        }
    }

    public boolean move(int fromX, int fromY, int toX, int toY, int turn) {
        if(board[fromX][fromY] != null){
            if(board[fromX][fromY].canMove(fromX, fromY, toX, toY)){
                turn++;

                if (board[fromX][fromY] instanceof SpecialPiece && ((SpecialPiece) board[fromX][fromY]).getHasMoved() == 0)
                    ((SpecialPiece) board[fromX][fromY]).moved(turn);


                board[toX][toY] = board[fromX][fromY];
                board[fromX][fromY] = null;

                if(toY == (board[toX][toY].getColor() == PlayerColor.WHITE ? width - 1 : 0 )){
                    if(board[toX][toY] instanceof Pawn){
                        System.out.println("Choisissez entre un cavalier, un fou ou une dame.");
                    }
                }

                drawBoard();
                return true;
            }
        }
        return false;
    }
}