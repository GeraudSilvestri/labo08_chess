package engine;

import chess.ChessController;
import chess.ChessView;
import chess.views.gui.GUIView;
import engine.GameManager;

public class Main {
    public static void main(String[] args) {
        // 1. Création du contrôleur pour gérer le jeu d’échec
        ChessController controller = new GameManager();

        // 2. Création de la vue en mode GUI ou mode Console
        ChessView view = new GUIView(controller);
        // ChessView view = new ConsoleView(controller);

        // 3. Lancement du programme.
        controller.start(view);
    }
}
