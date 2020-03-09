package Pieces;

public enum TileState {
    NORMAL(0),
    AVAILABLE_MOVE(1),
    ATTACKING(12),
    ATTACKED_BY_WHITE(10),
    ATTACKED_BY_BLACK(-10),
    ATTACKED_BY_BOTH(20);

    public final int typeOfState;

    private TileState(int typeOfState) {
        this.typeOfState = typeOfState;
    }
}
