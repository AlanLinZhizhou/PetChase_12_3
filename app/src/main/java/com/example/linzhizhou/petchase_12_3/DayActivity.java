package com.example.linzhizhou.petchase_12_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DayActivity extends AppCompatActivity
{

    @Bind(R.id.layout_title)
    TextView mLayoutTitle;
    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.tv_descript)
    TextView mTvDescript;


    private DayBean mData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        mLayoutTitle.setText("疫苗规划");
        mData = new DayBean("2019-5-27");//从数据库读取日期，如果是默认日期1970-1-1就待表没有疫苗规划
        setTitle(mData);
    }


    /*修改时间*/
    private void setTitle(DayBean data)
    {
        mTvDescript.setText(TimeUtils.getTimeDifference(data.getTime()));
        StringBuilder builder = new StringBuilder("目标日：");
        builder.append(data.getTime())
                .append(" ")
                .append(TimeUtils.getDayofWeek(data.getTime()));
        mTvTime.setText(builder);
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
                Intent it = new Intent(this, EditDayActivity.class);
                it.putExtra(EditDayActivity.class.getSimpleName(), mData);
                startActivity(it);
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent(DayBean data)
    {
        setTitle(data);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
