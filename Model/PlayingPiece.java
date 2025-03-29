package TicTac.Model;

public class PlayingPiece {
    public Piece piece;
    public PlayingPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}