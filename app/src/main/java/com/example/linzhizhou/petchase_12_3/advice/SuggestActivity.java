package com.example.linzhizhou.petchase_12_3.advice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linzhizhou.petchase_12_3.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SuggestActivity extends AppCompatActivity
{
    @Bind(R.id.layout_title)
    TextView mLayoutTitle;
    @Bind(R.id.ed_descript)
    EditText mEdDescript;

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
                Toast.makeText(this, "您的建议已收到，我们将努力改正~", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
