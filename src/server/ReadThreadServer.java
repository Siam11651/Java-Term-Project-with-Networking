package server;

import util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadThreadServer implements Runnable
{
    private final ArrayList<Player> players;
    private Thread thread;
    private NetworkUtil networkUtil;
    public HashMap<String, NetworkUtil> clientMap;

    public ReadThreadServer(ArrayList<Player> players, HashMap<String, NetworkUtil> map, NetworkUtil networkUtil)
    {
        this.players = players;
        this.clientMap = map;
        this.networkUtil = networkUtil;
        this.thread = new Thread(this);

        thread.start();
    }

    public void run()
    {
        try
        {
            while (true)
            {
                Object o = networkUtil.read();

                synchronized(players)
                {
                    if (o instanceof Message)
                    {
                        Message obj = (Message) o;
                        String to = obj.getTo();
                        NetworkUtil networkUtil = clientMap.get(to);

                        if (networkUtil != null)
                        {
                            networkUtil.write(obj);
                        }
                    }
                    else if(o instanceof CloseRequest)
                    {
                        CloseRequest closeRequest = (CloseRequest)o;
                        String from = closeRequest.GetFrom();
                        NetworkUtil networkUtil = clientMap.get(from);

                        if(networkUtil != null)
                        {
                            networkUtil.write(closeRequest);
                        }

                        for(Player player : players)
                        {
                            if(player.GetName().equalsIgnoreCase(from))
                            {
                                players.remove(player);

                                break;
                            }
                        }

                        clientMap.remove(from);
                        System.out.println("Client: '" + from + "' disconnecting...");

                        break;
                    }
                    else if(o instanceof PlayerSearchRequest)
                    {
                        PlayerSearchRequest playerSearchRequest = (PlayerSearchRequest)o;
                        String from = playerSearchRequest.GetFrom();
                        PlayerSearchResult playerSearchResult = PlayerSearchResult.GetResult(playerSearchRequest, players);
                        NetworkUtil networkUtil = clientMap.get(from);

                        if(networkUtil != null)
                        {
                            networkUtil.write(playerSearchResult);
                        }
                    }
                    else if(o instanceof TransferEnlistRequest)
                    {
                        TransferEnlistRequest transferEnlistRequest = (TransferEnlistRequest)o;
                        String from = transferEnlistRequest.GetFrom();
                        String playerName = transferEnlistRequest.GetPlayerName();
                        TransferUpdate transferUpdate = null;

                        for(Player player : players)
                        {
                            if(player.GetName().equalsIgnoreCase(playerName))
                            {
                                player.SetTransferListed(true);

                                transferUpdate = new TransferUpdate(playerName, player.GetClub(), "");

                                break;
                            }
                        }

                        for(Map.Entry<String, NetworkUtil> entry : clientMap.entrySet())
                        {
                            NetworkUtil networkUtil = entry.getValue();

                            if(networkUtil != null && transferUpdate != null)
                            {
                                networkUtil.write(transferUpdate);
                            }
                        }
                    }
                    else if(o instanceof TransferListRequest)
                    {
                        TransferListRequest transferListRequest = (TransferListRequest)o;
                        String from = transferListRequest.GetFrom();
                        TransferListResult transferListResult = TransferListResult.GetResult(players);
                        NetworkUtil networkUtil = clientMap.get(from);

                        if(networkUtil != null)
                        {
                            networkUtil.write(transferListResult);
                        }
                    }
                    else if(o instanceof BuyRequest)
                    {
                        BuyRequest buyRequest = (BuyRequest)o;
                        String from = buyRequest.GetFrom();
                        String playerName = buyRequest.GetPlayerName();
                        TransferUpdate transferUpdate = null;

                        for(Player player : players)
                        {
                            if(player.GetName().equalsIgnoreCase(playerName))
                            {
                                if(player.GetTransferListed())
                                {
                                    transferUpdate = new TransferUpdate(playerName, player.GetClub(), from);

                                    player.SetClub(from);
                                    player.SetTransferListed(false);
                                }
                                else
                                {
                                    transferUpdate = new TransferUpdate(playerName, "", from);
                                }

                                break;
                            }
                        }

                        for(Map.Entry<String, NetworkUtil> entry : clientMap.entrySet())
                        {
                            NetworkUtil networkUtil = entry.getValue();

                            if(networkUtil != null && transferUpdate != null)
                            {
                                networkUtil.write(transferUpdate);
                            }
                        }
                    }
                    else if(o instanceof MaxDataPlayerRequest)
                    {
                        MaxDataPlayerRequest maxDataPlayerRequest = (MaxDataPlayerRequest)o;
                        String from = maxDataPlayerRequest.GetFrom();
                        MaxDataPlayerResult maxDataPlayerResult = MaxDataPlayerResult.GetResult(maxDataPlayerRequest, players);
                        NetworkUtil networkUtil = clientMap.get(from);

                        if(networkUtil != null)
                        {
                            networkUtil.write(maxDataPlayerResult);
                        }
                    }
                    else if(o instanceof TotalSalaryRequest)
                    {
                        TotalSalaryRequest totalSalaryRequest = (TotalSalaryRequest)o;
                        String from = totalSalaryRequest.GetFrom();
                        TotalSalaryResult totalSalaryResult = TotalSalaryResult.GetResult(totalSalaryRequest, players);
                        NetworkUtil networkUtil = clientMap.get(from);

                        if(networkUtil != null)
                        {
                            networkUtil.write(totalSalaryResult);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                networkUtil.closeConnection();
                System.out.println("Disconnection successful");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}



