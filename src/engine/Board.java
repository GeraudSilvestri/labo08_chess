package engine;

import chess.ChessView;
import chess.PlayerColor;
import engine.pieces.*;

/**
 * Gère l'échiquier
 *
 * @author Géraud Silvestri
 * @author Loïc Rosset
 */
public class Board {
    private Piece[][] board;
    private final int width;
    private final ChessView view;

    /**
     * Gère une pièce ayant effectué un mouvement
     * Nécessaire pour pouvoir annuler un mouvement et remettre la pièce à sa place initiale
     */
    private class MovedPiece {
        Piece piece;
        int x;
        int y;

        /**
         * Constructeur
         * @param piece pièce concernée
         * @param x position x
         * @param y position y
         */
        private MovedPiece(Piece piece, int x, int y) {
            this.piece = piece;
            this.x = x;
            this.y = y;
        }
    }

    Piece lastEatenPiece;
    MovedPiece lastMovedPiece;

    /**
     * Constructeur
     * @param width taille de l'échiquier, communément 8
     * @param view view servant à la gestion de l'interface de l'échiquier
     */
    public Board(int width, ChessView view){
        this.width = width;
        this.view = view;
    }

    /**
     * retourne une pièce à l'index donnée
     * @param x index x
     * @param y index y
     * @return pièce concernée ou null si l'index est outOfBounds
     */
    public Piece at(int x, int y){
        //if(x >= width || x < 0 || y >= width || y < 0)
          //  return null;

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

        // création des pions
        color = PlayerColor.WHITE;
        for(int j = 1; j < width; j+=5) {
            for (int i = 0; i < width; ++i) {
                board[i][j] = new Pawn(color, this);
            }
            color = PlayerColor.BLACK;
        }
    }

    /**
     * ajoute les pièces dans la vue
     */
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

    /**
     * gère les mouvements effectués par l'utilisateur
     * @param fromX position X de départ
     * @param fromY position Y de départ
     * @param toX position X d'arrivée
     * @param toY position Y d'arrivée
     * @param playerTurn couleur du joueur devant faire le tour
     * @return si le déplacement a été fait ou non
     */
    public boolean move(int fromX, int fromY, int toX, int toY, PlayerColor playerTurn) {
        // vérifie que la position initiale correspond à une pièce, que la pièce soit de la couleur du joueur
        // et qu'un déplacement ai été fait
        /*if(board[fromX][fromY] != null
            && board[fromX][fromY].getColor() == playerTurn
            && (board[toX][toY] == null || board[toX][toY].getColor() != playerTurn)
            && (toX != fromX || toY != fromY)) {*/

        if(board[fromX][fromY] == null ||board[fromX][fromY].getColor() != playerTurn)
            return false;

            if (board[fromX][fromY].canMove(fromX, fromY, toX, toY)) {

                lastEatenPiece = board[toX][toY];
                lastMovedPiece = new MovedPiece(board[fromX][fromY], toX, toY);


                // si la pièce est un roi, une tour ou un pion, on dit qu'elle a bougé
                if (board[fromX][fromY] instanceof SpecialPiece && ((SpecialPiece) board[fromX][fromY]).getHasMoved())
                    ((SpecialPiece) board[fromX][fromY]).moved();

                board[toX][toY] = board[fromX][fromY];
                board[fromX][fromY] = null;

                // check si le roi du joueur est en échec
                if (isKingChecked(lastMovedPiece.piece.getColor())) {
                    board[fromX][fromY] = lastMovedPiece.piece;
                    board[toX][toY] = lastEatenPiece;
                    return false;
                }

                // promotion de pion
                if (toY == (playerTurn == PlayerColor.WHITE ? width - 1 : 0)) {
                    if (board[toX][toY] instanceof Pawn) {
                        board[toX][toY] = view.askUser("Promotion", "Switch", new Knight(playerTurn, this),
                                new Bishop(playerTurn, this), new Rook(playerTurn, this), new Queen(playerTurn, this));

                        if (board[toX][toY] instanceof SpecialPiece) {
                            ((SpecialPiece) board[toX][toY]).moved();
                        }
                    }
                }

                // met a jour la vue uniquement avec les pièces ayant bougé
                view.removePiece(fromX, fromY);
                view.putPiece(board[toX][toY].getType(), board[toX][toY].getColor(), toX, toY);

                return true;
            }
        //}
        return false;
    }

    /**
     * retourne la largeur de l'échiquier
     * @return ladite largeur
     */
    public int getWidth(){
        return width;
    }

    /**
     * vérifie qu'un roi d'une couleur est en échec
     * @param color couleur du joueur
     * @return true si le roi est en échec
     */
    public boolean isKingChecked(PlayerColor color){
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
            return isCellChecked(xKing, yKing, color);
        }
        throw(new RuntimeException("Roi non trouvé"));
    }

    /**
     * vérifie qu'une case est en échec ou non
     * @param x position X de la case
     * @param y position Y de la case
     * @return ladite case est-elle échec
     */
    public boolean isCellChecked(int x, int y, PlayerColor color) {

        // création d'une pièce temporaire pour pouvoir tester avec un pion
        Piece temp = board[x][y];
        board[x][y] = new King(color, this);

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < width; ++j) {
                // si une pièce adverse peut aller sur la case, elle est en échec
                if (board[i][j] != null && board[i][j].getColor() != board[x][y].getColor() && board[i][j].canMove(i, j, x, y)) {
                    board[x][y] = temp;
                    return true;
                }
            }
        }
        board[x][y] = temp;
        return false;
    }

    /**
     * vérifie que la pièce voulant être mangée en passant est bel et bien mangeable
     * @param piece pièce à vérifiée
     * @return la pièce est-elle mangeable en passant
     */
    public boolean checkEnPassant(Pawn piece){
        if(lastMovedPiece.piece == piece && piece.isEnPassantAble()){
            board[lastMovedPiece.x][lastMovedPiece.y] = null;
            view.removePiece(lastMovedPiece.x, lastMovedPiece.y);
            return true;
        }
        return false;
    }
}
