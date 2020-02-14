package com.example.linzhizhou.petchase_12_3.bind;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linzhizhou.petchase_12_3.R;
import com.example.linzhizhou.petchase_12_3.navigate.BaseAdapter;
import com.example.linzhizhou.petchase_12_3.navigate.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BindActivity extends AppCompatActivity
{
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.layout_title)
    TextView mLayoutTitle;

    private BaseAdapter mAdapter;
    private ProgressDialog prgDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        ButterKnife.bind(this);
        prgDialog= new ProgressDialog(this);
        prgDialog.setCancelable(false);
        mLayoutTitle.setText("绑定设备");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        mAdapter.setData(BindBean.getList(this));

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
                SystemClock.sleep(2300);
                //prgDialog.hide();
                Toast.makeText(this, "绑定成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
