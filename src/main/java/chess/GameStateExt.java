package chess;

import chess.pieces.Piece;

import java.util.Map;

/**
 * Created by User on 14.04.2016.
 */
public class GameStateExt extends GameState {

    public void setBoard(Map<Position, Piece> positionToPieceMap) {
        this.positionToPieceMap = positionToPieceMap;
    }
}
