package chess.pieces;

import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The King class
 */
public class King extends Piece {
    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }

    @Override
    public boolean move(Position from, Position to) {
        double destination = Math.pow(Character.getNumericValue(to.getColumn()) - Character.getNumericValue(from.getColumn()), 2) + Math.pow(to.getRow() - from.getRow(), 2);
        if (destination == 1 || destination == 2) {
            return getAllSteps(from).contains(to);
        }

        return  false;
    }

    @Override
    public List<Position> getAllSteps(Position from) {
        List<Position> allSteps = new ArrayList<Position>();

        int[][] steps = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};

        int j = from.getColumn();
        int i = from.getRow();
        for(int[] step : steps) {
            if(i + step[1] >= Position.MIN_ROW && i + step[1] <= Position.MAX_ROW &&
                    (char)(j + step[0]) >= Position.MIN_COLUMN && (char)(j + step[0]) <= Position.MAX_COLUMN) {
                addStep(i + step[1], j + step[0], allSteps);
            }
        }

        return allSteps;
    }

}
