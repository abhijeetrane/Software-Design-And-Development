package space.remote;

import java.rmi.Naming;

// Referenced classes of package space.remote:
//            RemoteSpaceService, MicroSpace

public class SpaceAccessor
{

    public static String SPACENAME = "rmi://localhost/space";

    public SpaceAccessor()
    {
    }

    public static MicroSpace getSpace()
        throws Exception
    {
        return getSpace(SPACENAME);
    }

    public static MicroSpace getSpace(String s)
        throws Exception
    {
        RemoteSpaceService remotespaceservice = (RemoteSpaceService)Naming.lookup(s);
        return new MicroSpace(remotespaceservice);
    }

}
