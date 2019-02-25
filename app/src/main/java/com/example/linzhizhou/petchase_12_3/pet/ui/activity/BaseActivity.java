package com.example.linzhizhou.petchase_12_3.pet.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linzhizhou.petchase_12_3.MainActivity;
import com.example.linzhizhou.petchase_12_3.R;
import com.example.linzhizhou.petchase_12_3.pet.utils.ActivityManager;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public abstract class BaseActivity extends RxAppCompatActivity
{
    /*获取布局资源*/
    protected abstract int getLayoutResource();

    /*设置状态栏颜色*/
    protected abstract void setStatusBarColor();

    protected BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityManager.getManager().addActivity(this);
        setContentView(getLayoutResource());
        mActivity = this;
        ButterKnife.bind(this);
        initView(savedInstanceState);
        initData();
    }

    @Override
    public void setContentView(int layoutResID)
    {
        setStatusBarColor();
        super.setContentView(layoutResID);
    }

    /*初始化View*/
    protected void initView(Bundle savedInstanceState)
    {

    }

    /*网络请求以及初始化数据*/
    protected void initData()
    {
    }

    private static long lastClickTime;//按钮点击记录时间
    private static final long TIME_CLICK = 800L;

    /*避免重复点击*/
    public boolean isFastDoubleClick()
    {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < TIME_CLICK)
        {
            return true;
        } else
        {
            lastClickTime = time;
            return false;
        }
    }





    /*Toast弹窗*/
    protected void alert(String msg)
    {
        if (TextUtils.isEmpty(msg)) return;
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


    /*关闭*/
    protected void doFinish()
    {
        this.finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /*延迟关闭*/
    protected void doFinish(long delay)
    {
        Observable.interval(delay, TimeUnit.MILLISECONDS).take(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Consumer<Long>()
                {
                    @Override
                    public void accept(Long aLong) throws Exception
                    {
                        doFinish();
                    }
                });
    }

    /*启动Activity*/
    protected void openActivity(Class<? extends BaseActivity> cls, Parcelable parcelable)
    {
        Intent it = new Intent(this, cls);
        if (parcelable != null)
        {
            it.putExtra(cls.getSimpleName(), parcelable);
        }
        startActivity(it);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }


    /*启动Activity*/
    protected void openActivityForResult(Class<? extends BaseActivity> cls, Parcelable parcelable, int requestCode)
    {
        Intent it = new Intent(this, cls);
        if (parcelable != null)
        {
            it.putExtra(cls.getSimpleName(), parcelable);
        }
        startActivityForResult(it, requestCode);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }




    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ButterKnife.unbind(this);
        ActivityManager.getManager().finishActivity(this);
    }
}
