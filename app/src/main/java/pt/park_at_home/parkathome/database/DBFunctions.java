package pt.park_at_home.parkathome.database;

import android.content.Context;
import pt.park_at_home.parkathome.utils.SimpleAlert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFunctions
{
    private Context context;

    public DBFunctions(Context context)
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

    public void runCommand(String Command)
    {
        DBConnection connection = new DBConnection(this.context);
        try
        {
            PreparedStatement statement = connection.get().prepareStatement(Command);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e)
        {
            e.printStackTrace();
            SimpleAlert alert = new SimpleAlert(this.context);
            alert.setContext(context);
            alert.setMessage(e.getMessage());
            alert.show();
        }
        //connection.close();
    }

    public ResultSet selectCommand(String Command)
    {
        DBConnection connection = new DBConnection(this.context);
        try
        {
            PreparedStatement statement = connection.get().prepareStatement(Command);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (Exception e)
        {
            e.printStackTrace();
            SimpleAlert alert = new SimpleAlert(this.context);
            alert.setContext(context);
            alert.setMessage(e.getMessage());
            alert.show();
        }
        return null;
    }
}
