package com.example.linzhizhou.petchase_12_3.reglogin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import com.example.linzhizhou.petchase_12_3.MainActivity;
import com.example.linzhizhou.petchase_12_3.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 设置宠物信息
 */
public class PetMsgActivity extends AppCompatActivity
{

    @Bind(R.id.top_text)
    TextView mTopText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_msg);
        TextView msglong=findViewById(R.id.msg_long);
        msglong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetMsgActivity.this, MainActivity.class));
                finish();
            }
        });
        TextView msgsave=findViewById(R.id.msg_save);
        msgsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetMsgActivity.this, MainActivity.class));
                finish();
            }
        });
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
