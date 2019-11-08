package com.riyad.p5.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    private Context context;

    MainAdapter(Context con){

        this.context = con;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(mData.get(position));


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

        public void bind(Article article) {

            String strDate = article.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date convertedDate = new Date();
            try {
                convertedDate = dateFormat.parse(strDate);
                SimpleDateFormat sdfnewformat = new SimpleDateFormat("dd MMMM yyyy");
                String finalDateString = sdfnewformat.format(convertedDate);
                mDatePub.setText(finalDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            mTvTitle.setText(article.getTitle());
            mDesc.setText(article.getShortDesc());

            mSection.setText(article.getSection());

            Glide.with(mIvThumbnail).load(article.getImageUrl()).centerCrop().into(mIvThumbnail);

            itemView.setOnClickListener(view -> {

                 Intent intent = new Intent(context, WebViewActivity.class);
                 intent.putExtra(WebViewActivity.Companion.getEXTRA_IMAGE_URL(),article.getArticleUrl());
                 context.startActivity(intent);


                //TODO ouvrir l'activité de la webView

            });
        }

    }
}
