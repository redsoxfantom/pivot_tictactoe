package com.company.game;

import com.company.exceptions.PlayerException;

public enum Player
{
    ONE("1","X"),
    TWO("2","O"),
    NONE("No Player",""),
    TIE("Tie","");

    private String humanfriendlyname;
    private String playerToken;

    public String getPlayerToken()
    {
        return playerToken;
    }

    public String getHumanFriendlyName()
    {
        return humanfriendlyname;
    }

    public static Player getPlayerFromToken(String token) throws PlayerException
    {
        switch (token)
        {
            case "X":
                return Player.ONE;
            case "Y":
                return Player.TWO;
            default:
                throw new PlayerException("Player token "+token+" not recognized");
        }
    }

    Player(String humanfriendlyname, String playerToken)
    {
        this.humanfriendlyname = humanfriendlyname;
        this.playerToken = playerToken;
    }
}
