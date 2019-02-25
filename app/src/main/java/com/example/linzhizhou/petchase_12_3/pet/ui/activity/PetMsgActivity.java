package com.example.linzhizhou.petchase_12_3.pet.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.linzhizhou.petchase_12_3.MainActivity;
import com.example.linzhizhou.petchase_12_3.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 设置宠物信息
 */
public class PetMsgActivity extends BaseActivity
{

    @Bind(R.id.top_text)
    TextView mTopText;


    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_pet_msg;
    }

    @Override
    protected void setStatusBarColor()
    {

    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        super.initView(savedInstanceState);
        mTopText.setText("添加宠物");
    }

    @OnClick({R.id.top_back, R.id.msg_long, R.id.msg_save})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.top_back:
                finish();
                break;
            case R.id.msg_long:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.msg_save:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
}
