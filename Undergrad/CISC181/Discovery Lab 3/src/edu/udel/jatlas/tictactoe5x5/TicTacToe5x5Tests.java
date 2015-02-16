package edu.udel.jatlas.tictactoe5x5;


import java.util.Arrays;
import java.util.List;

import edu.udel.jatlas.gameframework.Action;

import junit.framework.TestCase;


public class TicTacToe5x5Tests extends TestCase {
    // Piece is an immutable class so it is okay to make these constants
    // Do not make static final pieces in your tests if they are mutable
    public static final Piece X = new Piece('x');
    public static final Piece O = new Piece('o');
    public static final Piece E = new Piece(Piece.EMPTY);
    public static final Piece B = new Piece(Piece.BLOCKED);

    public static TicTacToe5x5Game createStartGame() {
        // uses the make start game method on TicTacToe5x5Game
        return TicTacToe5x5Game.makeStartGame('o', 'x');
    }
    
    public static TicTacToe5x5Game createMidGame1() {
        // an example board after 2 turns each (score 1 for x, 2 for o)
        Piece[][] board = 
            {{X, E, E, E, E},
             {E, E, E, E, O},
             {E, E, B, O, E},
             {X, E, E, E, E},
             {E, E, E, E, E}};
        return new TicTacToe5x5Game(board, 'o', 'x');
    }
    public static TicTacToe5x5Game createMidGame2() {
        // an example board mid-game (score 2 for x, 3 for o)
        Piece[][] board = 
            {{X, O, O, E, X},
             {X, E, O, E, O},
             {E, E, B, O, E},
             {X, O, X, X, E},
             {E, O, X, E, E}};
        return new TicTacToe5x5Game(board, 'o', 'x');
    }
    public static TicTacToe5x5Game createEndGame1() {
        // an example board where player x has won (score 4 for x, 3 for o)
        Piece[][] board = 
            {{X, O, O, E, X},
             {X, E, O, E, O},
             {X, E, B, O, E},
             {X, O, X, X, E},
             {E, E, E, E, E}};
        return new TicTacToe5x5Game(board, 'o', 'x');
    }
   
    public static TicTacToe5x5Game createEndGame2() {
        // an example board where nobody has won (score 3 for x, 3 for o) but there are no more moves
        Piece[][] board = 
            {{X, O, O, O, X},
             {O, O, O, X, O},
             {X, X, B, O, X},
             {X, O, X, X, X},
             {O, X, O, O, X}};
        return new TicTacToe5x5Game(board, 'o', 'x');
    }
    
    public static PlacePieceAction createAction() {
        // won't be valid for all TicTacToeGame states, but just an example
        return new PlacePieceAction('x', 2, 1);
    }
    
    public void test_changeTurn() {
        TicTacToe5x5Game game = createStartGame();
        assertEquals('x', game.getNotTurn());
        assertEquals('o', game.getTurn());
        game.changeTurn();
        assertEquals('x', game.getTurn());
        assertEquals('o', game.getNotTurn());
    }
    
    public void test_PlacePieceAction() {
        PlacePieceAction action1 = new PlacePieceAction('o', 3, 1);
        PlacePieceAction action2 = new PlacePieceAction('o', 2, 2);
        PlacePieceAction action3 = new PlacePieceAction('x', 3, 0);
        
        // use the initial state from TicTacToe5x5
        TicTacToe5x5Game game0 = createStartGame();
        assertTrue(action1.isValid(game0)); // it is o's turn
        assertFalse(action2.isValid(game0)); // middle square is not usable by either player
        assertFalse(action3.isValid(game0)); // it is not x's turn

        // test that performing action1 will put an O at 3, 1
        action1.update(game0);
        assertEquals('x', game0.getTurn());
        Piece[][] expectedBoard1 = 
            {{E, E, E, E, E},
             {E, E, E, E, E},
             {E, E, B, E, E},
             {E, O, E, E, E},
             {E, E, E, E, E}};
        assertTrue(Arrays.deepEquals(expectedBoard1, game0.getBoard()));
        
        // test that performing action3 will put an X at 3, 0
        assertTrue(action3.isValid(game0)); // it is o's turn now so this is ok
        action3.update(game0);
        assertEquals('o', game0.getTurn());
        Piece[][] expectedBoard2 = 
            {{E, E, E, E, E},
             {E, E, E, E, E},
             {E, E, B, E, E},
             {X, O, E, E, E},
             {E, E, E, E, E}};
        assertTrue(Arrays.deepEquals(expectedBoard2, game0.getBoard()));
    }
    
