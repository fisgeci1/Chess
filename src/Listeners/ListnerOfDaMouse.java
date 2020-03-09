package Listeners;

import Controlls.GameController;
import ViewClasses.Tile;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListnerOfDaMouse implements MouseListener {
    GameController gameController;

    public ListnerOfDaMouse(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Tile tile = (Tile) e.getSource();


        gameController.selectPieceToMove(tile.getTileRow(), tile.getTileCol());

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
