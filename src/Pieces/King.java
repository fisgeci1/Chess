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

    private Turn colorOfPiece = Turn.WHITE;

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
        castle();
        return false;
    }


    private void castle() {
        if (numOfMoves == 0 && gameController.isCheck() == false) {
            if (colorOfPiece == Turn.BLACK) {
                if (exist(0, 0)) {
                    if (checkIfCanCastle(0, 1, 3)) {
                        gameController.setDummyMove(false);
                        gameController.getChessBoard()[0][0].setTileState(TileState.AVAILABLE_MOVE);
                        gameController.getChessBoard()[0][0].getPieceInstance().setState(State.CanCastle);
                        state = State.CanCastle;
                    }
                }
                if (exist(0, 7)) {
                    if (checkIfCanCastle(0, 5, 6)) {
                        gameController.setDummyMove(false);
                        gameController.getChessBoard()[0][7].setTileState(TileState.AVAILABLE_MOVE);
                        gameController.getChessBoard()[0][7].getPieceInstance().setState(State.CanCastle);
                        state = State.CanCastle;
                    }
                }
            } else {
                if (exist(7, 0)) {
                    if (checkIfCanCastle(7, 1, 3)) {
                        gameController.setDummyMove(false);
                        gameController.getChessBoard()[7][0].setTileState(TileState.AVAILABLE_MOVE);
                        gameController.getChessBoard()[7][0].getPieceInstance().setState(State.CanCastle);
                        state = State.CanCastle;
                    }
                }
                if (exist(7, 7)) {
                    if (checkIfCanCastle(7, 5, 6)) {
                        gameController.setDummyMove(false);
                        gameController.getChessBoard()[7][7].setTileState(TileState.AVAILABLE_MOVE);
                        gameController.getChessBoard()[7][7].getPieceInstance().setState(State.CanCastle);
                        state = State.CanCastle;
                    }
                }
            }

        }
    }

    private boolean checkIfCanCastle(int row, int startCol, int endCol) {
        boolean canCastle = true;
        for (int i = startCol; i < endCol; i++) {
            if (gameController.getChessBoard()[row][i].getPiece() == TypeOfPiece.EMPTY && checkIfTileIsAttacked(row, i))
                return false;
        }
        return canCastle;
    }

    private boolean checkIfTileIsAttacked(int row, int col) {
        TileState colorOfPieceAttacking = (colorOfPiece == Turn.BLACK) ? TileState.ATTACKED_BY_WHITE : TileState.ATTACKED_BY_BLACK;
        return (gameController.getChessBoard()[row][col].getTileState() == colorOfPieceAttacking || gameController.getChessBoard()[row][col].getTileState() == TileState.ATTACKED_BY_BOTH);
    }

    private boolean exist(int row, int col) {

        try {
            gameController.getChessBoard()[row][col].getPieceInstance();

            TypeOfPiece matchingPieceColor = (colorOfPiece == Turn.BLACK) ? TypeOfPiece.BLACK_ROOK : TypeOfPiece.WHITE_ROOK;

            return gameController.getChessBoard()[row][col].getPiece() == matchingPieceColor && gameController.getChessBoard()[row][col].getPieceInstance().getNumOfMoves() == 0;
        } catch (NullPointerException e) {
            return false;
        }
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

    public int getNumOfMoves() {
        return numOfMoves;
    }

    public void setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
    }
}
