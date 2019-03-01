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

public class InformationActivity extends AppCompatActivity {

    @Bind(R.id.layout_title)
    TextView mLayoutTitle;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;


    private BaseAdapter<InformationBean> mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
        mLayoutTitle.setText("小科普学堂");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseAdapter<InformationBean>(this, R.layout.item_information)
        {

            @Override
            public void convert(BaseViewHolder holder, int position, InformationBean informationBean) {
                holder.setText(informationBean.getTitle(),R.id.item_title)
                        .setText(informationBean.getAuthor(),R.id.item_author)
                        .setText(informationBean.getStars(),R.id.item_stars)
                        .setImageResource(informationBean.getResourceId(),R.id.item_image);

            }
        };
        mAdapter.bindRecyclerView(mRecyclerView);
        mAdapter.setData(InformationBean.getList());
    }

    @OnClick(R.id.layout_finish)
    public void onViewClicked()
    {
        finish();
    }

}
