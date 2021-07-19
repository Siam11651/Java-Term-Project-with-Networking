package util;

import termproject.Main;

import java.util.ArrayList;

public class MaxDataPlayerResult
{
    private ArrayList<Player> players;

    public MaxDataPlayerResult(ArrayList<Player> players)
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

    public static MaxDataPlayerResult GetResult(MaxDataPlayerRequest maxDataPlayerRequest, ArrayList<Player> all)
    {
        ArrayList<Player> result = new ArrayList<>();

        if(maxDataPlayerRequest.GetMaxDataType() == MaxDataType.AGE)
        {
            int maxVal = -1;

            for(Player player : all)
            {
                if(maxDataPlayerRequest.GetFrom().equalsIgnoreCase("any") || maxDataPlayerRequest.GetFrom().equalsIgnoreCase(player.GetClub()))
                {
                    maxVal = Math.max(maxVal, player.GetAge());
                }
            }

            if(maxVal >= 0)
            {
                for(Player player : all)
                {
                    if(maxVal == player.GetAge())
                    {
                        result.add(player);
                    }
                }
            }
        }
        else if(maxDataPlayerRequest.GetMaxDataType() == MaxDataType.SALARY)
        {
            int maxVal = -1;

            for(Player player : all)
            {
                if(maxDataPlayerRequest.GetFrom().equalsIgnoreCase("any") || maxDataPlayerRequest.GetFrom().equalsIgnoreCase(player.GetClub()))
                {
                    maxVal = Math.max(maxVal, player.GetWeeklySalary());
                }
            }

            if(maxVal >= 0)
            {
                for(Player player : all)
                {
                    if(maxVal == player.GetWeeklySalary())
                    {
                        result.add(player);
                    }
                }
            }
        }
        else
        {
            double maxVal = -1;

            for(Player player : all)
            {
                if(maxDataPlayerRequest.GetFrom().equalsIgnoreCase("any") || maxDataPlayerRequest.GetFrom().equalsIgnoreCase(player.GetClub()))
                {
                    maxVal = Math.max(maxVal, player.GetHeight());
                }
            }

            if(maxVal >= 0)
            {
                for(Player player : all)
                {
                    if(maxVal == player.GetHeight())
                    {
                        result.add(player);
                    }
                }
            }
        }

        return new MaxDataPlayerResult(result);
    }
}
