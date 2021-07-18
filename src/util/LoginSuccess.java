package util;

import java.io.Serializable;

public class LoginSuccess implements Serializable
{
    private String name;

    public LoginSuccess(String name)
    {
        SetName(name);;
    }

    public void SetName(String name)
    {
        this.name = name;
    }

    public String GetName()
    {
        return name;
    }
}
