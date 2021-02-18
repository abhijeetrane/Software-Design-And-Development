package space.util;

import java.io.PrintStream;

public class _Assert
{

    public _Assert()
    {
    }

    private static void fail()
    {
        System.err.println("assertion failed:");
        Throwable throwable = new Throwable();
        throwable.printStackTrace();
        System.exit(1);
    }

    public static void _assert(boolean flag)
    {
        if(!flag)
        {
            fail();
        }
    }

    public static void _assert(long l)
    {
        if(l == 0L)
        {
            fail();
        }
    }

    public static void _assert(double d)
    {
        if(d == 0.0D)
        {
            fail();
        }
    }

    public static void _assert(Object obj)
    {
        if(obj == null)
        {
            fail();
        }
    }
}
