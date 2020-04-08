package com.example.linzhizhou.petchase_12_3;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {

    private FrameLayout homeContent;
    private RadioGroup radioGroup;
    private RadioButton rbHome,rbNavigate,rbfind,rbMe;
    static  final int NUM_ITEMS=4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        initView();
    }
    @Override
    protected void onStart() {
        super.onStart();
        radioGroup.check(R.id.home);
    }

    //不运行
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==4&&resultCode==4){
//            //通过Fragment的适配器Adapter和index来替换帧布局中的内容
//            Fragment fragment=(Fragment)adapter.instantiateItem(homeContent,4);
//
//            //开始将帧布局中的内容设置为第一个，即“首页”选项
//            adapter.setPrimaryItem(homeContent,4,fragment);
//            //设置回调，完成更新
//            adapter.finishUpdate(homeContent);
//        }
//    }

    protected void initView(){
        homeContent=findViewById(R.id.homeContent);
        radioGroup=findViewById(R.id.radioGroup);
        rbHome=findViewById(R.id.home);
        rbNavigate=findViewById(R.id.navigate);
        rbfind=findViewById(R.id.activity);
        rbMe=findViewById(R.id.me);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index=0;
                switch(checkedId){
                    case R.id.home:
                        index=0;
                        break;
                    case R.id.navigate:
                        index=1;
                        break;
                    case R.id.activity:
                        index=2;
                        break;
                    case R.id.me:
                        index=3;
                        break;
                }

                //通过Fragment的适配器Adapter和index来替换帧布局中的内容
                Fragment fragment=(Fragment)adapter.instantiateItem(homeContent,index);

                //开始将帧布局中的内容设置为第一个，即“首页”选项
                adapter.setPrimaryItem(homeContent,index,fragment);
                //设置回调，完成更新
                adapter.finishUpdate(homeContent);
            }
        });
    }
    FragmentStatePagerAdapter adapter=new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;
            switch (position){
                case 0:
                    fragment=new HomeFragment();
                    break;
                case 1:
                    fragment=new NavigateFragment();
                    break;
                case 2:
                    fragment=new FindFragment();
                    break;
                case 3:
                    fragment=new MeFragment();
                    break;
                default:
                    fragment=new HomeFragment();
                    break;
            }
            return fragment;
        }
    };
}
