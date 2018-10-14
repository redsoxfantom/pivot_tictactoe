package com.company.game.player;

import com.company.exceptions.PlayerException;
import com.company.game.GameState;
import com.company.game.GameWorld;
import com.company.game.ai.DifficultyLevel;
import com.company.game.ai.GameWorldTree;

public class ComputerPlayer implements IPlayer, MoveGenerator
{
    private GameWorldTree tree;

    public ComputerPlayer(DifficultyLevel difficulty)
    {
        tree = new GameWorldTree(difficulty);
    }

    @Override
    public GameState makeMove(GameState currentState) throws PlayerException
    {
        tree.setNewRoot(currentState.getCurrentGameWorld());
        GameWorld newWorld = tree.getChildGameWorld();
        tree.setNewRoot(newWorld);
        return makeMove(currentState.getCurrentGameWorld(), newWorld, currentState);
    }

    private GameState makeMove(GameWorld oldWorld, GameWorld newWorld, GameState currentState) throws PlayerException
    {
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                if(oldWorld.getPlayerInSquare(x,y) != newWorld.getPlayerInSquare(x,y))
                {
                    return currentState.makeMove(x,y);
                }
            }
        }

        throw new PlayerException("Computer Player failed to generate move");
    }
}
