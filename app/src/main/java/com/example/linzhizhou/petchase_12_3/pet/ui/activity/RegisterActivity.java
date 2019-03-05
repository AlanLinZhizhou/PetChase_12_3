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


import com.example.linzhizhou.petchase_12_3.MainActivity;
import com.example.linzhizhou.petchase_12_3.R;
import com.tckj.zyfsdk.ZYFSdk;
import com.tckj.zyfsdk.entity.BaseEntity;
import com.tckj.zyfsdk.entity.CodeEntity;
import com.tckj.zyfsdk.http.zhttp.listener.ZYFGetAuthCodeListener;
import com.tckj.zyfsdk.http.zhttp.listener.ZYFRegisterListener;


import butterknife.Bind;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements TextWatcher
{

    @Bind(R.id.top_text)
    TextView mTopText;
    @Bind(R.id.register_phone)
    EditText mRegisterPhone;
    @Bind(R.id.register_pass)
    EditText mRegisterPass;
    @Bind(R.id.register_btn)
    TextView mRegisterBtn;


    @Bind(R.id.code_nums)
    TextView mCodeNums;
    private Timer mTimer;
    private boolean timing = false;

    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_register;
    }

    @Override
    protected void setStatusBarColor()
    {

    }


    @Override
    protected void initView(Bundle savedInstanceState)
    {
        super.initView(savedInstanceState);
        mTopText.setText("注册");
        mRegisterPass.addTextChangedListener(this);
        mRegisterPhone.addTextChangedListener(this);


        mTimer = new Timer(10 * 1000, 1000);
        mCodeNums.setClickable(false);
    }



    @OnClick({R.id.top_back,R.id.code_nums, R.id.register_btn, R.id.parent_phone, R.id.parent_pass, R.id.register_agree})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.top_back://返回
                finish();
                break;
            case R.id.code_nums://获取验证码，显示倒计时
                mTimer.start();
                mRegisterPhone.getText();//手机号
                ZYFSdk.getInstance().getAuthCode( mRegisterPhone.getText().toString(), new ZYFGetAuthCodeListener() {
                    @Override
                    public void onComplete(CodeEntity codeEntity) {
                        codeEntity.getRtData();
                    }

                    @Override
                    public void onError(Exception e) {
//                        mRegisterPhone.setText(mRegisterPhone.getText()+e.toString());
                    }
                });
                break;
            case R.id.register_btn://注册成功并登录，到首页
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.parent_phone://点击父类获取焦点
                mRegisterPhone.requestFocus();
                break;
            case R.id.parent_pass://点击父类获取焦点
                mRegisterPass.requestFocus();
                break;
            case R.id.register_agree://用户协议
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
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {

    }

    @Override
    public void afterTextChanged(Editable s)
    {


        setCodeBtn();

        if (!mRegisterPass.getText().toString().equals("") && !mRegisterPhone.getText().toString().equals(""))
        {
            mRegisterBtn.setBackgroundResource(R.drawable.shape_register_btn_pet);
        } else
        {
            mRegisterBtn.setBackgroundResource(R.drawable.shape_register_btn_32);
        }
    }


    /*计时器*/
    protected class Timer extends CountDownTimer
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
        String code = mRegisterPhone.getText().toString();
        boolean click = (!TextUtils.isEmpty(code)) && (!timing);
        mCodeNums.setClickable(click);
        mCodeNums.setBackgroundResource(click ? R.drawable.shape_code_selected : R.drawable.shape_code_unselected);
        mCodeNums.setTextColor(Color.parseColor(click ? "#ffffff" : "#cccccc"));
    }


}
