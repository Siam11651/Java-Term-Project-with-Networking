package util;

import java.io.Serializable;

public class TransferEnlistRequest implements Serializable
{
    private String from, playerName;

    public TransferEnlistRequest(String from, String playerName)
    {
        SetFrom(from);
        SetPlayerName(playerName);
    }

    public void SetFrom(String from)
    {
        this.from = from;
    }

    public void SetPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public String GetFrom()
    {
        return from;
    }

    public String GetPlayerName()
    {
        return playerName;
    }
}
