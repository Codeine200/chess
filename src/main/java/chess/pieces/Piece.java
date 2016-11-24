package chess.pieces;

import chess.Player;
import chess.Position;

import java.util.*;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;
    Map<Position, Piece> positionToPieceMap;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }

    protected abstract char getIdentifyingCharacter();

    public abstract boolean move(Position from, Position to);

    public boolean move(String from, String to) {
        return move(new Position(from), new Position(to));
    }

    public abstract List<Position> getAllSteps(Position from);

    public void setBoard(Map<Position, Piece> positionToPieceMap) {
        this.positionToPieceMap = positionToPieceMap;
    }

    protected boolean isEnemy(Position position) {
        if(positionToPieceMap == null)
            return false;

        if(positionToPieceMap.containsKey(position)) {
            return positionToPieceMap.get(position).getOwner() != this.getOwner();
        }

        return false;
    }

    protected boolean addStep(int i, int j, List<Position> allSteps) {
        if(positionToPieceMap == null)
            return false;

        Position position = new Position((char)j + Integer.toString(i));
        if (!positionToPieceMap.containsKey(position)) { // free cell
            allSteps.add(position);
            return true;
        } else if (isEnemy(position)) {
            allSteps.add(position);
        }

        return false;
    }


}
