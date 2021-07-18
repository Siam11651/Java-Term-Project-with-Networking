package util;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerSearchResult implements Serializable
{
    private ArrayList<Player> players;

    public PlayerSearchResult(ArrayList<Player> players)
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

    public static PlayerSearchResult GetResult(PlayerSearchRequest playerSearchRequest, ArrayList<Player> all)
    {
        ArrayList<Player> result = new ArrayList<>();

        for(Player player : all)
        {
            boolean selectable = true;

            if(!playerSearchRequest.GetName().equalsIgnoreCase("any") &&
            !playerSearchRequest.GetName().equalsIgnoreCase(player.GetName()))
            {
                selectable = false;
            }
            else if(!playerSearchRequest.GetCountry().equalsIgnoreCase("any") &&
            !playerSearchRequest.GetCountry().equalsIgnoreCase(player.GetCountry()))
            {
                selectable = false;
            }
            else if(!playerSearchRequest.GetAge().equalsIgnoreCase("any") &&
            Integer.parseInt(playerSearchRequest.GetAge()) != player.GetAge())
            {
                selectable = false;
            }
            else if(!playerSearchRequest.GetHeight().equalsIgnoreCase("any") &&
            Double.parseDouble(playerSearchRequest.GetHeight()) != player.GetHeight())
            {
                selectable = false;
            }
            else if(!playerSearchRequest.GetNumber().equalsIgnoreCase("any") &&
            Integer.parseInt(playerSearchRequest.GetNumber()) != player.GetNumber())
            {
                selectable = false;
            }
            else if(!playerSearchRequest.GetPosition().equalsIgnoreCase("any") &&
            !playerSearchRequest.GetPosition().equalsIgnoreCase(player.GetPosition()))
            {
                selectable = false;
            }
            else if(!playerSearchRequest.GetWeeklySalary().equalsIgnoreCase("any") &&
            Integer.parseInt(playerSearchRequest.GetWeeklySalary()) != player.GetWeeklySalary())
            {
                selectable = false;
            }
            else if(!playerSearchRequest.GetFrom().equalsIgnoreCase("any") &&
            !playerSearchRequest.GetFrom().equalsIgnoreCase(player.GetClub()))
            {
                selectable = false;
            }

            if(selectable)
            {
                result.add(new Player(player));
            }
        }

        return new PlayerSearchResult(result);
    }
}
