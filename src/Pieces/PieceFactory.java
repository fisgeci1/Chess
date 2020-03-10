package Pieces;

import Controlls.GameController;
import Controlls.Turn;
import ViewClasses.ImageParser;
import ViewClasses.Tile;

public class PieceFactory {

    private GameController gameController;
    private ImageParser imageParser = new ImageParser();

    public Piece createPieceInstance(TypeOfPiece typeOfPiece, int row, int col) {
        switch (typeOfPiece) {
            case BLACK_PAWN:
                Pawn blackPawn = new Pawn(typeOfPiece, row, col, Turn.BLACK);
                blackPawn.setGameController(gameController);
                return blackPawn;
            case BLACK_ROOK:
                Rook bRook = new Rook(typeOfPiece, row, col, Turn.BLACK);
                bRook.setGameController(gameController);
                return bRook;
            case BLACK_BISHOP:
                Bishop blackBishop = new Bishop(typeOfPiece, row, col, Turn.BLACK);
                blackBishop.setGameController(gameController);
                return blackBishop;
            case BLACK_KNIGHT:
                Knight blackKnight = new Knight(typeOfPiece, row, col, Turn.BLACK);
                blackKnight.setGameController(gameController);
                return blackKnight;
            case BLACK_QUEEN:
                Queen blackQueen = new Queen(typeOfPiece, row, col, Turn.BLACK);
                blackQueen.setGameController(gameController);
                return blackQueen;
            case BLACK_KING:
                King blackKing = new King(typeOfPiece, row, col, Turn.BLACK);
                blackKing.setGameController(gameController);
                return blackKing;
            case WHITE_PAWN:
                Pawn pawn = new Pawn(typeOfPiece, row, col, Turn.WHITE);
                pawn.setGameController(gameController);
                return pawn;
            case WHITE_ROOK:
                Rook rook = new Rook(typeOfPiece, row, col, Turn.WHITE);
                rook.setGameController(gameController);
                return rook;
            case WHITE_BISHOP:
                Bishop bishop = new Bishop(typeOfPiece, row, col, Turn.WHITE);
                bishop.setGameController(gameController);
                return bishop;
            case WHITE_KNIGHT:
                Knight knight = new Knight(typeOfPiece, row, col, Turn.WHITE);
                knight.setGameController(gameController);
                return knight;
            case WHITE_QUEEN:
                Queen queen = new Queen(typeOfPiece, row, col, Turn.WHITE);
                queen.setGameController(gameController);
                return queen;
            case WHITE_KING:
                King king = new King(typeOfPiece, row, col, Turn.WHITE);
                king.setGameController(gameController);
                return king;
            case EMPTY:
                return null;
        }
        return null;
    }

    public void setUpPawns(Tile[][] chessBoard) {
        for (int i = 0; i < 8; i++) {
            chessBoard[1][i].setPieceTo(TypeOfPiece.BLACK_PAWN);
            chessBoard[1][i].setPieceInstance(this.createPieceInstance(TypeOfPiece.BLACK_PAWN, 1, i));
            chessBoard[1][i].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.BLACK_PAWN));
            chessBoard[1][i].getPieceInstance().setGameController(gameController);

