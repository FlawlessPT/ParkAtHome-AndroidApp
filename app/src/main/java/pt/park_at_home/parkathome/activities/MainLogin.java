package pt.park_at_home.parkathome.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pt.park_at_home.parkathome.R;
import pt.park_at_home.parkathome.database.DBConnection;
import pt.park_at_home.parkathome.managers.Admin;
import pt.park_at_home.parkathome.managers.LoggedUser;
import pt.park_at_home.parkathome.managers.User;
import pt.park_at_home.parkathome.utils.SimpleAlert;

public class MainLogin extends AppCompatActivity {
    EditText username;
    EditText password;
    TextView registerTextView;
    Button loginBtn;

    private Context ctx = MainLogin.this;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        MaxNumberMatriculasFile maxNumberMatriculasFile = new MaxNumberMatriculasFile(this);
//        maxNumberMatriculasFile.write("3");
//        TempFile tempFile = new TempFile(this);
//        tempFile.create();

//        final String androidId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
//        SimpleAlert alert = new SimpleAlert(this);
//        alert.setMessage(androidId);
//        alert.show();
        Admin admin = new Admin(ctx);
        LoggedUser loggedUser = new LoggedUser(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        registerTextView = findViewById(R.id.textView2);
        loginBtn = findViewById(R.id.buttonLogin);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);

        registerTextView.setOnClickListener(v -> goToUrl("https://parkathome.000webhostapp.com/Park@Home/Inicio/"));

        loginBtn.setOnClickListener(view ->
        {
            if (!(username.getText().toString().isEmpty()) && !(password.getText().toString().isEmpty())) {
                if (username.getText().toString().equalsIgnoreCase(admin.getUsername()) && password.getText().toString().equals(admin.getPassword())) {
                    openActivity(SettingsActivity.class);
                } else {
                    String username2 = username.getText().toString();
                    String password2 = password.getText().toString();

                    User user = new User(ctx, username2, password2);

                    if (user.existsUser()) {
                        ProgressDialog dialog = new ProgressDialog(this);
                        dialog.setMessage("A efetuar login...");
                        dialog.setCancelable(false);
                        dialog.setInverseBackgroundForced(false);
                        dialog.show();
                        Thread loginThread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    super.run();
                                    sleep(1000);
                                } catch (Exception ignored) {

                                } finally {
                                    dialog.dismiss();
                                    openActivity(Parking.class);
                                    loggedUser.setUserName(username2);
                                    loggedUser.saveUserTempData();
                                    //tempFile.write(getNameByUsername(username2));
                                    username.onEditorAction(EditorInfo.IME_ACTION_DONE);
                                    password.onEditorAction(EditorInfo.IME_ACTION_DONE);
                                }
                            }
                        };
                        loginThread.start();
                    } else {
                        Toast.makeText(MainLogin.this, "Utilizador ou password incorretos!", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(MainLogin.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //    private void startApplication()
    //    {
    //        TempFile tempFile = new TempFile(ctx);
    //        DBConnection connection = new DBConnection(ctx);
    //        //tempFile.create();
    //        //connection.open();
    //        //onlineUsers.loadUsers();
    //    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    private void openActivity(Class newActivity) {
        Intent intent = new Intent(getApplicationContext(), newActivity);
        startActivity(intent);
    }
}
