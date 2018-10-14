package com.company.game;

import java.util.Arrays;
import java.util.Objects;

public class GameWorld
{
    private Player[][] gameStatus;
    private Player winner;

    private GameWorld()
    {
        gameStatus = new Player[3][3];
        winner = Player.NONE;
    }

    public static GameWorld empty()
    {
        GameWorld world = new GameWorld();
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                world.gameStatus[x][y] = Player.NONE;
            }
        }
        return world;
    }

    public static GameWorld clone(GameWorld src)
    {
        GameWorld world = new GameWorld();
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                world.gameStatus[x][y] = src.gameStatus[x][y];
            }
        }
        world.winner = src.winner;
        return world;
    }

    public Player getPlayerInSquare(int x, int y)
    {
        return gameStatus[x][y];
    }

    public boolean isSpaceOccupied(int x, int y)
    {
        return gameStatus[x][y] != Player.NONE;
    }

    public GameWorld setPlayerMove(int x, int y, Player move)
    {
        GameWorld newWorld = clone(this);
        newWorld.gameStatus[x][y] = move;
        newWorld.recalculateWinner();
        return newWorld;
    }

    private void recalculateWinner()
    {
        findHorizontalWinner();
        findVerticalWinner();
        findDiagonalWinner();

        if(winner == Player.NONE)
        {
            checkForTie();
        }
    }

    private void checkForTie()
    {
        boolean foundNonePlayer = false;
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                if(gameStatus[x][y] == Player.NONE)
                {
                    foundNonePlayer = true;
                    break;
                }
            }
        }
        if(!foundNonePlayer)
        {
            winner = Player.TIE;
        }
    }

    private void findDiagonalWinner()
    {
        if(gameStatus[0][0] == gameStatus[1][1] && gameStatus[0][0] == gameStatus[2][2] && gameStatus[0][0] != Player.NONE)
        {
            winner = gameStatus[0][0];
            return;
        }
        if(gameStatus[2][0] == gameStatus[1][1] && gameStatus[2][0] == gameStatus[0][2] && gameStatus[2][0] != Player.NONE)
        {
            winner = gameStatus[2][0];
            return;
        }
    }

    private void findHorizontalWinner()
    {
        if(gameStatus[0][0] == gameStatus[1][0] && gameStatus[0][0] == gameStatus[2][0] && gameStatus[0][0] != Player.NONE)
        {
            winner = gameStatus[0][0];
            return;
        }
        if(gameStatus[0][1] == gameStatus[1][1] && gameStatus[0][1] == gameStatus[2][1] && gameStatus[0][1] != Player.NONE)
        {
            winner = gameStatus[0][1];
            return;
        }
        if(gameStatus[0][2] == gameStatus[1][2] && gameStatus[0][2] == gameStatus[2][2] && gameStatus[0][2] != Player.NONE)
        {
            winner = gameStatus[0][2];
            return;
        }
    }

    private void findVerticalWinner()
    {
        if(gameStatus[0][0] == gameStatus[0][1] && gameStatus[0][0] == gameStatus[0][2] && gameStatus[0][0] != Player.NONE)
        {
            winner = gameStatus[0][0];
            return;
        }
        if(gameStatus[1][0] == gameStatus[1][1] && gameStatus[1][0] == gameStatus[1][2] && gameStatus[1][0] != Player.NONE)
        {
            winner = gameStatus[1][0];
            return;
        }
        if(gameStatus[2][0] == gameStatus[2][1] && gameStatus[2][0] == gameStatus[2][2] && gameStatus[2][0] != Player.NONE)
        {
            winner = gameStatus[2][0];
            return;
        }
    }

    public boolean isGameOver()
    {
        return winner != Player.NONE;
    }

    public Player getWinner()
    {
        return winner;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        GameWorld gameWorld = (GameWorld) o;
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                if(gameStatus[x][y] != gameWorld.gameStatus[x][y])
                {
                    return false;
                }
            }
        }
        return winner == gameWorld.winner;
    }

    @Override
    public int hashCode()
    {
        int result = Objects.hash(winner);
        result = 31 * result + Arrays.hashCode(gameStatus);
        return result;
    }
}
