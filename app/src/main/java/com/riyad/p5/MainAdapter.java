package com.riyad.p5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.riyad.p5.model.MostPopularArticle;
import com.riyad.p5.model.TopStoriesArticle;

import java.util.List;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<TopStoriesArticle> mData;
    private List<MostPopularArticle> mDataMostPopular;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                      .inflate(R.layout.item_main, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTvTitle.setText(mData.get(position).getTitle());

        if (mData.get(position).getMultimedia() != null && !mData.get(position).getMultimedia().isEmpty()){

            String url = mData.get(position).getMultimedia().get(0).getUrl();

        Glide.with(holder.mIvThumbnail).load(url).centerInside().into(holder.mIvThumbnail);
        }else{

            // TODO afficher une image par défaut
            Glide.with(holder.mIvThumbnail).asDrawable(R.drawable.ic_launcher_background).into(holder.mIvThumbnail)
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setDataTopStories(@NonNull List<TopStoriesArticle> topStoriesArticles) {
        mData = topStoriesArticles;

        notifyDataSetChanged();
    }

    public void setDataMostPopular(@NonNull List<MostPopularArticle> mostPopularArticles) {
        mDataMostPopular = mostPopularArticles;

        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIvThumbnail;
        private final TextView mTvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mIvThumbnail = itemView.findViewById(R.id.item_main_iv_thumbnail);
            mTvTitle = itemView.findViewById(R.id.item_main_tv_title);
        }
    }
}
