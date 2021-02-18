package space.util;

import java.io.InputStream;

public class FastByteArrayInputStream extends InputStream
{

    protected byte buf[];
    protected int count;
    protected int pos;

    public FastByteArrayInputStream(byte abyte0[], int i)
    {
        buf = null;
        count = 0;
        pos = 0;
        buf = abyte0;
        count = i;
    }

    public final int available()
    {
        return count - pos;
    }

    public final int read()
    {
        return pos >= count ? -1 : buf[pos++] & 0xff;
    }

    public final int read(byte abyte0[], int i, int j)
    {
        if(pos >= count)
        {
            return -1;
        }
        if(pos + j > count)
        {
            j = count - pos;
        }
        System.arraycopy(buf, pos, abyte0, i, j);
        pos += j;
        return j;
    }

    public final long skip(long l)
    {
        if((long)pos + l > (long)count)
        {
            l = count - pos;
        }
        if(l < 0L)
        {
            return 0L;
        } else
        {
            pos += l;
            return l;
        }
    }
}
