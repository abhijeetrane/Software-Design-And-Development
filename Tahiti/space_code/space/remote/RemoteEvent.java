package space.remote;

import java.rmi.MarshalledObject;
import java.util.EventObject;

public class RemoteEvent extends EventObject
{

    private Object src;
    private MarshalledObject mobj;

    public RemoteEvent(Object obj, MarshalledObject marshalledobject)
    {
        super(obj);
        src = obj;
        mobj = marshalledobject;
    }

    public Object getSource()
    {
        return src;
    }

    public long getID()
    {
        return 0L;
    }

    public long getSequenceNumber()
    {
        return 0L;
    }

    public MarshalledObject getRegistrationObject()
    {
        return mobj;
    }
}
