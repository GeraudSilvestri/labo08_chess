package engine;
import chess.*;
import engine.pieces.*;

import java.awt.*;


public class GameManager implements ChessController {
    final int BOARD_SIZE = 8;
    Piece[][] board;
    int turn = 0;
    ChessView view;

    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
    }

    private void promotionPawn(){

    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        if(board[fromX][fromY] != null){
            if(board[fromX][fromY].canMove(fromX, fromY, toX, toY)){
                turn++;

                if (board[fromX][fromY] instanceof SpecialPiece && ((SpecialPiece) board[fromX][fromY]).getHasMoved() == 0)
                    ((SpecialPiece) board[fromX][fromY]).moved(turn);

                view.putPiece(board[fromX][fromY].getType(), board[fromX][fromY].getColor(), toX, toY);
                view.removePiece(fromX, fromY);

                board[toX][toY] = board[fromX][fromY];
                board[fromX][fromY] = null;

                if(toY == (board[toX][toY].getColor() == PlayerColor.WHITE ? BOARD_SIZE - 1 : 0 )){
                    if(board[toX][toY] instanceof Pawn){
                        System.out.println("Choisissez entre un cavalier, un fou ou une dame.");
                    }
                }

                return true;
            }
        }
        return false;
    }

    @Override
    public void newGame() {
        initBoard();
        for(int y = 0; y < BOARD_SIZE; y++){
            for(int x = 0; x < BOARD_SIZE; x++){
                if(board[x][y] != null)
                    view.putPiece(board[x][y].getType(), board[x][y].getColor(), x, y);
            }
        }
    }

    /**
     * crée les pièces au lancement d'une partie
     */
    private void initBoard(){
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        PlayerColor color = PlayerColor.WHITE;

        //création des pièces
        for(int i = 0; i < 2; ++i) {
            board[0][i*(BOARD_SIZE-1)] = new Rook(color, board);
            board[1][i*(BOARD_SIZE-1)] = new Knight(color, board);
            board[2][i*(BOARD_SIZE-1)] = new Bishop(color, board);
            // création des rois/reines
            board[3][i*(BOARD_SIZE-1)] = new Queen(color, board);
            board[4][i*(BOARD_SIZE-1)] = new King(color, board);
            board[5][i*(BOARD_SIZE-1)] = new Bishop(color, board);
            board[6][i*(BOARD_SIZE-1)] = new Knight(color, board);
            board[7][i*(BOARD_SIZE-1)] = new Rook(color, board);
            color = PlayerColor.BLACK;
        }

        /*
        board[3][0] = new Queen(PlayerColor.WHITE, board);
        board[4][0] = new King(PlayerColor.WHITE, board);
        board[3][7] = new Queen(PlayerColor.BLACK, board);
        board[4][7] = new King(PlayerColor.BLACK, board);
        */
        // création des pions

        color = PlayerColor.WHITE;
        for(int j = 1; j < BOARD_SIZE; j+=5) {
            for (int i = 0; i < BOARD_SIZE; ++i) {
                board[i][j] = new Pawn(color, board);
            }
            color = PlayerColor.BLACK;
        }
    }
}
