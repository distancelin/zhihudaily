package com.distancelin.zhihudaily.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.distancelin.zhihudaily.R;
import com.distancelin.zhihudaily.activity.NewsDetailActivity;
import com.distancelin.zhihudaily.adapter.HomepageRecyclerViewAdapter;
import com.distancelin.zhihudaily.bean.NewsBean;
import com.distancelin.zhihudaily.callback.RecyclerItemClickCallback;
import com.distancelin.zhihudaily.homepage.presenter.HomepagePresenter;
import com.distancelin.zhihudaily.homepage.presenter.HomepagePresenterImpl;
import com.distancelin.zhihudaily.homepage.view.IHomepageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by distancelin on 2017/5/14.
 */

public class FragmentHome extends Fragment implements IHomepageView, HomepageRecyclerViewAdapter.onItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private HomepagePresenter mPresenter;
    private HomepageRecyclerViewAdapter mAdapter;
    private String mCurrentDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenthome, container, false);
        ButterKnife.bind(this, view);
        AppCompatActivity parent = (AppCompatActivity) getActivity();
        parent.getSupportActionBar().setTitle("首页");
        mAdapter=new HomepageRecyclerViewAdapter(new ArrayList<NewsBean.StoriesBean>(),new ArrayList<NewsBean.TopStoriesBean>(),getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new HomepagePresenterImpl();
        mPresenter.attach(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnScrollListener(new LoadMoreScrollListener() {
            @Override
            public void onLoadMore() {
                mAdapter.setFooterView();
                mPresenter.getNewsBeforeDay(mCurrentDate);
            }
        });
        mAdapter.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void showLatestAndBeforeNews(List<NewsBean.StoriesBean> result) {
        mAdapter.addTittle(mCurrentDate);
        mAdapter.addNews(result);
    }

    @Override
    public void showBannerNews(List<NewsBean.TopStoriesBean> bannerNews) {
        mAdapter.addBannerNews(bannerNews);
        mAdapter.setHeaderView(null);
    }

    @Override
    public void setToday(String today) {
        mCurrentDate=today;
    }

    @Override
    public void onItemClick(int newsId) {
        Intent intent=new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra("newsId",newsId);
        getActivity().startActivity(intent);
    }


}

