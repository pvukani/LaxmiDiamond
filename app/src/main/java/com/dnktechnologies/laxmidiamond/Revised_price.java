package com.dnktechnologies.laxmidiamond;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import Adapter.Adapter_result_stone;
import Bean.Model;
import Dialog.Logout_Dialog;
import Dialog.Progress_Dialog;
import Handler.XmlHandler;

/**
 * Created by parth on 8/1/2016.
 */
public class Revised_price extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rv_revisedPR;
    String link;
    Button btn_home, btn_logout;
    Adapter_result_stone ad_revised_stone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_stone);
        rv_revisedPR= (RecyclerView) findViewById(R.id.rv_stone);

        btn_home = (Button) findViewById(R.id.btn_home);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_home.setOnClickListener(this);
        btn_logout.setOnClickListener(this);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rv_revisedPR.setLayoutManager(layoutManager);
        Url_Content.clear_parameter();
        Url_Content.isfancy="-1";
        Url_Content.RevisedPrice="1";
        link=GlobalApp.obtainLink();
//        Log.i("link_rpr",""+link);
        new GetRevisedPr().execute(link);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home: {
                Intent in = new Intent(Revised_price.this, activity_desboard.class);
                startActivity(in);
                break;
            }
            case R.id.btn_logout: {
                new Logout_Dialog(this);
                break;
            }
        }
    }

    public class GetRevisedPr extends AsyncTask<String,Void,String>
    {
        String url;
        XmlHandler myXMLHandler;
        Progress_Dialog progress_dialog = new Progress_Dialog(Revised_price.this);

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
            ArrayList<Model> itemsList = myXMLHandler.getItemsList();
//            Log.i("Size_revisedPr",""+itemsList.size());
            GlobalApp.Arr_revised_stone.clear();
            GlobalApp.Arr_revised_stone.addAll(itemsList);
            ad_revised_stone=new Adapter_result_stone(Revised_price.this,GlobalApp.Arr_revised_stone);
            rv_revisedPR.setAdapter(ad_revised_stone);
            progress_dialog.dismiss();
        }
    }
}
