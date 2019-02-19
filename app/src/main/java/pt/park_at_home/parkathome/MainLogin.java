package pt.park_at_home.parkathome;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainLogin extends AppCompatActivity
{

    EditText username;
    EditText password;
    TextView registerBtn;
    Button loginBtn;
    Button testBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        registerBtn = findViewById(R.id.textView3);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        loginBtn = findViewById(R.id.buttonLogin);
        testBtn = findViewById(R.id.testeButton);

        registerBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                goToUrl("http://www.facebook.com/");
            }
        });

        testBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!(username.getText().toString().equalsIgnoreCase("")) && !(password.getText().toString().equalsIgnoreCase("")))
                {
                    if (username.getText().toString().equalsIgnoreCase("admin") && password.getText().toString().equalsIgnoreCase("root"))
                    {

                        openActivity(SettingsActivity.class);

                    }
                    else
                    {

                        openActivity(Parking.class);

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToUrl(String url)
    {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    private void openActivity(Class activityClass)
    {
        startActivity(new Intent(this, activityClass));
    }


}
