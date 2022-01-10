package engine;
import chess.*;
import engine.pieces.*;


public class GameManager implements ChessController {
    final int BOARD_SIZE = 8;
    int turn = 0;
    ChessView view;
    Board board;

    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
        board = new Board(BOARD_SIZE, view);
        newGame();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        return board.move(fromX, fromY, toX, toY, turn);
    }

    private void promotionPawn(){

    }



    @Override
    public void newGame() {
        board.initBoard();
        board.drawBoard();
    }


}
