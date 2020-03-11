package Controlls;

import Pieces.*;
import ViewClasses.Board;
import ViewClasses.ImageParser;
import ViewClasses.Tile;

import javax.swing.*;

//TODO Add Castle
//TODO Fix transform of figures

public class GameController {
    private Board chessBoard;
    private int currentSelectedRow, currentSelectedCol;
    private Turn turn = Turn.WHITE;
    private Tile[][] dummyBoard = new Tile[8][8];
    private Piece pieceThatIsBeingMoved = null;
    private int numberOfMovesForBlackKing = 0;
    private int numberOfMovesForWhiteKing = 0;
    private int[] whiteKingPos = new int[]{7, 4};
    private int[] blackKingPos = new int[]{0, 4};
    private boolean isCheck = true;
    private boolean isDummyMove = false;

    public GameController(Board board) {
        chessBoard = board;
    }


    public boolean selectPieceToMove(int row, int col) {
        boolean validSelection = false;

        Tile selectedTile = chessBoard.getChessBoard()[row][col];
        TypeOfPiece pieceSelected = selectedTile.getPiece();

        if (pieceSelected != TypeOfPiece.EMPTY && turn == getColorOf(pieceSelected)) {
            if (pieceThatIsBeingMoved != null) {
                resetTileStates();
                chessBoard.repaintTiles(chessBoard.getChessBoard());
            }

            validSelection = true;
            Piece instanceOfPiece = selectedTile.getPieceInstance();

            this.currentSelectedRow = row;

            this.currentSelectedCol = col;

            pieceThatIsBeingMoved = instanceOfPiece;

            instanceOfPiece.setAvaliableMovesForPiece();

            chessBoard.repaintTiles(chessBoard.getChessBoard());
        } else if (selectedTile.getTileState() == TileState.AVAILABLE_MOVE) {

            movePiece(selectedTile, row, col, false);
            resetTileStates();
            resetPieceStates();
            isDummyMove = false;
            selectedTile.getPieceInstance().setTilesThatAreAttacked(row, col);
            turn = (turn == Turn.BLACK) ? Turn.WHITE : Turn.BLACK;

            chessBoard.repaintTiles(chessBoard.getChessBoard());


        }

        return validSelection;
    }

    public void movePiece(Tile selectedTile, int row, int col, boolean fakeMove) {

        selectedTile.removeAll();
        selectedTile.setPieceInstance(pieceThatIsBeingMoved);
        selectedTile.setPieceTo(pieceThatIsBeingMoved.getTypeOfPiece());
        if (pieceThatIsBeingMoved.getTypeOfPiece() == TypeOfPiece.WHITE_PAWN || pieceThatIsBeingMoved.getTypeOfPiece() == TypeOfPiece.BLACK_PAWN) {
            selectedTile.removeAll();
            selectedTile.setPieceInstance(getTransformOfTileIfAvailable(selectedTile.getPieceInstance()));
            selectedTile.setPieceTo(selectedTile.getPieceInstance().getTypeOfPiece());
            selectedTile.setPieceImage(new ImageParser().getLabelIconOfPiece(selectedTile.getPiece()));

        }

        if (pieceThatIsBeingMoved.getTypeOfPiece() == TypeOfPiece.BLACK_KING || pieceThatIsBeingMoved.getTypeOfPiece() == TypeOfPiece.WHITE_KING) {
            if (getColorOf(pieceThatIsBeingMoved.getTypeOfPiece()) == Turn.BLACK) {
                setBlackKingPos(new int[]{selectedTile.getTileRow(), selectedTile.getTileCol()});
            } else {
                setWhiteKingPos(new int[]{selectedTile.getTileRow(), selectedTile.getTileCol()});
            }
        }

        if (!fakeMove) {
            selectedTile.setPieceImage(new ImageParser().getLabelIconOfPiece(pieceThatIsBeingMoved.getTypeOfPiece()));
            pieceThatIsBeingMoved.setNumOfMoves(pieceThatIsBeingMoved.getNumOfMoves() + 1);
        }
        selectedTile.getPieceInstance().setRow(row);

        selectedTile.getPieceInstance().setCol(col);


        if (!fakeMove) {
            chessBoard.getChessBoard()[currentSelectedRow][currentSelectedCol].setPieceInstance(null);

            chessBoard.getChessBoard()[currentSelectedRow][currentSelectedCol].setPieceTo(TypeOfPiece.EMPTY);

            chessBoard.getChessBoard()[currentSelectedRow][currentSelectedCol].paintTile();


        }


        if (!fakeMove) {
            pieceThatIsBeingMoved = null;
        }

        selectedTile.getPieceInstance().setTilesThatAreAttacked(row, col);
        chessBoard.repaintTiles(chessBoard.getChessBoard());

    }

