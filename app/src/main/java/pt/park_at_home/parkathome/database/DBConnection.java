package pt.park_at_home.parkathome.database;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.widget.Toast;
import pt.park_at_home.parkathome.Parking;
import pt.park_at_home.parkathome.SettingsActivity;
import pt.park_at_home.parkathome.utils.OpenType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    private static Connection con = null;

    private static void openActivity(Context context, Class activityClass)
    {
        Intent myIntent = new Intent(context, activityClass);
        context.startActivity(myIntent);
    }

    public static void openConnection(Context context)
    {
        String user = "root";
        String password = "Tlcq296LHBLyTVoV";
        String connectionString = "jdbc:mysql://192.168.1.8/parkathome";

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionString, user, password);

        } catch (Exception e)
        {
            new AlertDialog.Builder(context).setTitle("Erro").setMessage(e.getMessage()).setCancelable(false).setPositiveButton("OK", (dialog, which) ->
            {

            }).show();
        }
    }

    public static void openConnection(Context context, OpenType openType)
    {
        String user = "root";
        String password = "Tlcq296LHBLyTVoV";
        String connectionString = "jdbc:mysql://89.154.60.146/parkathome";

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionString, user, password);

            if (openType == OpenType.SETTINGS)
            {
                openActivity(context, SettingsActivity.class);
            }
            else if (openType == OpenType.PARKING)
            {
                openActivity(context, Parking.class);
            }

        } catch (Exception e)
        {
            new AlertDialog.Builder(context).setTitle("Erro").setMessage(e.getMessage()).setCancelable(false).create().show();
        }
    }

    public static Connection getConection(Context context)
    {
        try
        {
            if (con == null || con.isClosed())
                openConnection(context);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return con;
    }

    public void closeConnection(Context context)
    {
        try
        {
            con.close();
        } catch (SQLException e)
        {
        }
    }
}
