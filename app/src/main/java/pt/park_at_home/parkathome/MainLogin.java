package pt.park_at_home.parkathome;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import pt.park_at_home.parkathome.database.DBConnection;
import pt.park_at_home.parkathome.database.DBFunctions;
import pt.park_at_home.parkathome.managers.Admin;
import pt.park_at_home.parkathome.managers.User;
import pt.park_at_home.parkathome.utils.SimpleAlert;
import pt.park_at_home.parkathome.utils.TempFile;

import java.sql.ResultSet;

public class MainLogin extends AppCompatActivity
{
    EditText username;
    EditText password;
    TextView registerTextView;
    Button loginBtn;

    private Context ctx = MainLogin.this;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        DBConnection connection = new DBConnection(this);
        connection.open();
        //        SimpleAlert alert = new SimpleAlert(this);
        //        alert.setMessage(connection.getStateToString());
        TempFile tempFile = new TempFile(ctx);
        Admin admin = new Admin(ctx);
        User user = new User(ctx);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        registerTextView = findViewById(R.id.textView2);
        loginBtn = findViewById(R.id.buttonLogin);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);

        registerTextView.setOnClickListener(v -> goToUrl("https://parkathome.000webhostapp.com/Park@Home/Inicio/"));

        loginBtn.setOnClickListener(view ->
        {
            if (!(username.getText().toString().isEmpty()) && !(password.getText().toString().isEmpty()))
            {
                if (username.getText().toString().equalsIgnoreCase(admin.getUsername()) && password.getText().toString().equalsIgnoreCase(admin.getPassword()))
                {
                    openActivity(SettingsActivity.class);
                }
                else
                {
                    String username2 = username.getText().toString();
                    String password2 = password.getText().toString();

                    if (user.existsUser(username2, password2))
                    {
                        //                        SimpleAlert alert = new SimpleAlert(this);
                        //                        alert.setMessage(connection.getStateToString());
                        //                        alert.show();
                        openActivity(Parking.class);
                        tempFile.write(getNameByUsername(username2));
                        username.onEditorAction(EditorInfo.IME_ACTION_DONE);
                        password.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    }
                    else
                    {
                        Toast.makeText(MainLogin.this, "Utilizador ou password incorretos!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else
            {
                Toast.makeText(MainLogin.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getNameByUsername(String username)
    {
        String name = null;
        DBConnection connection = new DBConnection(this);
        DBFunctions functions = new DBFunctions(ctx);
        ResultSet resultSet = functions.selectCommand(String.format("SELECT Nome FROM utilizadores WHERE NomeUtilizador='%s'", username));
        try
        {
            if (resultSet.next())
            {
                name = resultSet.getString("Nome");
            }
            //resultSet.close();
            //connection.close();
        } catch (Exception e)
        {
            e.printStackTrace();
            SimpleAlert simpleAlert = new SimpleAlert(ctx);
            simpleAlert.setMessage(e.getMessage());
            simpleAlert.show();
        }
        return name;
    }

    //    private void startApplication()
    //    {
    //        TempFile tempFile = new TempFile(ctx);
    //        DBConnection connection = new DBConnection(ctx);
    //        //tempFile.create();
    //        //connection.open();
    //        //onlineUsers.loadUsers();
    //    }

    @Override
    public void onDestroy()
    {
        DBConnection connection = new DBConnection(MainLogin.this);
        //System.out.println(connection.getStateToString());
        TempFile tempFile = new TempFile(this);
        //tempFile.destroy();
        //connection.close();
        finish();
        super.onDestroy();
    }

    private void goToUrl(String url)
    {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    private void openActivity(Class newActivity)
    {
        Intent intent = new Intent(getApplicationContext(), newActivity);
        startActivity(intent);
    }
}
