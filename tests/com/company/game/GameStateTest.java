package com.company.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest
{
    public GameState target;

    @BeforeEach
    public void init()
    {
        target = GameState.newGame();
    }

    @Test
    public void testNewGame()
    {
        assertEquals(Player.ONE,target.getCurrentPlayer());
        Player[][] board = target.getBoard();
        for(int x = 0; x < 3; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                assertEquals(Player.NONE,board[x][y]);
            }
        }
    }

    @Test
    public void testMakeMove()
    {
        target = target.makeMove(0,0);

        assertEquals(Player.TWO,target.getCurrentPlayer());
        assertEquals(Player.ONE,target.getBoard()[0][0]);
        assertFalse(target.gameComplete());
        assertEquals(Player.NONE,target.getWinner());
    }
}