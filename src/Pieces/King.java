package Pieces;

import Controlls.GameController;
import Controlls.Turn;

import java.util.ArrayList;

public class King implements Piece {
    private int row;

    private GameController gameController;
    private int numOfMoves = 0;


    private int col;

    private TypeOfPiece typeOfPiece;

    private Turn colorOfPiece;

    private State state = State.Free;

    private ArrayList<int[]> tilesBeingAttacked = new ArrayList<>();

    public King(TypeOfPiece typeOfPiece, int row, int col, Turn colorOfPiece) {
        this.row = row;
        this.colorOfPiece = colorOfPiece;
        this.col = col;
        this.typeOfPiece = typeOfPiece;
    }

    @Override
    public boolean setAvaliableMovesForPiece() {


        if (tryAndSetAvaliableMovesAt(row - 1, col, false)) {
            gameController.setDummyMove(false);
            gameController.getChessBoard()[row - 1][col].setTileState(TileState.AVAILABLE_MOVE);

        }
        if (tryAndSetAvaliableMovesAt(row - 1, col + 1, false)) {
            gameController.setDummyMove(false);
            gameController.getChessBoard()[row - 1][col + 1].setTileState(TileState.AVAILABLE_MOVE);

        }
        if (tryAndSetAvaliableMovesAt(row, col + 1, false)) {
            gameController.setDummyMove(false);
            gameController.getChessBoard()[row][col + 1].setTileState(TileState.AVAILABLE_MOVE);

        }
        if (tryAndSetAvaliableMovesAt(row + 1, col + 1, false)) {
            gameController.setDummyMove(false);
            gameController.getChessBoard()[row + 1][col + 1].setTileState(TileState.AVAILABLE_MOVE);

        }
        if (tryAndSetAvaliableMovesAt(row + 1, col, false)) {
            gameController.setDummyMove(false);
            gameController.getChessBoard()[row + 1][col].setTileState(TileState.AVAILABLE_MOVE);
        }
        if (tryAndSetAvaliableMovesAt(row + 1, col - 1, false)) {
            gameController.setDummyMove(false);
            gameController.getChessBoard()[row + 1][col - 1].setTileState(TileState.AVAILABLE_MOVE);
        }
        if (tryAndSetAvaliableMovesAt(row, col - 1, false)) {
            gameController.setDummyMove(false);
            gameController.getChessBoard()[row][col - 1].setTileState(TileState.AVAILABLE_MOVE);
        }
        if (tryAndSetAvaliableMovesAt(row - 1, col - 1, false)) {
            gameController.setDummyMove(false);
            gameController.getChessBoard()[row - 1][col - 1].setTileState(TileState.AVAILABLE_MOVE);

        }


        return false;
    }


    private boolean tryAndSetAvaliableMovesAt(int rowToCheck, int colToCheck, boolean isTake) {
        boolean avaliable = false;

        try {
            avaliable = gameController.checkIfKingCanMove(rowToCheck, colToCheck);

        } catch (ArrayIndexOutOfBoundsException e) {
            return avaliable;
        }
        return avaliable;
    }

    @Override
    public void setTilesThatAreAttacked(int row, int col) {
        tilesBeingAttacked.clear();
        if (tryAndSetTileToAttack(row - 1, col)) {
            tilesBeingAttacked.add(new int[]{row - 1, col});

        }
        if (tryAndSetTileToAttack(row - 1, col + 1)) {
            tilesBeingAttacked.add(new int[]{row - 1, col + 1});

        }
        if (tryAndSetTileToAttack(row, col + 1)) {
            tilesBeingAttacked.add(new int[]{row, col + 1});

        }
        if (tryAndSetTileToAttack(row + 1, col + 1)) {
            tilesBeingAttacked.add(new int[]{row + 1, col + 1});
        }
        if (tryAndSetTileToAttack(row + 1, col)) {
            tilesBeingAttacked.add(new int[]{row + 1, col});
        }
        if (tryAndSetTileToAttack(row + 1, col - 1)) {
            tilesBeingAttacked.add(new int[]{row + 1, col - 1});

        }
        if (tryAndSetTileToAttack(row, col - 1)) {
            tilesBeingAttacked.add(new int[]{row, col - 1});
        }
        if (tryAndSetTileToAttack(row - 1, col - 1)) {
            tilesBeingAttacked.add(new int[]{row - 1, col - 1});
        }

    }

    @Override
    public void setPiecesThatArePinned(int row, int col) {

    }

    @Override
    public ArrayList<int[]> getPiecesThatArePinned() {
        return null;
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
        } catch (IndexOutOfBoundsException e) {

        } catch (Exception e) {

        }
        return canSet;
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
