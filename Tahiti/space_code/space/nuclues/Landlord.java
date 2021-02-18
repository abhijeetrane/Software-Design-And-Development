package space.nucleus;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.ListIterator;

// Referenced classes of package space.nucleus:
//            MetaEntry, Logger, Nucleus, NucleusListener

public class Landlord extends Thread
{

    private static final int PERIOD = 5000;
    private LinkedList tuples;

    public Landlord(LinkedList linkedlist)
    {
        tuples = null;
        tuples = linkedlist;
    }

    public void run()
    {
        Logger.getInstance().write((new StringBuilder()).append("Landlord ").append(Logger.getVersion("$Revision: 8 $")).append("here...").toString());
        try
        {
            do
            {
                Thread.sleep(5000L);
                evict(tuples);
            } while(true);
        }
        catch(Exception exception)
        {
            System.err.println((new StringBuilder()).append("Landload thread: ").append(exception).toString());
        }
    }

    private void evict(LinkedList linkedlist)
    {
        synchronized(linkedlist)
        {
            int i = 0;
            ListIterator listiterator = linkedlist.listIterator(i);
            do
            {
                if(!listiterator.hasNext())
                {
                    break;
                }
                MetaEntry metaentry = (MetaEntry)listiterator.next();
                long l = System.currentTimeMillis();
                if(metaentry.isevictable(l))
                {
                    listiterator.remove();
                    if(Nucleus.getListener() != null && (Nucleus.getMask() & 128L) != 0L)
                    {
                        Nucleus.getListener().actionPerformed(128, metaentry, null);
                    }
                    Logger.getInstance().write((new StringBuilder()).append("EXPIRE ").append(metaentry.getEntry().getClass().getName()).append(" spid ").append(metaentry.getId()).append(" size ").append(linkedlist.size()).toString());
                }
            } while(true);
        }
    }
}
