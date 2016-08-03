package com.dnktechnologies.laxmidiamond;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import Bean.Model;
import Dialog.Progress_Dialog;
import Handler.XmlHandler;

public class Quick_search extends AppCompatActivity implements View.OnClickListener {
    Button btn_home, btn_logout;
    RecyclerView rv_result_stone;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_search);

        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_home = (Button) findViewById(R.id.btn_home);

        rv_result_stone = (RecyclerView) findViewById(R.id.rv_result_stone);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rv_result_stone.setLayoutManager(layoutManager);
        link = GlobalApp.url + "QuickSearch" + "?UserID=" + GlobalApp.User_Id;
        new GetQuick().execute(link);

        btn_home.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_home: {
                Intent in = new Intent(this, activity_desboard.class);
                startActivity(in);
                break;
            }
            case R.id.btn_logout: {
                new Dialog.Logout_Dialog(this);
                break;
            }
        }

    }

    public class GetQuick extends AsyncTask<String, Void, String> {
        String url;
        XmlHandler myXMLHandler;
        Progress_Dialog progress_dialog = new Progress_Dialog(Quick_search.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress_dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                url = params[0];
                SAXParserFactory spf = SAXParserFactory.newInstance();
                SAXParser sp = spf.newSAXParser();
                XMLReader xr = sp.getXMLReader();
                myXMLHandler = new XmlHandler();
                xr.setContentHandler(myXMLHandler);
                URL _url = new URL(url);
                xr.parse(new InputSource(_url.openStream()));

            } catch (ParserConfigurationException pce) {
                Log.e("SAX XML", "sax parse error", pce);
            } catch (SAXException se) {
                Log.e("SAX XML", "sax error", se);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<Model> item_quick = myXMLHandler.getItemsList();
            GlobalApp.Ar_List_quick.clear();
            GlobalApp.Ar_List_quick.addAll(item_quick);
            rv_result_stone.setAdapter(new Adapter_quick(GlobalApp.Ar_List_quick));
            progress_dialog.dismiss();
        }
    }

    public class Adapter_quick extends RecyclerView.Adapter<Adapter_quick.Holder> {
        List<Model> list = new ArrayList<Model>();

        public Adapter_quick(List<Model> list) {
            this.list = list;
        }

        @Override
        public Adapter_quick.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quick, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            final int pos=position;

            holder.txt_color.setText(list.get(pos).getColor());
            holder.txt_clarity.setText(list.get(pos).getClarity());
            holder.txt_step1.setText(list.get(pos).getSTEP1());
            holder.txt_step2.setText(list.get(pos).getSTEP2());
            holder.txt_step3.setText(list.get(pos).getSTEP3());
            holder.txt_step4.setText(list.get(pos).getSTEP4());
            holder.txt_step5.setText(list.get(pos).getSTEP5());
            holder.txt_step6.setText(list.get(pos).getSTEP6());
            holder.txt_step7.setText(list.get(pos).getSTEP7());
            holder.txt_step8.setText(list.get(pos).getSTEP8());
            holder.txt_step9.setText(list.get(pos).getSTEP9());
            holder.txt_step10.setText(list.get(pos).getSTEP10());
            holder.txt_step11.setText(list.get(pos).getSTEP11());
            holder.txt_step12.setText(list.get(pos).getSTEP12());
            holder.txt_step13.setText(list.get(pos).getSTEP13());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Holder extends RecyclerView.ViewHolder {

            TextView txt_color, txt_clarity, txt_step1, txt_step2, txt_step3, txt_step4, txt_step5, txt_step6, txt_step7,
                    txt_step8, txt_step9, txt_step10, txt_step11, txt_step12, txt_step13;

            public Holder(View itemView) {
                super(itemView);

                txt_color = (TextView) itemView.findViewById(R.id.txt_color);
                txt_clarity = (TextView) itemView.findViewById(R.id.txt_clarity);
                txt_step1 = (TextView) itemView.findViewById(R.id.txt_step1);
                txt_step2 = (TextView) itemView.findViewById(R.id.txt_step2);
                txt_step3 = (TextView) itemView.findViewById(R.id.txt_step3);
                txt_step4 = (TextView) itemView.findViewById(R.id.txt_step4);
                txt_step5 = (TextView) itemView.findViewById(R.id.txt_step5);
                txt_step6 = (TextView) itemView.findViewById(R.id.txt_step6);
                txt_step7 = (TextView) itemView.findViewById(R.id.txt_step7);
                txt_step8 = (TextView) itemView.findViewById(R.id.txt_step8);
                txt_step9 = (TextView) itemView.findViewById(R.id.txt_step9);
                txt_step10 = (TextView) itemView.findViewById(R.id.txt_step10);
                txt_step11 = (TextView) itemView.findViewById(R.id.txt_step11);
                txt_step12 = (TextView) itemView.findViewById(R.id.txt_step12);
                txt_step13 = (TextView) itemView.findViewById(R.id.txt_step13);

            }
        }

    }

}
