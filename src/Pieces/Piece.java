package Pieces;

import Controlls.GameController;

import java.util.ArrayList;

public interface Piece {


    public boolean setAvaliableMovesForPiece();

    public TypeOfPiece getTypeOfPiece();

    public State getState();

    public void setState(State state);

    public void setTilesThatAreAttacked(int row, int col);
    public void setPiecesThatArePinned(int row,int col);
    public ArrayList<int[]> getPiecesThatArePinned();

    public ArrayList<int[]> getTilesBeingAttacked();
    public int getRow();

    public void setRow(int row);

    public void setCol(int row);

    public int getCol();

    public void setNumOfMoves(int numOfMove);

    public int getNumOfMoves();

    public GameController getGameController();

    public void setGameController(GameController gameController);

}
