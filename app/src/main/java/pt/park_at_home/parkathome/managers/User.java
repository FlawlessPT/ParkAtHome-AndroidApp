package pt.park_at_home.parkathome.managers;

import android.content.Context;
import pt.park_at_home.parkathome.database.DBFunctions;
import pt.park_at_home.parkathome.utils.SimpleAlert;
import pt.park_at_home.parkathome.utils.TempFile;

import java.sql.ResultSet;

public class User
{
    private Context context;
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String NIF;
    private String userName;
    private String password;
    private int isAdmin;

    public User()
    {

    }

    public User(String nome, String email, String contacto, String nif, String username, String password)
    {
        this.name = nome;
        this.email = email;
        this.phoneNumber = contacto;
        this.NIF = nif;
        this.userName = username;
        this.password = password;
        this.isAdmin = 0;
    }

    public Context getContext()
    {
        return context;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public User(Context context)
    {
        this.context = context;
    }

    public String getNome()
    {
        String nome = null;
        //DBConnection connection = new DBConnection(this.context);
        DBFunctions functions = new DBFunctions(this.context);
        TempFile tempFile = new TempFile(this.context);
        ResultSet resultSet = functions.selectCommand(String.format("SELECT Nome FROM utilizadores WHERE NomeUtilizador='%s'", tempFile.read()));
        try
        {
            if (resultSet.next())
            {
                nome = resultSet.getString("Nome");
            }
            //resultSet.close();
            //connection.close();
        } catch (Exception e)
        {
            //connection.close();
            e.printStackTrace();
            SimpleAlert simpleAlert = new SimpleAlert(this.context);
            simpleAlert.setMessage(e.getMessage());
            simpleAlert.show();
        }
        return nome;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getNIF()
    {
        return NIF;
    }

    public void setNIF(String NIF)
    {
        this.NIF = NIF;
    }

    public String getUserName()
    {
        TempFile tempFile = new TempFile(this.context);
        String nomeUtilizador = tempFile.read();
        return nomeUtilizador;
    }

    public void setUserName(String userName)
    {
        TempFile tempFile = new TempFile(this.context);
        tempFile.write(userName);
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getIsAdmin()
    {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    public Boolean existsUser(String username, String password)
    {
        boolean exists = false;
        //DBConnection connection = new DBConnection(this.context);

        try
        {
            DBFunctions functions = new DBFunctions(this.context);
            ResultSet resultSet = functions.selectCommand(String.format("SELECT * FROM utilizadores WHERE NomeUtilizador='%s' AND Password='%s' LIMIT 1", username, password));
            if (resultSet.next())
            {
                exists = true;
            }
            //            SimpleAlert alert3 = new SimpleAlert(this.context);
            //            alert3.setMessage("ExistsUser#1: " + connection.getStateToString());
            //            alert3.show();
            //resultSet.close();
            //            SimpleAlert alert2 = new SimpleAlert(this.context);
            //            alert2.setMessage("ExistsUser#2: " + connection.getStateToString());
            //            alert2.show();
        } catch (Exception e)
        {
            //connection.close();
            e.printStackTrace();
            SimpleAlert alert = new SimpleAlert(this.context);
            alert.setMessage(e.getMessage());
            alert.show();
        }
        //connection.close();
        return exists;
    }

    public void updateUserData()
    {
        //DBConnection connection = new DBConnection(this.context);
        DBFunctions functions = new DBFunctions(this.context);
        try
        {
            functions.runCommand(String.format("UPDATE utilizadores SET Nome='%s', Email='%s', NrTelemovel='%s', NIF='%s', NomeUtilizador='%s', Password='%s', isAdmin='%s' WHERE ", this.name, this.email, this.phoneNumber, this.NIF, this.userName, this.password, this.isAdmin));
            SimpleAlert simpleAlert = new SimpleAlert(this.context);
            simpleAlert.setMessage("Dados atualizados com sucesso!");
            simpleAlert.show();
        } catch (Exception e)
        {
            e.printStackTrace();
            SimpleAlert simpleAlert = new SimpleAlert(this.context);
            simpleAlert.setMessage(e.getMessage());
            simpleAlert.show();
        }
        //connection.close();
    }

    public void saveUserTempData()
    {
        //DBConnection connection = new DBConnection(this.context);
        DBFunctions functions = new DBFunctions(this.context);
        try
        {
            ResultSet resultSet = functions.selectCommand("SELECT * FROM utilizadores WHERE NomeUtilizador='" + this.userName + "'");
            if (resultSet.next())
            {
                this.id = resultSet.getInt("Id");
                this.name = resultSet.getString("Nome");
                this.email = resultSet.getString("Email");
                this.phoneNumber = resultSet.getString("NrTelemovel");
                this.NIF = resultSet.getString("nif");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        //connection.close();
    }
}
