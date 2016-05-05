package lathia.accelerometercollector;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.io.IOException;

public class Accelerometer implements SensorEventListener
{
    /*
    http://developer.android.com/guide/topics/sensors/sensors_motion.html#sensors-motion-accel
     */

    private static final String LOG_TAG = "Accelerometer";
    private static Accelerometer instance;

    public static Accelerometer getInstance(final Context context)
    {
        if (instance == null)
        {
            instance = new Accelerometer(context);
        }
        return instance;
    }

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private DataWriter fileWriter;
    private boolean isSensing;

    protected Accelerometer(final Context context)
    {
        this.mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.isSensing = false;
    }

    public boolean isSensing()
    {
        return isSensing;
    }

    public void start(final Context context) throws IOException
    {
        fileWriter = new DataWriter(context, LabelPreferences.getLabel(context));
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
        isSensing = true;
    }

    public void stop() throws IOException
    {
        fileWriter.finish();
        mSensorManager.unregisterListener(this);
        isSensing = false;
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event)
    {
        try {
            fileWriter.append(event);
        }
        catch (IOException e)
        {
            Log.d(LOG_TAG, e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
