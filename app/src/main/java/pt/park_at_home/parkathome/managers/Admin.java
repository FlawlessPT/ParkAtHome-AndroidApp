package pt.park_at_home.parkathome.managers;

import android.content.Context;
import pt.park_at_home.parkathome.database.DBConnection;
import pt.park_at_home.parkathome.database.DBFunctions;
import pt.park_at_home.parkathome.utils.SimpleAlert;

import java.sql.ResultSet;

public class Admin
{
    private Context context;
    private String userName;
    private String password;

    public Admin(Context context)
    {
        this.context = context;
    }

    public ResultSet getData()
    {
        //DBConnection connection = new DBConnection(this.context);
        DBFunctions functions = new DBFunctions(this.context);
        try
        {
            ResultSet resultSet = functions.selectCommand("SELECT * FROM `utilizadores` WHERE IsAdmin='1' LIMIT 1");
            //            SimpleAlert alert = new SimpleAlert(this.context);
            //            alert.setMessage("AllData#1: " + connection.getStateToString());
            //            alert.show();
            //resultSet.close();

            return resultSet;
        } catch (Exception e)
        {
            //connection.close();
            e.printStackTrace();
            SimpleAlert alert = new SimpleAlert(this.context);
            alert.setMessage("AllData: " + e.getMessage());
            alert.show();
        }
        //        SimpleAlert alert2 = new SimpleAlert(this.context);
        //        alert2.setMessage("AllData#2: " + connection.getStateToString());
        //        alert2.show();
        //        connection.close();
        return null;
    }

    public String getUsername()
    {
        //DBConnection connection = new DBConnection(this.context);
        DBFunctions functions = new DBFunctions(this.context);
        try
        {
            //            SimpleAlert alert = new SimpleAlert(this.context);
            //            alert.setMessage("Username-Admin#1: " + connection.getStateToString());
            //            alert.show();
            ResultSet resultSet = functions.selectCommand("SELECT NomeUtilizador FROM utilizadores WHERE isAdmin='1' LIMIT 1");
            if (resultSet.next())
            {
                this.userName = resultSet.getString("NomeUtilizador");
            }
            //            SimpleAlert alert2 = new SimpleAlert(this.context);
            //            alert2.setMessage("Username-Admin#2: " + connection.getStateToString());
            //            alert2.show();
            //resultSet.close();
            //connection.close();
            //            SimpleAlert alert3 = new SimpleAlert(this.context);
            //            alert3.setMessage("Username-Admin#3: " + connection.getStateToString());
            //            alert3.show();
        } catch (Exception e)
        {
            e.printStackTrace();
            SimpleAlert alert = new SimpleAlert(this.context);
            alert.setMessage("Username: " + e.getMessage());
            alert.show();
        }
        return this.userName;
    }

    public void setUsername(String username)
    {
        DBConnection connection = new DBConnection(this.context);
        DBFunctions functions = new DBFunctions(this.context);
        try
        {
            functions.runCommand(String.format("UPDATE utilizadores SET NomeUtilizador='%s' WHERE isAdmin='1' LIMIT 1", username));
            //connection.close();
        } catch (Exception e)
        {
            //connection.close();
            e.printStackTrace();
            SimpleAlert alert = new SimpleAlert(this.context);
            alert.setMessage("Username: " + e.getMessage());
            alert.show();
        }
    }

    public String getPassword()
    {
        DBConnection connection = new DBConnection(this.context);
        DBFunctions functions = new DBFunctions(this.context);
        try
        {
            SimpleAlert alert = new SimpleAlert(this.context);
            alert.setMessage("Password-Admin#1: " + connection.getStateToString());
            alert.show();
            ResultSet resultSet = functions.selectCommand("SELECT Password FROM utilizadores WHERE isAdmin='1' LIMIT 1");
            if (resultSet.next())
                this.password = resultSet.getString("Password");
            SimpleAlert alert2 = new SimpleAlert(this.context);
            alert2.setMessage("Password-Admin#2: " + connection.getStateToString());
            alert2.show();
            //resultSet.close();
            //connection.close();
            //            SimpleAlert alert3 = new SimpleAlert(this.context);
            //            alert3.setMessage("Password-Admin#3: " + connection.getStateToString());
            //            alert3.show();
        } catch (Exception e)
        {
            //connection.close();
            e.printStackTrace();
            SimpleAlert alert = new SimpleAlert(this.context);
            alert.setMessage("Password: " + e.getMessage());
            alert.show();
        }
        return this.password;
    }

    public void setPassword(String password)
    {
        DBConnection connection = new DBConnection(this.context);
        DBFunctions functions = new DBFunctions(this.context);

        try
        {
            functions.runCommand(String.format("UPDATE utilizadores SET Password='%s' WHERE isAdmin='1' LIMIT 1", password));
            //connection.close();
        } catch (Exception e)
        {
            //connection.close();
            e.printStackTrace();
            SimpleAlert alert = new SimpleAlert(this.context);
            alert.setMessage("Password: " + e.getMessage());
            alert.show();
        }
    }
}
