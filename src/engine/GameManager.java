package engine;
import chess.*;
import engine.pieces.*;


public class GameManager implements ChessController {
    final int BOARD_SIZE = 8;
    Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];
    int turn = 0;
    ChessView view;

    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        if(board[fromX][fromY] != null){
            board[fromX][fromY].move(fromX, fromY, toX, toY);
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
        PlayerColor color = PlayerColor.WHITE;

        //création des pièces
        for(int i = 0; i < 2; ++i) {
            board[0][i*(BOARD_SIZE-1)] = new Rook(color);
            board[1][i*(BOARD_SIZE-1)] = new Knight(color);
            board[2][i*(BOARD_SIZE-1)] = new Bishop(color);
            board[5][i*(BOARD_SIZE-1)] = new Bishop(color);
            board[6][i*(BOARD_SIZE-1)] = new Knight(color);
            board[7][i*(BOARD_SIZE-1)] = new Rook(color);
            color = PlayerColor.BLACK;
        }

        // création des rois/reines
        board[3][0] = new Queen(PlayerColor.WHITE);
        board[4][0] = new King(PlayerColor.WHITE);

        board[3][7] = new Queen(PlayerColor.BLACK);
        board[4][7] = new King(PlayerColor.BLACK);

        // création des pions
        color = PlayerColor.WHITE;
        for(int j = 1; j < BOARD_SIZE; j+=5) {
            for (int i = 0; i < BOARD_SIZE; ++i) {
                board[i][j] = new Pawn(color);
            }
            color = PlayerColor.BLACK;
        }
    }
}
