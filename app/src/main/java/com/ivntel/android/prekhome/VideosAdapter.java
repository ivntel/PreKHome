package com.ivntel.android.prekhome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ivntel.android.prekhome.POJO.Video;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ivntel on 2017-08-19.
 */
public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {

    private List<Video> mVideos;
    private Context mContext;

    public static final String TAG = EbooksAdapter.class.getSimpleName();

    public VideosAdapter(Context context, List<Video> videos) {
        mContext = context;
        mVideos = videos;

    }

    public void updateData(List<Video> videos) {
        mVideos = videos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_video, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    //Replace contents of a view(invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Video video = mVideos.get(position);
        holder.rowVideoTitle.setText(video.getTitle());
        holder.rowVideoId.setText(String.valueOf(video.getId()));
        Glide.with(mContext).load(video.getIcon()).into(holder.rowVideoImage);

        holder.rowVideoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Clicked on " + mVideos.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(mContext, DetailActivity.class);
                //intent.putExtra(DetailActivity.ARG_SONG, song);
                //mContext.startActivity(intent);
            }
        });

    }
    // Replace the contents of a view (invoked by the layout manager)


    // Return the size of your dataset (invoked by the layout manager)
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.row_video_layout)
        RelativeLayout rowVideoLayout;

        @Bind(R.id.row_video_image)
        ImageView rowVideoImage;

        @Bind(R.id.row_video_title)
        TextView rowVideoTitle;

        @Bind(R.id.row_video_id)
        TextView rowVideoId;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}