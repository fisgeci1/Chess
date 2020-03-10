package Pieces;

import Controlls.GameController;
import Controlls.Turn;

import java.util.ArrayList;

public class Queen implements Piece {
    private int row;

    private GameController gameController;
    private int numOfMoves = 0;


    private int col;

    private TypeOfPiece typeOfPiece;

    private Turn colorOfPiece;

    private State state = State.Free;
    private ArrayList<int[]> tilesBeingAttacked = new ArrayList<>();

    public Queen(TypeOfPiece typeOfPiece, int row, int col, Turn colorOfPiece) {
        this.row = row;
        this.colorOfPiece = colorOfPiece;
        this.col = col;
        this.typeOfPiece = typeOfPiece;
    }


    @Override
    public boolean setAvaliableMovesForPiece() {


        int offSet = 1;
        while (exist(row - offSet, col - offSet)) {
            if (tryAndSetAvaliableMovesAt(row - offSet, col - offSet, false)) {
                gameController.setDummyMove(false);
                gameController.getChessBoard()[row - offSet][col - offSet].setTileState(TileState.AVAILABLE_MOVE);
            }
            if (gameController.getChessBoard()[row - offSet][col - offSet].getPiece() != TypeOfPiece.EMPTY) {
                break;
            }
            offSet++;
        }

        offSet = 1;
        while (exist(row - offSet, col + offSet)) {
            if (tryAndSetAvaliableMovesAt(row - offSet, col + offSet, false)) {
                gameController.setDummyMove(false);
                gameController.getChessBoard()[row - offSet][col + offSet].setTileState(TileState.AVAILABLE_MOVE);
            }
            if (gameController.getChessBoard()[row - offSet][col + offSet].getPiece() != TypeOfPiece.EMPTY) {
                break;
            }
            offSet++;
        }
        offSet = 1;
        while (exist(row + offSet, col - offSet)) {
            if (tryAndSetAvaliableMovesAt(row + offSet, col - offSet, false)) {
                gameController.setDummyMove(false);
                gameController.getChessBoard()[row + offSet][col - offSet].setTileState(TileState.AVAILABLE_MOVE);
            }
            if (gameController.getChessBoard()[row + offSet][col - offSet].getPiece() != TypeOfPiece.EMPTY) {
                break;
            }
            offSet++;
        }
        offSet = 1;
        while (exist(row + offSet, col + offSet)) {
            if (tryAndSetAvaliableMovesAt(row + offSet, col + offSet, false)) {
                gameController.setDummyMove(false);
                gameController.getChessBoard()[row + offSet][col + offSet].setTileState(TileState.AVAILABLE_MOVE);
            }
            if (gameController.getChessBoard()[row + offSet][col + offSet].getPiece() != TypeOfPiece.EMPTY) {
                break;
            }
            offSet++;
        }
        offSet = 1;
        while (exist(row - offSet, col)) {
            if (tryAndSetAvaliableMovesAt(row - offSet, col, false)) {
                gameController.setDummyMove(false);
                gameController.getChessBoard()[row - offSet][col].setTileState(TileState.AVAILABLE_MOVE);
            }
            if (gameController.getChessBoard()[row - offSet][col].getPiece() != TypeOfPiece.EMPTY) {
                break;
            }
            offSet++;
        }

        offSet = 1;
        while (exist(row + offSet, col)) {
            if (tryAndSetAvaliableMovesAt(row + offSet, col, false)) {
                gameController.setDummyMove(false);
                gameController.getChessBoard()[row + offSet][col].setTileState(TileState.AVAILABLE_MOVE);
            }
            if (gameController.getChessBoard()[row + offSet][col].getPiece() != TypeOfPiece.EMPTY) {
                break;
            }
            offSet++;
        }
        offSet = 1;
        while (exist(row, col - offSet)) {
            if (tryAndSetAvaliableMovesAt(row, col - offSet, false)) {
                gameController.setDummyMove(false);
                gameController.getChessBoard()[row][col - offSet].setTileState(TileState.AVAILABLE_MOVE);
            }
            if (gameController.getChessBoard()[row][col - offSet].getPiece() != TypeOfPiece.EMPTY) {
                break;
            }
            offSet++;
        }
        offSet = 1;
        while (exist(row, col + offSet)) {
            if (tryAndSetAvaliableMovesAt(row, col + offSet, false)) {
                gameController.setDummyMove(false);
                gameController.getChessBoard()[row][col + offSet].setTileState(TileState.AVAILABLE_MOVE);
            }
            if (gameController.getChessBoard()[row][col + offSet].getPiece() != TypeOfPiece.EMPTY) {
                break;
            }
            offSet++;
        }

        return false;
    }

