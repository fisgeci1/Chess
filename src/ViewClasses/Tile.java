package ViewClasses;

import Controlls.GameController;
import Listeners.ListnerOfDaMouse;
import Pieces.Piece;
import Pieces.TileState;
import Pieces.TypeOfPiece;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

    private Color colorOfTile;

    private TypeOfPiece pieceType = TypeOfPiece.EMPTY;

    private TileState tileState = TileState.NORMAL;
    private Piece pieceInstance;

    private JLabel piece = new JLabel();
    private int tileRow, tileCol;


    public TileState getTileState() {
        return tileState;
    }

    public void setTileState(TileState tileState) {
        this.tileState = tileState;
    }


    public Tile(int tileRow, int tileCol, Color colorOfTile, GameController gameController) {
        this.addMouseListener(new ListnerOfDaMouse(gameController));
        this.colorOfTile = colorOfTile;
        this.tileRow = tileRow;
        this.tileCol = tileCol;
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(colorOfTile);
        g.fillRect(0, 0, 100, 100);
        this.revalidate();
        if (pieceType != TypeOfPiece.EMPTY) {
            this.add(piece);
        } else {
            if (tileState == TileState.AVAILABLE_MOVE) {
                g.setColor(Color.GRAY);
                g.fillOval(20, 20, 40, 40);
            } else {
                removeAll();
            }

        }

    }

    public void paintTile() {
        repaint();
    }

    public Color getColorOfTile() {
        return colorOfTile;
    }


    public int getTileRow() {
        return tileRow;
    }


    public void setPieceImage(JLabel piece) {
        this.piece = piece;
    }

    public int getTileCol() {
        return tileCol;
    }


    public void setPieceTo(TypeOfPiece piece) {
        this.pieceType = piece;
    }

    public TypeOfPiece getPiece() {
        return pieceType;
    }

    public Piece getPieceInstance() {
        return pieceInstance;
    }

    public void setPieceInstance(Piece pieceInstance) {
        this.pieceInstance = pieceInstance;
    }
}
