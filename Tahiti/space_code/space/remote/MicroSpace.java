package space.remote;

import java.net.InetAddress;
import java.rmi.MarshalledObject;
import space.*;

// Referenced classes of package space.remote:
//            SpaceException, ProxyListener, RemoteSpaceService, RemoteEventListener

public class MicroSpace
    implements SpaceService
{

    private ProxyListener proxy;
    private Integer rid;
    private RemoteSpaceService spacesvc;
    private InetAddress address;

    public MicroSpace(RemoteSpaceService remotespaceservice)
        throws Exception, SpaceException
    {
        if(remotespaceservice == null)
        {
            throw new SpaceException("Remote space service reference is null.");
        } else
        {
            spacesvc = remotespaceservice;
            address = InetAddress.getLocalHost();
            return;
        }
    }

    public Lease write(Entry entry, Transaction transaction, long l)
        throws Exception
    {
        return spacesvc.write(entry, transaction, l, address);
    }

    public Entry take(Entry entry, Transaction transaction, long l)
        throws Exception
    {
        return spacesvc.take(entry, transaction, l, address);
    }

    public Entry takeIfExists(Entry entry, Transaction transaction, long l)
        throws Exception
    {
        return spacesvc.takeIfExists(entry, transaction, l, address);
    }

    public Entry read(Entry entry, Transaction transaction, long l)
        throws Exception
    {
        return spacesvc.read(entry, transaction, l, address);
    }

    public Entry readIfExists(Entry entry, Transaction transaction, long l)
        throws Exception
    {
        return spacesvc.readIfExists(entry, transaction, l, address);
    }

    public Entry snapshot(Entry entry)
        throws Exception
    {
        return entry;
    }

    public void notify(Entry entry, Transaction transaction, RemoteEventListener remoteeventlistener, long l, MarshalledObject marshalledobject)
        throws Exception
    {
        if(proxy == null)
        {
            proxy = new ProxyListener();
            rid = spacesvc.register(InetAddress.getLocalHost().getHostName(), proxy);
            if(rid == null)
            {
                throw new SpaceException("Nucleus was unable register listener.");
            }
        }
        Integer integer = spacesvc.pin(rid, entry, address);
        if(integer == null)
        {
            throw new SpaceException("Nucleus was unable to pin template.");
        } else
        {
            proxy.add(integer, remoteeventlistener, marshalledobject);
            return;
        }
    }
}
