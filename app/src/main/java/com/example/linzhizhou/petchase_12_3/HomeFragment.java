package com.example.linzhizhou.petchase_12_3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.support.v7.widget.SearchView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.Arrays;



public class HomeFragment extends Fragment implements View.OnClickListener
{

    private Banner mBanner;


    @Override
    public void setMenuVisibility(boolean menuVisible)
    {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
        {
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        ImageButton b1 = view.findViewById(R.id.bind_device);
        b1.setOnClickListener(this);
        ImageButton b2 = view.findViewById(R.id.button2);
        b2.setOnClickListener(this);
        ImageButton b3 = view.findViewById(R.id.button3);
        b3.setOnClickListener(this);
        ImageButton b4 = view.findViewById(R.id.button4);
        b4.setOnClickListener(this);
        ImageButton b5 = view.findViewById(R.id.button5);
        b5.setOnClickListener(this);
        ImageButton b6 = view.findViewById(R.id.button6);
        b6.setOnClickListener(this);
        ImageButton b7 = view.findViewById(R.id.button7);
        b7.setOnClickListener(this);
        ImageButton b8 = view.findViewById(R.id.button8);

        ImageButton b9 = view.findViewById(R.id.button81);
        b9.setOnClickListener(this);
        ImageButton b10 = view.findViewById(R.id.button41);
        b10.setOnClickListener(this);
        SearchView s1=view.findViewById(R.id.s1);
        //s1.setOnCloseListener(this);
        mBanner = view.findViewById(R.id.banner);
        mBanner.setImageLoader(new com.youth.banner.loader.ImageLoader()
        {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView)
            {
                String data = (String) path;
                Picasso.with(getActivity()).load(data).fit().centerCrop().into(imageView);
            }
        });
        mBanner.setImages(Arrays.asList(new String[]{
                "http://img.ivsky.com/img/tupian/pre/201808/29/cat-008.jpg",
                "http://img.ivsky.com/img/tupian/pre/201808/01/golden_retriever-008.jpg",
                "http://img.ivsky.com/img/tupian/pre/201803/18/xiaonaigou-004.jpg",
                "http://img.ivsky.com/img/tupian/pre/201101/21/jiating_chongwumao-032.jpg",
                "http://img.ivsky.com/img/tupian/pre/201101/21/jiating_chongwumao-021.jpg"
        }));
        mBanner.start();

        setParams(width, b1);
        setParams(width, b2);
        setParams(width, b3);
        setParams(width, b4);
        setParams(width, b5);
        setParams(width, b6);
        setParams(width, b7);
        setParams(width, b8);
        setParams(width, b9);
        setParams(width, b10);
        setParams2(width,s1);
        return view;
    }

    private void setParams(int width, ImageButton b)
    {
        GridLayout.LayoutParams params = (GridLayout.LayoutParams) b.getLayoutParams();
        params.width = width / 5 - 22;
        b.setLayoutParams(params);
    }
    private void setParams2(int width, SearchView b)
    {
        //GridLayout.LayoutParams params = (GridLayout.LayoutParams) b.getLayoutParams();
        LinearLayout.LayoutParams params=(LinearLayout.LayoutParams)b.getLayoutParams();
        params.width=width-40;
        b.setLayoutParams(params);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.bind_device:
                openActivity(BindActivity.class);
                break;
            case R.id.button2:
                openActivity(InformationActivity.class);
                break;
            case R.id.button4:
                openActivity(InfoActivity.class);
                break;
            case R.id.button5:
                openActivity(LuckydrawActivity.class);
                break;
            case R.id.button6:
                openActivity(SuggestActivity.class);
                break;
            case R.id.button3:
                openActivity(FindActivity.class);
                break;
            case R.id.button7:
                openActivity(DayActivity.class);
                break;
            case R.id.button81:
                openActivity(PayActivity.class);

                break;
            default:
                break;
        }


    }


    /*启动Activity*/
    public void openActivity(Class<? extends AppCompatActivity> cls)
    {
        Intent it = new Intent(getActivity(), cls);
        startActivity(it);
    }


}
