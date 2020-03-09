package Pieces;

import Controlls.GameController;
import Controlls.Turn;

import java.util.ArrayList;

public class Knight implements Piece {
    private int row;

    private GameController gameController;
    private int numOfMoves = 0;


    private int col;

    private TypeOfPiece typeOfPiece;

    private Turn colorOfPiece;

    private State state = State.Free;
    private ArrayList<int[]> tilesBeingAttacked = new ArrayList<>();

    public Knight(TypeOfPiece typeOfPiece, int row, int col, Turn colorOfPiece) {
        this.row = row;
        this.colorOfPiece = colorOfPiece;
        this.col = col;
        this.typeOfPiece = typeOfPiece;
    }

    @Override
    public boolean setAvaliableMovesForPiece() {

        if (tryAndSetAvaliableMovesAt(row - 1, col + 2, false)) {
            gameController.setDummyMove(false);
            gameController.getChessBoard()[row - 1][col + 2].setTileState(TileState.AVAILABLE_MOVE);
        }
        if (tryAndSetAvaliableMovesAt(row - 1, col - 2, false)) {
            gameController.setDummyMove(false);

            gameController.getChessBoard()[row - 1][col - 2].setTileState(TileState.AVAILABLE_MOVE);

        }
        if (tryAndSetAvaliableMovesAt(row + 1, col + 2, false)) {
            gameController.setDummyMove(false);

            gameController.getChessBoard()[row + 1][col + 2].setTileState(TileState.AVAILABLE_MOVE);

        }
        if (tryAndSetAvaliableMovesAt(row + 1, col - 2, false)) {
            gameController.setDummyMove(false);

            gameController.getChessBoard()[row + 1][col - 2].setTileState(TileState.AVAILABLE_MOVE);
        }

        if (tryAndSetAvaliableMovesAt(row - 2, col + 1, false)) {
            gameController.setDummyMove(false);

            gameController.getChessBoard()[row - 2][col + 1].setTileState(TileState.AVAILABLE_MOVE);

        }

        if (tryAndSetAvaliableMovesAt(row - 2, col - 1, false)) {
            gameController.setDummyMove(false);

            gameController.getChessBoard()[row - 2][col - 1].setTileState(TileState.AVAILABLE_MOVE);
        }

        if (tryAndSetAvaliableMovesAt(row + 2, col - 1, false)) {
            gameController.setDummyMove(false);

            gameController.getChessBoard()[row + 2][col - 1].setTileState(TileState.AVAILABLE_MOVE);
        }
        if (tryAndSetAvaliableMovesAt(row + 2, col + 1, false)) {
            gameController.setDummyMove(false);

            gameController.getChessBoard()[row + 2][col + 1].setTileState(TileState.AVAILABLE_MOVE);

        }

        return false;
    }


    private boolean tryAndSetAvaliableMovesAt(int rowToCheck, int colToCheck, boolean isTake) {
        boolean avaliable = false;

        try {
            avaliable = gameController.checkIfMoveIsValid(rowToCheck, colToCheck) || gameController.checkIfCanTake(rowToCheck, colToCheck);

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

    @Override
    public void setTilesThatAreAttacked(int row, int col) {
        tilesBeingAttacked.clear();
        if (tryAndSetTileToAttack(row - 1, col + 2)) {
            tilesBeingAttacked.add(new int[]{row - 1, col + 2});
        }
        if (tryAndSetTileToAttack(row - 1, col - 2)) {
            tilesBeingAttacked.add(new int[]{row - 1, col - 2});
        }
        if (tryAndSetTileToAttack(row + 1, col + 2)) {
            tilesBeingAttacked.add(new int[]{row + 1, col + 2});

        }
        if (tryAndSetTileToAttack(row + 1, col - 2)) {
            tilesBeingAttacked.add(new int[]{row + 1, col - 2});
        }

        if (tryAndSetTileToAttack(row - 2, col + 1)) {
            tilesBeingAttacked.add(new int[]{row - 2, col + 1});
        }

        if (tryAndSetTileToAttack(row - 2, col - 1)) {
            tilesBeingAttacked.add(new int[]{row - 2, col - 1});
        }

        if (tryAndSetTileToAttack(row + 2, col - 1)) {
            tilesBeingAttacked.add(new int[]{row + 2, col - 1});
        }
        if (tryAndSetTileToAttack(row + 2, col + 1)) {
            tilesBeingAttacked.add(new int[]{row + 2, col + 1});

        }
    }

    @Override
    public void setPiecesThatArePinned(int row, int col) {

    }

    @Override
    public ArrayList<int[]> getPiecesThatArePinned() {
        return null;
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

    @Override
    public ArrayList<int[]> getTilesBeingAttacked() {
        return tilesBeingAttacked;
    }

    private boolean tryAndSetTileToAttack(int row, int col) {
        boolean canSet = false;
        try {
            gameController.getChessBoard()[row][col].getPiece();
            canSet = true;
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return canSet;
    }

    public Turn getColorOfPiece() {
        return colorOfPiece;
    }

    public void setColorOfPiece(Turn colorOfPiece) {
        this.colorOfPiece = colorOfPiece;
    }

    public void setTypeOfPiece(TypeOfPiece typeOfPiece) {
        this.typeOfPiece = typeOfPiece;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }

    public void setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
    }
}
