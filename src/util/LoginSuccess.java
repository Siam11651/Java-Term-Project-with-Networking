package util;

import java.io.Serializable;

public class LoginSuccess implements Serializable
{
    private String name;
    private byte[] clubIconsByteArr;

    public LoginSuccess(String name, byte[] clubIconsByteArr)
    {
        SetName(name);
        SetClubIconByteArr(clubIconsByteArr);
    }

    public void SetName(String name)
    {
        this.name = name;
    }

    public void SetClubIconByteArr(byte[] clubIconsByteArr)
    {
        this.clubIconsByteArr = new byte[clubIconsByteArr.length];

        System.arraycopy(clubIconsByteArr, 0, this.clubIconsByteArr, 0, clubIconsByteArr.length);
    }

    public String GetName()
    {
        return name;
    }

    public byte[] GetClubIconsByteArr()
    {
        return clubIconsByteArr;
    }
}
