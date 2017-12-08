package com.nickljackson.fubgaming;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // setzt das View für die Actionbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        //setzt den View für den NavDrawer & den button oben links
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        //setzt den titel
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Members");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new MemberFragment());
        fragmentTransaction.commit();
        NavigationView mNavView = (NavigationView) findViewById(R.id.nav_view);
        mNavView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (SaveSharedPreferences.getSteamId(MainActivity.this) != 0) {
                            switch (item.getItemId()) {
                                case R.id.nav_members:
                                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.main_container, new MemberFragment());
                                    fragmentTransaction.commit();
                                    getSupportActionBar().setTitle("Members");
                                    item.setChecked(true);
                                    mDrawerLayout.closeDrawers();
                                    break;
                                case R.id.nav_temine:
                                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.main_container, new TerminFragment());
                                    fragmentTransaction.commit();
                                    getSupportActionBar().setTitle("Termine");
                                    item.setChecked(true);
                                    mDrawerLayout.closeDrawers();
                                    break;
                                case R.id.nav_account:
                                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.main_container, new SettingsFragment());
                                    fragmentTransaction.commit();
                                    getSupportActionBar().setTitle("Account Settings");
                                    item.setChecked(true);
                                    mDrawerLayout.closeDrawers();
                                    break;
                                case R.id.nav_logout:
                                    SaveSharedPreferences.setSteamId(MainActivity.this, 0);
                                    startLoginActivity();
                                    break;
                            }
                            return true;
                        }
                        return false;
                    }
                }
        );


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SaveSharedPreferences.getSteamId(this) == 0) {
            startLoginActivity();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
    }
}