    public Piece getTransformOfTileIfAvailable(Piece pieceToCheck) {
        Piece tranformedPiece = pieceToCheck;
        PieceFactory pieceFactory = new PieceFactory();
        pieceFactory.setGameController(this);
        if (getColorOf(pieceToCheck.getTypeOfPiece()) == Turn.BLACK) {
            if (pieceToCheck.getRow() == 6) {
                String pawnTransform = JOptionPane.showInputDialog("q for queen \nk for knight\nb for bishop\nr for rook");
                switch (pawnTransform) {
                    case "q":
                        return pieceFactory.createPieceInstance(TypeOfPiece.BLACK_QUEEN, 7, tranformedPiece.getCol());
                    case "k":
                        return pieceFactory.createPieceInstance(TypeOfPiece.BLACK_KNIGHT, 7, tranformedPiece.getCol());
                    case "b":
                        return pieceFactory.createPieceInstance(TypeOfPiece.BLACK_BISHOP, 7, tranformedPiece.getCol());
                    case "r":
                        return pieceFactory.createPieceInstance(TypeOfPiece.BLACK_ROOK, 7, tranformedPiece.getCol());
                    default:
                        break;
                }

            }
        } else {
            if (pieceToCheck.getRow() == 1) {

                String pawnTransform = JOptionPane.showInputDialog("q for queen \nk for knight\nb for bishop\nr for rook");
                switch (pawnTransform) {
                    case "q":
                        return pieceFactory.createPieceInstance(TypeOfPiece.WHITE_QUEEN, 0, tranformedPiece.getCol());
                    case "k":
                        return pieceFactory.createPieceInstance(TypeOfPiece.WHITE_KNIGHT, 0, tranformedPiece.getCol());
                    case "b":
                        return pieceFactory.createPieceInstance(TypeOfPiece.WHITE_BISHOP, 0, tranformedPiece.getCol());
                    case "r":
                        return pieceFactory.createPieceInstance(TypeOfPiece.WHITE_ROOK, 0, tranformedPiece.getCol());
                    default:
                        break;
                }
            }
        }
        return pieceToCheck;
    }

    public boolean checkIfCheck(Tile[][] tiles) {

        isCheck = false;
        if (turn == Turn.BLACK) {

            isCheck = (tiles[blackKingPos[0]][blackKingPos[1]].getTileState() == TileState.ATTACKED_BY_WHITE ||
                    tiles[blackKingPos[0]][blackKingPos[1]].getTileState() == TileState.ATTACKED_BY_BOTH);

        } else {

            isCheck = (tiles[whiteKingPos[0]][whiteKingPos[1]].getTileState() == TileState.ATTACKED_BY_BLACK ||
                    tiles[whiteKingPos[0]][whiteKingPos[1]].getTileState() == TileState.ATTACKED_BY_BOTH);

        }
        return isCheck;
    }


    public boolean checkIfMoveIsValid(int row, int col) {
        boolean moveIsValid = false;

        Tile potentialTileToMoveTo = chessBoard.getChessBoard()[row][col];
        int[] posOfWhiteKing = new int[]{getWhiteKingPos()[0], getWhiteKingPos()[1]};
        int[] posOfBlackKing = new int[]{getBlackKingPos()[0], getBlackKingPos()[1]};
        if ((potentialTileToMoveTo.getPiece() == TypeOfPiece.EMPTY || getColorOf(potentialTileToMoveTo.getPiece()) != turn)) {
            Tile[][] createDummyBoard = copyBoard();
            dummyBoard = createDummyBoard;
            moveDummy(createDummyBoard, row, col);
            moveIsValid = !checkIfCheck(createDummyBoard);
            setWhiteKingPos(posOfWhiteKing);
            setBlackKingPos(posOfBlackKing);
        }

        return moveIsValid;
    }

    private void moveDummy(Tile[][] tiles, int row, int col) {
        Tile selectedTile = tiles[row][col];

        if (pieceThatIsBeingMoved.getTypeOfPiece() == TypeOfPiece.BLACK_KING || pieceThatIsBeingMoved.getTypeOfPiece() == TypeOfPiece.WHITE_KING) {
            if (getColorOf(pieceThatIsBeingMoved.getTypeOfPiece()) == Turn.BLACK) {
                setBlackKingPos(new int[]{selectedTile.getTileRow(), selectedTile.getTileCol()});
            } else {
                setWhiteKingPos(new int[]{selectedTile.getTileRow(), selectedTile.getTileCol()});
            }
        }


        selectedTile.setPieceInstance(tiles[currentSelectedRow][currentSelectedCol].getPieceInstance());
        selectedTile.setPieceTo(tiles[currentSelectedRow][currentSelectedCol].getPiece());
        selectedTile.getPieceInstance().setRow(row);

        selectedTile.getPieceInstance().setCol(col);


        tiles[currentSelectedRow][currentSelectedCol].setPieceInstance(null);

        tiles[currentSelectedRow][currentSelectedCol].setPieceTo(TypeOfPiece.EMPTY);

        tiles[currentSelectedRow][currentSelectedCol].paintTile();


        isDummyMove = true;
//        selectedTile.getPieceInstance().setTilesThatAreAttacked(row, col);
        chessBoard.repaintTiles(tiles);
    }

