package com.dnktechnologies.laxmidiamond;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import Dialog.Logout_Dialog;
import Fragment.*;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by parth on 8/10/2016.
 */
public class Stone_List extends Activity implements View.OnClickListener {

    Button btn_home, btn_logout, btn_placeOrder, btn_addtoTrack, btn_addTocart, btn_gridView, btn_emailStone;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stone_list);

        btn_home = (Button) findViewById(R.id.btn_home);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_placeOrder = (Button) findViewById(R.id.btn_placeOrder);
        btn_addtoTrack = (Button) findViewById(R.id.btn_addtoTrack);
        btn_addTocart = (Button) findViewById(R.id.btn_addTocart);
        btn_gridView = (Button) findViewById(R.id.btn_gridView);
        btn_emailStone = (Button) findViewById(R.id.btn_emailStone);

        fragment=whichFragment();//which frag(bgm,new Arrival,REvised PR)
        setFragment(fragment);//set fragment

        btn_home.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_placeOrder.setOnClickListener(this);
        btn_addtoTrack.setOnClickListener(this);
        btn_addTocart.setOnClickListener(this);
        btn_gridView.setOnClickListener(this);
        btn_emailStone.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (v.getId()) {
            case R.id.btn_home: {
                Intent in = new Intent(Stone_List.this, activity_desboard.class);
                startActivity(in);
                break;
            }
            case R.id.btn_logout: {
                new Logout_Dialog(this);
                break;
            }
        }
    }

    public void setFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.frag_stone, fragment).commit();
    }

    public Fragment whichFragment() {
        String Parent = getIntent().getExtras().getString("parent");
        switch (Parent) {
            case "BGM": {
                fragment = new Frag_Bgm();
                break;
            }
            case "NARR": {
                fragment=new Frag_new_Arrival();
                break;
            }
            case "RPR": {
                fragment=new Frag_ReVised_Price();
                break;
            }
        }

        return fragment;
    }

}
