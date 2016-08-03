package com.dnktechnologies.laxmidiamond;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class My_cart extends AppCompatActivity implements View.OnClickListener {
    Button btn_home,btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_cart);
        btn_home= (Button) findViewById(R.id.btn_home);
        btn_logout= (Button) findViewById(R.id.btn_logout);
        btn_home.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.btn_home:
            {
                Intent in=new Intent(this,activity_desboard.class);
                startActivity(in);
                break;
            }
            case R.id.btn_logout:
            {
                new Dialog.Logout_Dialog(this);
                break;
            }
        }
    }
}
