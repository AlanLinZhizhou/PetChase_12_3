package com.example.linzhizhou.petchase_12_3.bind;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linzhizhou.petchase_12_3.R;
import com.example.linzhizhou.petchase_12_3.navigate.BaseViewHolder;
import com.example.linzhizhou.petchase_12_3.utils.HttpUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tckj.zyfsdk.ZYFSdk;
import com.tckj.zyfsdk.entity.BaseEntity;
import com.tckj.zyfsdk.http.zhttp.listener.ZYFBindDeviceListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;


public class BindActivity extends AppCompatActivity
{
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.layout_title)
    TextView mLayoutTitle;

    private BaseAdapter mAdapter;
    private ProgressDialog prgDialog;
    private RequestParams params = new RequestParams();
    private int status=0;//绑定状态
    Context context;
    Button bindbtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        ButterKnife.bind(this);
        prgDialog= new ProgressDialog(this);//用于显示绑定进度条
        prgDialog.setCancelable(false);
        mLayoutTitle.setText("绑定设备");
        context=getApplicationContext();
        //RecycleView-->类似listview
        //控制横向或者纵向滑动列表，设置为垂直布局，这也是默认的
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置完view后设置adapter
        //在使用RecyclerView时候，必须指定一个适配器Adapter和一个布局管理器LayoutManager。
        //适配器继承RecyclerView.Adapter类，具体实现类似ListView的适配器，取决于数据信息以及展示的UI。
        //布局管理器用于确定RecyclerView中Item的展示方式以及决定何时复用已经不可见的Item
        //避免重复创建以及执行高成本的findViewById()方法。

        mAdapter = new BaseAdapter<BindBean>(this, R.layout.item_bind)

        {
            @Override

            public void convert(BaseViewHolder holder, int position, BindBean data)

            {
                holder.setText(data.getTitle(), R.id.item_title)
                        .setText(data.getDescript(), R.id.item_descript)
                        .setTextColor(R.id.item_descript,data.isEditor() ? Color.BLACK : Color.parseColor("#aaaaaa"));
                EditText descript = holder.getView(R.id.item_descript);
                descript.setEnabled(data.isEditor());
            }

        };
        mAdapter.bindRecyclerView(mRecyclerView);

        //对这里进行更改,更新绑定状态，sn号

        HttpUtils client=new HttpUtils();
        params.add("uid","13338612187");//完善后sharedprefrence读
        client.get("bind?uid=13338612187", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
               String sn=new String(bytes);
                List<BindBean> list = new ArrayList<>();
                if(sn=="未绑定"){
                    list.add(new BindBean("设备绑定状态", "未绑定", false));
                    list.add(new BindBean("设备Sn号", "", true));
                }else {
                    list.add(new BindBean("设备绑定状态", "绑定成功", false));
                    list.add(new BindBean("设备Sn号", "001221A003E6", false));
                }
                list.add(new BindBean("AppID", "001", false));
                list.add(new BindBean("Product Key", "47d71eceec62", false));
                list.add(new BindBean("设备烧录情况", "已烧录成功", false));
                list.add(new BindBean("合作伙伴ID", "ec4f64cab2c94a891d08acf86", false));
                list.add(new BindBean("设备芯片", "MTK2503芯片组", false));
                list.add(new BindBean("技术支持平台", "智云服平台", false));
                mAdapter.setData(list);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                String sn=new String(bytes);
                List<BindBean> list = new ArrayList<>();
                if(sn=="未绑定"){
                    list.add(new BindBean("设备绑定状态", "未绑定", false));
                    list.add(new BindBean("设备Sn号", "", true));
                }else {
                    list.add(new BindBean("设备绑定状态", "绑定成功", false));
                    list.add(new BindBean("设备Sn号", sn, false));
                }
                list.add(new BindBean("AppID", "001", false));
                list.add(new BindBean("Product Key", "47d71eceec62", false));
                list.add(new BindBean("设备烧录情况", "已烧录成功", false));
                list.add(new BindBean("合作伙伴ID", "ec4f64cab2c94a891d08acf86", false));
                list.add(new BindBean("设备芯片", "MTK2503芯片组", false));
                list.add(new BindBean("技术支持平台", "智云服平台", false));
                mAdapter.setData(list);
                //设置单击绑定按钮Button id action
                bindbtn=findViewById(R.id.action);
                if(sn=="未绑定"){
                    bindbtn.setEnabled(true);
                    bindbtn.setBackgroundColor(android.graphics.Color.parseColor("#03A9F4"));
                }
            }
        });
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
                prgDialog.setMessage("验证中，请稍后");
                prgDialog.show();
                //cid即登录成功后返回的cid
                ZYFSdk.getInstance().bindDevice(context,"001221A003E6","13338612187",new ZYFBindDeviceListener(){
                    @Override
                    public void onComplete(BaseEntity result) {
                        status=1;
                    }

                    @Override
                    public void onError(Exception e) {
                        status=0;
                    }

                });
                SystemClock.sleep(2300);
                //prgDialog.hide();
                if(status==1){
                    Toast.makeText(this, "绑定成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(this, "绑定失败，请校验sn号与网络状态", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
