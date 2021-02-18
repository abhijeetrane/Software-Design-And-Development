package space.remote;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

public class SpaceRMISocketFactory extends RMISocketFactory
{

    private int serverPort;

    public SpaceRMISocketFactory()
    {
        serverPort = 0;
    }

    public SpaceRMISocketFactory(int i)
    {
        serverPort = 0;
        serverPort = i;
    }

    public Socket createSocket(String s, int i)
    {
        Socket socket = null;
        try
        {
            socket = new Socket(s, i);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return socket;
    }

    public ServerSocket createServerSocket(int i)
    {
        ServerSocket serversocket = null;
        try
        {
            if(i == 0)
            {
                i = serverPort;
            }
            serversocket = new ServerSocket(i);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return serversocket;
    }
}
