package com.example.linzhizhou.petchase_12_3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RechargeActivity extends AppCompatActivity {

    @Bind(R.id.layout_title)
    TextView mLayoutTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);

        mLayoutTitle.setText("物联网卡充值");
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
                Toast.makeText(this, "充值成功~", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

}
