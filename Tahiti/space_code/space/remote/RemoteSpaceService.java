package space.remote;

import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;
import space.*;

// Referenced classes of package space.remote:
//            RemoteEventListener

public interface RemoteSpaceService
    extends Remote
{

    public abstract Lease write(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException;

    public abstract Entry take(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException;

    public abstract Entry takeIfExists(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException;

    public abstract Entry read(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException;

    public abstract Entry readIfExists(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException;

    public abstract Integer register(String s, RemoteEventListener remoteeventlistener)
        throws RemoteException;

    public abstract Integer pin(Integer integer, Entry entry, InetAddress inetaddress)
        throws RemoteException;
}
