package com.example.linzhizhou.petchase_12_3.utils;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.HttpEntity;

public class HttpUtils {
    //定义String类型来接收接口相同的部分
//    private static final String BASE_URL = "http://lzzpros.cn:80/";

    //本地测试
    private static final String BASE_URL = "http://192.168.137.1:8080/";


    //建立静态的AsyncHttpClient
    private static AsyncHttpClient client = new AsyncHttpClient();

    //AsyncHttpClient中有get和post方法，需要用到public方法来修饰，以便调用
    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
        client.get(BASE_URL + url, responseHandler);
    }

    //post方法中HttpEntity参数是后面发送JSON格式所用到的一个方法,//已删除简化
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(BASE_URL + url, params, responseHandler);
    }


}
