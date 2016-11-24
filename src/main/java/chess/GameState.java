package chess;


import chess.pieces.*;

import java.util.*;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    /**
     * A map of board positions to pieces at that position
     */
    protected Map<Position, Piece> positionToPieceMap;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<Position, Piece>();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));
    }

    public void clearBoard() {
        positionToPieceMap.clear();
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return positionToPieceMap.get(position);
    }

    private boolean removePieceAt(Position position) {
        if(positionToPieceMap.containsKey(position)) {
            positionToPieceMap.remove(position);
            return true;
        }

        return false;
    }

    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     * @param position The position
     */
    private void placePiece(Piece piece, Position position) {
        positionToPieceMap.put(position, piece);
    }

    public boolean move(String colrowFrom, String colrowTo) {
        Position from = new Position(colrowFrom);
        Position to = new Position(colrowTo);
        boolean isMoved = false;

        if(positionToPieceMap.containsKey(from)) {
            Piece piece = getPieceAt(from);
            piece.setBoard(positionToPieceMap);
            if(getCurrentPlayer() == piece.getOwner()) {
                if (piece.move(from, to)) {
                    if(!isCheckAfterMove(from, to)) {
                        removePieceAt(to);
                        removePieceAt(from);
                        placePiece(piece, to);
                        isMoved = true;
                    }
                }
            }
        }

        return isMoved;
    }


    public List<Move> getAllPlayerPossibleMoves() {
        List<Move> positions = new ArrayList<Move>();

        Map<Position, Piece> pieces = getAllPlayerPieces();
        for(Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            Position from = entry.getKey();
            Piece piece = entry.getValue();
            piece.setBoard(positionToPieceMap);
            List<Position> steps = piece.getAllSteps(from);
            for(Position step : steps) {
                if(!isCheckAfterMove(from, step)) {
                    positions.add(new Move(from, step));
                }
            }
        }

        return positions;
    }

    private Map<Position, Piece> getAllPlayerPieces() {
        return getPiecesByPlayer(getCurrentPlayer(), positionToPieceMap);
    }

    private Map<Position, Piece> getAllEnemies(Map<Position, Piece> board) {
        Player enemy = (getCurrentPlayer() == Player.Black) ? Player.White : Player.Black;
        return getPiecesByPlayer(enemy, board);
    }

    private Map<Position, Piece> getPiecesByPlayer(Player player, Map<Position, Piece> board) {
        Map<Position, Piece> pieces = new HashMap<Position, Piece>();

        for(Map.Entry<Position, Piece> entry : board.entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            if(piece.getOwner() == player) {
                pieces.put(position, piece);
            }
        }

        return pieces;
    }

    private boolean isCheckAfterMove(Position from, Position to) {
        Set<Position> allSteps = new HashSet<Position>();
        Board board = new Board(this);

        board.addPiece(to.toString(), getPieceAt(from));
        board.removePiece(from.toString());

        Map<Position, Piece> map = board.getBoard();
        Map<Position, Piece> enemies = getAllEnemies(map);
        for(Map.Entry<Position, Piece> entry : enemies.entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            piece.setBoard(board.getBoard());
            allSteps.addAll(piece.getAllSteps(position));
        }

        return allSteps.contains(getPositionKing(map));
    }

    public boolean isCheck() {
        Set<Position> allSteps = new HashSet<Position>();
        Board board = new Board(this);

        Map<Position, Piece> map = board.getBoard();
        Map<Position, Piece> enemies = getAllEnemies(map);
        for(Map.Entry<Position, Piece> entry : enemies.entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            piece.setBoard(board.getBoard());
            allSteps.addAll(piece.getAllSteps(position));
        }

        return allSteps.contains(getPositionKing(map));
    }

    public boolean isDraw() {
        return getAllPlayerPossibleMoves().isEmpty();
    }

    private Position getPositionKing(Map<Position, Piece> board) {

        Position position = null;
        for(Map.Entry<Position, Piece> entry : board.entrySet()) {
            position = entry.getKey();
            Piece piece = entry.getValue();
            if(piece instanceof King && piece.getOwner() == getCurrentPlayer()) {
                return position;
            }
        }

        return null;
    }

}
