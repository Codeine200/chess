package chess;

import chess.pieces.*;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.*;

/**
 * Unit tests for check moving chess figures
 */
public class MoveTest {

    @Test
    public void testMoveBishopOnBoard() {
        GameState gameState = new GameState();
        gameState.reset();
        Board board = new Board(gameState);
        board.removePiece("g2");
        board.removePiece("e2");

        Bishop bishop = (Bishop)gameState.getPieceAt("f1");
        Map<Position, Piece> positionToPieceMap = board.getBoard();
        bishop.setBoard(positionToPieceMap);

        assertEquals("The move is true f1 -> g2", true, bishop.move("f1", "g2"));
        assertEquals("The move is true f1 -> h3", true, bishop.move("f1", "h3"));
        assertEquals("The move is true f1 -> e2", true, bishop.move("f1", "e2"));
        assertEquals("The move is true f1 -> d3", true, bishop.move("f1", "d3"));
        assertEquals("The move is true f1 -> c4", true, bishop.move("f1", "c4"));
        assertEquals("The move is true f1 -> b5", true, bishop.move("f1", "b5"));
        assertEquals("The move is true f1 -> a6", true, bishop.move("f1", "a6"));
    }

    @Test
    public void testMoveRookOnBoard() {
        GameState gameState = new GameState();
        gameState.reset();
        Board board = new Board(gameState);
        board.removePiece("g1");
        board.removePiece("f1");
        board.removePiece("h2");

        Rook rook = (Rook)gameState.getPieceAt("h1");
        Map<Position, Piece> positionToPieceMap = board.getBoard();
        rook.setBoard(positionToPieceMap);

        assertEquals("The move is true h1 -> h2", true, rook.move("h1", "h2"));
        assertEquals("The move is true h1 -> h2", true, rook.move("h1", "h2"));
        assertEquals("The move is true h1 -> h3", true, rook.move("h1", "h3"));
        assertEquals("The move is true h1 -> h4", true, rook.move("h1", "h4"));
        assertEquals("The move is true h1 -> h5", true, rook.move("h1", "h5"));
        assertEquals("The move is true h1 -> h6", true, rook.move("h1", "h6"));
        assertEquals("The move is true h1 -> h7", true, rook.move("h1", "h7"));
        assertEquals("The move is false h1 -> h8", false, rook.move("h1", "h8"));
        assertEquals("The move is true h1 -> g1", true, rook.move("h1", "g1"));
        assertEquals("The move is true h1 -> f1", true, rook.move("h1", "f1"));
        assertEquals("The move is false h1 -> e1", false, rook.move("h1", "e1"));
    }

    @Test
         public void testMoveKnightOnBoard() {
        GameState gameState = new GameState();
        gameState.reset();
        Board board = new Board(gameState);

        Knight knight = (Knight)gameState.getPieceAt("b1");
        Map<Position, Piece> positionToPieceMap = board.getBoard();
        knight.setBoard(positionToPieceMap);

        assertEquals("The move is true b1 -> a3", true, knight.move("b1", "a3"));
        assertEquals("The move is true b1 -> c3", true, knight.move("b1", "c3"));
        assertEquals("The move is false b1 -> b3", false, knight.move("b1", "b3"));
    }

    @Test
    public void testMoveQueenOnBoard() {
        GameState gameState = new GameState();
        gameState.reset();
        Board board = new Board(gameState);
        board.removePiece("d2");
        board.removePiece("c2");

        Queen queen  = (Queen)gameState.getPieceAt("d1");
        Map<Position, Piece> positionToPieceMap = board.getBoard();
        queen.setBoard(positionToPieceMap);

        assertEquals("The move is true d1 -> d2", true, queen.move("d1", "d2"));
        assertEquals("The move is true d1 -> d3", true, queen.move("d1", "d3"));
        assertEquals("The move is true d1 -> d4", true, queen.move("d1", "d4"));
        assertEquals("The move is true d1 -> d5", true, queen.move("d1", "d5"));
        assertEquals("The move is true d1 -> d6", true, queen.move("d1", "d6"));
        assertEquals("The move is true d1 -> d7", true, queen.move("d1", "d7"));
        assertEquals("The move is false d1 -> d8", false, queen.move("d1", "d8"));
        assertEquals("The move is true d1 -> c2", true, queen.move("d1", "c2"));
        assertEquals("The move is true d1 -> b3", true, queen.move("d1", "b3"));
        assertEquals("The move is true d1 -> a4", true, queen.move("d1", "a4"));
    }


    @Test
    public void testMoveKingOnBoard() {
        GameState gameState = new GameState();
        gameState.reset();
        Board board = new Board(gameState);
        board.removePiece("e2");
        board.removePiece("f2");
        board.removePiece("f1");

        King king = (King)gameState.getPieceAt("e1");
        Map<Position, Piece> positionToPieceMap = board.getBoard();
        king.setBoard(positionToPieceMap);

        assertEquals("The move is true e1 -> e2", true, king.move("e1", "e2"));
        assertEquals("The move is false e1 -> e3", false, king.move("e1", "e3"));
        assertEquals("The move is true e1 -> f2", true, king.move("e1", "f2"));
        assertEquals("The move is true e1 -> f1", true, king.move("e1", "f1"));
    }

