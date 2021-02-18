package space.remote;

import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;
import java.rmi.server.*;
import java.util.EventObject;

// Referenced classes of package space.remote:
//            RemoteEventListener

public final class ProxyListener_Stub extends RemoteStub
    implements RemoteEventListener
{

    private static final long serialVersionUID = 2L;
    private static Method $method_notify_0;

    public ProxyListener_Stub(RemoteRef remoteref)
    {
        super(remoteref);
    }

    static Class _mthclass$(String s)
    {
        try
        {
            return Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
    }

    public void notify(EventObject eventobject)
        throws RemoteException
    {
        try
        {
            super.ref.invoke(this, $method_notify_0, new Object[] {
                eventobject
            }, 0x322c6c2ca0e968c9L);
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception;
        }
        catch(Exception exception)
        {
            throw new UnexpectedException("undeclared checked exception", exception);
        }
    }

    static 
    {
        try
        {
            $method_notify_0 = (space.remote.RemoteEventListener.class).getMethod("notify", new Class[] {
                java.util.EventObject.class
            });
        }
        catch(NoSuchMethodException _ex)
        {
            throw new NoSuchMethodError("stub class initialization failed");
        }
    }
}
