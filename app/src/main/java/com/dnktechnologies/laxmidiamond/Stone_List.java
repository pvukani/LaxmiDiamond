package com.dnktechnologies.laxmidiamond;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import Adapter.Adapter_grid;
import Adapter.Adapter_result_stone;
import Dialog.Logout_Dialog;
import Fragment.*;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by parth on 8/10/2016.
 */
public class Stone_List extends Activity implements View.OnClickListener {

    Button btn_home, btn_logout, btn_placeOrder, btn_addtoTrack, btn_addTocart, btn_gridView, btn_emailStone;
    Adapter_grid adapter_grid;
    Adapter_result_stone adapter_result_stone;
    LinearLayout lout_header, lout_footer;
    FrameLayout frag_stone;
    RecyclerView rv_stone_grid;
    GridLayoutManager gridLayoutManager;
    RecyclerView.LayoutManager layoutManager;
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
        lout_header = (LinearLayout) findViewById(R.id.lout_header);
        lout_footer = (LinearLayout) findViewById(R.id.lout_footer);
        frag_stone = (FrameLayout) findViewById(R.id.frag_stone);
        rv_stone_grid = (RecyclerView) findViewById(R.id.rv_stone_grid);
        layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        setFragment();
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
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
            case R.id.btn_gridView: {

                if (btn_gridView.isSelected()) {
                    btn_gridView.setSelected(false);
                    btn_gridView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.list_view, 0, 0);
                    btn_gridView.setText("Photo View");
                    lout_header.setVisibility(View.VISIBLE);
                    frag_stone.setVisibility(View.VISIBLE);
                    lout_footer.setVisibility(View.VISIBLE);
                    rv_stone_grid.setLayoutManager(layoutManager);
                    adapter_result_stone=new Adapter_result_stone(this,GlobalApp.Arr_for_grid,GlobalApp.map_result);
                    rv_stone_grid.setAdapter(adapter_result_stone);

                } else {
                    btn_gridView.setSelected(true);
                    btn_gridView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.grid_view, 0, 0);
                    btn_gridView.setText("List View");
                    lout_header.setVisibility(View.GONE);
                    frag_stone.setVisibility(View.GONE);
                    lout_footer.setVisibility(View.GONE);
                    rv_stone_grid.setVisibility(View.VISIBLE);
                    rv_stone_grid.setLayoutManager(gridLayoutManager);
                    adapter_grid = new Adapter_grid(this, GlobalApp.Arr_for_grid, GlobalApp.map_result);
                    rv_stone_grid.setAdapter(adapter_grid);
                }
            }
        }
    }

    public void setFragment() {
        String Parent = getIntent().getExtras().getString("parent");

        switch (Parent) {
            case "BGM": {
                getFragmentManager().beginTransaction().replace(R.id.frag_stone, new Frag_Bgm()).commit();
                Log.i("which", "bgm");
                break;
            }
            case "NARR": {
                getFragmentManager().beginTransaction().replace(R.id.frag_stone, new Frag_new_Arrival()).commit();
                Log.i("which", "new R");
                break;
            }
            case "RPR": {
                getFragmentManager().beginTransaction().replace(R.id.frag_stone, new Frag_ReVised_Price()).commit();
                Log.i("which", "revised PR");
                break;
            }
            case "STONE": {
                getFragmentManager().beginTransaction().replace(R.id.frag_stone, new Frag_Result_stone()).commit();
                Log.i("which", "Result");
                break;
            }
        }

    }
}