package Pieces;

import Controlls.GameController;
import Controlls.Turn;

import java.util.ArrayList;

public class Rook implements Piece {
    private int row;

    private GameController gameController;
    private int numOfMoves = 0;


    private int col;

    private TypeOfPiece typeOfPiece;

    private Turn colorOfPiece;

    private ArrayList<int[]> tilesBeingAttacked = new ArrayList<>();
    private ArrayList<int[]> pinnedPieces = new ArrayList<>();


    private State state = State.Free;

    public Rook(TypeOfPiece typeOfPiece, int row, int col, Turn colorOfPiece) {
        this.row = row;
        this.colorOfPiece = colorOfPiece;
        this.col = col;
        this.typeOfPiece = typeOfPiece;
    }


    @Override
    public boolean setAvaliableMovesForPiece() {

        int offSet = 1;
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


    private boolean tryAndSetAvaliableMovesAt(int rowToCheck, int colToCheck, boolean isTake) {
        boolean avaliable = false;

        try {
            avaliable = gameController.checkIfMoveIsValid(rowToCheck, colToCheck) || gameController.checkIfCanTake(rowToCheck, colToCheck);

        } catch (ArrayIndexOutOfBoundsException e) {
            return avaliable;
        }
        return avaliable;
    }


    @Override
    public void setTilesThatAreAttacked(int row, int col) {

        //TODO To implement
        tilesBeingAttacked.clear();
        int offSet = 1;
        while (exist(row - offSet, col)) {
            tilesBeingAttacked.add(new int[]{row - offSet, col});
            if (gameController.getChessBoard()[row - offSet][col].getPiece() != TypeOfPiece.EMPTY &&
                    (gameController.getChessBoard()[row - offSet][col].getPiece() != TypeOfPiece.WHITE_KING && gameController.getChessBoard()[row - offSet][col].getPiece() != TypeOfPiece.BLACK_KING)) {
                break;
            }
            offSet++;
        }

        offSet = 1;
        while (tryAndSetTileToAttack(row + offSet, col)) {
            tilesBeingAttacked.add(new int[]{row + offSet, col});

            if (gameController.getChessBoard()[row + offSet][col].getPiece() != TypeOfPiece.EMPTY &&
                    (gameController.getChessBoard()[row + offSet][col].getPiece() != TypeOfPiece.WHITE_KING && gameController.getChessBoard()[row + offSet][col].getPiece() != TypeOfPiece.BLACK_KING)) {
                break;
            }
            offSet++;
        }
        offSet = 1;
        while (tryAndSetTileToAttack(row, col - offSet)) {
            tilesBeingAttacked.add(new int[]{row, col - offSet});
            if (gameController.getChessBoard()[row][col - offSet].getPiece() != TypeOfPiece.EMPTY &&
                    (gameController.getChessBoard()[row][col - offSet].getPiece() != TypeOfPiece.WHITE_KING && gameController.getChessBoard()[row][col - offSet].getPiece() != TypeOfPiece.BLACK_KING)) {
                break;
            }
            offSet++;
        }
        offSet = 1;
        while (tryAndSetTileToAttack(row, col + offSet)) {
            tilesBeingAttacked.add(new int[]{row, col + offSet});

            if (gameController.getChessBoard()[row][col + offSet].getPiece() != TypeOfPiece.EMPTY &&
                    (gameController.getChessBoard()[row][col + offSet].getPiece() != TypeOfPiece.WHITE_KING && gameController.getChessBoard()[row][col + offSet].getPiece() != TypeOfPiece.BLACK_KING)) {
                break;
            }
            offSet++;
        }
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
    public void setPiecesThatArePinned(int row, int col) {

        pinnedPieces.clear();
        int offset = 1;
        System.out.println("In here");

        while (tryAndPin(row - offset, col)) {
            if (colorOfPiece.colorOfPiece == -1) {
                if (row - offset == gameController.getBlackKingPos()[0] && col == gameController.getBlackKingPos()[1]) {
                    pinnedPieces.add(new int[]{row + 1 - offset, col});
                    break;
                }
            } else {
                if (row - offset == gameController.getBlackKingPos()[0] && col == gameController.getBlackKingPos()[1]) {
                    pinnedPieces.add(new int[]{row + 1 - offset, col});
                    break;
                }
            }
            offset++;
        }
        offset = 1;
        while (tryAndPin(row + offset, col)) {
            if (colorOfPiece.colorOfPiece == -1) {
                if (row + offset == gameController.getBlackKingPos()[0] && col == gameController.getBlackKingPos()[1]) {
                    pinnedPieces.add(new int[]{row + offset - 1, col});
                    break;
                }
            } else {
                if (row + offset == gameController.getBlackKingPos()[0] && col == gameController.getBlackKingPos()[1]) {
                    pinnedPieces.add(new int[]{row + offset - 1, col});
                    break;
                }
            }
            offset++;
        }
        offset = 1;
        while (tryAndPin(row, col + offset)) {
            if (colorOfPiece.colorOfPiece == -1) {
                if (row == gameController.getBlackKingPos()[0] && col + offset == gameController.getBlackKingPos()[1]) {
                    pinnedPieces.add(new int[]{row, col + offset - 1});
                    break;
                }
            } else {
                if (row == gameController.getBlackKingPos()[0] && col + offset == gameController.getBlackKingPos()[1]) {
                    pinnedPieces.add(new int[]{row, col + offset - 1});
                    break;
                }
            }
            offset++;
        }
        offset = 1;
        while (tryAndPin(row, col - offset)) {
            if (colorOfPiece.colorOfPiece == -1) {
                if (row == gameController.getBlackKingPos()[0] && col - offset == gameController.getBlackKingPos()[1]) {
                    pinnedPieces.add(new int[]{row, col - offset + 1});
                    break;
                }
            } else {
                if (row == gameController.getBlackKingPos()[0] && col - offset == gameController.getBlackKingPos()[1]) {
                    pinnedPieces.add(new int[]{row, col - offset + 1});
                    break;
                }
            }
            offset++;
        }
    }


    private boolean tryAndPin(int row, int col) {
        boolean pinned = false;


        try {
            gameController.getChessBoard()[row][col].getPiece();
            pinned = true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return pinned;
    }

    @Override
    public ArrayList<int[]> getPiecesThatArePinned() {
        return pinnedPieces;
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

    @Override
    public ArrayList<int[]> getTilesBeingAttacked() {
        return tilesBeingAttacked;
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
