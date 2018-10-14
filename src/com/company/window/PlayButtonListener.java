package com.company.window;

import com.company.game.ai.DifficultyLevel;

public interface PlayButtonListener
{
    void playGame(int numPlayers, boolean hasComputerPlayer, DifficultyLevel computerDifficulty);
}
