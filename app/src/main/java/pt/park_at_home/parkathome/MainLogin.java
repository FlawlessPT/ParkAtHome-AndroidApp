package pt.park_at_home.parkathome;

import android.content.DialogInterface;
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
import pt.park_at_home.parkathome.database.Functions;
import pt.park_at_home.parkathome.managers.SettingsLogin;
import pt.park_at_home.parkathome.utils.OpenType;

public class MainLogin extends AppCompatActivity
{
    EditText username;
    EditText password;
    TextView registerTextView;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        registerTextView = findViewById(R.id.textView2);
        loginBtn = findViewById(R.id.buttonLogin);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);

        registerTextView.setOnClickListener(v -> goToUrl("http://www.parkathome.tk"));

        loginBtn.setOnClickListener(view ->
        {
            SettingsLogin settingsLogin = new SettingsLogin();

            if (!(username.getText().toString().equalsIgnoreCase("")) && !(password.getText().toString().equalsIgnoreCase("")))
            {
                if (username.getText().toString().equalsIgnoreCase(settingsLogin.getUsername()) && password.getText().toString().equalsIgnoreCase(settingsLogin.getPassword()))
                {
                    DBConnection.openConnection(MainLogin.this, OpenType.SETTINGS);
                }
                else
                {
                    String username2 = username.getText().toString();
                    String password2 = password.getText().toString();

                    if (Functions.existsUser(username2, password2, MainLogin.this))
                    {
                        Toast.makeText(MainLogin.this, "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                        DBConnection.openConnection(MainLogin.this, OpenType.PARKING);
                        username.onEditorAction(EditorInfo.IME_ACTION_DONE);
                        password.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    }
                    else
                    {
                        Toast.makeText(MainLogin.this, "Utilizador ou password incorretos!", Toast.LENGTH_LONG).show();
                        username.onEditorAction(EditorInfo.IME_ACTION_DONE);
                        password.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    }
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
