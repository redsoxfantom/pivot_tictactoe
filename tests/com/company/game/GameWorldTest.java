package com.company.game;

import com.company.window.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameWorldTest
{
    private GameWorld target;

    @BeforeEach
    public void setUp()
    {
        target = GameWorld.empty();
    }

    @Test
    public void testEmptyGameWorld()
    {
        target = GameWorld.empty();
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                assertFalse(target.isSpaceOccupied(x,y));
            }
        }
    }

    @Test
    public void testPlayerMove()
    {
        GameWorld after = target.setPlayerMove(1,1,Player.ONE);
        assertTrue(after.isSpaceOccupied(1,1));
    }

    @Test
    public void testClone()
    {
        GameWorld after = GameWorld.clone(target);
        target = target.setPlayerMove(0,0,Player.ONE);
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                assertFalse(after.isSpaceOccupied(x,y));
            }
        }
    }

    @Test
    public void testDiagonalWinners()
    {
        target = target.setPlayerMove(0,0,Player.ONE);
        target = target.setPlayerMove(1,1,Player.ONE);
        target = target.setPlayerMove(2,2,Player.ONE);
        assertTrue(target.isGameOver());
        assertEquals(Player.ONE,target.getWinner());

        target = GameWorld.empty();
        target = target.setPlayerMove(2,0,Player.ONE);
        target = target.setPlayerMove(1,1,Player.ONE);
        target = target.setPlayerMove(0,2,Player.ONE);
        assertTrue(target.isGameOver());
        assertEquals(Player.ONE,target.getWinner());

        target = target.setPlayerMove(0,0,Player.TWO);
        target = target.setPlayerMove(1,1,Player.TWO);
        target = target.setPlayerMove(2,2,Player.TWO);
        assertTrue(target.isGameOver());
        assertEquals(Player.TWO,target.getWinner());

        target = GameWorld.empty();
        target = target.setPlayerMove(2,0,Player.TWO);
        target = target.setPlayerMove(1,1,Player.TWO);
        target = target.setPlayerMove(0,2,Player.TWO);
        assertTrue(target.isGameOver());
        assertEquals(Player.TWO,target.getWinner());
    }

    @Test
    public void testVerticalWinners()
    {
        target = target.setPlayerMove(0,0,Player.ONE);
        target = target.setPlayerMove(0,1,Player.ONE);
        target = target.setPlayerMove(0,2,Player.ONE);
        assertTrue(target.isGameOver());
        assertEquals(Player.ONE,target.getWinner());

        target = GameWorld.empty();
        target = target.setPlayerMove(1,0,Player.ONE);
        target = target.setPlayerMove(1,1,Player.ONE);
        target = target.setPlayerMove(1,2,Player.ONE);
        assertTrue(target.isGameOver());
        assertEquals(Player.ONE,target.getWinner());

        target = GameWorld.empty();
        target = target.setPlayerMove(2,0,Player.ONE);
        target = target.setPlayerMove(2,1,Player.ONE);
        target = target.setPlayerMove(2,2,Player.ONE);
        assertTrue(target.isGameOver());
        assertEquals(Player.ONE,target.getWinner());

        target = GameWorld.empty();
        target = target.setPlayerMove(0,0,Player.TWO);
        target = target.setPlayerMove(0,1,Player.TWO);
        target = target.setPlayerMove(0,2,Player.TWO);
        assertTrue(target.isGameOver());
        assertEquals(Player.TWO,target.getWinner());

        target = GameWorld.empty();
        target = target.setPlayerMove(1,0,Player.TWO);
        target = target.setPlayerMove(1,1,Player.TWO);
        target = target.setPlayerMove(1,2,Player.TWO);
        assertTrue(target.isGameOver());
        assertEquals(Player.TWO,target.getWinner());

        target = GameWorld.empty();
        target = target.setPlayerMove(2,0,Player.TWO);
        target = target.setPlayerMove(2,1,Player.TWO);
        target = target.setPlayerMove(2,2,Player.TWO);
        assertTrue(target.isGameOver());
        assertEquals(Player.TWO,target.getWinner());
    }

    @Test
    public void testHorizontalWinners()
    {
        target = target.setPlayerMove(0,0,Player.ONE);
        target = target.setPlayerMove(1,0,Player.ONE);
        target = target.setPlayerMove(2,0,Player.ONE);
        assertTrue(target.isGameOver());
        assertEquals(Player.ONE,target.getWinner());

        target = GameWorld.empty();
        target = target.setPlayerMove(0,1,Player.ONE);
        target = target.setPlayerMove(1,1,Player.ONE);
        target = target.setPlayerMove(2,1,Player.ONE);
        assertTrue(target.isGameOver());
        assertEquals(Player.ONE,target.getWinner());

        target = GameWorld.empty();
        target = target.setPlayerMove(0,2,Player.ONE);
        target = target.setPlayerMove(1,2,Player.ONE);
        target = target.setPlayerMove(2,2,Player.ONE);
        assertTrue(target.isGameOver());
        assertEquals(Player.ONE,target.getWinner());

        target = GameWorld.empty();
        target = target.setPlayerMove(0,0,Player.TWO);
        target = target.setPlayerMove(1,0,Player.TWO);
        target = target.setPlayerMove(2,0,Player.TWO);
        assertTrue(target.isGameOver());
        assertEquals(Player.TWO,target.getWinner());

        target = GameWorld.empty();
        target = target.setPlayerMove(0,1,Player.TWO);
        target = target.setPlayerMove(1,1,Player.TWO);
        target = target.setPlayerMove(2,1,Player.TWO);
        assertTrue(target.isGameOver());
        assertEquals(Player.TWO,target.getWinner());

        target = GameWorld.empty();
        target = target.setPlayerMove(0,2,Player.TWO);
        target = target.setPlayerMove(1,2,Player.TWO);
        target = target.setPlayerMove(2,2,Player.TWO);
        assertTrue(target.isGameOver());
        assertEquals(Player.TWO,target.getWinner());
    }
}