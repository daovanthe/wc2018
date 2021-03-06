package th.wc2018.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import th.wc2018.R;
import th.wc2018.WcService;
import th.wc2018.activity.fragment.CommonFragment;
import th.wc2018.activity.fragment.GroupScoreFragment;
import th.wc2018.activity.fragment.LiveScoreFragment;
import th.wc2018.activity.fragment.MatchesFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // for testing

        initFragment();

        Intent serviceIntent = new Intent();
        serviceIntent.setPackage("th.wc2018");
        serviceIntent.setClass(this, WcService.class);


//        this.bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
        startService(serviceIntent);
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();

        liveScoreFragment = new LiveScoreFragment();
        matchesFragment = new MatchesFragment();
        groupScoreFragment = new GroupScoreFragment();

        liveScoreFragment.setISwipeListener(new CommonFragment.ISwipeListener() {
            @Override
            public void swipeTo(byte direction) {
                if (direction == CommonFragment.SWIPE_LEFT) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_to_enter_from_right, R.anim.slide_to_left);
                    fragmentTransaction.replace(R.id.frag_holder, matchesFragment);
                    fragmentTransaction.commit();
                }
            }
        });

        matchesFragment.setISwipeListener(new CommonFragment.ISwipeListener() {
            @Override
            public void swipeTo(byte direction) {
                if (direction == CommonFragment.SWIPE_LEFT) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_to_enter_from_right, R.anim.slide_to_left);
                    fragmentTransaction.replace(R.id.frag_holder, groupScoreFragment);
                    fragmentTransaction.commit();
                } else if (direction == CommonFragment.SWIPE_RIGHT) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_to_enter_from_left, R.anim.slide_to_right);
                    fragmentTransaction.replace(R.id.frag_holder, liveScoreFragment);
                    fragmentTransaction.commit();
                }
            }
        });


        groupScoreFragment.setISwipeListener(new CommonFragment.ISwipeListener() {
            @Override
            public void swipeTo(byte direction) {
                if (direction == CommonFragment.SWIPE_RIGHT) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_to_enter_from_left, R.anim.slide_to_right);
                    fragmentTransaction.replace(R.id.frag_holder, matchesFragment);
                    fragmentTransaction.commit();
                }
            }
        });

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frag_holder, matchesFragment);
        fragmentTransaction.commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }


    // matchesFragment

    FragmentManager fragmentManager;

    LiveScoreFragment liveScoreFragment;
    MatchesFragment matchesFragment;
    GroupScoreFragment groupScoreFragment;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_live_score) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag_holder, liveScoreFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_matches) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag_holder, matchesFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_group_score) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag_holder, groupScoreFragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
///

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Intent intent = new Intent(this, MatchesActivity.class);
//        startActivity(intent);
//
//    }

    public void refresh() {
        Log.e("THE_DV", "Service ENd");
        runOnUiThread(() -> {
            matchesFragment.refreshData();
        });
    }

    //region SERVICE_CONNECT

    private WcService mservice;
    private boolean isBound;

//    private ServiceConnection connection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName className, IBinder service) {
//            Log.d("THE_DV", "Connected Service loadData");
//            WcService.LocalBinder binder = (WcService.LocalBinder) service;
//            mservice = binder.getService();
//            LoadData loadData = new LoadData(MainActivity.this, "wcdata");
//            mservice.onLoadDataOK(new ILoadDataApiListener() {
//                public void loadDone() {
//                    refresh();
//                }
//            });
//            mservice.startLoadData(loadData);
//            mservice.runServiceTask();
//            isBound = true;
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName arg0) {
//            isBound = false;
//        }
//    };
    //endregion


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();


    }
}
