package ViewClasses;

import Controlls.GameController;

import javax.swing.*;

public class GameFrame extends JFrame {


    public GameFrame(int width, int height) {

        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        Board board = new Board();
        GameController gameController = board.getGameController();
        board.setGameController(gameController);
        this.getContentPane().add(board);


        this.setVisible(true);


    }
}
