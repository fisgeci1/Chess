package Pieces;

public enum TypeOfPiece {
    BLACK_PAWN("/home/behso/Desktop/Chess/src/images/blackPieces/blackPawn.png"),
    BLACK_ROOK("/home/behso/Desktop/Chess/src/images/blackPieces/blackRook.png"),
    BLACK_BISHOP("/home/behso/Desktop/Chess/src/images/blackPieces/blackBishop.png"),
    BLACK_KNIGHT("/home/behso/Desktop/Chess/src/images/blackPieces/blackKnight.png"),
    BLACK_QUEEN("/home/behso/Desktop/Chess/src/images/blackPieces/blackQueen.png"),
    BLACK_KING("/home/behso/Desktop/Chess/src/images/blackPieces/blackKing.png"),
    WHITE_PAWN("/home/behso/Desktop/Chess/src/images/whitePieces/whitePawn.png"),
    WHITE_ROOK("/home/behso/Desktop/Chess/src/images/whitePieces/whiteRook.png"),
    WHITE_BISHOP("/home/behso/Desktop/Chess/src/images/whitePieces/whiteBishop.png"),
    WHITE_KNIGHT("/home/behso/Desktop/Chess/src/images/whitePieces/whiteKnight.png"),
    WHITE_QUEEN("/home/behso/Desktop/Chess/src/images/whitePieces/whiteQueen.png"),
    WHITE_KING("/home/behso/Desktop/Chess/src/images/whitePieces/whiteKing.png"),
    EMPTY("");

    public final String imagePath;

    private TypeOfPiece(String imagePath) {
        this.imagePath = imagePath;
    }
}
