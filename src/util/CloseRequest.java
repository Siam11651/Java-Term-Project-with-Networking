package util;

import java.io.Serializable;

public class CloseRequest implements Serializable
{
    private String from;

    public CloseRequest(String from)
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
