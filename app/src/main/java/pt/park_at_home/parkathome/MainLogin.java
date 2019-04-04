package pt.park_at_home.parkathome;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import pt.park_at_home.parkathome.database.DBConnection;
import pt.park_at_home.parkathome.utils.AlertDialogBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainLogin extends AppCompatActivity
{
    EditText username;
    EditText password;
    TextView registerTextView;
    Button loginBtn;

    public static Connection con = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        registerTextView = findViewById(R.id.textViewRegister);
        loginBtn = findViewById(R.id.buttonLogin);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);

        registerTextView.setOnClickListener(v -> goToUrl("http://www.facebook.com/"));

        loginBtn.setOnClickListener(view ->
        {
//                                new AlertDialog.Builder(MainLogin.this)
//                                        .setTitle("Aviso")
//                                        .setMessage("Ligação efetuada com sucesso!")
//                                        .setCancelable(false)
//                                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                Toast.makeText(getApplicationContext(), "teste", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }).show();
            if (!(username.getText().toString().equalsIgnoreCase("")) && !(password.getText().toString().equalsIgnoreCase("")))
            {
                if (username.getText().toString().equalsIgnoreCase("admin") && password.getText().toString().equalsIgnoreCase("root"))
                {
                    DBConnection.openConnection(MainLogin.this, "Settings");
                }

                else
                {
                    DBConnection.openConnection(MainLogin.this, "Parking");
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToUrl(String url)
    {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
