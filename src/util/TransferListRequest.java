package util;

import java.io.Serializable;

public class TransferListRequest implements Serializable
{
    private String from;

    public TransferListRequest(String from)
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
