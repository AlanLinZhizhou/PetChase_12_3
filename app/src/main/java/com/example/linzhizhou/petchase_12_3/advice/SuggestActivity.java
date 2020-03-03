package com.example.linzhizhou.petchase_12_3.advice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linzhizhou.petchase_12_3.R;
import com.example.linzhizhou.petchase_12_3.utils.HttpUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;


public class SuggestActivity extends AppCompatActivity
{
    @Bind(R.id.layout_title)
    TextView mLayoutTitle;
    @Bind(R.id.advice_descript)
    EditText mEdDescript;
    private RequestParams params=new RequestParams();
    private int a_status=0;//建议接收状态
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        ButterKnife.bind(this);
        mLayoutTitle.setText("投诉建议");
    }

    @OnClick({R.id.layout_finish, R.id.tv_action})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.layout_finish:
                finish();
                break;
            case R.id.tv_action:
                HttpUtils client=new HttpUtils();
                String url="advice";
                params.put("aid","13338612187");
                params.put("acontent",mEdDescript.getText().toString().trim());
                client.post(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        String temp=new String(bytes);
                            a_status=1;
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }
                });

                Toast.makeText(this, "您的建议已收到，我们将努力改正~", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