    private Tile[][] copyBoard() {
        Tile[][] copiedBoard = new Tile[8][8];
        PieceFactory pieceFactory = new PieceFactory();
        pieceFactory.setGameController(this);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                Tile tileToCopy = chessBoard.getChessBoard()[i][j];
                copiedBoard[i][j] = new Tile(i, j, tileToCopy.getColorOfTile(), this);
                copiedBoard[i][j].setPieceTo(tileToCopy.getPiece());
                if (tileToCopy.getPieceInstance() != null) {
                    copiedBoard[i][j].setPieceInstance(pieceFactory.createPieceInstance(tileToCopy.getPiece(), i, j));
                }
            }

        return copiedBoard;
    }

    public boolean checkIfKingCanMove(int row, int col) {

        boolean canMove = false;
        Tile potentialTileToMoveTo = chessBoard.getChessBoard()[row][col];
        int[] posOfWhiteKing = new int[]{getWhiteKingPos()[0], getWhiteKingPos()[1]};
        int[] posOfBlackKing = new int[]{getBlackKingPos()[0], getBlackKingPos()[1]};
        if (potentialTileToMoveTo.getPiece() == TypeOfPiece.EMPTY || getColorOf(potentialTileToMoveTo.getPiece()) != turn) {

            int checkOppositeAttack = (turn == Turn.BLACK) ? 10 : -10;
            if (potentialTileToMoveTo.getTileState().typeOfState == checkOppositeAttack || potentialTileToMoveTo.getTileState().typeOfState == 20) {
                return false;
            }
            Tile[][] createDummyBoard = copyBoard();
            dummyBoard = createDummyBoard;
            moveDummy(createDummyBoard, row, col);
            canMove = !checkIfCheck(createDummyBoard);
            setWhiteKingPos(posOfWhiteKing);
            setBlackKingPos(posOfBlackKing);
            return canMove;

        }


        return canMove;
    }


    public boolean checkIfCanTake(int row, int col) {
        boolean canTake = false;

        int[] posOfWhiteKing = new int[]{getWhiteKingPos()[0], getWhiteKingPos()[1]};
        int[] posOfBlackKing = new int[]{getBlackKingPos()[0], getBlackKingPos()[1]};
        isDummyMove = false;
        if (getColorOf(chessBoard.getChessBoard()[row][col].getPiece()) != turn && chessBoard.getChessBoard()[row][col].getPiece() != TypeOfPiece.EMPTY) {
            Tile[][] createDummyBoard = copyBoard();
            dummyBoard = createDummyBoard;
            moveDummy(createDummyBoard, row, col);
            canTake = !checkIfCheck(createDummyBoard);
            setWhiteKingPos(posOfWhiteKing);
            setBlackKingPos(posOfBlackKing);


        }
        return canTake;
    }

    private void resetTileStates() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                chessBoard.getChessBoard()[i][j].setTileState(TileState.NORMAL);
            }
    }

    private void resetPieceStates() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                try {
                    chessBoard.getChessBoard()[i][j].getPieceInstance().setState(State.Free);
                } catch (NullPointerException e) {

                }
            }
    }

    public Turn getColorOf(TypeOfPiece selectedPiece) {
        if (selectedPiece.imagePath.contains("black")) {
            return Turn.BLACK;
        } else {
            return Turn.WHITE;
        }
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public Tile[][] getChessBoard() {
        if (!isDummyMove) {
            return chessBoard.getChessBoard();
        }
        return dummyBoard;
    }

    public Piece getPieceThatIsBeingMoved() {
        return pieceThatIsBeingMoved;
    }

    public int getNumberOfMovesForBlackKing() {
        return numberOfMovesForBlackKing;
    }

    public void setNumberOfMovesForBlackKing(int numberOfMovesForBlackKing) {
        this.numberOfMovesForBlackKing = numberOfMovesForBlackKing;
    }

    public int getNumberOfMovesForWhiteKing() {
        return numberOfMovesForWhiteKing;
    }

    public void setNumberOfMovesForWhiteKing(int numberOfMovesForWhiteKing) {
        this.numberOfMovesForWhiteKing = numberOfMovesForWhiteKing;
    }

    public int[] getWhiteKingPos() {
        return whiteKingPos;
    }

    public void setWhiteKingPos(int[] whiteKingPos) {
        this.whiteKingPos = whiteKingPos;
    }

    public int[] getBlackKingPos() {
        return blackKingPos;
    }

    public void setDummyMove(boolean dummyMove) {
        isDummyMove = dummyMove;
    }

    public void setBlackKingPos(int[] blackKingPos) {
        this.blackKingPos = blackKingPos;
    }

    public static void main(String[] args) {
        GameController gameController = new GameController(null);
        gameController.getColorOf(TypeOfPiece.WHITE_BISHOP);
    }
}
