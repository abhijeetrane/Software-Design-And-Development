package space.remote;

import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.EventObject;
import java.util.Hashtable;

// Referenced classes of package space.remote:
//            Binding, Jumper, RemoteEvent, RemoteEventListener

public class ProxyListener
    implements RemoteEventListener
{

    private Hashtable bindtab;

    public ProxyListener()
        throws RemoteException
    {
        bindtab = new Hashtable();
        UnicastRemoteObject.exportObject(this);
    }

    public void add(Integer integer, RemoteEventListener remoteeventlistener, MarshalledObject marshalledobject)
    {
        bindtab.put(integer, new Binding(remoteeventlistener, marshalledobject));
    }

    public void notify(EventObject eventobject)
    {
        try
        {
            Jumper jumper = new Jumper((RemoteEvent)eventobject, bindtab);
            jumper.start();
        }
        catch(Exception exception) { }
    }
}
