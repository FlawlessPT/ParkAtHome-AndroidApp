package pt.park_at_home.parkathome.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pt.park_at_home.parkathome.R;
import pt.park_at_home.parkathome.database.DBConnection;

public class loading_screen extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        DBConnection connection = new DBConnection(this);
        connection.open();

        ProgressDialog dialog = new ProgressDialog(loading_screen.this);
        dialog.setMessage("A carregar dados...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
        Thread welcomeThread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    super.run();
                    sleep(1500);  //Delay of 3 seconds
                } catch (Exception e)
                {

                } finally
                {
                    dialog.dismiss();
                    Intent i = new Intent(loading_screen.this, MainLogin.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}
