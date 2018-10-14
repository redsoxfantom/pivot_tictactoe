package com.company.window;

import com.company.game.GameManager;
import com.company.game.GameState;
import com.company.game.GameStateListener;
import com.company.game.Player;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.*;

import java.net.URL;

public class Game extends TablePane implements Bindable
{
    @BXML
    private Label playerlabel;
    @BXML
    private Button button1x1;
    @BXML
    private Button button1x2;
    @BXML
    private Button button1x3;
    @BXML
    private Button button2x1;
    @BXML
    private Button button2x2;
    @BXML
    private Button button2x3;
    @BXML
    private Button button3x1;
    @BXML
    private Button button3x2;
    @BXML
    private Button button3x3;
    @BXML
    private Button resetgamebutton;

    public ResetButtonListener resetButtonListener;

    private GameManager mManager;

    @Override
    public void initialize(Map<String, Object> map, URL url, Resources resources)
    {
        button1x1.getButtonPressListeners().add(button -> {
            mManager.makeMove(0,0);
        });
        button1x2.getButtonPressListeners().add(button -> {
            mManager.makeMove(0,1);
        });
        button1x3.getButtonPressListeners().add(button -> {
            mManager.makeMove(0,2);
        });
        button2x1.getButtonPressListeners().add(button -> {
            mManager.makeMove(1,0);
        });
        button2x2.getButtonPressListeners().add(button -> {
            mManager.makeMove(1,1);
        });
        button2x3.getButtonPressListeners().add(button -> {
            mManager.makeMove(1,2);
        });
        button3x1.getButtonPressListeners().add(button -> {
            mManager.makeMove(2,0);
        });
        button3x2.getButtonPressListeners().add(button -> {
            mManager.makeMove(2,1);
        });
        button3x3.getButtonPressListeners().add(button -> {
            mManager.makeMove(2,2);
        });
        resetgamebutton.getButtonPressListeners().add(button -> {
            resetGame();
        });
    }

    private void resetGame()
    {
        if(resetButtonListener != null)
        {
            resetButtonListener.resetPressed();
        }
    }

    public void setGameManager(GameManager manager)
    {
        mManager = manager;
        updatePlayerLabel();
        redrawBoard();

        mManager.getStateChangeListeners().add(newState -> {
            updatePlayerLabel();
            redrawBoard();
        });
    }

    private void updatePlayerLabel()
    {
        if(mManager.isGameOver())
        {
            if(mManager.getWinner() == Player.TIE)
            {
                playerlabel.setText("Tie Game!");
            }
            else
            {
                playerlabel.setText(String.format("Player %s Wins!", mManager.getWinner().getHumanFriendlyName()));
            }
        }
        else
        {
            playerlabel.setText(String.format("Player %s's Turn", mManager.getCurrentPlayer().getHumanFriendlyName()));
        }
    }

    private void redrawBoard()
    {
        Player[][] board = mManager.getBoard();
        button1x1.setButtonData(board[0][0].getPlayerToken());
        button2x1.setButtonData(board[1][0].getPlayerToken());
        button3x1.setButtonData(board[2][0].getPlayerToken());
        button1x2.setButtonData(board[0][1].getPlayerToken());
        button2x2.setButtonData(board[1][1].getPlayerToken());
        button3x2.setButtonData(board[2][1].getPlayerToken());
        button1x3.setButtonData(board[0][2].getPlayerToken());
        button2x3.setButtonData(board[1][2].getPlayerToken());
        button3x3.setButtonData(board[2][2].getPlayerToken());
    }
}
