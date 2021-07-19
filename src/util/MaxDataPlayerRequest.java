package util;

public class MaxDataPlayerRequest
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
