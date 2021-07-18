package util;

import java.io.Serializable;

public class LoginFail implements Serializable
{
    private String message;

    public LoginFail()
    {

    }

    public LoginFail(String message)
    {
        SetMessage(message);
    }

    public void SetMessage(String message)
    {
        this.message = message;
    }

    public String GetMessage()
    {
        return message;
    }
}
