package com.example.linzhizhou.petchase_12_3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageButton;

public class HomeFragment extends Fragment implements View.OnClickListener
{

    @Override
    public void setMenuVisibility(boolean menuVisible)
    {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
        {
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        ImageButton b1 = view.findViewById(R.id.bind_device);
        b1.setOnClickListener(this);
        ImageButton b2 = view.findViewById(R.id.button2);
        ImageButton b3 = view.findViewById(R.id.button3);
        b3.setOnClickListener(this);
        ImageButton b4 = view.findViewById(R.id.button4);
        ImageButton b5 = view.findViewById(R.id.button5);
        b5.setOnClickListener(this);
        ImageButton b6 = view.findViewById(R.id.button6);
        b6.setOnClickListener(this);
        ImageButton b7 = view.findViewById(R.id.button7);
        b7.setOnClickListener(this);
        ImageButton b8 = view.findViewById(R.id.button8);
        setParams(width, b1);
        setParams(width, b2);
        setParams(width, b3);
        setParams(width, b4);
        setParams(width, b5);
        setParams(width, b6);
        setParams(width, b7);
        setParams(width, b8);
        return view;
    }

    private void setParams(int width, ImageButton b)
    {
        GridLayout.LayoutParams params = (GridLayout.LayoutParams) b.getLayoutParams();
        params.width = width /5 - 22;
        b.setLayoutParams(params);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.bind_device:
                openActivity(BindActivity.class);
                break;
            case R.id.button5:
                openActivity(LuckydrawActivity.class);
                break;
            case R.id.button6:
                openActivity(SuggestActivity.class);
                break;
            case R.id.button3:
                openActivity(FindActivity.class);
                break;
            case R.id.button7:
                openActivity(DayActivity.class);
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
