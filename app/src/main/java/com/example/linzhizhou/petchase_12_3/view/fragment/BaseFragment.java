package com.example.linzhizhou.petchase_12_3.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;

public abstract class BaseFragment extends Fragment {
    public abstract int getResource();//初始化资源文件

    public abstract void init(View view);//初始化组件

    public abstract void loadingDatas();//加载数据，初始化数据，初始化对象

    public abstract void startdestroy();//销毁数据，释放内存

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = LayoutInflater.from(getActivity()).inflate(getResource(), container, false);
        init(view);
        loadingDatas();
        return view;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Fresco.getImagePipeline().pause();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Fresco.getImagePipeline().resume();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        startdestroy();
        System.gc();
    }
}
