package com.example.linzhizhou.petchase_12_3.view.child;

import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linzhizhou.petchase_12_3.R;
import com.example.linzhizhou.petchase_12_3.pet.model.DepositModel;
import com.example.linzhizhou.petchase_12_3.pet.ui.adapter.DepositAdapter;
import com.example.linzhizhou.petchase_12_3.pet.utils.BaseHandler;
import com.example.linzhizhou.petchase_12_3.view.fragment.BaseFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DepositFragment extends BaseFragment
{
    @Bind(R.id.zhazi_recycler)
    RecyclerView mZhaziRecycler;
    @Bind(R.id.zazhi_viewPager)
    ViewPager mZazhiViewPager;
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
    private List<View> mViewList;
    private boolean isBanner = false;
    private BaseHandler<DepositFragment> mHandler = new BaseHandler<DepositFragment>(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            mZazhiViewPager.setCurrentItem(msg.what % images.length);
        }
    };

    //*******************************************************//


    @Override
    public int getResource()
    {
        return R.layout.fragment_deposit;
    }

    @Override
    public void init(View view)
    {
        mList = new ArrayList<>();

        mList.add(new DepositModel(R.drawable.zazhi1, "第1期", "May.2017"));
        mList.add(new DepositModel(R.drawable.zazhi2, "第2期", "Apr.2017"));
        mList.add(new DepositModel(R.drawable.zazhi3, "第3期", "Mar.2017"));
        mList.add(new DepositModel(R.drawable.zazhi4, "第4期", "Feb.2017"));
        mList.add(new DepositModel(R.drawable.zazhi5, "第5期", "Jan.2017"));
        mList.add(new DepositModel(R.drawable.zazhi6, "第6期", "Dec.2016"));

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mZhaziRecycler.setNestedScrollingEnabled(false);
        mZhaziRecycler.setLayoutManager(manager);
        mZhaziRecycler.setAdapter(mAdapter = new DepositAdapter(mList, getActivity()));

    }


    @Override
    public void loadingDatas()
    {
        mViewList = new ArrayList<>();
        for (int i = 0; i < images.length; i++)
        {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.header_msg_deposit, null);
            ImageView simple = (ImageView) view.findViewById(R.id.item_images);
            TextView title = (TextView) view.findViewById(R.id.item_titles);
            Picasso.with(getActivity()).load(images[i]).resize(414,233).centerCrop().into(simple);
            title.setText(titles[i]);
            mViewList.add(view);
        }

        mZazhiViewPager.setAdapter(new DepositBanner());
        mZazhiViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                resetIndic();
                switch (position)
                {
                    case 0:
                        mInidc1.setBackgroundResource(R.drawable.shape_msg_tuijian_circle_ff);
                        break;
                    case 1:
                        mInidc2.setBackgroundResource(R.drawable.shape_msg_tuijian_circle_ff);
                        break;
                    case 2:
                        mInidc3.setBackgroundResource(R.drawable.shape_msg_tuijian_circle_ff);
                        break;
                    case 3:
                        mInidc4.setBackgroundResource(R.drawable.shape_msg_tuijian_circle_ff);
                        break;
                    case 4:
                        mInidc5.setBackgroundResource(R.drawable.shape_msg_tuijian_circle_ff);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
        isBanner = true;
        startBanner();
    }

    private void resetIndic()
    {
        mInidc1.setBackgroundResource(R.drawable.shape_msg_tuijian_circle_f4);
        mInidc2.setBackgroundResource(R.drawable.shape_msg_tuijian_circle_f4);
        mInidc3.setBackgroundResource(R.drawable.shape_msg_tuijian_circle_f4);
        mInidc4.setBackgroundResource(R.drawable.shape_msg_tuijian_circle_f4);
        mInidc5.setBackgroundResource(R.drawable.shape_msg_tuijian_circle_f4);
    }

    private void startBanner()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                super.run();
                int position = 0;
                while (isBanner)
                {
                    mHandler.sendEmptyMessage(position++);
                    try
                    {
                        Thread.sleep(5000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    @Override
    public void onResume()
    {
        super.onResume();
        isBanner = true;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        isBanner = false;
    }

    @Override
    public void startdestroy()
    {
    }


    private class DepositBanner extends PagerAdapter
    {

        @Override
        public int getCount()
        {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            container.addView(mViewList.get(position));

            return mViewList.get(position);
        }
    }
}
