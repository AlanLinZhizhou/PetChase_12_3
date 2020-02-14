package com.example.linzhizhou.petchase_12_3.chargeIoTcard;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linzhizhou.petchase_12_3.R;
import com.example.linzhizhou.petchase_12_3.navigate.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


public class ChargeAdapter extends RecyclerView.Adapter<BaseViewHolder>
{
    private Context mContext;
    private List<String> mList;
    private int lastPosition;
    private OnItemClickListener mListener;

    public ChargeAdapter(Context context, OnItemClickListener listener)
    {
        mContext = context.getApplicationContext();
        mList = new ArrayList<>();
        this.mListener = listener;
    }


    public void setData(List<String> list)
    {
        if (list == null)
        {
            return;
        }
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.item_charge, parent, false);
        return BaseViewHolder.getViewHolder(layout, mContext);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position)
    {
        final String data = mList.get(position);
        holder.setText(data, R.id.item_title)
                .itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (lastPosition == position) return;
                lastPosition = position;
                if (mListener != null)
                {
                    mListener.onItemClickListener(position, data);
                }
                notifyDataSetChanged();
            }
        });
        if (lastPosition == position)
        {
            holder.setTextColor(R.id.item_title, Color.WHITE)
                    .setBackgroundResource(R.drawable.shape_charge_aomunt_selected, R.id.item_title);
        } else
        {
            holder.setTextColor(R.id.item_title, mContext.getResources().getColor(R.color.pet))
                    .setBackgroundResource(R.drawable.shape_charge_aomunt_unselected, R.id.item_title);
        }
    }

    @Override
    public int getItemCount()
    {
        return mList == null ? 0 : mList.size();
    }


    public interface OnItemClickListener
    {
        void onItemClickListener(int position, String data);
    }
}