    private boolean exist(int row, int col) {
        try {
            gameController.getChessBoard()[row][col].getPiece();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<int[]> getTilesBeingAttacked() {
        return tilesBeingAttacked;
    }

    private boolean tryAndSetAvaliableMovesAt(int rowToCheck, int colToCheck, boolean isTake) {
        boolean avaliable = false;

        try {
            if (!isTake) {
                avaliable = gameController.checkIfMoveIsValid(rowToCheck, colToCheck) ||  gameController.checkIfCanTake(rowToCheck, colToCheck);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            return avaliable;
        } catch (Exception e) {

        }
        return avaliable;
    }

    @Override
    public void setTilesThatAreAttacked(int row, int col) {
        tilesBeingAttacked.clear();
        int offSet = 1;
        while (tryAndSetTileToAttack(row - offSet, col - offSet)) {
            tilesBeingAttacked.add(new int[]{row - offSet, col - offSet});
            if (gameController.getChessBoard()[row - offSet][col - offSet].getPiece() != TypeOfPiece.EMPTY) {
                break;
            }
            offSet++;
        }

        offSet = 1;
        while (tryAndSetTileToAttack(row - offSet, col + offSet)) {
            tilesBeingAttacked.add(new int[]{row - offSet, col + offSet});

            if (gameController.getChessBoard()[row - offSet][col + offSet].getPiece() != TypeOfPiece.EMPTY)
                break;

            offSet++;
        }
        offSet = 1;
        while (tryAndSetTileToAttack(row + offSet, col - offSet)) {
            tilesBeingAttacked.add(new int[]{row + offSet, col - offSet});
            if (gameController.getChessBoard()[row + offSet][col - offSet].getPiece() != TypeOfPiece.EMPTY)
                break;

            offSet++;
        }
        offSet = 1;
        while (tryAndSetTileToAttack(row + offSet, col + offSet)) {
            tilesBeingAttacked.add(new int[]{row + offSet, col + offSet});

            if (gameController.getChessBoard()[row + offSet][col + offSet].getPiece() != TypeOfPiece.EMPTY)
                break;

            offSet++;
        }
        offSet = 1;
        while (tryAndSetTileToAttack(row - offSet, col)) {
            tilesBeingAttacked.add(new int[]{row - offSet, col});
            if (gameController.getChessBoard()[row - offSet][col].getPiece() != TypeOfPiece.EMPTY)
                break;

            offSet++;
        }

        offSet = 1;
        while (tryAndSetTileToAttack(row + offSet, col)) {
            tilesBeingAttacked.add(new int[]{row + offSet, col});

            if (gameController.getChessBoard()[row + offSet][col].getPiece() != TypeOfPiece.EMPTY)
                break;

            offSet++;
        }
        offSet = 1;
        while (tryAndSetTileToAttack(row, col - offSet)) {
            tilesBeingAttacked.add(new int[]{row, col - offSet});
            if (gameController.getChessBoard()[row][col - offSet].getPiece() != TypeOfPiece.EMPTY)
                break;

            offSet++;
        }
        offSet = 1;
        while (tryAndSetTileToAttack(row, col + offSet)) {
            tilesBeingAttacked.add(new int[]{row, col + offSet});

            if (gameController.getChessBoard()[row][col + offSet].getPiece() != TypeOfPiece.EMPTY)
                break;

            offSet++;
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
