package Pieces;

import Controlls.GameController;
import Controlls.Turn;

import java.util.ArrayList;

//TODO Add transform to pawns when the reach the end on the board(white and black)
public class Pawn implements Piece {
    private int row;

    private GameController gameController;
    private int numOfMoves = 0;


    private int col;

    private TypeOfPiece typeOfPiece;

    private Turn colorOfPiece;

    private State state = State.Free;

    private ArrayList<int[]> tilesBeingAttacked = new ArrayList<>();

    public Pawn(TypeOfPiece typeOfPiece, int row, int col, Turn colorOfPiece) {
        this.row = row;
        this.colorOfPiece = colorOfPiece;
        this.col = col;
        this.typeOfPiece = typeOfPiece;
    }

    @Override
    public boolean setAvaliableMovesForPiece() {


        if (tryAndSetAvaliableMovesAt(row - 1 * colorOfPiece.colorOfPiece, col, false)) {
            gameController.setDummyMove(false);
            gameController.getChessBoard()[row - 1 * colorOfPiece.colorOfPiece][col].setTileState(TileState.AVAILABLE_MOVE);

        }

        if (numOfMoves == 0 && tryAndSetAvaliableMovesAt(row - 2 * colorOfPiece.colorOfPiece, col, false)) {
            gameController.setDummyMove(false);

            gameController.getChessBoard()[row - 2 * colorOfPiece.colorOfPiece][col].setTileState(TileState.AVAILABLE_MOVE);

        }

        if (tryAndSetAvaliableMovesAt(row - 1 * colorOfPiece.colorOfPiece, col - 1, true)) {
            gameController.setDummyMove(false);

            gameController.getChessBoard()[row - 1 * colorOfPiece.colorOfPiece][col - 1].setTileState(TileState.AVAILABLE_MOVE);
        }

        if (tryAndSetAvaliableMovesAt(row - 1 * colorOfPiece.colorOfPiece, col + 1, true)) {
            gameController.setDummyMove(false);

            gameController.getChessBoard()[row - 1 * colorOfPiece.colorOfPiece][col + 1].setTileState(TileState.AVAILABLE_MOVE);
        }


        return false;
    }

    public void setTilesThatAreAttacked(int row, int col) {
        tilesBeingAttacked.clear();
        if (tryAndSetTileToAttack(row - 1 * colorOfPiece.colorOfPiece, col - 1)) {
            tilesBeingAttacked.add(new int[]{row - 1 * colorOfPiece.colorOfPiece, col - 1});
        }
        if (tryAndSetTileToAttack(row - 1 * colorOfPiece.colorOfPiece, col + 1)) {
            tilesBeingAttacked.add(new int[]{row - 1 * colorOfPiece.colorOfPiece, col + 1});

        }

    }

    @Override
    public void setPiecesThatArePinned(int row, int col) {

    }

    @Override
    public ArrayList<int[]> getPiecesThatArePinned() {
        return null;
    }

    private boolean tryAndSetTileToAttack(int row, int col) {
        boolean canSet = false;
        try {
            gameController.getChessBoard()[row][col].getPiece();
            canSet = true;
        } catch (IndexOutOfBoundsException e) {

        }
        return canSet;
    }

    private boolean tryAndSetAvaliableMovesAt(int rowToCheck, int colToCheck, boolean isTake) {
        boolean avaliable = false;

        try {
            if (!isTake) {

                avaliable = gameController.checkIfMoveIsValid(rowToCheck, colToCheck);
            } else {
                avaliable = gameController.checkIfCanTake(rowToCheck, colToCheck);
            }


        } catch (ArrayIndexOutOfBoundsException e) {
            return avaliable;
        }
        return avaliable;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCol() {
        return col;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public State getState() {
        return state;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public TypeOfPiece getTypeOfPiece() {
        return typeOfPiece;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }

    public void setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
    }

    public ArrayList<int[]> getTilesBeingAttacked() {
        return tilesBeingAttacked;
    }

    public void setTilesBeingAttacked(ArrayList<int[]> tilesBeingAttacked) {
        this.tilesBeingAttacked = tilesBeingAttacked;
    }
}
