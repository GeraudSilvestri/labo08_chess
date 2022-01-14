package engine;
import chess.*;

/**
 * Classe gérant le déroulement d'une partie d'échecs
 *
 * @authors Géraud Silvestri
 * @authors Loïc Rosset
 */
public class GameManager implements ChessController {
    final int BOARD_SIZE = 8;
    ChessView view;
    Board board;
    PlayerColor playerTurn;

    /**
     * lance la partie
     * @param view la vue à utiliser
     */
    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
        board = new Board(BOARD_SIZE, view);
        newGame();
    }

    /**
     * Effectue le déplacement d'une pièce après vérifications
     * @param fromX postion X de départ
     * @param fromY postion Y de départ
     * @param toX position X de fin
     * @param toY position Y de fin
     * @return ledit déplacement a-t'il été effectué
     */
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        boolean turnIsGood = false;
        if(board.move(fromX, fromY, toX, toY, playerTurn)){
            playerTurn = playerTurn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
            turnIsGood = true;
        }
        String str = "Au tour des "
                + (playerTurn == PlayerColor.WHITE ? "blancs" : "noirs")
                + (board.isKingChecked(playerTurn) ? " (Check!)" : "");
        view.displayMessage(str);

        return turnIsGood;
    }

    /**
     * lance une nouvelle partie, génère l'échiquier et place les pièces
     */
    @Override
    public void newGame() {
        board.initBoard();
        board.drawBoard();
        playerTurn = PlayerColor.WHITE;
        view.displayMessage("Au tour des blancs");
    }


}
