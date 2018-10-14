package com.company.game;

import com.company.exceptions.PlayerException;
import com.company.game.ai.DifficultyLevel;
import com.company.game.player.ComputerPlayer;
import com.company.game.player.HumanPlayer;
import com.company.game.player.IPlayer;
import com.company.game.player.MoveGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager
{
    private GameState mState;
    private Map<Player, IPlayer> mPlayers;
    private List<GameStateListener> mListeners;

    public GameManager(boolean hasComputerPlayer, DifficultyLevel computerDifficulty)
    {
        mListeners = new ArrayList<>();
        mState = GameState.newGame();
        mPlayers = new HashMap<>();

        mPlayers.put(Player.ONE, new HumanPlayer());
        if(hasComputerPlayer)
        {
            mPlayers.put(Player.TWO, new ComputerPlayer(computerDifficulty));
        }
        else
        {
            mPlayers.put(Player.TWO, new HumanPlayer());
        }
    }

    public List<GameStateListener> getStateChangeListeners()
    {
        return mListeners;
    }

    public void makeMove(int x, int y)
    {
        IPlayer currPlayerObj = mPlayers.get(mState.getCurrentPlayer());
        if(isMoveLegal(x,y,currPlayerObj))
        {
            mState = mState.makeMove(x,y);
            updatelisteners();
            if(!mState.gameComplete())
            {
                currPlayerObj = mPlayers.get(mState.getCurrentPlayer());
                if (currPlayerObj instanceof MoveGenerator) {
                    try {
                        mState = ((MoveGenerator) currPlayerObj).makeMove(mState);
                    } catch (PlayerException e) {
                        System.err.println(e.getMessage());
                    }
                    updatelisteners();
                }
            }
        }
    }

    private void updatelisteners()
    {
        for(GameStateListener listener : mListeners)
        {
            listener.updated(mState);
        }
    }

    private boolean isMoveLegal(int x, int y, IPlayer currentPlayer)
    {
        if(mState.gameComplete())
        {
            return false;
        }
        if(mState.isSpaceOccupied(x,y))
        {
            return false;
        }
        if(currentPlayer instanceof MoveGenerator)
        {
            return false;
        }
        return true;
    }

    public Player getCurrentPlayer()
    {
        return mState.getCurrentPlayer();
    }

    public boolean isGameOver()
    {
        return mState.gameComplete();
    }

    public Player getWinner()
    {
        return mState.getWinner();
    }

    public Player[][] getBoard()
    {
        return mState.getBoard();
    }
}
