package com.example.linzhizhou.petchase_12_3.release_info;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linzhizhou.petchase_12_3.R;
import com.example.linzhizhou.petchase_12_3.utils.HttpUtils;
import com.example.linzhizhou.petchase_12_3.utils.PermisionUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class Realease_InfoActivity extends AppCompatActivity {

    private int RESULT_LOAD_IMG = 1;
    private String imgPath;
    private ProgressDialog prgDialog;
    private Bitmap bitmap;
    private String encodedString;
    private RequestParams params = new RequestParams();
    @Bind(R.id.layout_title)
    TextView mLayoutTitle;
    @Bind(R.id.release_content)
    EditText mEdDescript;
    @Bind(R.id.rgp_release)
    RadioGroup radioGroup;
    @Bind(R.id.kepu)
    RadioButton kepu;
    @Bind(R.id.find_pet)
    RadioButton findpet;
    @Bind(R.id.uploadImg)
    TextView uploadImg;
    @Bind(R.id.bacImg)
    ImageView bacImg;

    private int status = 0; //status=0表示发布寻宠启示

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        ButterKnife.bind(this);
        mLayoutTitle.setText("发布新动态");
        prgDialog = new ProgressDialog(this);
        prgDialog.setCancelable(false);
        PermisionUtils.verifyStoragePermissions(this);
//        findpet.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.find_pet:
                        status = 0;
                        //发布寻宠启示
                        uploadImg.setText("+ 请选择寻宠图片");
                        uploadImg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadImage();
                            }
                        });
                        break;
                    case R.id.kepu:
                        status = 1;
                        uploadImg.setText("+ 请选择科普图片");
                        //发布科普信息
                        uploadImg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadImage();
                            }
                        });
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if(prgDialog!=null){
            prgDialog.dismiss();
        }
        super.onDestroy();

    }

    //当图片被选中的返回结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                // 获取游标
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgPath = cursor.getString(columnIndex);
                cursor.close();
                //imgView.setBackground();
                Bitmap bm = BitmapFactory.decodeFile(imgPath);
                bacImg.setImageBitmap(bm);
                //重新勾选寻宠还是科普
                radioGroup.clearCheck();
                if (status == 0) {
//                    findpet.setChecked(true);
                    findpet.setSelected(true);
                    findpet.setChecked(true);
                    kepu.setSelected(false);
                } else {
//                    kepu.setChecked(true);
                    kepu.setSelected(true);
                    kepu.setChecked(true);
                    findpet.setSelected(false);
                }
            } else {
                Toast.makeText(this, "您还没有选择图片",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "出错拉", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        radioGroup.check(R.id.rgp_release);
    }

    private void loadImage() {
        //这里就写了从相册中选择图片，相机拍照的就略过了
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @OnClick({R.id.layout_finish, R.id.tv_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_finish:
                //返回
                finish();
                break;
            case R.id.tv_action:
                //发布信息
                if (status == 0) {//寻宠启示
                    //上传头像
                    uploadImage();
                } else {//科普信息
                    uploadImage();
                }
                finish();
                break;
        }
    }

    private void uploadImage() {
        if (imgPath != null && !imgPath.isEmpty()) {
            prgDialog.setMessage("压缩图片");
            prgDialog.show();
            encodeImagetoString();
        } else {
            Toast.makeText(getApplicationContext(), "上传前必须要选好图片哦",
                    Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void encodeImagetoString() {
        new AsyncTask<Void, Void, String>() {
            protected void onPreExecute() {

            }


            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            protected String doInBackground(Void... params) {
                BitmapFactory.Options options = null;
                options = new BitmapFactory.Options();
                options.inSampleSize = 3;
                bitmap = BitmapFactory.decodeFile(imgPath,
                        options);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // 压缩图片
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byte_arr = stream.toByteArray();
                // Base64图片转码为String
                encodedString=java.util.Base64.getEncoder().encodeToString(byte_arr);
//                encodedString = Base64.encodeToString(byte_arr, 0);
                return "";
            }

            @Override
            protected void onPostExecute(String msg) {
                prgDialog.setMessage("正在上传");
                // 将转换后的图片添加到上传的参数中
                params.put("image", encodedString);
                //params.put("filename", editTextName.getText().toString());
                // 上传图片

                imageUpload();
            }
        }.execute(null, null, null);
    }

    public void imageUpload() {
        prgDialog.setMessage("图片上传中");
        String url;
        if(status==0){
            url="release";
            params.put("contact","13338612187");
        }
        else{
            url="releasekepu";
            params.put("k_trust","4");
        }
        HttpUtils client = new HttpUtils();
        String content=mEdDescript.getText().toString();
        params.put("releaser","admin");
        params.put("re_cotent",content);
        client.post(url, params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                prgDialog.hide();
                // prgDialog.setMessage("识别中");
                Toast.makeText(getApplicationContext(), "发布成功", Toast.LENGTH_SHORT).show();
                String temp = new String(bytes);
                Log.i("dixjk", temp);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                prgDialog.hide();
                if (statusCode == 404) {
                    Toast.makeText(getApplicationContext(),
                            "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // 当 Http 响应码'500'
                else if (statusCode == 500) {
                    Toast.makeText(getApplicationContext(),
                            "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // 当 Http 响应码 404, 500
                else {
                    Toast.makeText(
                            getApplicationContext(), "Error Occured n Most Common Error: n1. Device " +
                                    "not connected to Internetn2. Web App is not deployed in App servern3." +
                                    " App server is not runningn HTTP Status code : "
                                    + statusCode, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