            chessBoard[6][i].setPieceTo(TypeOfPiece.WHITE_PAWN);
            chessBoard[6][i].setPieceInstance(this.createPieceInstance(TypeOfPiece.WHITE_PAWN, 6, i));
            chessBoard[6][i].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.WHITE_PAWN));
            chessBoard[6][i].getPieceInstance().setGameController(gameController);

        }

    }

    public void setUpRooks(Tile[][] chessBoard) {
        chessBoard[0][0].setPieceTo(TypeOfPiece.BLACK_ROOK);
        chessBoard[0][0].setPieceInstance(this.createPieceInstance(TypeOfPiece.BLACK_ROOK, 0, 0));
        chessBoard[0][0].getPieceInstance().setGameController(gameController);
        chessBoard[0][0].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.BLACK_ROOK));

        chessBoard[0][7].setPieceTo(TypeOfPiece.BLACK_ROOK);
        chessBoard[0][7].setPieceInstance(this.createPieceInstance(TypeOfPiece.BLACK_ROOK, 0, 7));
        chessBoard[0][7].getPieceInstance().setGameController(gameController);
        chessBoard[0][7].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.BLACK_ROOK));


        chessBoard[7][0].setPieceTo(TypeOfPiece.WHITE_ROOK);
        chessBoard[7][0].setPieceInstance(this.createPieceInstance(TypeOfPiece.WHITE_ROOK, 7, 0));
        chessBoard[7][0].getPieceInstance().setGameController(gameController);
        chessBoard[7][0].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.WHITE_ROOK));

        chessBoard[7][7].setPieceTo(TypeOfPiece.WHITE_ROOK);
        chessBoard[7][7].setPieceInstance(this.createPieceInstance(TypeOfPiece.WHITE_ROOK, 7, 7));
        chessBoard[7][7].getPieceInstance().setGameController(gameController);
        chessBoard[7][7].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.WHITE_ROOK));


    }

    public void setUpKnights(Tile[][] chessBoard) {

        chessBoard[0][1].setPieceTo(TypeOfPiece.BLACK_KNIGHT);
        chessBoard[0][1].setPieceInstance(this.createPieceInstance(TypeOfPiece.BLACK_KNIGHT, 0, 1));
        chessBoard[0][1].getPieceInstance().setGameController(gameController);
        chessBoard[0][1].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.BLACK_KNIGHT));
        chessBoard[0][6].setPieceTo(TypeOfPiece.BLACK_KNIGHT);
        chessBoard[0][6].setPieceInstance(this.createPieceInstance(TypeOfPiece.BLACK_KNIGHT, 0, 6));
        chessBoard[0][6].getPieceInstance().setGameController(gameController);
        chessBoard[0][6].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.BLACK_KNIGHT));


        chessBoard[7][6].setPieceTo(TypeOfPiece.WHITE_KNIGHT);
        chessBoard[7][6].setPieceInstance(this.createPieceInstance(TypeOfPiece.WHITE_KNIGHT, 7, 6));
        chessBoard[7][6].getPieceInstance().setGameController(gameController);
        chessBoard[7][6].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.WHITE_KNIGHT));

        chessBoard[7][1].setPieceTo(TypeOfPiece.WHITE_KNIGHT);
        chessBoard[7][1].setPieceInstance(this.createPieceInstance(TypeOfPiece.WHITE_KNIGHT, 7, 1));
        chessBoard[7][1].getPieceInstance().setGameController(gameController);
        chessBoard[7][1].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.WHITE_KNIGHT));


    }

    public void setUpBishops(Tile[][] chessBoard) {
        chessBoard[0][5].setPieceTo(TypeOfPiece.BLACK_BISHOP);
        chessBoard[0][5].setPieceInstance(this.createPieceInstance(TypeOfPiece.BLACK_BISHOP, 0, 5));
        chessBoard[0][5].getPieceInstance().setGameController(gameController);
        chessBoard[0][5].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.BLACK_BISHOP));

        chessBoard[0][2].setPieceTo(TypeOfPiece.BLACK_BISHOP);
        chessBoard[0][2].setPieceInstance(this.createPieceInstance(TypeOfPiece.BLACK_BISHOP, 0, 2));
        chessBoard[0][2].getPieceInstance().setGameController(gameController);
        chessBoard[0][2].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.BLACK_BISHOP));


        chessBoard[7][2].setPieceTo(TypeOfPiece.WHITE_BISHOP);
        chessBoard[7][2].setPieceInstance(this.createPieceInstance(TypeOfPiece.WHITE_BISHOP, 7, 2));
        chessBoard[7][2].getPieceInstance().setGameController(gameController);
        chessBoard[7][2].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.WHITE_BISHOP));

        chessBoard[7][5].setPieceTo(TypeOfPiece.WHITE_BISHOP);
        chessBoard[7][5].setPieceInstance(this.createPieceInstance(TypeOfPiece.WHITE_BISHOP, 7, 5));
        chessBoard[7][5].getPieceInstance().setGameController(gameController);
        chessBoard[7][5].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.WHITE_BISHOP));


    }

    public void setUpKingAndQueens(Tile[][] chessBoard) {

        chessBoard[7][4].setPieceTo(TypeOfPiece.WHITE_KING);
        chessBoard[7][4].setPieceInstance(this.createPieceInstance(TypeOfPiece.WHITE_KING, 7, 4));
        chessBoard[7][4].getPieceInstance().setGameController(gameController);
        chessBoard[7][4].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.WHITE_KING));

        chessBoard[7][3].setPieceTo(TypeOfPiece.WHITE_QUEEN);
        chessBoard[7][3].setPieceInstance(this.createPieceInstance(TypeOfPiece.WHITE_QUEEN, 7, 3));
        chessBoard[7][3].getPieceInstance().setGameController(gameController);
        chessBoard[7][3].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.WHITE_QUEEN));


        chessBoard[0][4].setPieceTo(TypeOfPiece.BLACK_KING);
        chessBoard[0][4].setPieceInstance(this.createPieceInstance(TypeOfPiece.BLACK_KING, 0, 4));
        chessBoard[0][4].getPieceInstance().setGameController(gameController);
        chessBoard[0][4].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.BLACK_KING));

        chessBoard[0][3].setPieceTo(TypeOfPiece.BLACK_QUEEN);
        chessBoard[0][3].setPieceInstance(this.createPieceInstance(TypeOfPiece.BLACK_QUEEN, 0, 3));
        chessBoard[0][3].getPieceInstance().setGameController(gameController);
        chessBoard[0][3].setPieceImage(imageParser.getLabelIconOfPiece(TypeOfPiece.BLACK_QUEEN));

    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public GameController getGameController() {
        return gameController;
    }
}
