package space.util;

import java.io.*;

// Referenced classes of package space.util:
//            FastByteArrayOutputStream

public class DeepCopy
{

    public DeepCopy()
    {
    }

    public static Object copy(Object obj)
    {
        Object obj1 = null;
        try
        {
            FastByteArrayOutputStream fastbytearrayoutputstream = new FastByteArrayOutputStream();
            ObjectOutputStream objectoutputstream = new ObjectOutputStream(fastbytearrayoutputstream);
            objectoutputstream.writeObject(obj);
            objectoutputstream.flush();
            objectoutputstream.close();
            ObjectInputStream objectinputstream = new ObjectInputStream(fastbytearrayoutputstream.getInputStream());
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
