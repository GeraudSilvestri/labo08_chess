package engine;
import chess.*;
import engine.pieces.*;


public class GameManager implements ChessController {
    final int BOARD_SIZE = 8;
    int turn = 0;
    ChessView view;
    Board board;
    PlayerColor playerTurn;

    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
        board = new Board(BOARD_SIZE, view);
        newGame();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        boolean turnIsGood = false;
        if(board.move(fromX, fromY, toX, toY, playerTurn)){
            turn++;
            playerTurn = playerTurn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
            turnIsGood = true;
        }
        String str = "Au tour des "
                + (playerTurn == PlayerColor.WHITE ? "blancs" : "noirs")
                + (board.isKingChecked(playerTurn) ? " (Checked!)" : "");
        view.displayMessage(str);

        return turnIsGood;
    }

    private void promotionPawn(){

    }



    @Override
    public void newGame() {
        board.initBoard();
        board.drawBoard();
        playerTurn = PlayerColor.WHITE;
        view.displayMessage("Au tour des blancs");
    }


}
