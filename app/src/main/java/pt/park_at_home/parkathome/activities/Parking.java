package pt.park_at_home.parkathome.activities;

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

import pt.park_at_home.parkathome.fragments.MatriculasManagerFragment;
import pt.park_at_home.parkathome.fragments.ParkingFragment;
import pt.park_at_home.parkathome.fragments.ProfileFragment;
import pt.park_at_home.parkathome.R;
import pt.park_at_home.parkathome.managers.LoggedUser;

public class Parking extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private Context ctx = Parking.this;
    TextView drawerTitle;
    TextView drawerDesc;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        LoggedUser loggedUser = new LoggedUser(this);
        drawerTitle = headerView.findViewById(R.id.ProfileNameTextView);
        drawerDesc = headerView.findViewById(R.id.MatriculasTextView);
        drawerTitle.setText(loggedUser.getNome());
        drawerDesc.setText(loggedUser.getMatriculas());

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
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.nav_parking)
        {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            View headerView = navigationView.getHeaderView(0);
            LoggedUser loggedUser = new LoggedUser(this);
            drawerTitle = headerView.findViewById(R.id.ProfileNameTextView);
            drawerDesc = headerView.findViewById(R.id.MatriculasTextView);
            drawerTitle.setText(loggedUser.getNome());
            drawerDesc.setText(loggedUser.getMatriculas());
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ParkingFragment()).commit();
        }
        else if (id == R.id.nav_profile)
        {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            View headerView = navigationView.getHeaderView(0);
            LoggedUser loggedUser = new LoggedUser(this);
            drawerTitle = headerView.findViewById(R.id.ProfileNameTextView);
            drawerDesc = headerView.findViewById(R.id.MatriculasTextView);
            drawerTitle.setText(loggedUser.getNome());
            drawerDesc.setText(loggedUser.getMatriculas());
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
        }
        else if (id == R.id.nav_matriculas) {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            View headerView = navigationView.getHeaderView(0);
            LoggedUser loggedUser = new LoggedUser(this);
            drawerTitle = headerView.findViewById(R.id.ProfileNameTextView);
            drawerDesc = headerView.findViewById(R.id.MatriculasTextView);
            drawerTitle.setText(loggedUser.getNome());
            drawerDesc.setText(loggedUser.getMatriculas());
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MatriculasManagerFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
