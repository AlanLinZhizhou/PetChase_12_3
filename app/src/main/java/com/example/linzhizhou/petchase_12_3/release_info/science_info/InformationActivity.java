package com.example.linzhizhou.petchase_12_3.release_info.science_info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.linzhizhou.petchase_12_3.R;
import com.example.linzhizhou.petchase_12_3.bind.BaseAdapter;
import com.example.linzhizhou.petchase_12_3.navigate.BaseViewHolder;
import com.example.linzhizhou.petchase_12_3.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class InformationActivity extends AppCompatActivity {

    @Bind(R.id.layout_title)
    TextView mLayoutTitle;
    @Bind(R.id.irecyclerView)
    RecyclerView mRecyclerView;


    private BaseAdapter<InformationBean> mAdapter;
    private List<InformationBean> list = new ArrayList<>();
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
//                Log.i("info",informationBean.getAuthor());
                 holder.setText(informationBean.getTitle(),R.id.item_title)
                            .setText(informationBean.getAuthor(),R.id.item_author)
                            .setText(informationBean.getStars(),R.id.item_stars)
                            .setImageResource(informationBean.getUrl(),R.id.item_image);



            }
        };
        mAdapter.bindRecyclerView(mRecyclerView);
        HttpUtils client = new HttpUtils();
        client.get("findkepu", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String sn = new String(bytes);
                List<InformationBean> list = new ArrayList<>();
                Gson gson=new Gson();
                List<KepuBean> flist = gson.fromJson(sn, new TypeToken<List<KepuBean>>(){}.getType());
                for (int j = 0; j < 3; j++) {//一次从数据库中取20条记录
                    KepuBean kp=flist.get(j);
                    list.add(new InformationBean(kp.getK_icon(),kp.getK_content(),
                            kp.getK_releaser(),kp.getK_trust()));
                }
                mAdapter.setData(list);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                list.add(new InformationBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "请确保网络连接正常哟", "管理员", "友情提示"));
                mAdapter.setData(list);
            }
//        mAdapter.setData(FindBean.getList());
        });
        mAdapter.setData(list);
    }

    @OnClick(R.id.layout_finish)
    public void onViewClicked()
    {
        finish();
    }

}
