package space.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.EventListener;
import java.util.EventObject;

public interface RemoteEventListener
    extends Remote, EventListener
{

    public abstract void notify(EventObject eventobject)
        throws RemoteException;
}
