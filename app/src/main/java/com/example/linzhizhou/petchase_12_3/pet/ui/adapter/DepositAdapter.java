package com.example.linzhizhou.petchase_12_3.pet.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.linzhizhou.petchase_12_3.R;
import com.example.linzhizhou.petchase_12_3.pet.model.DepositModel;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DepositAdapter extends RecyclerView.Adapter<DepositAdapter.ViewHolder> {

    private List<DepositModel> mList;
    private Context mContext;


    public DepositAdapter(List<DepositModel> list, Context context)
    {
        mList = list;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position)
    {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_msg_deposit, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Picasso.with(mContext).load(mList.get(position).getImage()).resize(116,155).centerCrop().into(holder.mItemView);
        holder.mItemName.setText(mList.get(position).getNames());
        holder.mItemTimes.setText(mList.get(position).getTimes());
    }

    @Override
    public int getItemCount()
    {
        return mList == null ? 0 : mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.item_parents)
        AutoLinearLayout mItemParents;
        @Bind(R.id.item_image)
        SimpleDraweeView mItemView;
        @Bind(R.id.item_name)
        TextView mItemName;
        @Bind(R.id.item_times)
        TextView mItemTimes;

        public ViewHolder(View itemView)
        {
            super(itemView);
            AutoUtils.autoSize(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
