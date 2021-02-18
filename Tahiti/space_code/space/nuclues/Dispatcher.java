package space.nucleus;

import java.io.PrintStream;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.util.Vector;
import space.Entry;
import space.remote.*;

// Referenced classes of package space.nucleus:
//            Pinnable, Nucleus, NucleusListener, Logger

class Dispatcher extends Thread
{

    private Vector pintable;
    private Entry entry;

    public Dispatcher(Vector vector, Entry entry1)
    {
        pintable = null;
        pintable = vector;
        entry = entry1;
    }

    public void run()
    {
        Vector vector = new Vector();
        synchronized(pintable)
        {
            for(int i = 0; i < pintable.size(); i++)
            {
                vector.add(pintable.elementAt(i));
            }

        }
        Vector vector2 = new Vector();
label0:
        for(int j = 0; j < vector.size(); j++)
        {
            Pinnable pinnable = (Pinnable)vector.elementAt(j);
            pinnable.reset();
            do
            {
                int k;
                if((k = pinnable.nextPin()) == -1)
                {
                    continue label0;
                }
                Entry entry1 = pinnable.getTemplate(k);
                if(Nucleus.entriesMatch(entry, entry1))
                {
                    RemoteEventListener remoteeventlistener = pinnable.getListener();
                    int l = pinnable.getRid();
                    String s = pinnable.getHost();
                    try
                    {
                        MarshalledObject marshalledobject = new MarshalledObject(new Passback(l, k));
                        remoteeventlistener.notify(new RemoteEvent("notifyhandler", marshalledobject));
                        if(Nucleus.getListener() != null && (Nucleus.getMask() & 16L) != 0L)
                        {
                            Nucleus.getListener().actionPerformed(16, null, entry1);
                        }
                        Logger.getInstance().write((new StringBuilder()).append("NOTIFY dispatched ").append(entry1.getClass().getName()).append(" rid = ").append(l).append(" pinid = ").append(k).append(" host = ").append(s).toString());
                    }
                    catch(RemoteException remoteexception)
                    {
                        vector2.add(new Integer(l));
                    }
                    catch(Exception exception1)
                    {
                        System.err.println(exception1);
                        exception1.printStackTrace();
                    }
                }
            } while(true);
        }

        deregister(vector2);
    }

    private void deregister(Vector vector)
    {
        if(vector.size() == 0)
        {
            return;
        }
        synchronized(pintable)
        {
label0:
            for(int i = 0; i < vector.size(); i++)
            {
                Integer integer = (Integer)vector.elementAt(i);
                int j = 0;
                do
                {
                    if(j >= pintable.size())
                    {
                        continue label0;
                    }
                    if(((Pinnable)pintable.elementAt(j)).getRid() == integer.intValue())
                    {
                        pintable.remove(j);
                        Logger.getInstance().write((new StringBuilder()).append("NOTIFY deregistered rid = ").append(integer).append(" unreachable.").toString());
                        continue label0;
                    }
                    j++;
                } while(true);
            }

        }
    }
}
