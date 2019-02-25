package com.example.linzhizhou.petchase_12_3;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.ButterKnife;

import butterknife.OnClick;

public class FindFragment extends Fragment {
    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private  int i;
    private String strurl = "http://192.168.137.1:8080/Serverlet/hello?id=";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    Context context;
    SwipeFlingAdapterView flingContainer;

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_find, null);
        flingContainer = view.findViewById(R.id.frame);
        //ButterKnife.inject(getActivity());
        new Thread(networkTask).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                //makeToast(MyActivity.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {

                //makeToast(MyActivity.this, "Right!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                al.add("时间太赶拉，来不及写爬虫去爬数据，先凑活凑活拉(●'◡'●) ");
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                // i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });
        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(getActivity(), "Clicked!");
            }
        });
        //sleep();
        return view;
    }

    static void makeToast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.right)
    public void right() {
        /**
         * Trigger the right event manually.
         */
        flingContainer.getTopCardListener().selectRight();
    }

    @OnClick(R.id.left)
    public void left() {
        flingContainer.getTopCardListener().selectLeft();
    }

    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
// TODO
            // 在这里进行 http request.网络请求相关操作
            al = new ArrayList<>();
            //可以从数据库中读取，然后加入
            //          al.add("php");
//            al.add("c");
//            al.add("python");
//            al.add("java");
//            al.add("html");
//            al.add("c++");
//            al.add("css");
//            al.add("javascript");
//            arrayAdapter = new ArrayAdapter<>(context, R.layout.item, R.id.helloText, al );
            URL url = null;
            //采用get方式访问spring云服务器
            for (int i=1;i<=10 ;i++ ) {
                try {
                    //String temp= ((String) i);
                    strurl="http://192.168.137.1:8080/Serverlet/hello?id=";
                    strurl+=i;
                    url = new URL(strurl);
                    HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                    InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(in);
                    String result = "";
                    String readLine = null;
                    while ((readLine = bufferedReader.readLine()) != null) {
                        result += readLine;
                    }
                    al.add(result);
                    in.close();

                    urlConn.disconnect();//关闭连接
                } catch (Exception e) {
                    e.printStackTrace();
                }

                arrayAdapter = new ArrayAdapter<>(context, R.layout.item, R.id.helloText, al);
                flingContainer.setAdapter(arrayAdapter);

            }

        }
    };
}