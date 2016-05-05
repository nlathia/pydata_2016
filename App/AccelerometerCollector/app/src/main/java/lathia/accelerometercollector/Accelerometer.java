package lathia.accelerometercollector;

public class Accelerometer
{
    private static Accelerometer instance;

    public static Accelerometer getInstance()
    {
        if (instance == null)
        {
            instance = new Accelerometer();
        }
        return instance;
    }

    private boolean isSensing;

    protected Accelerometer()
    {
        this.isSensing = false;
    }

    public boolean isSensing()
    {
        return isSensing;
    }

    public void start()
    {}

    public void stop()
    {}
}
