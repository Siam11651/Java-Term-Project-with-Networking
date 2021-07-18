package termproject;

import util.Message;
import util.NetworkUtil;

import java.io.IOException;
import java.util.Scanner;

public class WriteThreadClient implements Runnable
{
    private Thread thread;
    private NetworkUtil networkUtil;
    String name;

    public WriteThreadClient(NetworkUtil networkUtil, String name)
    {
        this.networkUtil = networkUtil;
        this.name = name;
        this.thread = new Thread(this, "Client Write Thread");
        thread.start();
    }

    public void run()
    {
        try
        {
            while (true)
            {

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                networkUtil.closeConnection();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}