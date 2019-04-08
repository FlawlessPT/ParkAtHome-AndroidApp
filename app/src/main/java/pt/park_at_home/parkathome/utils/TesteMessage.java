package pt.park_at_home.parkathome.utils;

import android.content.Context;
import android.widget.Toast;

public class TesteMessage
{
    public static void sendTesteMessage1(Context context)
    {
        Toast.makeText(context, "Test Message1", Toast.LENGTH_LONG).show();
    }

    public static void sendTesteMessage2(Context context)
    {
        Toast.makeText(context, "Test Message2", Toast.LENGTH_LONG).show();
    }

    public static void sendTesteMessage3(Context context)
    {
        Toast.makeText(context, "Test Message3", Toast.LENGTH_LONG).show();
    }

    public static void sendTesteMessage4(Context context)
    {
        Toast.makeText(context, "Test Message4", Toast.LENGTH_LONG).show();
    }
}
