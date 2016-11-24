package chess.pieces;

import chess.Player;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * The Pawn
 */
public class Pawn extends Piece {
    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

    @Override
    public boolean move(Position from, Position to) {
        return getAllSteps(from).contains(to);
    }

    @Override
    public List<Position> getAllSteps(Position from) {
        List<Position> allSteps = new ArrayList<Position>();

        if(getOwner() == Player.White) {
            int j = from.getColumn();
            int i = from.getRow() + 1;

            addAttackStep(i, j + 1, allSteps);
            addAttackStep(i, j - 1, allSteps);

            if(from.getRow() == 2) {
                for(int n=1; n<=2; n++) {
                    if (!addStraightStep(i++, j, allSteps)) break;
                }
            } else {
                addStraightStep(i, j, allSteps);
            }
        } else {
            int j = from.getColumn();
            int i = from.getRow() - 1;

            addAttackStep(i, j + 1, allSteps);
            addAttackStep(i, j - 1, allSteps);

            if(from.getRow() == 7) {
                for(int n=1; n<=2; n++) {
                    if (!addStraightStep(i--, j, allSteps)) break;
                }
            } else {
                addStraightStep(i, j, allSteps);
            }
        }

        return allSteps;
    }

    private boolean addStraightStep(int i, int j, List<Position> allSteps) {
        if(positionToPieceMap == null)
            return false;

        Position position = new Position((char)j + Integer.toString(i));
        if (!positionToPieceMap.containsKey(position)) { // free cell
            allSteps.add(position);
            return true;
        }

        return false;
    }

    private boolean addAttackStep(int i, int j, List<Position> allSteps) {
        if(positionToPieceMap == null)
            return false;

        Position position = new Position((char)j + Integer.toString(i));
        if (isEnemy(position)) {
            allSteps.add(position);
        }

        return false;
    }
}
