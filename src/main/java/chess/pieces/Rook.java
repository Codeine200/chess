package chess.pieces;

import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    public Rook(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }

    @Override
    public boolean move(Position from, Position to) {
        if (to.getColumn() == from.getColumn() || to.getRow() == from.getRow()) {
            return getAllSteps(from).contains(to);
        }

        return  false;
    }

    @Override
    public List<Position> getAllSteps(Position from) {
        List<Position> allSteps = new ArrayList<Position>();

        int j = from.getColumn() + 1;
        int i = from.getRow();
        while(j <= Position.MAX_COLUMN) {
            if(!addStep(i, j++, allSteps)) break;
        }

        j = from.getColumn();
        i = from.getRow() + 1;
        while(i <= Position.MAX_ROW) {
            if(!addStep(i++, j, allSteps)) break;
        }

        j = from.getColumn() - 1;
        i = from.getRow();
        while(j >= Position.MIN_COLUMN) {
            if(!addStep(i, j--, allSteps)) break;
        }

        j = from.getColumn();
        i = from.getRow() - 1;
        while(i >= Position.MIN_ROW) {
            if(!addStep(i--, j, allSteps)) break;
        }

        return allSteps;
    }

}
