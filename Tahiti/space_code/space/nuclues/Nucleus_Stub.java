package space.nucleus;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.rmi.*;
import java.rmi.server.*;
import space.*;
import space.remote.RemoteEventListener;
import space.remote.RemoteSpaceService;

public final class Nucleus_Stub extends RemoteStub
    implements RemoteSpaceService, Remote
{

    private static final long serialVersionUID = 2L;
    private static Method $method_pin_0;
    private static Method $method_read_1;
    private static Method $method_readIfExists_2;
    private static Method $method_register_3;
    private static Method $method_take_4;
    private static Method $method_takeIfExists_5;
    private static Method $method_write_6;

    public Nucleus_Stub(RemoteRef remoteref)
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

    public Integer pin(Integer integer, Entry entry, InetAddress inetaddress)
        throws RemoteException
    {
        try
        {
            Object obj = super.ref.invoke(this, $method_pin_0, new Object[] {
                integer, entry, inetaddress
            }, 0xa54fd80f319bfd92L);
            return (Integer)obj;
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

    public Entry read(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException
    {
        try
        {
            Object obj = super.ref.invoke(this, $method_read_1, new Object[] {
                entry, transaction, new Long(l), inetaddress
            }, 0xc2c4d240807e2c61L);
            return (Entry)obj;
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

    public Entry readIfExists(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException
    {
        try
        {
            Object obj = super.ref.invoke(this, $method_readIfExists_2, new Object[] {
                entry, transaction, new Long(l), inetaddress
            }, 0x55cf7865a5993732L);
            return (Entry)obj;
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

    public Integer register(String s, RemoteEventListener remoteeventlistener)
        throws RemoteException
    {
        try
        {
            Object obj = super.ref.invoke(this, $method_register_3, new Object[] {
                s, remoteeventlistener
            }, 0x733992b3d2b6ede8L);
            return (Integer)obj;
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

    public Entry take(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException
    {
        try
        {
            Object obj = super.ref.invoke(this, $method_take_4, new Object[] {
                entry, transaction, new Long(l), inetaddress
            }, 0xdce0ac7ea4b07cd6L);
            return (Entry)obj;
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

    public Entry takeIfExists(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException
    {
        try
        {
            Object obj = super.ref.invoke(this, $method_takeIfExists_5, new Object[] {
                entry, transaction, new Long(l), inetaddress
            }, 0xc560b6f79aadd213L);
            return (Entry)obj;
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

    public Lease write(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException
    {
        try
        {
            Object obj = super.ref.invoke(this, $method_write_6, new Object[] {
                entry, transaction, new Long(l), inetaddress
            }, 0xbd86e4d47468e91dL);
            return (Lease)obj;
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
            $method_pin_0 = (space.remote.RemoteSpaceService.class).getMethod("pin", new Class[] {
                java.lang.Integer.class, space.Entry.class, java.net.InetAddress.class
            });
            $method_read_1 = (space.remote.RemoteSpaceService.class).getMethod("read", new Class[] {
                space.Entry.class, space.Transaction.class, Long.TYPE, java.net.InetAddress.class
            });
            $method_readIfExists_2 = (space.remote.RemoteSpaceService.class).getMethod("readIfExists", new Class[] {
                space.Entry.class, space.Transaction.class, Long.TYPE, java.net.InetAddress.class
            });
            $method_register_3 = (space.remote.RemoteSpaceService.class).getMethod("register", new Class[] {
                java.lang.String.class, space.remote.RemoteEventListener.class
            });
            $method_take_4 = (space.remote.RemoteSpaceService.class).getMethod("take", new Class[] {
                space.Entry.class, space.Transaction.class, Long.TYPE, java.net.InetAddress.class
            });
            $method_takeIfExists_5 = (space.remote.RemoteSpaceService.class).getMethod("takeIfExists", new Class[] {
                space.Entry.class, space.Transaction.class, Long.TYPE, java.net.InetAddress.class
            });
            $method_write_6 = (space.remote.RemoteSpaceService.class).getMethod("write", new Class[] {
                space.Entry.class, space.Transaction.class, Long.TYPE, java.net.InetAddress.class
            });
        }
        catch(NoSuchMethodException _ex)
        {
            throw new NoSuchMethodError("stub class initialization failed");
        }
    }
}
