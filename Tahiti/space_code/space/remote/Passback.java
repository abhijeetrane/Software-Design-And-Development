package space.remote;

import java.io.Serializable;

public class Passback
    implements Serializable
{

    private int rid;
    private int pid;

    public Passback(int i, int j)
    {
        rid = i;
        pid = j;
    }

    public Integer getRid()
    {
        return new Integer(rid);
    }

    public Integer getPid()
    {
        return new Integer(pid);
    }
}
