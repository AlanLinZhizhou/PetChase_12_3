package com.example.linzhizhou.petchase_12_3.pet.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;


import com.example.linzhizhou.petchase_12_3.R;

import butterknife.Bind;
import butterknife.OnClick;

public class AgreeDetialActivity extends BaseActivity
{

    @Bind(R.id.top_text)
    TextView mTopText;
    @Bind(R.id.agree_web)
    WebView mAgreeWeb;

    private String url_agree = "http://www.yc.cn/app/info/agreement.html?petVer=390&petPlat=1&packetId=2000";


    @OnClick(R.id.top_back)
    public void onViewClicked()
    {
        finish();
    }

    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_agree_detial;
    }

    @Override
    protected void setStatusBarColor()
    {

    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        super.initView(savedInstanceState);
        mTopText.setText("用户服务协议");
        mAgreeWeb.loadUrl(url_agree);
    }
}
