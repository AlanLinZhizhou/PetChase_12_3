package com.example.linzhizhou.petchase_12_3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EditDayActivity extends AppCompatActivity
{
    @Bind(R.id.layout_title)
    TextView mLayoutTitle;
    @Bind(R.id.et_name)
    TextView mEtName;
    @Bind(R.id.et_phone)
    TextView mEtPhone;

    private DayBean mData;
    private TimePickerView mPickerView;


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_day);
        ButterKnife.bind(this);

        mData = getIntent().getParcelableExtra(EditDayActivity.class.getSimpleName());
        mLayoutTitle.setText("编辑");
        mEtName.setText(mData != null ? mData.getTitle() : "");
        mEtPhone.setText(mData != null ? mData.getTime() : "");


        mPickerView = new TimePickerBuilder(this, new OnTimeSelectListener()
        {
            @Override
            public void onTimeSelect(Date date, View v)
            {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String time = formatter.format(date);
                mData.setTime(time);
                mEtPhone.setText(time);
            }
        })
                .setCancelText("取消")
                .setSubmitText("确定")
                .setRangDate(Calendar.getInstance(), null)
                .setDate(Calendar.getInstance())
                .build();
    }

    @OnClick({R.id.layout_finish, R.id.tv_action, R.id.et_phone})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.layout_finish:
                finish();
                break;
            case R.id.tv_action:
                Toast.makeText(this, "保存成功~", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(mData);
                finish();
                break;
            case R.id.et_phone:
                mPickerView.show();
                break;
        }
    }
}
