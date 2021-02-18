package space.nucleus;

import java.util.Vector;
import space.Entry;
import space.remote.RemoteEventListener;

// Referenced classes of package space.nucleus:
//            Pinnable, Dispatcher, Logger

public class NotifyHandler
{

    private Vector pintable;

    public NotifyHandler()
    {
        pintable = new Vector();
        Logger.getInstance().write((new StringBuilder()).append("Notify handler ").append(Logger.getVersion("$Revision: 10 $")).append("here...").toString());
    }

    public int register(String s, RemoteEventListener remoteeventlistener)
    {
        Vector vector = pintable;
        JVM INSTR monitorenter ;
        int i;
        Pinnable pinnable = new Pinnable(s, remoteeventlistener);
        i = pinnable.getRid();
        pintable.add(pinnable);
        Logger.getInstance().write((new StringBuilder()).append("NOTIFY registered ").append(remoteeventlistener.getClass().getName()).append(" @ ").append(s).append(" rid = ").append(i).toString());
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int pin(Integer integer, Entry entry)
    {
        Vector vector = pintable;
        JVM INSTR monitorenter ;
        int i = 0;
_L1:
        int j;
        if(i >= pintable.size())
        {
            break MISSING_BLOCK_LABEL_117;
        }
        Pinnable pinnable = (Pinnable)pintable.elementAt(i);
        if(pinnable.getRid() != integer.intValue())
        {
            break MISSING_BLOCK_LABEL_111;
        }
        j = pinnable.pin(entry);
        Logger.getInstance().write((new StringBuilder()).append("NOTIFY pinned ").append(entry.getClass().getName()).append(" to rid = ").append(integer).append(" pinid = ").append(j).toString());
        return j;
        i++;
          goto _L1
        vector;
        JVM INSTR monitorexit ;
          goto _L2
        Exception exception;
        exception;
        throw exception;
_L2:
        Logger.getInstance().write((new StringBuilder()).append("NOTIFY rid = ").append(integer).append(" not found in pin table!").toString());
        return -1;
    }

    public void testEntry(Entry entry)
    {
        if(pintable.size() == 0)
        {
            return;
        } else
        {
            Dispatcher dispatcher = new Dispatcher(pintable, entry);
            dispatcher.start();
            return;
        }
    }
}
