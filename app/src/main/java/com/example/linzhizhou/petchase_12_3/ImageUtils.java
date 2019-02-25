package com.example.linzhizhou.petchase_12_3;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.support.annotation.IntRange;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

//图片加载
public class ImageUtils
{
    private Context mContext;


    public ImageUtils(Context context)
    {
        this.mContext = context;
    }


    //加载用户头像
    public void setUserImageResource(String imageUrls, ImageView view)
    {
        if (view == null || TextUtils.isEmpty(imageUrls)) return;
        RequestOptions options = new RequestOptions()
                .circleCrop();
        Glide.with(mContext)
                .asBitmap()
                .load(imageUrls)
                .apply(options)
                .into(view);
    }

    //加载用户头像
    public void setUserImageResource(File file, ImageView view)
    {
        if (view == null || (!file.exists())) return;
        RequestOptions options = new RequestOptions()
                .circleCrop();
        Glide.with(mContext)
                .load(file)
                .apply(options)
                .into(view);
    }


    //加载网络圆形图片
    public void setCircleImageResource(String imageUrls, @DimenRes int resize, ImageView view)
    {
        if (view == null || TextUtils.isEmpty(imageUrls)) return;
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .override(mContext.getResources().getDimensionPixelSize(resize))
                .circleCrop();
        Glide.with(mContext)
                .asBitmap()
                .load(imageUrls)
                .apply(options)
                .into(view);
    }

    //加载网络图片
    public void setImageResource(String imageUrls, ImageView view)
    {
        if (view == null || TextUtils.isEmpty(imageUrls)) return;
        Glide.with(mContext)
                .load(imageUrls)
                .into(view);
    }

    //加载本地File文件
    public void setImageFileResource(File file, ImageView view)
    {
        if (file == null || !file.exists()) return;
        Glide.with(mContext)
                .load(file)
                .into(view);
    }


    //加载圆角图片
    public void setRoundImageResource(String imageUrls, @IntRange(from = 0) int round, ImageView view)
    {
        if (view == null || TextUtils.isEmpty(imageUrls)) return;
        RequestOptions options = new RequestOptions()
                .transforms(new CenterCrop(), new RoundedCorners(round));
        Glide.with(mContext)
                .load(imageUrls)
                .apply(options)
                .into(view);
    }


}
