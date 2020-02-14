package com.example.linzhizhou.petchase_12_3.chargeIoTcard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linzhizhou.petchase_12_3.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PayActivity extends AppCompatActivity
{
    @Bind(R.id.layout_finish)
    ImageView mLayoutFinish;
    @Bind(R.id.layout_title)
    TextView mLayoutTitle;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.img_alipay_check)
    ImageView mImgAlipay;
    @Bind(R.id.img_weixin_check)
    ImageView mImgWeixin;

    private ChargeAdapter mAdapter;
    private int mPayType = 0;


    private static final int PAY_ALI = 0;
    private static final int PAY_WX = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);

        mLayoutTitle.setText("物联网卡充值");
        setPayType(0);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new ChargeAdapter(this, new ChargeAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClickListener(int position, String data)
            {

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(getData());
    }


    //获取充值实体类
    private List<String> getData()
    {
        List<String> list = new ArrayList<>();
        list.add("2元，2M");
        list.add("5元，5M");
        list.add("10元，10M");
        list.add("15元，20M");
        return list;
    }


    private void setPayType(int position)
    {
        mImgAlipay.setImageResource(R.drawable.shape_charge_type_unselected);
        mImgWeixin.setImageResource(R.drawable.shape_charge_type_unselected);
        switch (position)
        {
            case 0:
                mImgAlipay.setImageResource(R.mipmap.charge_selected);
                mPayType = 0;
                break;
            case 1:
                mImgWeixin.setImageResource(R.mipmap.charge_selected);
                mPayType = 1;
                break;
        }
    }


    @OnClick({R.id.tv_alipay, R.id.tv_weixin, R.id.tv_charge})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_alipay://支付宝
                setPayType(PAY_ALI);
                break;
            case R.id.tv_weixin://微信:
                setPayType(PAY_WX);
                break;
            case R.id.tv_charge://充值:
                Toast.makeText(this, "充值成功~", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }


}

