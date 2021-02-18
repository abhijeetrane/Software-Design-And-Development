package space.nucleus;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;
import space.*;
import space.remote.RemoteEventListener;
import space.remote.RemoteSpaceService;

// Referenced classes of package space.nucleus:
//            Landlord, NotifyHandler, MetaEntry, Logger, 
//            NucleusListener

public class Nucleus extends UnicastRemoteObject
    implements RemoteSpaceService
{

    private static final boolean DEBUG = false;
    protected LinkedList tuples;
    private NotifyHandler notifier;
    protected static NucleusListener listener = null;
    protected static long mask;
    private int threadsWaiting;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
    private static final String DUMPER_CLASS_NAME = "space.util.DumperEntry";

    public Nucleus(String s)
        throws RemoteException
    {
        tuples = new LinkedList();
        notifier = null;
        threadsWaiting = 0;
        Logger.getInstance().write((new StringBuilder()).append("Space nucleus ").append(Logger.getVersion("$Revision: 18 $")).append("started at ").append(s).toString());
        Landlord landlord = new Landlord(tuples);
        landlord.start();
        notifier = new NotifyHandler();
    }

    public Lease write(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException
    {
        if(entry == null)
        {
            Logger.getInstance().write("WRITE got null entry not written.");
            return new Lease(0L);
        }
        if(isdumper(entry))
        {
            Logger.getInstance().dump(tuples);
            return new Lease(0L);
        } else
        {
            MetaEntry metaentry = insertEntry(entry, l, inetaddress);
            return new Lease(metaentry.getExpiration());
        }
    }

    public Entry take(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException
    {
        if(entry == null)
        {
            Logger.getInstance().write("TAKE got null template not searchable.");
            return null;
        }
        MetaEntry metaentry = readEntry(entry, l, true);
        if(metaentry == null)
        {
            Logger.getInstance().write((new StringBuilder()).append("TAKE tmpl ").append(entry.getClass().getName()).append(" no match size ").append(tuples.size()).append(" threads ").append(threadsWaiting).toString());
            if(listener != null && (mask & 4L) != 0L)
            {
                listener.actionPerformed(4, null, entry);
            }
            return null;
        }
        metaentry.setTaker(inetaddress);
        if(listener != null && (mask & 4L) != 0L)
        {
            listener.actionPerformed(4, metaentry, entry);
        }
        Logger.getInstance().write((new StringBuilder()).append("TAKE ").append(metaentry.getEntry().getClass().getName()).append(" spid ").append(metaentry.getId()).append(" size ").append(tuples.size()).append(" threads ").append(threadsWaiting).toString());
        return metaentry.getEntry();
    }

    public Entry takeIfExists(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException
    {
        if(entry == null)
        {
            Logger.getInstance().write("TAKEIF got null template not searchable.");
            return null;
        }
        MetaEntry metaentry = readEntry(entry, 0L, true);
        if(metaentry == null)
        {
            Logger.getInstance().write((new StringBuilder()).append("TAKEIF tmpl ").append(entry.getClass().getName()).append(" no match size ").append(tuples.size()).append(" waiting ").append(threadsWaiting).toString());
            if(listener != null && (mask & 4L) != 0L)
            {
                listener.actionPerformed(4, null, entry);
            }
            return null;
        }
        metaentry.setTaker(inetaddress);
        if(listener != null && (mask & 4L) != 0L)
        {
            listener.actionPerformed(4, metaentry, entry);
        }
        Logger.getInstance().write((new StringBuilder()).append("TAKEIF ").append(metaentry.getEntry().getClass().getName()).append(" spid ").append(metaentry.getId()).append(" size ").append(tuples.size()).append(" waiting ").append(threadsWaiting).toString());
        return metaentry.getEntry();
    }

    public Entry read(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException
    {
        if(entry == null)
        {
            Logger.getInstance().write("READ got null template not searchable.");
            return null;
        }
        MetaEntry metaentry = readEntry(entry, l, false);
        if(metaentry == null)
        {
            Logger.getInstance().write((new StringBuilder()).append("READ tmpl ").append(entry.getClass().getName()).append(" no match size ").append(tuples.size()).append(" waiting ").append(threadsWaiting).append(")").toString());
            if(listener != null && (mask & 2L) != 0L)
            {
                listener.actionPerformed(2, null, entry);
            }
            return null;
        }
        if(listener != null && (mask & 2L) != 0L)
        {
            listener.actionPerformed(2, metaentry, entry);
        }
        Logger.getInstance().write((new StringBuilder()).append("READ ").append(metaentry.getEntry().getClass().getName()).append(" spid ").append(metaentry.getId()).append(" size ").append(tuples.size()).append(" waiting ").append(threadsWaiting).toString());
        return metaentry.getEntry();
    }

    public Entry readIfExists(Entry entry, Transaction transaction, long l, InetAddress inetaddress)
        throws RemoteException
    {
        if(entry == null)
        {
            Logger.getInstance().write("READIF got null template not searchable.");
            return null;
        }
        MetaEntry metaentry = readEntry(entry, 0L, false);
        if(metaentry == null)
        {
            Logger.getInstance().write((new StringBuilder()).append("READIF tmpl ").append(entry.getClass().getName()).append(" no match waiting ").append(threadsWaiting).toString());
            if(listener != null && (mask & 2L) != 0L)
            {
                listener.actionPerformed(2, null, entry);
            }
            return null;
        }
        if(listener != null && (mask & 2L) != 0L)
        {
            listener.actionPerformed(2, metaentry, entry);
        }
        Logger.getInstance().write((new StringBuilder()).append("READIF ").append(metaentry.getEntry().getClass().getName()).append(" spid ").append(metaentry.getId()).append(" size ").append(tuples.size()).append(" waiting ").append(threadsWaiting).toString());
        return metaentry.getEntry();
    }

    public void notify(Entry entry, Transaction transaction, RemoteEventListener remoteeventlistener, long l, MarshalledObject marshalledobject, InetAddress inetaddress)
        throws RemoteException
    {
        Logger.getInstance().write("NOTIFY ignored.");
    }

    public Entry snapshot(Entry entry, InetAddress inetaddress)
        throws RemoteException
    {
        return entry;
    }

    public Integer register(String s, RemoteEventListener remoteeventlistener)
        throws RemoteException
    {
        if(remoteeventlistener == null)
        {
            Logger.getInstance().write("REGISTER received null proxy.");
            return null;
        }
        int i = notifier.register(s, remoteeventlistener);
        if(i == -1)
        {
            return null;
        } else
        {
            return new Integer(i);
        }
    }

    public Integer pin(Integer integer, Entry entry, InetAddress inetaddress)
        throws RemoteException
    {
        if(integer == null)
        {
            Logger.getInstance().write("PIN received null rid.");
            return null;
        }
        if(entry == null)
        {
            Logger.getInstance().write((new StringBuilder()).append("PIN received null template for rid = ").append(integer).toString());
            return null;
        }
        int i = notifier.pin(integer, entry);
        if(i == -1)
        {
            return null;
        }
        if(listener != null && (mask & 8L) != 0L)
        {
            listener.actionPerformed(8, null, entry);
        }
        return new Integer(i);
    }

    public static NucleusListener getListener()
    {
        return listener;
    }

    public static long getMask()
    {
        return mask;
    }

    private MetaEntry insertEntry(Entry entry, long l, InetAddress inetaddress)
    {
        MetaEntry metaentry = new MetaEntry(entry, null, l, inetaddress);
        synchronized(tuples)
        {
            tuples.addLast(metaentry);
            tuples.notifyAll();
            String s;
            if(l == 0x7fffffffffffffffL)
            {
                s = "forever";
            } else
            {
                s = formatter.format(new Date(metaentry.getExpiration()));
            }
            if(listener != null && (mask & 1L) != 0L)
            {
                listener.actionPerformed(1, metaentry, null);
            }
            Logger.getInstance().write((new StringBuilder()).append("WRITE ").append(metaentry.getEntry().getClass().getName()).append(" spid ").append(metaentry.getId()).append(" size ").append(tuples.size()).append(" waiting ").append(threadsWaiting).append(" until ").append(s).toString());
        }
        notifier.testEntry(entry);
        return metaentry;
    }

    private boolean isdumper(Entry entry)
    {
        return entry.getClass().getName().equals("space.util.DumperEntry");
    }

    private MetaEntry readEntry(Entry entry, long l, boolean flag)
    {
        MetaEntry metaentry = null;
        synchronized(tuples)
        {
            ListIterator listiterator = searchEntries(entry);
            if(listiterator != null)
            {
                metaentry = (MetaEntry)listiterator.previous();
                if(flag)
                {
                    listiterator.remove();
                }
            } else
            if(l > 0L)
            {
                metaentry = waitEntry(entry, l, flag);
            }
            tuples.notifyAll();
        }
        return metaentry;
    }

    private MetaEntry waitEntry(Entry entry, long l, boolean flag)
    {
        if(l <= 0L)
        {
            return null;
        }
        long l1 = System.currentTimeMillis() + l;
        String s = "forever";
        if(l != 0x7fffffffffffffffL)
        {
            s = formatter.format(new Date(l1));
        }
        Logger.getInstance().write((new StringBuilder()).append("WAIT template ").append(entry.getClass().getName()).append(" threads ").append(threadsWaiting + 1).append(" until ").append(s).toString());
        do
        {
            try
            {
                threadsWaiting++;
                if(listener != null && (mask & 32L) != 0L)
                {
                    listener.actionPerformed(32, null, entry);
                }
                tuples.wait(l);
                threadsWaiting--;
                if(listener != null && (mask & 64L) != 0L)
                {
                    listener.actionPerformed(64, null, entry);
                }
            }
            catch(InterruptedException interruptedexception)
            {
                return null;
            }
            catch(Exception exception)
            {
                System.err.println(exception);
            }
            ListIterator listiterator = searchEntries(entry);
            if(listiterator != null)
            {
                MetaEntry metaentry = (MetaEntry)listiterator.previous();
                if(flag)
                {
                    listiterator.remove();
                }
                return metaentry;
            }
            l = l1 - System.currentTimeMillis();
        } while(l > 0L);
        return null;
    }

    private ListIterator searchEntries(Entry entry)
    {
        long l = System.currentTimeMillis();
        for(ListIterator listiterator = tuples.listIterator(0); listiterator.hasNext();)
        {
            MetaEntry metaentry = (MetaEntry)listiterator.next();
            if(!metaentry.isevictable(l))
            {
                Entry entry1 = metaentry.getEntry();
                if(entriesMatch(entry1, entry))
                {
                    return listiterator;
                }
            }
        }

        return null;
    }

    public static boolean entriesMatch(Entry entry, Entry entry1)
    {
        if(classesMatch(entry, entry1))
        {
            return entryFieldsMatch(entry, entry1);
        } else
        {
            return false;
        }
    }

    private static boolean classesMatch(Entry entry, Entry entry1)
    {
        Class class1 = entry.getClass();
        Class class2 = entry1.getClass();
        return class1.getName().equals(class2.getName()) || class2.isInstance(entry);
    }

    private static boolean entryFieldsMatch(Object obj, Object obj1)
    {
        Field afield[];
        int i;
        afield = obj1.getClass().getFields();
        i = 0;
_L1:
        String s;
        if(i >= afield.length)
        {
            break MISSING_BLOCK_LABEL_98;
        }
        s = afield[i].getName();
        Object obj2 = afield[i].get(obj1);
        if(obj2 == null)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        if(afield[i].getType().isPrimitive())
        {
            break MISSING_BLOCK_LABEL_92;
        }
        Object obj3;
        Field field = obj.getClass().getField(s);
        obj3 = field.get(obj);
        if(!obj2.equals(obj3))
        {
            return false;
        }
        break MISSING_BLOCK_LABEL_92;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        i++;
          goto _L1
        return true;
        Exception exception;
        exception;
        System.err.println(exception);
        exception.printStackTrace();
        return false;
    }

}
