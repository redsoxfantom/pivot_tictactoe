package com.company.game;

public class GameState
{
    private Player currentPlayer;
    private GameWorld currentGameWorld;

    private GameState()
    {
        currentPlayer = Player.ONE;
        currentGameWorld = GameWorld.empty();
    }

    public static GameState newGame()
    {
        return new GameState();
    }

    public static GameState clone(GameState src)
    {
        GameState newState = new GameState();
        newState.currentPlayer = src.currentPlayer;
        newState.currentGameWorld = GameWorld.clone(src.currentGameWorld);
        return newState;
    }

    public GameWorld getCurrentGameWorld()
    {
        return currentGameWorld;
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public GameState makeMove(int x, int y)
    {
        currentGameWorld = currentGameWorld.setPlayerMove(x,y,currentPlayer);
        if(currentPlayer == Player.ONE)
        {
            currentPlayer = Player.TWO;
        }
        else if(currentPlayer == Player.TWO)
        {
            currentPlayer = Player.ONE;
        }
        return GameState.clone(this);
    }

    public boolean gameComplete()
    {
        return currentGameWorld.isGameOver();
    }

    public Player getWinner()
    {
        return currentGameWorld.getWinner();
    }

    public boolean isSpaceOccupied(int x, int y)
    {
        return currentGameWorld.isSpaceOccupied(x,y);
    }

    public Player[][] getBoard()
    {
        Player[][] board = new Player[3][3];
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                board[x][y] = currentGameWorld.getPlayerInSquare(x,y);
            }
        }

        return board;
    }
}
