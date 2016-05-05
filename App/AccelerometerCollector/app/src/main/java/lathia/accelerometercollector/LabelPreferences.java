package lathia.accelerometercollector;

import android.content.Context;
import android.content.SharedPreferences;

public class LabelPreferences
{
    private static final String PREFS = "app-preferences";
    private static final String KEY = "label";

    private static SharedPreferences getPreferences(final Context context)
    {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static void setLabel(Context context, String value)
    {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY, value);
        editor.commit();
    }

    public static String getLabel(Context context)
    {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(KEY, null);
    }

    public static void clear(Context context)
    {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
}