    @Test
    public void testMoveWhitePawnOnBoard() {
        GameState gameState = new GameState();
        gameState.reset();
        Board board = new Board(gameState);

        board.addPiece("b3", new Pawn(Player.Black));

        Pawn pawn = (Pawn)gameState.getPieceAt("a2");
        Map<Position, Piece> positionToPieceMap = board.getBoard();
        pawn.setBoard(positionToPieceMap);

        assertEquals("The move is true a2 -> a3", true, pawn.move("a2", "a3"));
        assertEquals("The move is true a2 -> a4", true, pawn.move("a2", "a4"));
        assertEquals("The move is true a2 -> b3", true, pawn.move("a2", "b3"));
        assertEquals("The move is false a2 -> b2", false, pawn.move("a2", "b2"));
    }

    @Test
    public void testMoveBlackPawnOnBoard() {
        GameState gameState = new GameState();
        gameState.reset();
        gameState.setCurrentPlayer(Player.Black);
        Board board = new Board(gameState);
        board.addPiece("a5", new Pawn(Player.White));

        Pawn pawn = (Pawn)gameState.getPieceAt("a7");
        Map<Position, Piece> positionToPieceMap = board.getBoard();
        pawn.setBoard(positionToPieceMap);

        assertEquals("The move is true a7 -> a3", true, pawn.move("a7", "a6"));
        assertEquals("The move is false a7 -> a5", false, pawn.move("a7", "a5"));
        assertEquals("The move is false a7 -> b7", false, pawn.move("a7", "b7"));
    }

    @Test
    public void testMoveWhitePawnOnBoardWithCheck1() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        gameState.clearBoard();

        Board board = new Board(gameState);
        board.addPiece("e4", new Pawn(Player.White));
        board.addPiece("d3", new King(Player.White));
        board.addPiece("f5", new Bishop(Player.Black));

        Map<Position, Piece> positionToPieceMap = board.getBoard();
        gameState.setBoard(positionToPieceMap);

