package com.example.linzhizhou.petchase_12_3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FindActivity extends AppCompatActivity
{
    @Bind(R.id.layout_title)
    TextView mLayoutTitle;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;


    private BaseAdapter<FindBean> mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        ButterKnife.bind(this);
        mLayoutTitle.setText("领养");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseAdapter<FindBean>(this, R.layout.item_find)
        {
            @Override
            public void convert(BaseViewHolder holder, int position, FindBean data)
            {
                holder.setImageResource(data.getUrls(), R.id.item_image)
                        .setText(data.getTitle(), R.id.item_title)
                        .setText(data.getDescript(), R.id.item_descript)
                        .setText(data.getTime(), R.id.item_time);
            }
        };
        mAdapter.bindRecyclerView(mRecyclerView);
        mAdapter.setData(FindBean.getList());
    }

    @OnClick(R.id.layout_finish)
    public void onViewClicked()
    {
        finish();
    }
}
