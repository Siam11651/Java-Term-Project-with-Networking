package server;

import util.Player;
import util.LoginFail;
import util.LoginSuccess;
import util.NetworkUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Server
{
    private ServerSocket serverSocket;
    private final ArrayList<Player> players;
    public HashMap<String, NetworkUtil> clientMap;

    Server(ArrayList<Player> players)
    {
        clientMap = new HashMap<>();
        this.players = players;

        try
        {
            serverSocket = new ServerSocket(33333);

            while (true)
            {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
                System.out.println("New client connecting...");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException
    {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        String clientName = (String) networkUtil.read();

        boolean contains = false;

        for(Player player : players)
        {
            if(player.GetClub().equalsIgnoreCase(clientName))
            {
                contains = true;

                break;
            }
        }

        if(contains)
        {
            String clubNameFormal = "";

            for(Player player : players)
            {
                if(player.GetClub().equalsIgnoreCase(clientName))
                {
                    clubNameFormal = player.GetClub();

                    break;
                }
            }

            LoginSuccess loginSuccess = new LoginSuccess(clubNameFormal);

            System.out.println("Client:'" + clubNameFormal + "' connected successfully");
            networkUtil.write(loginSuccess);
            clientMap.put(clubNameFormal, networkUtil);
            new ReadThreadServer(players, clientMap, networkUtil);
        }
        else
        {
            LoginFail loginFail = new LoginFail("No such club");

            networkUtil.write(loginFail);
        }
    }

    public static void main(String args[]) throws FileNotFoundException
    {
        ArrayList<Player> players = new ArrayList<>();
        File file = new File("data/players.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine())
        {
            String[] data = scanner.nextLine().split(",");

            if(data.length == 8)
            {
                players.add(new Player(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), data[4], data[5], Integer.parseInt(data[6]), (int)Double.parseDouble(data[7]), false));
            }
        }

        scanner.close();
        Server server = new Server(players);
    }
}
