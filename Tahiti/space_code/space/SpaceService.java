package space;

import java.rmi.MarshalledObject;
import space.remote.RemoteEventListener;

// Referenced classes of package space:
//            Entry, Transaction, Lease

public interface SpaceService
{

    public abstract Lease write(Entry entry, Transaction transaction, long l)
        throws Exception;

    public abstract Entry take(Entry entry, Transaction transaction, long l)
        throws Exception;

    public abstract Entry takeIfExists(Entry entry, Transaction transaction, long l)
        throws Exception;

    public abstract Entry read(Entry entry, Transaction transaction, long l)
        throws Exception;

    public abstract Entry readIfExists(Entry entry, Transaction transaction, long l)
        throws Exception;

    public abstract Entry snapshot(Entry entry)
        throws Exception;

    public abstract void notify(Entry entry, Transaction transaction, RemoteEventListener remoteeventlistener, long l, MarshalledObject marshalledobject)
        throws Exception;
}
