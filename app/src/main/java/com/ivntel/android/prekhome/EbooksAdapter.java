package com.ivntel.android.prekhome;

/**
 * Created by ivnte on 2017-08-19.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ivntel.android.prekhome.POJO.Ebook;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ivntel on 2017-08-19.
 */
public class EbooksAdapter extends RecyclerView.Adapter<EbooksAdapter.ViewHolder> {

    private List<Ebook> mEbooks;
    private Context mContext;

    public static final String TAG = EbooksAdapter.class.getSimpleName();

    public EbooksAdapter(Context context, List<Ebook> ebooks) {
        mContext = context;
        mEbooks = ebooks;

    }

    public void updateData(List<Ebook> ebooks) {
        mEbooks = ebooks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mEbooks.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ebook, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    //Replace contents of a view(invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Ebook ebook = mEbooks.get(position);
        holder.rowEbookTitle.setText(ebook.getTitle());
        holder.rowEbookId.setText(String.valueOf(ebook.getId()));
        Glide.with(mContext).load(ebook.getIcon()).into(holder.rowEbookImage);

        /*Picasso.with(mContext)
                .load(Uri.parse(ebook.getIcon()))
                .noFade()
                .into(holder.rowEbookImage);*/

        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Clicked on " + mEbooks.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, EbookActivity.class);
                intent.putExtra(EbookActivity.ARG_EBOOK, ebook.getId());
                mContext.startActivity(intent);
            }
        });

    }
    // Replace the contents of a view (invoked by the layout manager)


    // Return the size of your dataset (invoked by the layout manager)
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.row_ebook_layout)
        RelativeLayout rowLayout;

        @Bind(R.id.row_ebook_image)
        ImageView rowEbookImage;

        @Bind(R.id.row_ebook_title)
        TextView rowEbookTitle;

        @Bind(R.id.row_ebook_id)
        TextView rowEbookId;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}