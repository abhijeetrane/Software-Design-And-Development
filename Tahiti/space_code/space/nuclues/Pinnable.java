package space.nucleus;

import java.util.Enumeration;
import java.util.Hashtable;
import space.Entry;
import space.remote.RemoteEventListener;

class Pinnable
{

    private static int registryid = 0;
    private Hashtable templates;
    private RemoteEventListener listener;
    private String host;
    private int rid;
    private int pinid;
    private Enumeration pins;

    public Pinnable(String s, RemoteEventListener remoteeventlistener)
    {
        templates = new Hashtable();
        pinid = -1;
        pins = null;
        host = s;
        listener = remoteeventlistener;
        rid = registryid++;
    }

    public RemoteEventListener getListener()
    {
        return listener;
    }

    public int pin(Entry entry)
    {
        pinid++;
        templates.put(new Integer(pinid), entry);
        return pinid;
    }

    public int getRid()
    {
        return rid;
    }

    public int nextPin()
    {
        if(pins == null)
        {
            pins = templates.keys();
        }
        if(!pins.hasMoreElements())
        {
            return -1;
        } else
        {
            return ((Integer)pins.nextElement()).intValue();
        }
    }

    public Entry getTemplate(int i)
    {
        return (Entry)templates.get(new Integer(i));
    }

    public String getHost()
    {
        return host;
    }

    public void reset()
    {
        pins = null;
    }

}
