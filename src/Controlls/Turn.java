package Controlls;

public enum Turn {

    BLACK(-1),
    WHITE(1);

    public final int colorOfPiece;

    private Turn(int num) {
        this.colorOfPiece = num;
    }

}
