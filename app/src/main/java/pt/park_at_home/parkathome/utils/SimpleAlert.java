package pt.park_at_home.parkathome.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

public class SimpleAlert
{
    private Context context;
    private String message;

    public SimpleAlert(Context context)
    {
        this.context = context;
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
        new AlertDialog.Builder(this.context).setTitle("Aviso: ").setCancelable(true).setMessage(this.message).setPositiveButton("OK", (dialog, which) -> dialog.cancel()).show();
    }
}
