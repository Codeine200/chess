package chess.pieces;

import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Queen
 */
public class Queen extends Piece{
    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }

    @Override
    public boolean move(Position from, Position to) {
        if(Math.abs(Character.getNumericValue(to.getColumn()) - Character.getNumericValue(from.getColumn())) == Math.abs(to.getRow() - from.getRow())) {
            return getAllSteps(from).contains(to);
        }

        if(to.getColumn() == from.getColumn() || to.getRow() == from.getRow()) {
            return getAllSteps(from).contains(to);
        }

        return false;
    }

    @Override
    public List<Position> getAllSteps(Position from) {
        List<Position> allSteps = new ArrayList<Position>();

        int j = from.getColumn() + 1;
        int i = from.getRow() + 1;
        while(j <= Position.MAX_COLUMN && i <= Position.MAX_ROW) {
            if(!addStep(i++, j++, allSteps)) break;
        }

        j = from.getColumn() + 1;
        i = from.getRow() - 1;
        while(j <= Position.MAX_COLUMN && i >= Position.MIN_ROW) {
            if(!addStep(i--, j++, allSteps)) break;
        }

        j = from.getColumn() - 1;
        i = from.getRow() + 1;
        while(j >= Position.MIN_COLUMN && i <= Position.MAX_ROW) {
            if(!addStep(i++, j--, allSteps)) break;
        }

        j = from.getColumn() - 1;
        i = from.getRow() - 1;
        while(j >= Position.MIN_COLUMN && i >= Position.MIN_ROW) {
            if(!addStep(i--, j--, allSteps)) break;
        }

        j = from.getColumn() + 1;
        i = from.getRow();
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
