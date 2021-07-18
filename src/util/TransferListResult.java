package util;

import java.io.Serializable;
import java.util.ArrayList;

public class TransferListResult implements Serializable
{
    private ArrayList<Player> players;

    public TransferListResult(ArrayList<Player> players)
    {
        SetPlayers(players);
    }

    public void SetPlayers(ArrayList<Player> players)
    {
        this.players = players;
    }

    public ArrayList<Player> GetPlayers()
    {
        return players;
    }

    public static TransferListResult GetResult(ArrayList<Player> all)
    {
        ArrayList<Player> result = new ArrayList<>();

        for(Player player : all)
        {
            if(player.GetTransferListed())
            {
                result.add(new Player(player));
            }
        }

        return new TransferListResult(result);
    }
}
