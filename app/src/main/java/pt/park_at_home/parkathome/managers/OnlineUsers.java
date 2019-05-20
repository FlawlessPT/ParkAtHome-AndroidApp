package pt.park_at_home.parkathome.managers;

import android.annotation.SuppressLint;
import android.content.Context;
import pt.park_at_home.parkathome.database.DBFunctions;
import pt.park_at_home.parkathome.utils.SimpleAlert;
import pt.park_at_home.parkathome.utils.TesteMessage;

import java.sql.ResultSet;
import java.util.ArrayList;

public class OnlineUsers
{
    private ArrayList<String> onlineUsers = new ArrayList<>();
    private Context context;

    public OnlineUsers(Context context)
    {
        this.context = context;
    }

    public ArrayList<String> getOnlineUsers()
    {
        return onlineUsers;
    }

    public void setOnlineUsers(ArrayList<String> onlineUsers)
    {
        this.onlineUsers = onlineUsers;
    }

    public int getIdByUsername(String username)
    {
        int id = 0;
        DBFunctions functions = new DBFunctions(this.context);
        ResultSet resultSet = functions.selectCommand(String.format("SELECT Id FROM utilizadores WHERE NomeUtilizador='%s'", username));
        try
        {
            if (resultSet.next())
            {
                id = resultSet.getInt("Id");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            SimpleAlert simpleAlert = new SimpleAlert(this.context);
            simpleAlert.setMessage(e.getMessage());
            simpleAlert.show();
        }
        return id;
    }

    @SuppressLint("DefaultLocale")
    public void addUser(String username)
    {
        DBFunctions functions = new DBFunctions(this.context);
        try
        {
            functions.runCommand(String.format("INSERT INTO LoggedUsers (Id, NomeUtilizador) VALUES (%d, '%s')", getIdByUsername(username), username));
            this.onlineUsers.add(username);
        } catch (Exception e)
        {
            e.printStackTrace();
            SimpleAlert simpleAlert = new SimpleAlert(this.context);
            simpleAlert.setMessage(e.getMessage());
            simpleAlert.show();
        }
    }

    public void loadUsers()
    {
        DBFunctions functions = new DBFunctions(this.context);
        ResultSet resultSet = functions.selectCommand("SELECT NomeUtilizador FROM LoggedUsers");
        try
        {
            if (resultSet.next())
            {
                onlineUsers.add(resultSet.getString("NomeUtilizador"));
                for (int i = 0; i <= onlineUsers.size(); i++)
                {
                    TesteMessage.sendToastMessage(this.context, onlineUsers.get(i));
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            SimpleAlert simpleAlert = new SimpleAlert(this.context);
            simpleAlert.setMessage(e.getMessage());
            simpleAlert.show();
        }
    }

    public void removeUser(String username)
    {
        DBFunctions functions = new DBFunctions(this.context);
        try
        {
            functions.runCommand("DELETE FROM LoggedUsers WHERE Id=" + getIdByUsername(username) + "");
            //this.onlineUsers.remove(username);
        } catch (Exception e)
        {
            e.printStackTrace();
            SimpleAlert simpleAlert = new SimpleAlert(this.context);
            simpleAlert.setMessage(e.getMessage());
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
