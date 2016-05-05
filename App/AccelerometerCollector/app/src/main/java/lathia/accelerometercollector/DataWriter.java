package lathia.accelerometercollector;

import android.content.Context;
import android.hardware.SensorEvent;

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
        File filesDir = context.getFilesDir();
        dataFile = new File(filesDir, activity + "_" + System.currentTimeMillis() + ".csv");
        writer = new BufferedWriter(new FileWriter(dataFile));
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
