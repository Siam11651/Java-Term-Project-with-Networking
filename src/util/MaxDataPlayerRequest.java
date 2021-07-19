package util;

import java.io.Serializable;

public class MaxDataPlayerRequest implements Serializable
{
    private String from;
    private MaxDataType maxDataType;

    public MaxDataPlayerRequest(String from, MaxDataType maxDataType)
    {
        SetFrom(from);
        SetMaxDataType(maxDataType);
    }

    public void SetFrom(String from)
    {
        this.from = from;
    }

    public void SetMaxDataType(MaxDataType maxDataType)
    {
        this.maxDataType = maxDataType;
    }

    public String GetFrom()
    {
        return from;
    }

    public MaxDataType GetMaxDataType()
    {
        return maxDataType;
    }
}
