package com.riyad.p5.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.riyad.p5.R;
import com.riyad.p5.data.model.ui.Article;

import java.util.List;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Article> mData;


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
        holder.mTvTitle.setText(mData.get(position).getShortDesc());

        Glide.with(holder.mIvThumbnail).load(mData.get(position).getImageUrl()).centerInside().into(holder.mIvThumbnail);

// todo afficher les autres données sauf title.
    }


    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setData(@NonNull List<Article> articles) {
        mData = articles;

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
