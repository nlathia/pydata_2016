package lathia.accelerometercollector;

import android.content.Context;
import android.hardware.SensorEvent;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter
{
    private File dataFile;
    private BufferedWriter writer;

    public DataWriter(final Context context, final String activity) throws IOException
    {
        dataFile = createFile(activity);
        writer = new BufferedWriter(new FileWriter(dataFile));

        Log.d("DataWriter", "Writing to: "+dataFile.getAbsolutePath());
    }

    private File createFile(String activity)
    {
        File directory = new File(Environment.getExternalStorageDirectory(), "AccelerometerData");
        if (!directory.exists())
        {
            directory.mkdirs();
        }
        return new File(directory, activity + "_" + System.currentTimeMillis() + ".csv");
    }

    public void append(SensorEvent event) throws IOException
    {
        String row = "" + System.currentTimeMillis();
        for (int i=0; i<3; i++)
        {
            row += "," + event.values[i];
        }
        writer.write(row + "\n");
    }

    public void finish() throws IOException
    {
        writer.flush();
        writer.close();
    }
}
