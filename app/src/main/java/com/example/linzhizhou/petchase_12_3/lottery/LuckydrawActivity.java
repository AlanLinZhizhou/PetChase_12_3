package com.example.linzhizhou.petchase_12_3.lottery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.linzhizhou.petchase_12_3.R;
import com.example.linzhizhou.petchase_12_3.reglogin.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class LuckydrawActivity extends AppCompatActivity
{

    @Bind(R.id.layout_title)
    TextView mLayoutTitle;
    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_phone)
    EditText mEtPhone;
    @Bind(R.id.tv_city)
    TextView mTvCity;
    @Bind(R.id.ed_descript)
    EditText mEdDescript;


    private List<ProvinceBean> options1Items = new ArrayList<>();
    private List<List<String>> options2Items = new ArrayList<>();
    private OptionsPickerView mLocationPickView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luckydraw);
        ButterKnife.bind(this);

        mLayoutTitle.setText("添加收货地址");
        setLocationPickData();
    }


    /*获取省市区三级联动数据源*/
    private void setLocationPickData()
    {
        Flowable.create(new FlowableOnSubscribe<List<ProvinceBean>>()
        {
            @Override
            public void subscribe(FlowableEmitter<List<ProvinceBean>> e) throws Exception
            {
                Gson gson = new Gson();
                String json = StringUtils.getJson(LuckydrawActivity.this, "City.json");
                options1Items = gson.fromJson(json, new TypeToken<List<ProvinceBean>>()
                {
                }.getType());
                e.onNext(options1Items);
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<List<ProvinceBean>, List<List<String>>>()
                {
                    @Override
                    public List<List<String>> apply(List<ProvinceBean> list) throws Exception
                    {
                        for (ProvinceBean data : list)
                        {
                            List<String> cities = new ArrayList<>();
                            for (ProvinceBean.CityBean city : data.getCity())
                            {
                                cities.add(city.getName());
                            }
                            options2Items.add(cities);
                        }
                        return options2Items;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<List<String>>>()
                {
                    @Override
                    public void accept(List<List<String>> lists) throws Exception
                    {
                        mLocationPickView = new OptionsPickerBuilder(LuckydrawActivity.this, new OnOptionsSelectListener()
                        {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v)
                            {
                                StringBuilder builder = new StringBuilder();
                                builder.append(options1Items.get(options1).getPickerViewText())
                                        .append(" ")
                                        .append(options2Items.get(options1).get(options2));
                                mTvCity.setText(builder);
                            }
                        }).setSubCalSize(20)
                                .setCancelText("取消")
                                .setSubmitText("确定")
                                .setContentTextSize(20).build();
                        mLocationPickView.setPicker(options1Items, options2Items);
                    }
                });
    }


    @OnClick({R.id.layout_finish, R.id.tv_action, R.id.tv_city})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.layout_finish:
                finish();
                break;
            case R.id.tv_action:
                Toast.makeText(this, "保存成功哦~", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.tv_city:
                mLocationPickView.show();
                break;
        }
    }
}
