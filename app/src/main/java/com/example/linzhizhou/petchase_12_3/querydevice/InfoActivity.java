package com.example.linzhizhou.petchase_12_3.querydevice;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.linzhizhou.petchase_12_3.R;
import com.example.linzhizhou.petchase_12_3.navigate.BaseAdapter;
import com.example.linzhizhou.petchase_12_3.navigate.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.layout_title)
    TextView mLayoutTitle;

    private BaseAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

        mLayoutTitle.setText("设备信息");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseAdapter<InfoBean>(this, R.layout.item_info)

        {
            @Override
            public void convert(BaseViewHolder holder, int position, InfoBean infoBean) {

                holder.setText(infoBean.getTitle(), R.id.item_title)
                        .setText(infoBean.getDescript(), R.id.item_descript)
                        .setTextColor(R.id.item_descript,infoBean.isEditor() ? Color.BLACK : Color.parseColor("#aaaaaa"));
                EditText descript = holder.getView(R.id.item_descript);
                descript.setEnabled(infoBean.isEditor());
            }


        };
        mAdapter.bindRecyclerView(mRecyclerView);
        mAdapter.setData(InfoBean.getList(this));

    }

    @OnClick({R.id.layout_finish, R.id.action})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.layout_finish:
                finish();
                break;
            case R.id.action:
//                Toast.makeText(this, getString(R.string.toast_bind), Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, "绑定成功~", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
