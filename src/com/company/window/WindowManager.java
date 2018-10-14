package com.company.window;

import com.company.game.GameManager;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.CardPane;
import org.apache.pivot.wtk.Window;

import java.net.URL;

public class WindowManager extends Window implements Bindable
{
    private MainMenu menu = null;
    private CardPane root = null;
    private Game game = null;

    @Override
    public void initialize(Map<String, Object> map, URL url, Resources resources)
    {
        root = (CardPane)map.get("root");

        BXMLSerializer ser = new BXMLSerializer();
        try
        {
            menu = (MainMenu)ser.readObject(MainMenu.class,"mainmenu.bxml");
            menu.playButtonListener = (numPlayers, hasComputerPlayer, computerDifficulty) -> {
                GameManager mgr = new GameManager(hasComputerPlayer, computerDifficulty);
                game.setGameManager(mgr);
                root.setSelectedIndex(1);
            };
            game = (Game)ser.readObject(Game.class,"game.bxml");
            game.resetButtonListener = () ->{
                root.setSelectedIndex(0);
            };

            root.add(menu);
            root.add(game);
        }
        catch (Exception e)
        {
            System.out.println("Error in reading child bxml "+e);
        }
    }
}
