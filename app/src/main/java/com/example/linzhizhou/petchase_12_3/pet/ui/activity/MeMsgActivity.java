package com.example.linzhizhou.petchase_12_3.pet.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linzhizhou.petchase_12_3.MainActivity;
import com.example.linzhizhou.petchase_12_3.R;

import butterknife.Bind;
import butterknife.OnClick;

public class MeMsgActivity extends AppCompatActivity {

    @Bind(R.id.top_text)
    TextView mTopText;

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_me2);
        TextView save=findViewById(R.id.msg_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main=new Intent(MeMsgActivity.this,MainActivity.class);
                startActivity(main);
            }
        });

    }


}
