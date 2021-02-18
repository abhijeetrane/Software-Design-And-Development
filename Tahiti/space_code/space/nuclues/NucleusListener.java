package space.nucleus;

import space.Entry;

// Referenced classes of package space.nucleus:
//            MetaEntry

public interface NucleusListener
{

    public abstract void actionPerformed(int i, MetaEntry metaentry, Entry entry);
}
