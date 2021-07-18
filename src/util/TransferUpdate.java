package util;

import java.io.Serializable;

public class TransferUpdate implements Serializable
{
    private String playerName, from, to;

    public TransferUpdate(String playerName, String from, String to)
    {
        SetPlayerName(playerName);
        SetFrom(from);
        SetTo(to);
    }

    public void SetPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public void SetFrom(String from)
    {
        this.from = from;
    }

    public void SetTo(String to)
    {
        this.to = to;
    }

    public String GetPlayerName()
    {
        return playerName;
    }

    public String GetFrom()
    {
        return from;
    }

    public String GetTo()
    {
        return to;
    }
}
