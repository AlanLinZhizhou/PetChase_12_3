package com.example.linzhizhou.petchase_12_3.view.child;

import android.app.Fragment;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linzhizhou.petchase_12_3.R;
import com.example.linzhizhou.petchase_12_3.pet.utils.BaseHandler;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class DepositFragment extends Fragment {
    @Bind(R.id.chongzhi_recycler)
    RecyclerView mChongzhiRecycler;
    @Bind(R.id.chongzhi_viewPager)
    ViewPager mChongzhiViewPager;
    @Bind(R.id.inidc1)
    ImageView mInidc1;
    @Bind(R.id.inidc2)
    ImageView mInidc2;
    @Bind(R.id.inidc3)
    ImageView mInidc3;
    @Bind(R.id.inidc4)
    ImageView mInidc4;
    @Bind(R.id.inidc5)
    ImageView mInidc5;
    @Bind(R.id.titles)
    TextView mTitles;

    private List<DepositModel> mList;
    private DepositAdapter mAdapter;


    //*************************轮播滚动******************************//
    private int[] images =
            {
                    R.drawable.banner_zazhi1,
                    R.drawable.banner_zazhi2,
                    R.drawable.banner_zazhi3,
                    R.drawable.banner_zazhi4,
            };
    private String[] titles =
            {
                    "封面故事--《家·猫·果实》",
                    "专题巨献--《帝苑猫说》",
                    "封面故事--《戏痞士与惊魂鸟》",
                    "活着--《生存游戏》",
            };

    private BaseHandler<DepositFragment> mHandler = new BaseHandler<DepositFragment>(this) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mChongzhiViewPager.setCurrentItem(msg.what % images.length);
        }
    };

    //*******************************************************//


    public int getResource() {
        return R.layout.fragment_deposit;
    }

    public void init(View view) {
        mList = new ArrayList<>();

        mList.add(new DepositModel(R.drawable.family, "1元", "5M"));
        mList.add(new DepositModel(R.drawable.family, "2元", "10M"));
        mList.add(new DepositModel(R.drawable.family, "3元", "15M"));
        mList.add(new DepositModel(R.drawable.family, "4元", "20M"));
        mList.add(new DepositModel(R.drawable.family, "5元", "25M"));
        mList.add(new DepositModel(R.drawable.family, "6元", "30M"));

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mChongzhiRecycler.setNestedScrollingEnabled(false);
        mChongzhiRecycler.setLayoutManager(manager);
        mChongzhiRecycler.setAdapter(mAdapter = new DepositAdapter(mList, getActivity()));

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }


}