    public void test_hasEmptySpace() {
        assertTrue(createStartGame().hasEmptySpace());
        assertTrue(createMidGame1().hasEmptySpace());
        assertTrue(createEndGame1().hasEmptySpace());
        assertFalse(createEndGame2().hasEmptySpace());
    }
    
    public void test_getMaxSequence() {
        TicTacToe5x5Game game = createMidGame2();
        
        // test row 0
        assertEquals(2, game.getMaxSequence(0, 0, 0, 1, 'o'));
        assertEquals(1, game.getMaxSequence(0, 0, 0, 1, 'x'));
        // test row 3
        assertEquals(1, game.getMaxSequence(3, 0, 0, 1, 'o'));
        assertEquals(2, game.getMaxSequence(3, 0, 0, 1, 'x'));

        // test column 0
        assertEquals(0, game.getMaxSequence(0, 0, 1, 0, 'o'));
        assertEquals(2, game.getMaxSequence(0, 0, 1, 0, 'x'));
        // test column 1
        assertEquals(2, game.getMaxSequence(0, 1, 1, 0, 'o'));
        assertEquals(0, game.getMaxSequence(0, 1, 1, 0, 'x'));

        // test down-right diagonal 1,0
        assertEquals(0, game.getMaxSequence(1, 0, 1, 1, 'o'));
        assertEquals(1, game.getMaxSequence(1, 0, 1, 1, 'x'));
        // test down-right diagonal 0,1
        assertEquals(3, game.getMaxSequence(0, 1, 1, 1, 'o'));
        assertEquals(0, game.getMaxSequence(0, 1, 1, 1, 'x'));

        // test down-left diagonal 1,4
        assertEquals(2, game.getMaxSequence(1, 4, 1, -1, 'o'));
        assertEquals(1, game.getMaxSequence(1, 4, 1, -1, 'x'));
        // test down-left diagonal 2,4
        assertEquals(0, game.getMaxSequence(2, 4, 1, -1, 'o'));
        assertEquals(2, game.getMaxSequence(2, 4, 1, -1, 'x'));
        
        // test middle square (it is not a free square, it is blocked)
        assertEquals(2, game.getMaxSequence(0, 2, 1, 0, 'o'));
        assertEquals(2, game.getMaxSequence(0, 2, 1, 0, 'x'));
        assertEquals(1, game.getMaxSequence(2, 0, 0, 1, 'o'));
        assertEquals(0, game.getMaxSequence(2, 0, 0, 1, 'x'));
        assertEquals(0, game.getMaxSequence(0, 0, 1, 1, 'o'));
        assertEquals(1, game.getMaxSequence(0, 0, 1, 1, 'x'));
        assertEquals(1, game.getMaxSequence(0, 4, 1, -1, 'o'));
        assertEquals(1, game.getMaxSequence(0, 4, 1, -1, 'x'));
    }
    
    public void test_getScore() {
        assertEquals(0, createStartGame().getScore('x'));
        assertEquals(0, createStartGame().getScore('o'));
        assertEquals(1, createMidGame1().getScore('x'));
        assertEquals(2, createMidGame1().getScore('o'));
        assertEquals(2, createMidGame2().getScore('x'));
        assertEquals(3, createMidGame2().getScore('o'));
        assertEquals(4, createEndGame1().getScore('x'));
        assertEquals(3, createEndGame1().getScore('o'));
        assertEquals(3, createEndGame2().getScore('x'));
        assertEquals(3, createEndGame2().getScore('o'));
    }
    
