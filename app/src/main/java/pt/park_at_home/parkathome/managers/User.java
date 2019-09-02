package pt.park_at_home.parkathome.managers;

import android.annotation.SuppressLint;
import android.content.Context;

import java.sql.ResultSet;

import pt.park_at_home.parkathome.database.DBFunctions;
import pt.park_at_home.parkathome.utils.SimpleAlert;
import pt.park_at_home.parkathome.utils.TempFile;

public class User {
    private Context context;
    private String username;
    private String password;

    public User() {

    }

    public User(Context context) {
        this.context = context;
    }

    public User(Context context, String username, String password) {
        this.context = context;
        this.username = username;
        this.password = password;
    }

    public Boolean existsUser() {
        boolean exists = false;

        try {
            DBFunctions functions = new DBFunctions(this.context);
            ResultSet resultSet = functions.selectCommand(String.format("SELECT * FROM utilizadores WHERE NomeUtilizador='%s' AND Password='%s' LIMIT 1", username, password));
            if (resultSet.next()) {
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }
}
