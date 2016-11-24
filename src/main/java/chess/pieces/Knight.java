package chess.pieces;

import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

    @Override
    public boolean move(Position from, Position to) {
        return getAllSteps(from).contains(to);
    }

    @Override
    public List<Position> getAllSteps(Position from) {
        List<Position> allSteps = new ArrayList<Position>();

        int[][] steps = {{2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}};

        char j = from.getColumn();
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
