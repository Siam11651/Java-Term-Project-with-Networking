package util;

import java.io.Serializable;
import java.util.ArrayList;

public class TotalSalaryResult implements Serializable
{
    private int totalSalary;

    public TotalSalaryResult(int totalSalary)
    {
        SetTotalSalary(totalSalary);
    }

    public void SetTotalSalary(int totalSalary)
    {
        this.totalSalary = totalSalary;
    }

    public int GetTotalSalary()
    {
        return totalSalary;
    }

    public static TotalSalaryResult GetResult(TotalSalaryRequest totalSalaryRequest, ArrayList<Player> all)
    {
        int result = 0;

        for(Player player : all)
        {
            if(totalSalaryRequest.GetFrom().equalsIgnoreCase(player.GetClub()))
            {
                result += player.GetWeeklySalary();
            }
        }

        return new TotalSalaryResult(result);
    }
}
