package com.elcsresearch.mouachir.payroll;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.model.HelpLiveo;
import br.liveo.model.Navigation;
import br.liveo.navigationliveo.NavigationLiveo;

public class NavigationMainLiveo extends NavigationLiveo
        implements OnItemClickListener, TabDashboard.OnFragmentInteractionListener,
         TabEmployeesList.OnFragmentInteractionListener , TabArchives.OnFragmentInteractionListener {


    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private HelpLiveo mHelpLiveo;

    @Override
    public void onInt(Bundle savedInstanceState) {


        this.userName.setText("Remache Amine");
        this.userEmail.setText("ea_remache@esi.dz");
        this.userPhoto.setImageResource(R.mipmap.ic_launcher_round);
        this.userBackground.setImageResource(R.drawable.ic_user_background_second);

        // Creating items navigation
        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.nav_dashboard), R.drawable.icon_dashboard);
        mHelpLiveo.add(getString(R.string.nav_list), R.drawable.icon_employees);
        mHelpLiveo.add(getString(R.string.nav_records), R.drawable.icon_history);
        mHelpLiveo.add(getString(R.string.nav_settings), R.drawable.ic_menu_manage);
        mHelpLiveo.addSeparator(); // Item separator
        mHelpLiveo.addSubHeader(getString(R.string.nav_support)); //Item subHeader
        mHelpLiveo.add(getString(R.string.nav_report), R.drawable.ic_menu_share);
        mHelpLiveo.add(getString(R.string.nav_contact), R.drawable.ic_menu_send);

        //with(this, Navigation.THEME_DARK); //add theme dark
        with(this, Navigation.THEME_LIGHT);  //add theme light

        with(this) // default theme is dark
                .startingPosition(0) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())
                //.footerItem(R.string.action_settings, R.drawable.ic_rudsonlive)
                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                .build();

    }


    @Override //The "R.id.container" should be used in "beginTransaction (). Replace"
    public void onItemClick(int position) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        //Fragment mFragment = new TabDashboard().newInstance(mHelpLiveo.get(position).getName());

        Fragment mFragment = null;
        String title = getString(R.string.app_name);

        switch (position) {
            case R.id.nav_dashboard:
                mFragment = new TabDashboard();
                title = getString(R.string.nav_dashboard);

                break;
            case R.id.nav_list:
                mFragment = new TabEmployeesList();
                title = getString(R.string.nav_list);
                break;
            case R.id.nav_records:
                mFragment = new TabArchives();
                title = getString(R.string.nav_records);

        }

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.content_frame, mFragment).commit();
        }
    }


    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };



    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayView(R.id.nav_dashboard);
    }*/

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
        getMenuInflater().inflate(R.menu.navigation_main, menu);
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




    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.nav_dashboard:
                fragment = new TabDashboard();
                title  = "Dashboard";

                break;
            case R.id.nav_list:
                fragment = new TabEmployeesList();
                title = "List";
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

    }




    //@SuppressWarnings("StatementWithEmptyBody")
    /*@Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Handle navigation view item clicks here.
        int id = item.getItemId();


        /*if (id == R.id.nav_dashboard) {

        } else if (id == R.id.nav_list) {

        } else if (id == R.id.nav_records) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_report) {

        } else if (id == R.id.nav_contact) {

        }
        displayView(id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
