package com.distancelin.zhihudaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.distancelin.zhihudaily.GlideImageLoader;
import com.distancelin.zhihudaily.R;
import com.distancelin.zhihudaily.bean.NewsBean;
import com.distancelin.zhihudaily.utils.DateUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.distancelin.zhihudaily.bean.NewsBean.StoriesBean.ITEM_NEWS_TITTLE;
import static com.distancelin.zhihudaily.bean.NewsBean.StoriesBean.ITEM_TYPE_FOOTER;
import static com.distancelin.zhihudaily.bean.NewsBean.StoriesBean.ITEM_TYPE_HEADER;
import static com.distancelin.zhihudaily.bean.NewsBean.StoriesBean.ITEM_TYPE_NEWS;

/**
 * Created by distancelin on 2017/5/15.
 */

public class HomepageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewsBean.StoriesBean> mNews;
    private List<String> mBannerImageUrls = new ArrayList<>();
    private List<String> mBannerTittles = new ArrayList<>();
    private Context mContext;
    private ImageLoader mLoader;
    private onItemClickListener mListener;

    public HomepageRecyclerViewAdapter(List<NewsBean.StoriesBean> Data, List<NewsBean.TopStoriesBean> bannerNews, Context context) {
        this.mNews = Data;
        this.mContext = context;
        mLoader = new GlideImageLoader();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;

        switch (viewType) {
            case ITEM_TYPE_HEADER:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_banner, parent, false);
                holder = new BannerHolder(view);
                Log.i("H", "onCreateViewHolder,banner");
                break;
            case ITEM_NEWS_TITTLE:
                view = LayoutInflater.from(mContext).inflate(R.layout.news_item_date, parent, false);
                holder = new TittleHolder(view);
                Log.i("H", "onCreateViewHolder,tittle");
                break;
            case ITEM_TYPE_NEWS:
                view = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
                holder = new NewsHolder(view);
                Log.i("H", "onCreateViewHolder,news");
                break;
            case ITEM_TYPE_FOOTER:
                view = LayoutInflater.from(mContext).inflate(R.layout.load_more, parent, false);
                holder = new FooterHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerHolder) {
            ((BannerHolder) holder).banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            ((BannerHolder) holder).banner.setImageLoader(mLoader);
            ((BannerHolder) holder).banner.setImages(mBannerImageUrls);
            ((BannerHolder) holder).banner.setBannerTitles(mBannerTittles);
            ((BannerHolder) holder).banner.start();
        } else if (holder instanceof NewsHolder) {
            Glide.with(mContext).load(mNews.get(position).getImages().get(0)).into(((NewsHolder) holder).image);
            ((NewsHolder) holder).tittle.setText(mNews.get(position).getTitle());
            if (!mNews.get(position).isMultipic()) {
                ((NewsHolder) holder).multipic.setVisibility(View.GONE);
            }
        }
        else if(holder instanceof TittleHolder){
            ((TittleHolder) holder).textView.setText(DateUtil.format(mNews.get(position).getDate()));
        }

    }


    @Override
    public int getItemCount() {
        //增加底部的加载更多字样
        return mNews.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==mNews.size()) return ITEM_TYPE_FOOTER;
        return mNews.get(position).getItemType();
    }

    public void addNews(List<NewsBean.StoriesBean> news) {
        mNews.addAll(news);
        notifyDataSetChanged();
    }

    public void addBannerNews(List<NewsBean.TopStoriesBean> banner) {
        for (NewsBean.TopStoriesBean bean : banner
                ) {
            mBannerImageUrls.add(bean.getImage());
            mBannerTittles.add(bean.getTitle());
        }
    }

    public void setHeaderView(View headerView) {
        mNews.add(0, new NewsBean.StoriesBean(ITEM_TYPE_HEADER));
        notifyItemInserted(0);
    }

    public void addTittle(String date) {
        mNews.add(new NewsBean.StoriesBean(ITEM_NEWS_TITTLE,date));
        notifyDataSetChanged();
    }

    public void setFooterView() {
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(onItemClickListener mListener) {
        this.mListener = mListener;
    }

     class NewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tittle)
        TextView tittle;
        @BindView(R.id.picture)
        ImageView image;
        @BindView(R.id.multipic)
        TextView multipic;

        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(mNews.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    static class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_banner)
//        ImageView imageView;
        Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class FooterHolder extends RecyclerView.ViewHolder {

        public FooterHolder(View itemView) {
            super(itemView);
        }
    }
    static class TittleHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.date)
        TextView textView;
        public TittleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface onItemClickListener{
        void onItemClick(int newsId);
    }

}