        assertEquals("The move is false e4 -> a5", false, gameState.move("e4", "e5"));
        assertEquals("The move is true e4 -> f5", true, gameState.move("e4", "f5"));
    }

    @Test
    public void testMoveWhitePawnOnBoardWithCheck2() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        gameState.clearBoard();

        Board board = new Board(gameState);
        board.addPiece("e4", new Pawn(Player.White));
        board.addPiece("d3", new King(Player.White));
        board.addPiece("f5", new Bishop(Player.Black));

        Map<Position, Piece> positionToPieceMap = board.getBoard();
        gameState.setBoard(positionToPieceMap);

        assertEquals("The move is true e4 -> f5", true, gameState.move("e4", "f5"));
    }

    @Test
    public void testMoveBlackBishopOnBoard() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        Board board = new Board(gameState);
        board.removePiece("c8");
        board.removePiece("e8");

        gameState.setCurrentPlayer(Player.Black);
        board.addPiece("d7", new Bishop(Player.Black));
        gameState.setBoard(board.getBoard());

        Piece piece = gameState.getPieceAt("d7");
        Map<Position, Piece> positionToPieceMap = board.getBoard();
        piece.setBoard(positionToPieceMap);

        assertEquals("The move is true d7 -> c8", true, piece.move("d7", "c8"));
        assertEquals("The move is true d7 -> e8", true, piece.move("d7", "e8"));
        assertEquals("The move is true d7 -> c6", true, piece.move("d7", "c6"));
        assertEquals("The move is true d7 -> e6", true, piece.move("d7", "e6"));
    }

    @Test
    public void testMoveBishopOnBoardWithCheck1() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        gameState.clearBoard();

        Board board = new Board(gameState);
        board.addPiece("a3", new King(Player.White));
        board.addPiece("c3", new Bishop(Player.White));
        board.addPiece("c5", new Bishop(Player.Black));

        Map<Position, Piece> positionToPieceMap = board.getBoard();
        gameState.setBoard(positionToPieceMap);

        assertEquals("The move is true c3 -> b4", true, gameState.move("c3", "b4"));
    }

    @Test
    public void testMoveBishopOnBoardWithCheck2() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        gameState.clearBoard();

        Board board = new Board(gameState);
        board.addPiece("a3", new King(Player.White));
        board.addPiece("c3", new Bishop(Player.White));
        board.addPiece("c5", new Bishop(Player.Black));

        Map<Position, Piece> positionToPieceMap = board.getBoard();
        gameState.setBoard(positionToPieceMap);

        assertEquals("The move is false c3 -> d4", false, gameState.move("c3", "d4"));
    }

    @Test
    public void testMoveBlackKnightOnBoardWithCheck1() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        gameState.clearBoard();

        Board board = new Board(gameState);
        board.addPiece("b2", new Knight(Player.Black));
        board.addPiece("c4", new Rook(Player.White));
        board.addPiece("d4", new King(Player.Black));

        Map<Position, Piece> positionToPieceMap = board.getBoard();
        gameState.setBoard(positionToPieceMap);
        gameState.setCurrentPlayer(Player.Black);

        assertEquals("The move is true b2 -> c4", true, gameState.move("b2", "c4"));
    }

    @Test
    public void testMoveBlackKnightOnBoardWithCheck2() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        gameState.clearBoard();

        Board board = new Board(gameState);
        board.addPiece("b2", new Knight(Player.Black));
        board.addPiece("c4", new Rook(Player.White));
        board.addPiece("d4", new King(Player.Black));

        Map<Position, Piece> positionToPieceMap = board.getBoard();
        gameState.setBoard(positionToPieceMap);
        gameState.setCurrentPlayer(Player.Black);

        assertEquals("The move is true b2 -> d3", false, gameState.move("b2", "d3"));
    }

    @Test
    public void testMoveWhiteRookOnBoardWithCheck1() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        gameState.clearBoard();

        Board board = new Board(gameState);
        board.addPiece("d3", new Rook(Player.White));
        board.addPiece("c3", new King(Player.White));
        board.addPiece("f3", new Rook(Player.Black));

        Map<Position, Piece> positionToPieceMap = board.getBoard();
        gameState.setBoard(positionToPieceMap);

        assertEquals("The move is true d3 -> e3", true, gameState.move("d3", "e3"));
    }

    @Test
    public void testMoveWhiteRookOnBoardWithCheck2() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        gameState.clearBoard();

        Board board = new Board(gameState);
        board.addPiece("d3", new Rook(Player.White));
        board.addPiece("c3", new King(Player.White));
        board.addPiece("f3", new Rook(Player.Black));

        Map<Position, Piece> positionToPieceMap = board.getBoard();
        gameState.setBoard(positionToPieceMap);

        assertEquals("The move is true d3 -> e4", false, gameState.move("d3", "e4"));
    }

    @Test
    public void testMoveWhiteRookOnBoardWithCheck3() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        gameState.clearBoard();

        Board board = new Board(gameState);
        board.addPiece("d3", new Rook(Player.White));
        board.addPiece("c3", new King(Player.White));
        board.addPiece("f3", new Rook(Player.Black));

        Map<Position, Piece> positionToPieceMap = board.getBoard();
        gameState.setBoard(positionToPieceMap);

        assertEquals("The move is true d3 -> f3", true, gameState.move("d3", "f3"));
    }

    @Test
    public void testMoveWhiteQueenOnBoardWithCheck1() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        gameState.clearBoard();

        Board board = new Board(gameState);
        board.addPiece("h1", new King(Player.White));
        board.addPiece("h2", new Queen(Player.White));
        board.addPiece("h8", new Rook(Player.Black));

        Map<Position, Piece> positionToPieceMap = board.getBoard();
        gameState.setBoard(positionToPieceMap);

        assertEquals("The move is true h2 -> h8", true, gameState.move("h2", "h8"));
    }

    @Test
    public void testMoveWhiteQueenOnBoardWithCheck2() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        gameState.clearBoard();

        Board board = new Board(gameState);
        board.addPiece("h1", new King(Player.White));
        board.addPiece("h2", new Queen(Player.White));
        board.addPiece("h8", new Rook(Player.Black));

        Map<Position, Piece> positionToPieceMap = board.getBoard();
        gameState.setBoard(positionToPieceMap);

        assertEquals("The move is true h2 -> g3", false, gameState.move("h2", "g3"));
    }

    @Test
    public void testDraw() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        gameState.clearBoard();

        Board board = new Board(gameState);
        board.addPiece("a1", new King(Player.White));
        board.addPiece("a2", new Bishop(Player.White));
        board.addPiece("a5", new Rook(Player.Black));
        board.addPiece("b8", new Rook(Player.Black));

        Map<Position, Piece> positionToPieceMap = board.getBoard();
        gameState.setBoard(positionToPieceMap);

        assertEquals("isDraw is true", true, gameState.isDraw());
    }

    @Test
    public void testCheckMate() {
        GameStateExt gameState = new GameStateExt();
        gameState.reset();
        gameState.clearBoard();

        Board board = new Board(gameState);
        board.addPiece("a1", new King(Player.White));
        board.addPiece("a3", new Queen(Player.Black));
        board.addPiece("b6", new Rook(Player.Black));

        Map<Position, Piece> positionToPieceMap = board.getBoard();
        gameState.setBoard(positionToPieceMap);

        assertEquals("CHECKMATE is true", true, gameState.isDraw() && gameState.isCheck());
    }

    @Test
    public void testFoolsCheckMate() {
        GameState gameState = new GameState();
        gameState.reset();

        gameState.move("f2", "f3");
        gameState.setCurrentPlayer(Player.Black);
        gameState.move("e7", "e6");
        gameState.setCurrentPlayer(Player.White);
        gameState.move("g2", "g4");
        gameState.setCurrentPlayer(Player.Black);
        gameState.move("d8", "h4");
        gameState.setCurrentPlayer(Player.White);

        assertEquals("CHECKMATE is true", true, gameState.isDraw() && gameState.isCheck());
    }
}
