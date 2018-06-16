package th.wc2018.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.MobileAds;

import th.wc2018.R;
import th.wc2018.WcService;
import th.wc2018.activity.fragment.GroupScoreFragment;
import th.wc2018.activity.fragment.HistoryScoreFragment;
import th.wc2018.activity.fragment.LiveScoreFragment;
import th.wc2018.activity.fragment.MatchesFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , HistoryScoreFragment.IActivityCallBack {

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

        //this.bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
        //startService(serviceIntent);
        ///
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(1);
        // admob

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-1510017343836393~6283549009");
        // video
    }

    // region Admob

    // endregion

    ViewPager mPager;
    ScreenSlidePagerAdapter mPagerAdapter;

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private boolean isEmptyLiveScore;




    public interface CalendarPageFragmentListener {
        void onSwitchToNextFragment();
    }

    public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        int NUM_PAGES = 4;

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return liveScoreFragment;
                case 1:
                    return matchesFragment;
                case 2:
                    return historyScoreFragment;
                case 3:
                    return groupScoreFragment;
            }
            return liveScoreFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public void callBack() {

    }
    private void initFragment() {
        //

        Log.e("THE_DV", "ini fragment ");

        fragmentManager = getSupportFragmentManager();
        liveScoreFragment = new LiveScoreFragment();// ((SwipeRefreshLayout.OnRefreshListener) liveScoreFragment).onRefresh();
        matchesFragment = new MatchesFragment();//((SwipeRefreshLayout.OnRefreshListener) matchesFragment).onRefresh();
        groupScoreFragment = new GroupScoreFragment();//((SwipeRefreshLayout.OnRefreshListener) groupScoreFragment).onRefresh();
        historyScoreFragment = new HistoryScoreFragment();//((SwipeRefreshLayout.OnRefreshListener) historyScoreFragment).onRefresh();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.frag_holder, liveScoreFragment);
//        fragmentTransaction.commit();

//        liveScoreFragment.setISwipeListener(new CommonFragment.ISwipeListener() {
//            @Override
//            public void swipeTo(byte direction) {
//                if (direction == CommonFragment.SWIPE_LEFT) {
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.setCustomAnimations(R.anim.slide_to_enter_from_right, R.anim.slide_to_left);
//                    fragmentTransaction.replace(R.id.frag_holder, matchesFragment);
//                    fragmentTransaction.commit();
//                }
//            }
//        });
//
//        matchesFragment.setISwipeListener(new CommonFragment.ISwipeListener() {
//            @Override
//            public void swipeTo(byte direction) {
//                if (direction == CommonFragment.SWIPE_LEFT) {
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.setCustomAnimations(R.anim.slide_to_enter_from_right, R.anim.slide_to_left);
//                    fragmentTransaction.replace(R.id.frag_holder, groupScoreFragment);
//                    fragmentTransaction.commit();
//                } else if (direction == CommonFragment.SWIPE_RIGHT) {
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.setCustomAnimations(R.anim.slide_to_enter_from_left, R.anim.slide_to_right);
//                    fragmentTransaction.replace(R.id.frag_holder, liveScoreFragment);
//                    fragmentTransaction.commit();
//                }
//            }
//        });
//
//
//        groupScoreFragment.setISwipeListener(new CommonFragment.ISwipeListener() {
//            @Override
//            public void swipeTo(byte direction) {
//                if (direction == CommonFragment.SWIPE_RIGHT) {
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.setCustomAnimations(R.anim.slide_to_enter_from_left, R.anim.slide_to_right);
//                    fragmentTransaction.replace(R.id.frag_holder, matchesFragment);
//                    fragmentTransaction.commit();
//                }
//            }
//        });


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

    Fragment liveScoreFragment;
    MatchesFragment matchesFragment;
    GroupScoreFragment groupScoreFragment;
    HistoryScoreFragment historyScoreFragment;


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_live_score) {
            mPager.setCurrentItem(0);
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frag_holder, liveScoreFragment);
//            fragmentTransaction.commit();
        } else if (id == R.id.nav_matches) {
            mPager.setCurrentItem(1);
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frag_holder, matchesFragment);
//            fragmentTransaction.commit();
        } else if (id == R.id.nav_group_score) {
            mPager.setCurrentItem(2);
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frag_holder, groupScoreFragment);
//            fragmentTransaction.commit();

        } else if (id == R.id.nav_history_score) {
            mPager.setCurrentItem(3);
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
