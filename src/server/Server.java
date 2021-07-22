package server;

import javafx.scene.image.Image;
import util.Player;
import util.LoginFail;
import util.LoginSuccess;
import util.NetworkUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server
{
    private ServerSocket serverSocket;
    private final ArrayList<Player> players;
    private HashMap<String, NetworkUtil> clientMap;
    private HashMap<String, byte[]> clubIconsMap;

    Server(ArrayList<Player> players, HashMap<String, byte[]> clubIconsMap)
    {
        clientMap = new HashMap<>();
        this.players = players;
        this.clubIconsMap = clubIconsMap;

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

            byte[] clubIconByteArr = null;

            for(Map.Entry<String, byte[]> entry : clubIconsMap.entrySet())
            {
                if(entry.getKey().equalsIgnoreCase(clubNameFormal))
                {
                    clubIconByteArr = entry.getValue();

                    break;
                }
            }

            LoginSuccess loginSuccess = new LoginSuccess(clubNameFormal, clubIconByteArr);

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

    public static void main(String args[]) throws IOException
    {
        ArrayList<Player> players = new ArrayList<>();
        File file = new File("data/players.dat");
        FileInputStream playerFileInputStream = new FileInputStream(file);

        byte[] playerCountByteArr = new byte[Integer.SIZE / 8];

        playerFileInputStream.read(playerCountByteArr, 0, Integer.SIZE / 8);

        int playerCount = ByteBuffer.wrap(playerCountByteArr).getInt();

        for(int i = 0; i < playerCount; i++)
        {
            String[] data = new String[8];

            for(int j = 0; j < 8; j++)
            {
                byte[] stringSizeByteArr = new byte[Integer.SIZE / 8];

                playerFileInputStream.read(stringSizeByteArr, 0, Integer.SIZE / 8);

                int stringSize = ByteBuffer.wrap(stringSizeByteArr).getInt();
                byte[] stringContentByteArr = new byte[stringSize];

                playerFileInputStream.read(stringContentByteArr, 0, stringSize);

                data[j] = new String(stringContentByteArr);
            }

            byte[] imageSizeByteArr = new byte[Integer.SIZE / 8];

            playerFileInputStream.read(imageSizeByteArr, 0, Integer.SIZE / 8);

            int imageSize = ByteBuffer.wrap(imageSizeByteArr).getInt();
            byte[] imageByteArr = new byte[imageSize];

            playerFileInputStream.read(imageByteArr, 0, imageSize);

            players.add(new Player(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), data[4], data[5], Integer.parseInt(data[6]), (int)Double.parseDouble(data[7]), imageByteArr, false));
        }

        playerFileInputStream.close();

        HashMap<String, byte[]> clubIconsMap = new HashMap<>();
        FileInputStream clubIconFileInputStream = new FileInputStream("data/club-icons.dat");

        byte[] clubCountByteArr = new byte[Integer.SIZE / 8];

        clubIconFileInputStream.read(clubCountByteArr, 0, Integer.SIZE / 8);

        int clubCount = ByteBuffer.wrap(clubCountByteArr).getInt();

        for(int i = 0; i < clubCount; i++)
        {
            byte[] nameSizeByteArr = new byte[Integer.SIZE / 8];

            clubIconFileInputStream.read(nameSizeByteArr, 0, Integer.SIZE / 8);

            int nameSize = ByteBuffer.wrap(nameSizeByteArr).getInt();
            byte[] nameByteArr = new byte[nameSize];

            clubIconFileInputStream.read(nameByteArr, 0, nameSize);

            String name = new String(nameByteArr);
            byte[] imageSizeByteArr = new byte[Integer.SIZE / 8];

            clubIconFileInputStream.read(imageSizeByteArr, 0, Integer.SIZE / 8);

            int imageSize = ByteBuffer.wrap(imageSizeByteArr).getInt();

            clubIconsMap.put(name, new byte[imageSize]);
            clubIconFileInputStream.read(clubIconsMap.get(name), 0, imageSize);
        }

        clubIconFileInputStream.close();
        System.out.println("Data Read Complete");

        Server server = new Server(players, clubIconsMap);
    }
}
