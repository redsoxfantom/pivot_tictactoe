package com.company.window;

import com.company.game.ai.DifficultyLevel;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.*;

import java.net.URL;

public class MainMenu extends BoxPane implements Bindable
{
    private PushButton playButton;
    private ButtonGroup numPlayersGroup;
    private ButtonGroup compDiffGroup;
    private Button player1Button;
    private Button player2Button;
    private Button easyDiffButton;
    private Button medDiffButton;
    private Button hardDiffButton;
    private BoxPane computerDiffDiv;
    private boolean hasComputerPlayer;
    private int numPlayers;
    public PlayButtonListener playButtonListener = null;

    @Override
    public void initialize(Map<String, Object> map, URL url, Resources resources)
    {
        computerDiffDiv = (BoxPane)map.get("compdivdiff");
        player1Button = (Button)map.get("oneplayer");
        player2Button = (Button)map.get("twoplayers");
        easyDiffButton = (Button)map.get("easydifficulty");
        medDiffButton = (Button)map.get("mediumdifficulty");
        hardDiffButton = (Button)map.get("harddifficulty");
        playButton = (PushButton)map.get("playbutton");
        playButton.getButtonPressListeners().add(button -> {
            playPressed();
        });
        numPlayersGroup = (ButtonGroup)map.get("playerselect");
        numPlayersGroup.getButtonGroupListeners().add(new ButtonGroupListener() {
            @Override
            public void buttonAdded(ButtonGroup buttonGroup, Button button) {}
            @Override
            public void buttonRemoved(ButtonGroup buttonGroup, Button button) {}
            @Override
            public void selectionChanged(ButtonGroup buttonGroup, Button button)
            {
                updateSelectedNumPlayers();
            }
        });
        compDiffGroup = (ButtonGroup)map.get("difficultyselect");
        updateSelectedNumPlayers();
    }

    private void updateSelectedNumPlayers()
    {
        Button selectedPlayer = numPlayersGroup.getSelection();
        if(selectedPlayer == player1Button)
        {
            computerDiffDiv.setVisible(true);
            hasComputerPlayer = true;
            numPlayers = 1;
        }
        if(selectedPlayer == player2Button)
        {
            computerDiffDiv.setVisible(false);
            hasComputerPlayer = false;
            numPlayers = 2;
        }
    }

    private void playPressed()
    {
        DifficultyLevel diffLevel = DifficultyLevel.EASY;
        Button difficulty = compDiffGroup.getSelection();
        if(difficulty == easyDiffButton)
        {
            diffLevel = DifficultyLevel.EASY;
        }
        if(difficulty == medDiffButton)
        {
            diffLevel = DifficultyLevel.MEDIUM;
        }
        if(difficulty == hardDiffButton)
        {
            diffLevel = DifficultyLevel.HARD;
        }

        if(playButtonListener != null)
        {
            playButtonListener.playGame(numPlayers,hasComputerPlayer,diffLevel);
        }
    }
}
