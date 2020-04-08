package com.example.linzhizhou.petchase_12_3;

//import android.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.MapFragment;
import com.example.linzhizhou.petchase_12_3.advice.DevelopingActivity;
import com.example.linzhizhou.petchase_12_3.advice.SuggestActivity;
import com.example.linzhizhou.petchase_12_3.reglogin.ui.activity.MeMsgActivity;
import com.example.linzhizhou.petchase_12_3.reglogin.ui.activity.PetMsgActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeFragment extends Fragment implements View.OnClickListener{

    @Bind(R.id.xiugai)
    public ImageButton xiugaibtm;

    @Bind(R.id.qiandao)
    public ImageButton qiandaobtm;
    @Bind(R.id.qiandaotxt)
    public TextView qiandaotxt;
    @Bind(R.id.liuchong)
    public ImageButton liuchongbtm;
    @Bind(R.id.liuchongtxt)
    public TextView liuchongtxt;
    @Bind(R.id.xiaoxi)
    public ImageButton xiaoxibtm;
    @Bind(R.id.xiaoxitxt)
    public TextView xiaoxitxt;

    @Bind(R.id.chongzuan)
    public TextView chongzuanbtm;
    @Bind(R.id.chongzuantxt)
    public TextView chongzuantxt;
    @Bind(R.id.chongbi)
    public TextView chongbibtm;
    @Bind(R.id.chongbitxt)
    public TextView chongbitxt;
    @Bind(R.id.youhui)
    public TextView youhuibtm;
    @Bind(R.id.youhuitxt)
    public TextView youhuitxt;
    @Bind(R.id.liwu)
    public TextView liwubtm;
    @Bind(R.id.liwutxt)
    public TextView liwutxt;

    @Bind(R.id.chongwu)
    public ImageButton chongwubtm;
    @Bind(R.id.chongwutxt)
    public TextView chongwutxt;
    @Bind(R.id.chongyou)
    public ImageView chongyoubtm;
    @Bind(R.id.chongyoutxt)
    public TextView chongyoutxt;
    @Bind(R.id.shequ)
    public ImageView shequbtm;
    @Bind(R.id.shequtxt)
    public TextView shequtxt;
    @Bind(R.id.shoucang)
    public ImageView shoucangbtm;
    @Bind(R.id.shoucangtxt)
    public TextView shoucangtxt;

    @Bind(R.id.zhanghumingxi)
    public ImageView zhanghumingxibtm;
    @Bind(R.id.zhanghumingxitxt)
    public TextView zhuanghumingxitxt;
    @Bind(R.id.gexingzhuangban)
    public ImageView gexingzhuangbanbtm;
    @Bind(R.id.gexingzhuangbantxt)
    public TextView gexingzhuangbantxt;
    @Bind(R.id.shezhi)
    public ImageView shezhibtm;
    @Bind(R.id.shezhitxt)
    public TextView shezhitxt;
    @Bind(R.id.yijianfankui)
    public ImageView yijianfankuibtm;
    @Bind(R.id.yijianfankuitxt)
    public TextView yijianfankuitxt;


    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if(this.getView()!=null){
            this.getView().setVisibility(menuVisible?View.VISIBLE:View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_me,null);

        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    @OnClick({R.id.xiugai,
            R.id.qiandao, R.id.qiandaotxt, R.id.liuchong,R.id.liuchongtxt,R.id.xiaoxi,R.id.xiaoxitxt,
            R.id.chongzuan,R.id.chongzuantxt,R.id.chongbi,R.id.chongbitxt,R.id.youhui,R.id.youhuitxt,R.id.liwu,R.id.liwutxt,
            R.id.chongwu,R.id.chongwutxt,R.id.chongyou,R.id.chongyoutxt,R.id.shequ,R.id.shequtxt,R.id.shoucang,R.id.shoucangtxt,
            R.id.zhanghumingxi,R.id.zhanghumingxitxt,R.id.gexingzhuangban,R.id.gexingzhuangbantxt,R.id.shezhi,R.id.shezhitxt,R.id.yijianfankui,R.id.yijianfankuitxt,
    })
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.xiugai:
                openActivity(MeMsgActivity.class);
//                Intent temp1=new Intent(getActivity(),MeMsgActivity.class);
//                startActivity(temp1);
                break;

            case R.id.qiandao:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.qiandaotxt:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.liuchong:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.liuchongtxt:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.xiaoxi:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.xiaoxitxt:
                openActivity(DevelopingActivity.class);
                break;

            case R.id.chongbi:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.chongbitxt:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.youhui:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.youhuitxt:
            openActivity(DevelopingActivity.class);
            break;
            case R.id.liwu:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.liwutxt:
                openActivity(DevelopingActivity.class);
                break;

            case R.id.chongwu:
                openActivity(PetMsgActivity.class);
                break;
            case R.id.chongwutxt:
                openActivity(PetMsgActivity.class);
                break;
            case R.id.chongyou:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.chongyoutxt:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.shequ:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.shequtxt:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.shoucang:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.shoucangtxt:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.zhanghumingxi:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.zhanghumingxitxt:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.gexingzhuangban:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.gexingzhuangbantxt:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.shezhi:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.shezhitxt:
                openActivity(DevelopingActivity.class);
                break;
            case R.id.yijianfankui:
                openActivity(SuggestActivity.class);
                break;
            case R.id.yijianfankuitxt:
                openActivity(SuggestActivity.class);
                break;
            default:
                break;

        }
    }

    /*启动Activity*/
    public void openActivity(Class<? extends AppCompatActivity> cls)
    {
        Intent it = new Intent(getActivity(), cls);
        startActivityForResult(it,4);
//        startActivity(it);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MainActivity mainActivity=(MainActivity)getActivity();

        FragmentManager fragmentManager=mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.homeContent,new MeFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