    public void test_isWinner() {
        assertFalse(createStartGame().isWinner('x'));
        assertFalse(createStartGame().isWinner('o'));
        assertFalse(createMidGame1().isWinner('x'));
        assertFalse(createMidGame1().isWinner('o'));
        assertFalse(createMidGame2().isWinner('x'));
        assertFalse(createMidGame2().isWinner('o'));
        assertTrue(createEndGame1().isWinner('x'));
        assertFalse(createEndGame1().isWinner('o'));
        assertFalse(createEndGame2().isWinner('x'));
        assertFalse(createEndGame2().isWinner('o'));
    }
    
    public void test_isEnd() {
        assertFalse(createStartGame().isEnd());
        assertFalse(createMidGame1().isEnd());
        assertFalse(createMidGame2().isEnd());
        assertTrue(createEndGame1().isEnd());
        assertTrue(createEndGame2().isEnd());
    }
   
    public void test_AI_getAllValidActions() {
        TicTacToe5x5Game game0 = createStartGame();
        TicTacToe5x5AI ai = new TicTacToe5x5AI(game0, "o");
        
        List<Action<TicTacToe5x5Game>> actions = ai.getAllValidActions(game0);
        // check to make sure at least all empty space moves are in there
        int missingMoves = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (game0.getBoard()[i][j].isEmpty()) {
                    boolean found = false;
                    for (Action<TicTacToe5x5Game> action : actions) {
                        PlacePieceAction ppa = (PlacePieceAction)action;
                        if (ppa.getRow() == i && ppa.getColumn() == j) {
                            found = true;
                        }
                    }
                    if (!found) {
                        missingMoves++;
                    }
                }
            }
        }
        assertEquals(0, missingMoves); // should be 0 missing moves
    }
    
    public void test_AI_getHeuristicScore() {
        TicTacToe5x5Game game0 = createMidGame2();
        TicTacToe5x5AI aiO = new TicTacToe5x5AI(game0, "o");
        TicTacToe5x5AI aiX = new TicTacToe5x5AI(game0, "x");

        // score of mid game 2 is 3 for player o. If player o plays at 3, 4
        //  then they would win with a score of 4
        assertEquals(4.0, aiO.getHeuristicScore(new PlacePieceAction('o', 3, 4), game0));
        // score should still be 3. if this is 4 then your heuristic is mutating
        //  the board and not properly undoing its mutation.
        assertEquals(3.0, aiO.getHeuristicScore(new PlacePieceAction('o', 2, 4), game0));

        // change the turn so if the AI is checking for validity of the action the test
        //  will still work
        game0.changeTurn();
        // score of mid game 2 is 2 for player x. However, if player x plays at 2, 0
        //  then they would win with a score of 4
        assertEquals(4.0, aiX.getHeuristicScore(new PlacePieceAction('x', 2, 0), game0));
        // score should still be 2. if this is 5 then your heuristic is mutating
        //  the board and not properly undoing its mutation.
        System.out.println(game0);
        assertEquals(2.0, aiX.getHeuristicScore(new PlacePieceAction('x', 4, 0), game0));
    }
    
    public void test_AI_getBestAction() {
        // check to see if on board 2 they will make the move that will win the game
        TicTacToe5x5Game game0 = createMidGame2();
        TicTacToe5x5AI ai = new TicTacToe5x5AI(game0, "o");
        
        PlacePieceAction ppa = (PlacePieceAction)ai.getBestAction(game0);
        assertEquals(3, ppa.getRow());
        assertEquals(4, ppa.getColumn());
    }
    
    public static void main(String[] args) {
        // print some games and sample actions
        System.out.println(createStartGame());
        System.out.println(createMidGame2());
        System.out.println(createEndGame2());
        
        System.out.println(createAction());
    }
}
