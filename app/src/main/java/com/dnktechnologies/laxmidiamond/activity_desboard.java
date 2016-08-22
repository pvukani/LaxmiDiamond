package com.dnktechnologies.laxmidiamond;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class activity_desboard extends AppCompatActivity implements View.OnClickListener {

    LinearLayout single_stone_search, fancy_color_search, quick_search,
            new_arrival, bgm_stones, revised_price, tracked_stones, my_cart,
            my_offers, my_purchase, lout_logout;

    Bundle bundle;
    String parent = "parent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_desboard);

        lout_logout = (LinearLayout) findViewById(R.id.lout_logout);
        single_stone_search = (LinearLayout) findViewById(R.id.single_stone_search);
        fancy_color_search = (LinearLayout) findViewById(R.id.fancy_color_search);
        quick_search = (LinearLayout) findViewById(R.id.quick_search);
        new_arrival = (LinearLayout) findViewById(R.id.new_arrival);
        bgm_stones = (LinearLayout) findViewById(R.id.bgm_stones);
        revised_price = (LinearLayout) findViewById(R.id.revised_price);
        tracked_stones = (LinearLayout) findViewById(R.id.tracked_stones);
        my_cart = (LinearLayout) findViewById(R.id.my_cart);
        my_offers = (LinearLayout) findViewById(R.id.my_offers);
        my_purchase = (LinearLayout) findViewById(R.id.my_purchase);

        bundle = new Bundle();

        single_stone_search.setOnClickListener(this);
        fancy_color_search.setOnClickListener(this);
        lout_logout.setOnClickListener(this);
        quick_search.setOnClickListener(this);
        my_cart.setOnClickListener(this);
        my_offers.setOnClickListener(this);
        bgm_stones.setOnClickListener(this);
        revised_price.setOnClickListener(this);
        new_arrival.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.single_stone_search: {
                Intent in = new Intent(activity_desboard.this, Single_stone_search.class);
                startActivity(in);
                break;
            }
            case R.id.fancy_color_search: {
                GlobalApp.flag_fancy = true;
                Intent in = new Intent(this, Single_stone_search.class);
                startActivity(in);
                break;
            }
            case R.id.lout_logout: {
                new Dialog.Logout_Dialog(activity_desboard.this);
                break;
            }
            case R.id.quick_search: {
                Intent in = new Intent(activity_desboard.this, Quick_search.class);
                startActivity(in);
                break;
            }
            case R.id.my_cart: {
                Intent in = new Intent(this, Stone_List.class);
                bundle.putString(parent, "CART");
                in.putExtras(bundle);
                startActivity(in);
                break;
            }
            case R.id.my_offers: {
                Intent in = new Intent(this, My_offers.class);
                startActivity(in);
                break;
            }
            case R.id.bgm_stones: {
                Intent in = new Intent(this, Stone_List.class);
                bundle.putString(parent, "BGM");
                in.putExtras(bundle);
                startActivity(in);
                break;
            }
            case R.id.new_arrival: {
                Intent in = new Intent(this, Stone_List.class);
                bundle.putString(parent, "NARR");
                in.putExtras(bundle);
                startActivity(in);
                break;
            }
            case R.id.revised_price: {
                Intent in = new Intent(this, Stone_List.class);
                bundle.putString(parent, "RPR");
                in.putExtras(bundle);
                startActivity(in);
                break;
            }

        }
    }
}
