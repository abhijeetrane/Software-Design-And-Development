package space.nucleus;


public class NucleusAction
{

    public static final int WRITE = 1;
    public static final int READ = 2;
    public static final int TAKE = 4;
    public static final int REGISTER = 8;
    public static final int NOTIFY = 16;
    public static final int SLEEP = 32;
    public static final int WAKEUP = 64;
    public static final int EXPIRE = 128;
    public static final int ALL = 255;

    public NucleusAction()
    {
    }
}
