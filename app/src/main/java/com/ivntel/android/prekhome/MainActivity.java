package com.ivntel.android.prekhome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

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

import static com.ivntel.android.prekhome.R.id.btnSubmit;
import static com.ivntel.android.prekhome.R.id.spinner1;
import static com.ivntel.android.prekhome.R.id.spinner2;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.ebookRecyclerview)
    RecyclerView mEbookRecyclerView;

    @Bind(R.id.videoRecyclerview)
    RecyclerView mVideoRecyclerView;

    @Bind(spinner1)
    Spinner mSpinner1;

    @Bind(spinner2)
    Spinner mSpinner2;

    @Bind(btnSubmit)
    Button mButtonSubmit;

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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

    public void submitButton(View v){
        age = String.valueOf(mSpinner2.getSelectedItem());
        language = String.valueOf(mSpinner1.getSelectedItem());
        if(language.equals("Spanish")){
            language = "2";
        }
        else{
            language = "1";
        }
        getGetPreKNewResults(age, language);
    }
}




