package engine;

import chess.ChessView;
import chess.PlayerColor;
import engine.pieces.*;

import javax.swing.text.View;

public class Board {
    private Piece[][] board;
    private final int width;
    private final ChessView view;

    private class movedPiece {
        Piece piece;
        int x;
        int y;

        private movedPiece(Piece piece, int toX, int toY) {
            this.piece = piece;
            this.x = toX;
            this.y = toY;
        }
    }

    Piece lastEatenPiece;
    movedPiece lastMovedPiece;

    public Board(int width, ChessView view){
        this.width = width;
        this.view = view;
    }

    public Piece at(int x, int y){
        return board[x][y];
    }

    /**
     * crée les pièces au lancement d'une partie
     */
    public void initBoard(){
        board = new Piece[width][width];
        PlayerColor color = PlayerColor.WHITE;

        //création des pièces
        for(int i = 0; i < 2; ++i) {
            board[0][i*(width-1)] = new Rook(color, this);
            board[1][i*(width-1)] = new Knight(color, this);
            board[2][i*(width-1)] = new Bishop(color, this);
            // création des rois/reines
            board[3][i*(width-1)] = new Queen(color, this);
            board[4][i*(width-1)] = new King(color, this);
            board[5][i*(width-1)] = new Bishop(color, this);
            board[6][i*(width-1)] = new Knight(color, this);
            board[7][i*(width-1)] = new Rook(color, this);
            color = PlayerColor.BLACK;
        }

        color = PlayerColor.WHITE;
        for(int j = 1; j < width; j+=5) {
            for (int i = 0; i < width; ++i) {
                board[i][j] = new Pawn(color, this);
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

    public boolean move(int fromX, int fromY, int toX, int toY) {
        if(board[fromX][fromY] != null){
            if(board[fromX][fromY].canMove(fromX, fromY, toX, toY)){

                if (board[fromX][fromY] instanceof SpecialPiece && !((SpecialPiece) board[fromX][fromY]).getHasMoved())
                    ((SpecialPiece) board[fromX][fromY]).moved();

                lastEatenPiece = board[toX][toY];//new movedPiece(board[toX][toY], toX, toY);
                lastMovedPiece = new movedPiece(board[fromX][fromY], toX, toY);

                board[toX][toY] = board[fromX][fromY];
                board[fromX][fromY] = null;

                // check if own king is checked
                if(isKingChecked(lastMovedPiece.piece.getColor())){
                    board[fromX][fromY] = lastMovedPiece.piece;
                    board[toX][toY] = lastEatenPiece;
                    return false;
                }else if (isKingChecked((lastMovedPiece.piece.getColor() == PlayerColor.WHITE) ? PlayerColor.BLACK : PlayerColor.WHITE)){
                    view.displayMessage("Checked");
                }

                // Pawn promotion
                PlayerColor currentPieceColor = board[toX][toY].getColor();
                if (toY == (currentPieceColor == PlayerColor.WHITE ? width - 1 : 0)) {
                    if (board[toX][toY] instanceof Pawn) {
                        board[toX][toY] = view.askUser("Promotion", "Switch", new Knight(currentPieceColor, this),
                                new Bishop(currentPieceColor, this), new Rook(currentPieceColor, this), new Queen(currentPieceColor, this));

                        if (board[toX][toY] instanceof SpecialPiece) {
                            ((SpecialPiece) board[toX][toY]).moved();
                        }
                    }
                }

                view.removePiece(fromX, fromY);
                view.putPiece(board[toX][toY].getType(), board[toX][toY].getColor(), toX, toY);

                return true;
            }
        }
        return false;
    }

    public int getWidth(){
        return width;
    }

    private boolean isKingChecked(PlayerColor color){
        int yKing = -1;
        int xKing = -1;

        for(int i = 0; i < width; ++i){
            for(int j = 0; j < width; ++j){
                if(board[i][j] != null && board[i][j].getColor() == color && board[i][j] instanceof King){
                    xKing = i;
                    yKing = j;
                    break;
                }
            }
        }

        if(xKing != -1){
            return cellChecked(xKing, yKing);
        }
        throw(new RuntimeException("Roi non trouvé"));
    }

    private boolean cellChecked(int x, int y){
        for(int i = 0; i < width; ++i){
            for(int j = 0; j < width; ++j){
                if(board[i][j] != null && board[i][j].getColor() != board[x][y].getColor() && board[i][j].canMove(i,j,x,y)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkEnPassant(Pawn piece){
        if(lastMovedPiece.piece == piece && piece.isEnPassantAble()){
            board[lastMovedPiece.x][lastMovedPiece.y] = null;
            view.removePiece(lastMovedPiece.x, lastMovedPiece.y);
            return true;
        }
        return false;
    }
}
