package com.example.linzhizhou.petchase_12_3.release_info.findpet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.linzhizhou.petchase_12_3.R;
import com.example.linzhizhou.petchase_12_3.bind.BaseAdapter;
import com.example.linzhizhou.petchase_12_3.bind.BindBean;
import com.example.linzhizhou.petchase_12_3.navigate.BaseViewHolder;
import com.example.linzhizhou.petchase_12_3.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;


public class FindActivity extends AppCompatActivity {
    @Bind(R.id.layout_title)
    TextView mLayoutTitle;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;


    private BaseAdapter<FindBean> mAdapter;
    private List<FindBean> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        ButterKnife.bind(this);
        mLayoutTitle.setText("寻宠启示");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseAdapter<FindBean>(this, R.layout.item_find) {
            @Override
            public void convert(BaseViewHolder holder, int position, FindBean data) {
                holder.setImageResource(data.getUrls(), R.id.item_image)
                        .setText(data.getTitle(), R.id.item_title)
                        .setText(data.getDescript(), R.id.item_descript)
                        .setText(data.getTime(), R.id.item_time);
            }
        };
        mAdapter.bindRecyclerView(mRecyclerView);

        HttpUtils client = new HttpUtils();
//        params.add("uid","13338612187");//完善后sharedprefrence读
        client.get("findpet", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String sn = new String(bytes);
//                FindPetBean findPetBean = new FindPetBean();

                List<FindBean> list = new ArrayList<>();
                //从服务器取数据
//                byte[] b=bytes;
                int a = 0;
                Gson gson=new Gson();
//                list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "上海嘉定区天祝路555弄丢失橘猫一只", "毛先生", "10秒钟以前"));
                List<FindPetBean> flist = gson.fromJson(sn, new TypeToken<List<FindPetBean>>(){}.getType());
                for (int j = 0; j < 3; j++) {//一次从数据库中取20条记录
////                    list.add(new FindBean(findPetBean));
                    FindPetBean findPetBean=flist.get(j);
//                    FindPetBean findPetBean=gson.fromJson(sn,FindPetBean.class);
                    String temp=findPetBean.getIcon();
                    int aa=0;
                    list.add(new FindBean(findPetBean.getIcon(),findPetBean.getRe_content(),findPetBean.getReleaser(),findPetBean.getRe_contact()));
                }
                mAdapter.setData(list);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                list.add(new FindBean("http://p1.ycw.com/share/201812/21/eeae50b7386108d54b942ac805d8cc52_s600", "请确保网络连接正常哟", "管理员", "友情提示"));
                mAdapter.setData(list);
            }
//        mAdapter.setData(FindBean.getList());
        });
    }

    @OnClick(R.id.layout_finish)
    public void onViewClicked() {
        finish();
    }
}

