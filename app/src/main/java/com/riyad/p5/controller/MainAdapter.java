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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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


        String strDate = mData.get(position).getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(strDate);
            SimpleDateFormat sdfnewformat = new SimpleDateFormat("dd MMMM yyyy");
            String finalDateString = sdfnewformat.format(convertedDate);
            holder.mDatePub.setText(finalDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.mTvTitle.setText(mData.get(position).getTitle());
        holder.mDesc.setText(mData.get(position).getShortDesc());

        holder.mSection.setText("Section : " + mData.get(position).getSection());

        Glide.with(holder.mIvThumbnail).load(mData.get(position).getImageUrl()).centerCrop().into(holder.mIvThumbnail);

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
        private final TextView mDesc;
        private final TextView mDatePub;
        private final TextView mSection;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mIvThumbnail = itemView.findViewById(R.id.item_main_iv_thumbnail);
            mTvTitle = itemView.findViewById(R.id.item_main_tv_title);
            mDesc = itemView.findViewById(R.id.item_main_iv_desc);
            mDatePub = itemView.findViewById(R.id.item_main_tv_date_pub);
            mSection = itemView.findViewById(R.id.item_main_tv_section);
        }
    }
}
