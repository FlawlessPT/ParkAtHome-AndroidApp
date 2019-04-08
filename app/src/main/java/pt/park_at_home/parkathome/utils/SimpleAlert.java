package pt.park_at_home.parkathome.utils;

import android.app.AlertDialog;
import android.content.Context;

public class SimpleAlert
{
    private Context context;
    private String message;

    public SimpleAlert()
    {
    }

    public Context getContext()
    {
        return context;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void show()
    {
        new AlertDialog.Builder(this.context).setTitle("Aviso: ").setCancelable(true).setMessage(this.message).create().show();
    }
}
