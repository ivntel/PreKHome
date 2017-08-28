package com.ivntel.android.prekhome;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.ivntel.android.prekhome.POJO.Ebook;
import com.ivntel.android.prekhome.POJO.Example;
import com.ivntel.android.prekhome.POJO.Video;
import com.ivntel.android.prekhome.Retrofit.Restclient;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ivntel.android.prekhome.R.id.spinner1;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.ebookRecyclerview)
    RecyclerView mEbookRecyclerView;

    @Bind(R.id.videoRecyclerview)
    RecyclerView mVideoRecyclerView;

    @Bind(spinner1)
    Spinner mSpinner1;

    private LinearLayoutManager mEbookLayoutManager, mVideoLayoutManager;
    //the adapter that works with the array
    private EbooksAdapter mEbooksAdapter;
    private VideosAdapter mVideosAdapter;
    //giving the array of songs its variable name
    private List<Ebook> mEbooks = new ArrayList<Ebook>();
    private List<Video> mVideos = new ArrayList<Video>();
    private Example example;
    String language, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getEbooksAndVideos();

        //sets up Ebook list
        mEbookRecyclerView.setHasFixedSize(false);
        mEbookLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mEbookRecyclerView.setLayoutManager(mEbookLayoutManager);
        mEbooksAdapter = new EbooksAdapter(this, mEbooks);
        mEbookRecyclerView.setAdapter(mEbooksAdapter);
        //sets up video list
        mVideoRecyclerView.setHasFixedSize(false);
        mVideoLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mVideoRecyclerView.setLayoutManager(mVideoLayoutManager);
        mVideosAdapter = new VideosAdapter(this, mVideos);
        mVideoRecyclerView.setAdapter(mVideosAdapter);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getEbooksAndVideos() {
        Restclient.getExampleApi().getPreKContents().enqueue(getPreKResults);
    }

    Callback<Example> getPreKResults = new Callback<Example>() {
        @Override
        public void onResponse(Call<Example> call, Response<Example> response) {
            if (response.isSuccessful()) {
                Log.d("Successful response", response.body().getResults().getEbooks().get(0).getTitle());
                mEbooks = response.body().getResults().getEbooks();
                mEbooksAdapter.updateData(mEbooks);

                Log.d("Successful response", response.body().getResults().getVideos().get(0).getTitle());
                mVideos = response.body().getResults().getVideos();
                mVideosAdapter.updateData(mVideos);
            } else {
                Log.d("Not successful response", "in MA");
            }
        }

        @Override
        public void onFailure(Call<Example> call, Throwable t) {
            Log.d("Failure", t.toString());
        }
    };

    public void getGetPreKNewResults(String language, String age) {
        Restclient.getExampleApi().getPreKContentsAgeAndLanguage(language, age).enqueue(getPreKNewEbooksAndVideoResults);
    }

    Callback<Example> getPreKNewEbooksAndVideoResults = new Callback<Example>() {
        @Override
        public void onResponse(Call<Example> call, Response<Example> response) {
            if (response.isSuccessful()) {
                Log.d("Successful response", response.body().getResults().getEbooks().get(0).getTitle());
                mEbooks = response.body().getResults().getEbooks();
                mEbooksAdapter.updateData(mEbooks);

                Log.d("Successful response", response.body().getResults().getVideos().get(0).getTitle());
                mVideos = response.body().getResults().getVideos();
                mVideosAdapter.updateData(mVideos);
            } else {
                Log.d("Not successful response", "in MA");
            }
        }

        @Override
        public void onFailure(Call<Example> call, Throwable t) {
            Log.d("Failure", t.toString());
        }
    };

    public void onClickThree(View v){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        language = String.valueOf(mSpinner1.getSelectedItem());
        if(language.equals("Spanish")){
            language = "2";
        }
        else{
            language = "1";
        }
        age = "3";
        getGetPreKNewResults(age, language);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void onClickFour(View v){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        language = String.valueOf(mSpinner1.getSelectedItem());
        if(language.equals("Spanish")){
            language = "2";
        }
        else{
            language = "1";
        }
        age = "4";
        getGetPreKNewResults(age, language);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void onClickFive(View v){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        language = String.valueOf(mSpinner1.getSelectedItem());
        if(language.equals("Spanish")){
            language = "2";
        }
        else{
            language = "1";
        }
        age = "5";
        getGetPreKNewResults(age, language);
        drawer.closeDrawer(GravityCompat.START);
    }
}
