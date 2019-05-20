package pt.park_at_home.parkathome.database;

import android.content.Context;
import android.os.StrictMode;
import pt.park_at_home.parkathome.utils.SimpleAlert;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection
{
    private Context context;
    public static Connection con;

    public DBConnection()
    {
    }

    public DBConnection(Context context)
    {
        this.context = context;
    }

    public void open()
    {
        String user = "root";
        String password = "Tlcq296LHBLyTVoV";
        String connectionString = "jdbc:mysql://213.22.152.216/parkathome";

        try
        {
            if (con == null || con.isClosed())
            {
                synchronized (this)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(connectionString, user, password);
                    System.out.println("[DATABASE] Ligação efetuada com sucesso!");
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            SimpleAlert simpleAlert = new SimpleAlert(this.context);
            simpleAlert.setMessage("Ligação já se encontra aberta!" + " " + e.getMessage());
            simpleAlert.show();
        }
    }

    public Connection get()
    {
        //        try
        //        {
        //            if (con == null || con.isClosed())
        //            {
        //                SimpleAlert alert = new SimpleAlert(this.context);
        //                alert.setMessage("ABRIU");
        //                alert.show();
        //                open();
        //            }
        //        } catch (Exception e)
        //        {
        //            e.printStackTrace();
        //            SimpleAlert simpleAlert = new SimpleAlert(this.context);
        //            simpleAlert.setMessage(e.getMessage());
        //            simpleAlert.show();
        //        }
        return con;
    }

    public String getStateToString()
    {
        String state = "aberta";
        try
        {
            if (con == null)
            {
                state = "fechada";
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            SimpleAlert simpleAlert = new SimpleAlert(this.context);
            simpleAlert.setMessage(e.getMessage());
            simpleAlert.show();
        }
        return state;
    }

    public Boolean getState()
    {
        boolean state = true;

        try
        {
            if (con == null)
            {
                state = false;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            SimpleAlert alert = new SimpleAlert(this.context);
            alert.setMessage(e.getMessage());
            alert.show();
        }
        return state;
    }

    public void close()
    {
        try
        {
            if (con != null)
            {
                con.close();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            SimpleAlert simpleAlert = new SimpleAlert(this.context);
            simpleAlert.setMessage("A ligação não se encontra aberta!");
            simpleAlert.show();
        }
    }

    public Context getContext()
    {
        return context;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }
}
