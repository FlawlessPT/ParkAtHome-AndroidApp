package pt.park_at_home.parkathome.database;

import android.content.Context;
import pt.park_at_home.parkathome.utils.SimpleAlert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Functions
{
    public static void runCommand(String Command, Context context)
    {
        try
        {
            PreparedStatement statement = DBConnection.getConection(context).prepareStatement(Command);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static ResultSet selectCommand(String Command, Context context)
    {
        try
        {
            PreparedStatement statement = DBConnection.getConection(context).prepareStatement(Command);
            ResultSet results = statement.executeQuery();
            return results;

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean existsUser(String username, String password, Context context)
    {
        boolean exists = false;

        try
        {
            ResultSet resultSet = selectCommand(String.format("SELECT * FROM utilizadores WHERE NomeUtilizador='%s' AND PASSWORD='%s' LIMIT 1", username, password), context);
            if (resultSet.next())
            {
                exists = true;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return exists;
    }
}
