package space.util;

import java.io.*;

public class UnoptimizedDeepCopy
{

    public UnoptimizedDeepCopy()
    {
    }

    public static Object copy(Object obj)
    {
        Object obj1 = null;
        try
        {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            ObjectOutputStream objectoutputstream = new ObjectOutputStream(bytearrayoutputstream);
            objectoutputstream.writeObject(obj);
            objectoutputstream.flush();
            objectoutputstream.close();
            ObjectInputStream objectinputstream = new ObjectInputStream(new ByteArrayInputStream(bytearrayoutputstream.toByteArray()));
            obj1 = objectinputstream.readObject();
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            classnotfoundexception.printStackTrace();
        }
        return obj1;
    }
}
