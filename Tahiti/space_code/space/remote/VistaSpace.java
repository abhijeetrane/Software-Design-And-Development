package space.remote;

import java.util.LinkedList;
import java.util.ListIterator;
import space.nucleus.*;

// Referenced classes of package space.remote:
//            RemoteSpace

public class VistaSpace extends RemoteSpace
{

    public VistaSpace()
        throws Exception
    {
    }

    public VistaSpace(String s)
        throws Exception
    {
        super(s);
    }

    public VistaSpace(String s, Integer integer)
        throws Exception
    {
        super(s, integer, null);
    }

    public VistaSpace(String s, Integer integer, Integer integer1)
        throws Exception
    {
        super(s, integer, integer1);
    }

    public void dispose(long l)
    {
        LinkedList linkedlist = tuples;
        JVM INSTR monitorenter ;
        long l1;
        ListIterator listiterator;
        l1 = System.currentTimeMillis();
        listiterator = tuples.listIterator(0);
        MetaEntry metaentry;
        do
        {
            if(!listiterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_148;
            }
            metaentry = (MetaEntry)listiterator.next();
        } while(metaentry.isevictable(l1) || metaentry.getId() != l);
        listiterator.remove();
        metaentry.setDisposed();
        Logger.getInstance().write((new StringBuilder()).append("DISPOSE ").append(metaentry.getEntry().getClass().getName()).append(" spid ").append(metaentry.getId()).append(" size ").append(tuples.size()).toString());
        return;
        Exception exception;
        exception;
        throw exception;
        Logger.getInstance().write((new StringBuilder()).append("DISPOSE failed spid ").append(l).append(" size ").append(tuples.size()).toString());
        return;
    }

    public static void setListener(NucleusListener nucleuslistener, long l)
    {
        Nucleus.listener = nucleuslistener;
        Nucleus.mask = l;
    }

    public static void setListener(NucleusListener nucleuslistener)
    {
        setListener(nucleuslistener, 255L);
    }
}
