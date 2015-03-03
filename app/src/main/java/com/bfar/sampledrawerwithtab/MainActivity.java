package com.bfar.sampledrawerwithtab;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bfar.sampledrawerwithtab.fragments.FragmentAbout;
import com.bfar.sampledrawerwithtab.fragments.FragmentCategories;
import com.bfar.sampledrawerwithtab.fragments.FragmentHome;
import com.bfar.sampledrawerwithtab.fragments.FragmentSample;
import com.bfar.sampledrawerwithtab.fragments.FragmentTopPaid;
import com.bfar.sampledrawerwithtab.fragments.FragmentWithTab;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        FragmentWithTab.OnFragmentInteractionListener,FragmentCategories.OnFragmentInteractionListener,
        FragmentHome.OnFragmentInteractionListener,FragmentTopPaid.OnFragmentInteractionListener,
        FragmentSample.OnFragmentInteractionListener,FragmentAbout.OnFragmentInteractionListener

        {

    private String TAG = MainActivity.class.getSimpleName();

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
//    private NavigationDrawerFragment mNavigationDrawerFragment;

//    DrawerLayout mDrawerLayout;
    NavigationDrawerFragment mNavigationDrawerFragment;

    ListView mDrawerList;
    
    private CharSequence mDrawerTitle;

    private String[] mDrawerMenus;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        selectItem(position);

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.drawer_tab);
                break;
            case 1:
                mTitle = getString(R.string.drawer_sample);
                break;
            case 2:
                mTitle = getString(R.string.drawer_about);
                break;
        }
    }


            // The click listener for ListView in the navigation drawer
    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            selectItem(position);
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    private void loadSelectedFragmentInDrawer(){


        /*FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();*/
    }

    private void selectItem(int position) {
        Log.e(TAG, "onViewCreated:" + position);
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = FragmentWithTab.newInstance("","");
                break;

            case 1:
                fragment = FragmentSample.newInstance("","");
                break;

            case 2:
                fragment = FragmentAbout.newInstance("","");
                break;
            default:
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, "" + mTitle)
                        .commit();

        onSectionAttached(position); //Change action bar title

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
