package com.example.linzhizhou.petchase_12_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linzhizhou.petchase_12_3.pet.ui.activity.MeMsgActivity;
import com.example.linzhizhou.petchase_12_3.pet.ui.activity.PetMsgActivity;

public class MeFragment extends Fragment implements View.OnClickListener{
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
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.xiugai:
                openActivity(MeMsgActivity.class);
                break;
            case R.id.chongwu:
                openActivity(PetMsgActivity.class);
                break;
            default:
                break;

        }
    }

    /*启动Activity*/
    public void openActivity(Class<? extends AppCompatActivity> cls)
    {
        Intent it = new Intent(getActivity(), cls);
        startActivity(it);
    }
}
