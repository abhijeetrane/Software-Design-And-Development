import java.io.PrintStream;
import space.remote.RemoteSpace;

public class RemoteSpaceServer
{

    public static final Integer REGISTRY_PORT = new Integer(1099);
    public static final String SERVER_LOCATION = "rmi://localhost/space";

    public RemoteSpaceServer()
    {
    }

    public static void main(String args[])
    {
        String s;
        if(args.length == 0)
        {
            s = "rmi://localhost/space";
        } else
        {
            s = args[0];
        }
        RemoteSpace remotespace;
        try
        {
            remotespace = new RemoteSpace(s, REGISTRY_PORT);
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }
    }

}
