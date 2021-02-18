package space.nucleus;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package space.nucleus:
//            MetaEntry

public class Logger
{

    private static Logger logger;
    private FileWriter fw;
    private static String LOGFILE = "space.log";
    private static SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss a");

    public static Logger getInstance()
    {
        return getInstance(LOGFILE);
    }

    public static Logger getInstance(String s)
    {
        if(logger != null)
        {
            return logger;
        } else
        {
            logger = new Logger(s);
            return logger;
        }
    }

    private Logger(String s)
    {
        fw = null;
        try
        {
            fw = new FileWriter(LOGFILE, true);
            fw.write("\n---\n");
        }
        catch(IOException ioexception)
        {
            System.err.println((new StringBuilder()).append("Logger: unable to open ").append(s).append(" for append: ").append(ioexception).toString());
        }
    }

    public void write(String s)
    {
        if(fw == null)
        {
            return;
        }
        Date date = new Date();
        String s1 = formatter.format(date);
        try
        {
            fw.write((new StringBuilder()).append(s1).append(" ").append(s).append("\n").toString());
            fw.flush();
        }
        catch(IOException ioexception) { }
    }

    public void dump(LinkedList linkedlist)
    {
        if(fw == null)
        {
            return;
        }
        synchronized(linkedlist)
        {
            Date date = new Date();
            String s = formatter.format(date);
            try
            {
                fw.write((new StringBuilder()).append(s).append(" Space dump:").append("\n").toString());
                int i = linkedlist.size();
                fw.write((new StringBuilder()).append("Space sz = ").append(i).append("\n").toString());
                ListIterator listiterator = linkedlist.listIterator(0);
                for(int j = 0; listiterator.hasNext(); j++)
                {
                    MetaEntry metaentry = (MetaEntry)listiterator.next();
                    String s1;
                    if(metaentry.getExpiration() == 0x7fffffffffffffffL)
                    {
                        s1 = "never";
                    } else
                    {
                        s1 = formatter.format(new Date(metaentry.getExpiration()));
                    }
                    space.Entry entry = metaentry.getEntry();
                    fw.write((new StringBuilder()).append("[").append(j).append("] ").append(entry.getClass().getName()).append(" spid = ").append(metaentry.getId()).append(" expires ").append(s1).append("\n").toString());
                    Field afield[] = entry.getClass().getFields();
                    for(int k = 0; k < afield.length; k++)
                    {
                        String s2 = afield[k].getName();
                        Object obj = afield[k].get(entry);
                        fw.write((new StringBuilder()).append("  ").append(s2).append(" = ").append(obj).toString());
                        if(afield[k].getType().isPrimitive())
                        {
                            fw.write(" (primitive)");
                        }
                        fw.write("\n");
                    }

                }

                fw.flush();
            }
            catch(IOException ioexception) { }
            catch(Exception exception)
            {
                System.err.println((new StringBuilder()).append("Logger.dump: ").append(exception).toString());
            }
        }
    }

    public static String getVersion(String s)
    {
        int i;
        int j;
        i = s.indexOf(" ");
        j = s.lastIndexOf("$");
        if(i == -1 || j == 1)
        {
            return "";
        }
        int k;
        String s1 = s.substring(i + 1, j - 1);
        k = Integer.parseInt(s1);
        return (new StringBuilder()).append("v1.").append(k).append(" ").toString();
        Exception exception;
        exception;
        return "";
    }

}
