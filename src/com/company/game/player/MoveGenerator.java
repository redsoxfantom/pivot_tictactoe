package com.company.game.player;

import com.company.exceptions.PlayerException;
import com.company.game.GameState;
import com.company.game.GameWorld;

public interface MoveGenerator
{
    GameState makeMove(GameState currentState) throws PlayerException;
}
