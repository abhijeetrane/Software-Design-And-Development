import java.io.PrintStream;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import space.Entry;
import space.nucleus.MetaEntry;
import space.nucleus.NucleusListener;
import space.remote.VistaSpace;

public class VistaSpaceServer
    implements NucleusListener
{

    public VistaSpace vistaSpace;
    public static final Integer REGISTRY_PORT = new Integer(1099);
    public static final String SERVER_LOCATION = "rmi://localhost/space";
    private SimpleDateFormat formatter;
    private int spaceWrites;
    private int spaceReads;
    private int spaceTakes;
    private int spaceSleeping;
    private int spaceRegisters;
    private int spaceNotifies;
    private int spaceExpires;
    private int clidIndex;
    private Hashtable clidtab;
    String actionStrings[] = {
        "BOGY", "WRITE", "READ", "TAKE", "REGISTER", "NOTIFY", "SLEEP", "WAKEUP", "EXPIRE"
    };

    public static void main(String args[])
    {
        String s;
        if(args.length == 0)
        {
            s = "rmi://localhost/space";
        } else
        {
            s = args[0];
        }
        try
        {
            VistaSpace vistaspace = new VistaSpace(s, REGISTRY_PORT);
            VistaSpaceServer vistaspaceserver = new VistaSpaceServer(vistaspace);
        }
        catch(Exception exception)
        {
            System.err.println(exception);
            System.exit(1);
        }
    }

    public VistaSpaceServer(VistaSpace vistaspace)
    {
        vistaSpace = null;
        formatter = new SimpleDateFormat("hh:mm:ss a");
        spaceWrites = 0;
        spaceReads = 0;
        spaceTakes = 0;
        spaceSleeping = 0;
        spaceRegisters = 0;
        spaceNotifies = 0;
        spaceExpires = 0;
        clidIndex = 0;
        clidtab = new Hashtable();
        VistaSpace _tmp = vistaspace;
        VistaSpace.setListener(this);
    }

    public void actionPerformed(int i, MetaEntry metaentry, Entry entry)
    {
        System.out.println((new StringBuilder()).append("Action: ").append(actionStrings[getActionNumber(i)]).toString());
        switch(i)
        {
        case 1: // '\001'
            spaceWrites++;
            break;

        case 2: // '\002'
            spaceReads++;
            break;

        case 4: // '\004'
            spaceTakes++;
            break;

        case 32: // ' '
            spaceSleeping++;
            break;

        case 64: // '@'
            spaceSleeping--;
            break;

        case 8: // '\b'
            spaceRegisters++;
            break;

        case 16: // '\020'
            spaceNotifies++;
            break;

        case 128: 
            spaceExpires++;
            break;
        }
        print(metaentry, entry);
    }

    private void print(MetaEntry metaentry, Entry entry)
    {
        System.out.println((new StringBuilder()).append("Number writes: ").append(spaceWrites).toString());
        System.out.println((new StringBuilder()).append("Number reads: ").append(spaceReads).toString());
        System.out.println((new StringBuilder()).append("Number takes: ").append(spaceTakes).toString());
        System.out.println((new StringBuilder()).append("Sleeping threads: ").append(spaceSleeping).toString());
        System.out.println((new StringBuilder()).append("Notify registers: ").append(spaceRegisters).toString());
        System.out.println((new StringBuilder()).append("Notifies: ").append(spaceNotifies).toString());
        if(metaentry != null)
        {
            Entry entry1 = metaentry.getEntry();
            System.out.println((new StringBuilder()).append("Spid: ").append(metaentry.getId()).toString());
            System.out.println((new StringBuilder()).append("Class: ").append(entry1.getClass().getName()).toString());
            System.out.println((new StringBuilder()).append("Clid: ").append(getClid(entry1.getClass().getName())).toString());
            System.out.println((new StringBuilder()).append("Owner: ").append(metaentry.getOwner().toString()).toString());
            long l = metaentry.getExpiration();
            System.out.print("Expiry: ");
            if(l == 0x7fffffffffffffffL)
            {
                System.out.println("never");
            } else
            {
                System.out.println(formatter.format(new Date(l)));
            }
        }
        if(entry != null)
        {
            System.out.println((new StringBuilder()).append("Read/take template: ").append(entry.getClass().getName()).toString());
        }
        System.out.println("---");
    }

    private Integer getClid(String s)
    {
        Integer integer = (Integer)clidtab.get(s);
        if(integer == null)
        {
            clidIndex++;
            integer = new Integer(clidIndex);
            clidtab.put(s, integer);
        }
        return integer;
    }

    private int getActionNumber(long l)
    {
        for(int i = 1; i <= 32; i++)
        {
            if((l & 1L) != 0L)
            {
                return i;
            }
            l >>= 1;
        }

        return 0;
    }

}
