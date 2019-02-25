package com.example.linzhizhou.petchase_12_3.pet.ui.activity;

import android.os.Bundle;
import android.view.View;


import com.example.linzhizhou.petchase_12_3.R;

import butterknife.OnClick;

public class MissActivity extends BaseActivity
{


    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_miss;
    }

    @Override
    protected void setStatusBarColor()
    {

    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        super.initView(savedInstanceState);

    }

    @OnClick({R.id.top_back, R.id.miss_btn})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.top_back:
                finish();
                break;
            case R.id.miss_btn:
                finish();
                break;
            default:
                break;
        }
    }
}
