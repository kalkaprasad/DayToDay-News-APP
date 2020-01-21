package firbase.app.daytodayindiaapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_admin) {

            Intent i = new Intent(WelcomeActivity.this,admin.class);
            startActivity(i);
            // Handle the camera action
        }
          else if (id == R.id.nav_share) {
  Intent i = new Intent(WelcomeActivity.this,Phonenumber.class);
  startActivity(i);
  finish();
        }
          else if(id==R.id.profile)
        {
            Intent i= new Intent(WelcomeActivity.this,Phonenumber.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void treand(View view)
    {
        //Toast.makeText(this, "You are clicked on the Treanding news", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(WelcomeActivity.this,treandinglist.class);
        startActivity(i);
        Toast.makeText(this, "tranding news on", Toast.LENGTH_SHORT).show();
    }
    public  void business(View view)
    {

        Intent i = new Intent(WelcomeActivity.this,businessNews.class);
        startActivity(i);
        //Toast.makeText(this, " you are clicked on the Business News", Toast.LENGTH_SHORT).show();
    }
    public void tech(View view)
    {

        Intent i= new Intent(WelcomeActivity.this,teachnews.class);
        startActivity(i);
         // Toast.makeText(this, "You are clicked on the Teach news", Toast.LENGTH_SHORT).show();
    }
    public  void  world(View view)
    {
        Intent i = new Intent(WelcomeActivity.this,worldnews.class);
        startActivity(i);
       // Toast.makeText(this, " You are clicked on the World news", Toast.LENGTH_SHORT).show();
    }
    public  void   breaking(View view)
    {
       Intent i = new Intent(WelcomeActivity.this,breakingNews.class);
       startActivity(i);
        // Toast.makeText(this, " You are clicked on the Breaking News", Toast.LENGTH_SHORT).show();
    }
    public  void  crime(View view)
    {
        Intent i= new Intent(WelcomeActivity.this,crimenews.class);
        startActivity(i);
        // Toast.makeText(this, "You are clicked on the Crime news", Toast.LENGTH_SHORT).show();
    }

}
