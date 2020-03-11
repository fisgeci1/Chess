package ViewClasses;

import Controlls.GameController;
import Controlls.Turn;
import Pieces.Piece;
import Pieces.PieceFactory;
import Pieces.State;
import Pieces.TileState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    private final int rows = 8;
    private final int cols = 8;
    private GameController gameController = new GameController(this);

    private Tile[][] chessBoard = new Tile[rows][cols];
    private Tile[][] testBoard = new Tile[rows][cols];

    public Board() {


        GridLayout grid = new GridLayout(8, 8);
        setLayout(grid);


        setSize(800, 800);


        createChessBoard();
        setUpBoard();
        repaintTiles(chessBoard);
    }

    public void createChessBoard() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                Color tileColor = ((i + j) % 2 == 0) ? Color.BLACK : Color.white;
                chessBoard[i][j] = new Tile(i, j, tileColor, gameController);
                this.add(chessBoard[i][j]);
            }
    }


    @Override
    protected void paintComponent(Graphics g) {
        repaint();
    }

    public void repaintTiles(Tile[][] tiles) {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                tiles[i][j].paintTile();


                if (tiles[i][j].getPieceInstance() != null) {
                    tiles[i][j].getPieceInstance().setTilesThatAreAttacked(i, j);
                    setTileStates(tiles, tiles[i][j].getPieceInstance().getTilesBeingAttacked(), tiles[i][j].getPieceInstance());
                    setPieceStates(tiles[i][j].getPieceInstance().getPiecesThatArePinned());
                }
            }

    }

    private void setTileStates(Tile[][] tiles, ArrayList<int[]> cords, Piece pieceThatIsAttacking) {

        try {
            cords.forEach(tileCord -> {
                if (tiles[tileCord[0]][tileCord[1]].getTileState() != TileState.AVAILABLE_MOVE) {

                    Turn colorOfPieceThatIsAttacking = gameController.getColorOf(pieceThatIsAttacking.getTypeOfPiece());

                    TileState whatColorIsAttacking = (colorOfPieceThatIsAttacking == Turn.BLACK) ? TileState.ATTACKED_BY_BLACK : TileState.ATTACKED_BY_WHITE;

                    if (tiles[tileCord[0]][tileCord[1]].getTileState() != whatColorIsAttacking && tiles[tileCord[0]][tileCord[1]].getTileState() != TileState.NORMAL) {
                        whatColorIsAttacking = TileState.ATTACKED_BY_BOTH;
                    }
                    tiles[tileCord[0]][tileCord[1]].setTileState(whatColorIsAttacking);
                }

            });
        } catch (Exception e) {

        }

    }

    private void setPieceStates(ArrayList<int[]> cords) {

        try {
            cords.forEach(pieceCord -> {
                System.out.println(chessBoard[pieceCord[0]][pieceCord[1]].getPieceInstance().getTypeOfPiece());
                chessBoard[pieceCord[0]][pieceCord[1]].getPieceInstance().setState(State.Pinned);
            });
        } catch (Exception e) {

        }

    }

    private void setUpBoard() {
        PieceFactory pieceFactory = new PieceFactory();

        pieceFactory.setGameController(gameController);
        pieceFactory.setUpPawns(chessBoard);


//        pieceFactory.setUpRooks(chessBoard);
//
//
//        pieceFactory.setUpKnights(chessBoard);
//
//
        pieceFactory.setUpBishops(chessBoard);
//
//
//        pieceFactory.setUpKingAndQueens(chessBoard);


    }

    public Tile[][] getTestBoard() {
        return testBoard;
    }

    public void setTestBoard(Tile[][] testBoard) {
        this.testBoard = testBoard;
    }

    public Tile[][] getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(Tile[][] chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
