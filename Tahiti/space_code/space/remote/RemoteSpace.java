package space.remote;

import java.io.PrintStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;
import space.nucleus.Nucleus;

// Referenced classes of package space.remote:
//            SpaceRMISocketFactory

public class RemoteSpace extends Nucleus
{

    private static final Integer REGISTRY_PORT = new Integer(1099);
    private static final String DEFAULT_URL = "rmi://localhost/space";

    public RemoteSpace()
        throws Exception
    {
        this("rmi://localhost/space", REGISTRY_PORT, null);
    }

    public RemoteSpace(String s)
        throws Exception
    {
        this(s, REGISTRY_PORT, null);
    }

    public RemoteSpace(String s, Integer integer)
        throws Exception
    {
        this(s, integer, null);
    }

    public RemoteSpace(String s, Integer integer, Integer integer1)
        throws Exception
    {
        super(s);
        try
        {
            if(integer1 != null)
            {
                RMISocketFactory.setSocketFactory(new SpaceRMISocketFactory(integer1.intValue()));
            }
            int i = integer == null ? REGISTRY_PORT.intValue() : integer.intValue();
            LocateRegistry.createRegistry(i);
            Naming.rebind(s, this);
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }
    }

}
