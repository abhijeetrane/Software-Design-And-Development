package space.nucleus;

import java.net.InetAddress;
import space.Entry;
import space.Transaction;

public class MetaEntry
{

    private static int spaceid = 0;
    private Entry entry;
    private long expiration;
    private Transaction txn;
    private long id;
    private InetAddress owner;
    private InetAddress taker;
    private boolean disposed;
    private long attribute;

    public MetaEntry(Entry entry1, Transaction transaction, long l, InetAddress inetaddress)
    {
        disposed = false;
        entry = entry1;
        txn = transaction;
        owner = inetaddress;
        if(l == 0x7fffffffffffffffL)
        {
            expiration = 0x7fffffffffffffffL;
        } else
        {
            expiration = l + System.currentTimeMillis();
        }
        id = spaceid++;
    }

    public Entry getEntry()
    {
        return entry;
    }

    public boolean isevictable(long l)
    {
        return l > expiration;
    }

    public boolean isdisposed()
    {
        return disposed;
    }

    public long getId()
    {
        return id;
    }

    public long getExpiration()
    {
        return expiration;
    }

    public InetAddress getOwner()
    {
        return owner;
    }

    public InetAddress getTaker()
    {
        return taker;
    }

    public long getAttribute()
    {
        return attribute;
    }

    public void setAttribute(long l)
    {
        attribute = l;
    }

    public void setEntry(Entry entry1)
    {
        entry = entry1;
    }

    public void setTaker(InetAddress inetaddress)
    {
        taker = inetaddress;
    }

    public void setDisposed()
    {
        disposed = true;
    }

}
