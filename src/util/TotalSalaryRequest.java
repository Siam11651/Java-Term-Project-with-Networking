package util;

import java.io.Serializable;

public class TotalSalaryRequest implements Serializable
{
    private String from;

    public TotalSalaryRequest(String from)
    {
        SetFrom(from);
    }

    public void SetFrom(String from)
    {
        this.from = from;
    }

    public String GetFrom()
    {
        return from;
    }
}
