package space.remote;

import java.rmi.MarshalledObject;

// Referenced classes of package space.remote:
//            RemoteEventListener

class Binding
{

    private RemoteEventListener client;
    private MarshalledObject handback;

    public Binding(RemoteEventListener remoteeventlistener, MarshalledObject marshalledobject)
    {
        client = remoteeventlistener;
        handback = marshalledobject;
    }

    RemoteEventListener getListener()
    {
        return client;
    }

    MarshalledObject getHandback()
    {
        return handback;
    }
}
