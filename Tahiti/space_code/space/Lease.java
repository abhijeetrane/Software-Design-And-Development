package space;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Lease
    implements Serializable
{

    public static final long FOREVER = 0x7fffffffffffffffL;
    public static final long ANY = -1L;
    public static final int DURATION = 1;
    public static final int ABSOLUTE = 2;
    private long expiration;

    public Lease(long l)
    {
        expiration = l;
    }

    public long getExpiration()
    {
        return expiration;
    }

    public void cancel()
    {
    }

    public void renew(long l)
        throws RemoteException
    {
    }

    void setSerialFormat(int i)
    {
    }

    int getSerialFormat()
    {
        return 0;
    }

    boolean canBatch(Lease lease)
    {
        return false;
    }
}
