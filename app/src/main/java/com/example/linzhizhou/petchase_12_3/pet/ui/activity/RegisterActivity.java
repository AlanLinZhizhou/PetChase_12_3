package com.example.linzhizhou.petchase_12_3.pet.ui.activity;

import android.app.ProgressDialog;
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
import android.widget.Toast;


import com.example.linzhizhou.petchase_12_3.MainActivity;
import com.example.linzhizhou.petchase_12_3.R;
import com.tckj.zyfsdk.ZYFSdk;
import com.tckj.zyfsdk.entity.BaseEntity;
import com.tckj.zyfsdk.entity.CodeEntity;
import com.tckj.zyfsdk.http.zhttp.listener.ZYFGetAuthCodeListener;
import com.tckj.zyfsdk.http.zhttp.listener.ZYFRegisterListener;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements TextWatcher {

    @Bind(R.id.top_text)
    TextView mTopText;
    @Bind(R.id.register_phone)
    EditText mRegisterPhone;
    @Bind(R.id.register_pass)
    EditText mRegisterPass;
    @Bind(R.id.register_btn)
    TextView mRegisterBtn;
    @Bind(R.id.code_getnums)
    EditText code_getnums;
    @Bind(R.id.code_nums)
    TextView mCodeNums;
    private Timer mTimer;
    private boolean timing = false;
    private String realVerifyCode;
    private String inputVerifyCode;
    private String phonenum;
    private String password;
    private String strurl;
    private int flag = 0;
    private ProgressDialog prgDialog;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;
    }

    @Override
    protected void setStatusBarColor() {

    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTopText.setText("注册");
        mRegisterPass.addTextChangedListener(this);
        mRegisterPhone.addTextChangedListener(this);

        prgDialog = new ProgressDialog(this);
        prgDialog.setCancelable(false);

        mTimer = new Timer(60 * 1000, 1000);
        mCodeNums.setClickable(false);
    }


    @OnClick({R.id.top_back, R.id.code_nums, R.id.register_btn, R.id.parent_phone, R.id.parent_pass, R.id.register_agree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back://返回
                finish();
                break;
            case R.id.code_nums://获取验证码，显示倒计时
                mTimer.start();
                phonenum = mRegisterPhone.getText().toString();//手机号
                ZYFSdk.getInstance().getAuthCode(mRegisterPhone.getText().toString(), new ZYFGetAuthCodeListener() {
                    @Override
                    public void onComplete(CodeEntity codeEntity) {
                        realVerifyCode = codeEntity.getRtData();
                    }

                    @Override
                    public void onError(Exception e) {
//                        mRegisterPhone.setText(mRegisterPhone.getText()+e.toString());
                    }
                });
                break;
            case R.id.register_btn://注册成功并登录，到首页
                register();
//                startActivity(new Intent(this, MainActivity.class));
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
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {


        setCodeBtn();

        if (!mRegisterPass.getText().toString().equals("") && !mRegisterPhone.getText().toString().equals("")) {
            mRegisterBtn.setBackgroundResource(R.drawable.shape_register_btn_pet);
        } else {
            mRegisterBtn.setBackgroundResource(R.drawable.shape_register_btn_32);
        }
    }


    /*计时器*/
    protected class Timer extends CountDownTimer {
        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            timing = true;
            setCodeBtn();
            mCodeNums.setText(l / 1000 + "s");
        }

        @Override
        public void onFinish() {
            timing = false;
            setCodeBtn();
            mCodeNums.setText("获取验证码");
        }
    }


    /*设置按钮风格*/
    private void setCodeBtn() {
        String code = mRegisterPhone.getText().toString();
        boolean click = (!TextUtils.isEmpty(code)) && (!timing);
        mCodeNums.setClickable(click);
        mCodeNums.setBackgroundResource(click ? R.drawable.shape_code_selected : R.drawable.shape_code_unselected);
        mCodeNums.setTextColor(Color.parseColor(click ? "#ffffff" : "#cccccc"));
    }

    private void register() {
        phonenum=mRegisterPhone.getText().toString();

        inputVerifyCode = code_getnums.getText().toString();
        if (inputVerifyCode.equals(realVerifyCode)) {
//        if (true) {
            prgDialog.setMessage("验证中，请稍后");
            prgDialog.show();
            password = md5Decode16(mRegisterPass.getText().toString());
            new Thread(register2server).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (flag == 1) {
                prgDialog.hide();
                startActivity(new Intent(this, MainActivity.class));
            } else {
                prgDialog.hide();
                Toast.makeText(getApplicationContext(), "用户名已存在，请直接登陆", Toast.LENGTH_SHORT);
            }
        } else {
            Toast.makeText(getApplicationContext(), "请输入正确的验证码", Toast.LENGTH_SHORT);
        }
    }

    /**
     * 32位MD5加密
     *
     * @param content -- 待加密内容
     * @return
     */
    public String md5Decode(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }
        //对生成的16字节数组进行补零操作
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 16位MD5加密
     * 实际是截取的32位加密结果的中间部分(8-24位)
     *
     * @param content
     * @return
     */
    public String md5Decode16(String content) {
        return md5Decode(content).substring(8, 24);
    }

    Runnable register2server = new Runnable() {
        @Override
        public void run() {
//            strurl = "http://192.168.137.1:8080/register?uphone=";
            strurl = "http://lzzpros.cn:80/register?uphone=";
            strurl = strurl + phonenum + "&upass=" + password;
            try {
                URL url = null;
                url = new URL(strurl);

                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(in);
                String result = "";
                String readLine = null;
                while ((readLine = bufferedReader.readLine()) != null) {
                    result += readLine;
                }
                if (result.equals("RegisterOK")) {
                    flag = 1;
                }
                in.close();
                urlConn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };
}
