package com.example.linzhizhou.petchase_12_3.pet.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.linzhizhou.petchase_12_3.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 注册界面获取验证码
 */
public class VerificationCodeActivity extends BaseActivity implements TextWatcher
{

    @Bind(R.id.top_text)
    TextView mTopText;
    @Bind(R.id.code_nums)
    TextView mCodeNums;
    @Bind(R.id.code_getnums)
    EditText mCodeGetnums;


    private Timer mTimer;
    private boolean timing = false;


    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_verification_code;
    }

    @Override
    protected void setStatusBarColor()
    {

    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        super.initView(savedInstanceState);
        mTimer = new Timer(10 * 1000, 1000);
        mTopText.setText("注册");
        mCodeGetnums.addTextChangedListener(this);
        mCodeNums.setClickable(false);
    }


    @OnClick({R.id.top_back, R.id.code_nums, R.id.code_btn, R.id.register_agree})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.top_back:
                finish();
                break;
            case R.id.code_nums://获取验证码
                mTimer.start();
                break;
            case R.id.code_btn:
                startActivity(new Intent(this, PetMsgActivity.class));
                finish();
                break;
            case R.id.register_agree:
                startActivity(new Intent(this, AgreeDetialActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (mTimer != null)
        {
            mTimer.cancel();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {

    }

    @Override
    public void afterTextChanged(Editable editable)
    {
        setCodeBtn();
    }


    /*计时器*/
    private class Timer extends CountDownTimer
    {
        public Timer(long millisInFuture, long countDownInterval)
        {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l)
        {
            timing = true;
            setCodeBtn();
            mCodeNums.setText(l / 1000 + "s");
        }

        @Override
        public void onFinish()
        {
            timing = false;
            setCodeBtn();
            mCodeNums.setText("获取验证码");
        }
    }


    /*设置按钮风格*/
    private void setCodeBtn()
    {
        String code = mCodeGetnums.getText().toString();
        boolean click = (!TextUtils.isEmpty(code)) && (!timing);
        mCodeNums.setClickable(click);
        mCodeNums.setBackgroundResource(click ? R.drawable.shape_code_selected : R.drawable.shape_code_unselected);
        mCodeNums.setTextColor(Color.parseColor(click ? "#ffffff" : "#cccccc"));
    }


}
