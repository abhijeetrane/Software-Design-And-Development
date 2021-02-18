package space.util;

import java.io.InputStream;
import java.io.OutputStream;

// Referenced classes of package space.util:
//            FastByteArrayInputStream

public class FastByteArrayOutputStream extends OutputStream
{

    protected byte buf[];
    protected int size;

    public FastByteArrayOutputStream()
    {
        this(5120);
    }

    public FastByteArrayOutputStream(int i)
    {
        buf = null;
        size = 0;
        size = 0;
        buf = new byte[i];
    }

    private void verifyBufferSize(int i)
    {
        if(i > buf.length)
        {
            byte abyte0[] = buf;
            buf = new byte[Math.max(i, 2 * buf.length)];
            System.arraycopy(abyte0, 0, buf, 0, abyte0.length);
            abyte0 = null;
        }
    }

    public int getSize()
    {
        return size;
    }

    public byte[] getByteArray()
    {
        return buf;
    }

    public final void write(byte abyte0[])
    {
        verifyBufferSize(size + abyte0.length);
        System.arraycopy(abyte0, 0, buf, size, abyte0.length);
        size += abyte0.length;
    }

    public final void write(byte abyte0[], int i, int j)
    {
        verifyBufferSize(size + j);
        System.arraycopy(abyte0, i, buf, size, j);
        size += j;
    }

    public final void write(int i)
    {
        verifyBufferSize(size + 1);
        buf[size++] = (byte)i;
    }

    public void reset()
    {
        size = 0;
    }

    public InputStream getInputStream()
    {
        return new FastByteArrayInputStream(buf, size);
    }
}
