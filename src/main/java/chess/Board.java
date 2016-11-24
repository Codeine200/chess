package chess;

import chess.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private Map<Position, Piece> board = new HashMap<Position, Piece>();
    private GameState gameState = null;

    Board() {

    }

    Board(GameState gameState) {
        this.gameState = gameState;
        init();
    }

    private void init() {
        if(gameState != null) {
            for (char j = Position.MIN_COLUMN; j <= Position.MAX_COLUMN; j++) {
                for (int i = Position.MIN_ROW; i <= Position.MAX_ROW; i++) {
                    Position position = new Position(j + Integer.toString(i));
                    Piece piece = gameState.getPieceAt(position);
                    if (piece != null) {
                        board.put(position, piece);
                    }
                }
            }
        }
    }

    public boolean removePiece(String strPosition) {
        Position position = new Position(strPosition);

        Map<Position, Piece> board = getBoard();
        if(!board.isEmpty() && board.containsKey(position)) {
            board.remove(position);
            return true;
        }

        return  false;
    }

    public void addPiece(String strPosition, Piece piece) {
        Position position = new Position(strPosition);
        Map<Position, Piece> board = getBoard();
        board.put(position, piece);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
