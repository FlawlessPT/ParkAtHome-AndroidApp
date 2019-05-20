package pt.park_at_home.parkathome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import pt.park_at_home.parkathome.database.DBConnection;
import pt.park_at_home.parkathome.utils.SimpleAlert;
import pt.park_at_home.parkathome.utils.TempFile;

import java.util.Timer;
import java.util.TimerTask;

public class Parking extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private Context ctx = Parking.this;
    TextView drawerTitle;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //        DBConnection connection = new DBConnection(ctx);
        //        SimpleAlert simpleAlert = new SimpleAlert(ctx);
        //        simpleAlert.setMessage("Estado da ligação: " + connection.getStateToString());
        //        simpleAlert.show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TempFile tempFile = new TempFile(ctx);
        drawerTitle = headerView.findViewById(R.id.ProfileNameTextView);
        int separador = tempFile.read().indexOf("|");
        drawerTitle.setText(tempFile.read().substring(separador + 1));

        //        SimpleAlert simpleAlert2 = new SimpleAlert(Parking.this);
        //
        //        Timer t = new Timer();
        //
        //        t.schedule(new TimerTask()
        //        {
        //            @Override
        //            public void run()
        //            {
        //                int contar = 0;
        //                contar++;
        //                System.out.println(contar + "");
        //            }
        //        }, 0, 3000);
        //        System.out.println("Resetou!");

        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ParkingFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_parking);
        }
    }

    private void checkPark()
    {

    }

    @Override
    public void onDestroy()
    {
        //        TesteMessage.sendTesteMessage1(this);
        //        OnlineUsers onlineUsers = new OnlineUsers(this);
        //        TempFile tempFile = new TempFile(ctx);
        //        int separador = tempFile.read().indexOf("|");
        //        onlineUsers.removeUser(tempFile.read().substring(0, separador-1));
        super.onDestroy();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parking, menu);
        return true;
    }

    //    @Override
    //    public boolean onOptionsItemSelected(MenuItem item)
    //    {
    //        // Handle action bar item clicks here. The action bar will
    //        // automatically handle clicks on the Home/Up button, so long
    //        // as you specify a parent activity in AndroidManifest.xml.
    //        int id = item.getItemId();
    //
    //        //noinspection SimplifiableIfStatement
    ////        if (id == R.id.action_settings)
    ////        {
    ////            return true;
    ////        }
    //
    //        return super.onOptionsItemSelected(item);
    //    }

    //
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_parking)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ParkingFragment()).commit();
            //Toast.makeText(getApplicationContext(), "Parking", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_profile)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            //Toast.makeText(getApplicationContext(), "Perfil", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
