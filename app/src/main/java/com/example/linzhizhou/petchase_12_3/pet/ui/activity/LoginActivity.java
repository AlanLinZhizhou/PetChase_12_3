package com.example.linzhizhou.petchase_12_3.pet.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.linzhizhou.petchase_12_3.MainActivity;
import com.example.linzhizhou.petchase_12_3.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.Bind;
import butterknife.OnClick;




public class LoginActivity extends BaseActivity
{
    @Bind(R.id.top_text)
    TextView mTopText;
    EditText phone;
    EditText passwd;
    private int flag;
    private String strurl ;
    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_login;
    }

    @Override
    protected void setStatusBarColor()
    {

    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        super.initView(savedInstanceState);
        phone=findViewById(R.id.register_phone);
        passwd=findViewById(R.id.register_pass);
        flag=0;
        mTopText.setText("登录");
    }


    @OnClick({R.id.top_back, R.id.login_miss, R.id.login_btn, R.id.login_more})
    public void onViewClicked(View view)
    {
        //URL url = null;
        switch (view.getId())
        {
            case R.id.top_back:
                finish();
                break;
            case R.id.login_miss:
                startActivity(new Intent(this, MissActivity.class));
                break;
            case R.id.login_btn:
                new Thread(testLogin).start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(flag==1){
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                else {
                    Toast t=Toast.makeText(getApplicationContext(),"用户名或密码错误",Toast.LENGTH_SHORT);
                    t.show();
                }
                break;
            default:
                break;
        }
    }

    Runnable testLogin=new Runnable() {
        @Override
        public void run() {
            String encodePass;
            encodePass=md5Decode16(passwd.getText().toString());
//            strurl="http://192.168.137.1:8080/TestLogin?uphone=";
            strurl="http://lzzpros.cn:80/TestLogin?uphone=";
//            strurl=strurl+phone.getText().toString()+"&upass="+passwd.getText().toString();
            strurl=strurl+phone.getText().toString()+"&upass="+encodePass;
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
                if(result.equals("OK")){
                    flag=1;
                }
                in.close();
                urlConn.disconnect();}
            catch(Exception e){
                e.printStackTrace();
            }
//            if(flag==1) {
//                //startActivity(new Intent(this, MainActivity.class));
//
//                finish();
//            }
//            else {
//                Toast t=Toast.makeText(getApplicationContext(),"用户名或密码错误",Toast.LENGTH_SHORT);
//                t.show();
//            }
        }
    };

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
}
