package space.remote;

import java.io.PrintStream;
import java.rmi.MarshalledObject;
import java.util.Hashtable;

// Referenced classes of package space.remote:
//            Passback, Binding, RemoteEvent, RemoteEventListener

class Jumper extends Thread
{

    private RemoteEvent rev;
    private Hashtable bindtab;

    public Jumper(RemoteEvent remoteevent, Hashtable hashtable)
    {
        rev = remoteevent;
        bindtab = hashtable;
    }

    public void run()
    {
        Binding binding;
        Passback passback = (Passback)rev.getRegistrationObject().get();
        Integer integer = passback.getPid();
        Integer integer1 = passback.getRid();
        binding = (Binding)bindtab.get(integer);
        if(binding == null)
        {
            System.err.println((new StringBuilder()).append("Internal error passback pinid = ").append(integer).append(" for rid = ").append(integer1).append(" not found!").toString());
            return;
        }
        try
        {
            RemoteEventListener remoteeventlistener = binding.getListener();
            remoteeventlistener.notify(new RemoteEvent("proxylistener", binding.getHandback()));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return;
    }
}
