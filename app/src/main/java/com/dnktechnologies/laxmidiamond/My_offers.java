package com.dnktechnologies.laxmidiamond;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import Adapter.Adapter_offerPage;
import Adapter.Adapter_result_stone;
import Bean.Model;
import Handler.XmlHandler;

public class My_offers extends AppCompatActivity implements View.OnClickListener {
    Button btn_home, btn_logout, btn_search, btn_clear;
    EditText edit_ofr_no, edit_ofrDate_f, edit_ofrDate_t, edit_ttlStone_f, edit_ttlStone_t,
            edit_ttlCarat_f, edit_ttlCarat_t, edit_ttlamt_f, edit_ttlamt_t;
    ImageButton left_arrow_btn, right_arrow_btn;
    TextView txt_ttl_ofr, total_no_page_txt;
    String user_id;
    String curent_page_no, total_page;
    String ttl_offer;
    RecyclerView rv_my_ofr;
    Adapter_offerPage adapter_offerPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_offers);
        user_id = GlobalApp.User_Id;
        FindViewById();

        new GetOffer().execute(GlobalApp.url + "GetOffer?PageNo=1" + "&OfferNoList=" + "&OfferFromDate=" + "&OfferToDate=" + "&FromTotalStone=" +
                "&ToTotalStone=" + "&FromTotalCarat=" + "&ToTotalCarat=" + "&FromTotalAmount=" + "&ToTotalAmount=" +
                "&Source=" + "&UserID=" + GlobalApp.User_Id);
        rv_my_ofr = (RecyclerView) findViewById(R.id.rv_my_ofr);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rv_my_ofr.setLayoutManager(layoutManager);
    }

    private void FindViewById() {
        btn_home = (Button) findViewById(R.id.btn_home);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        txt_ttl_ofr = (TextView) findViewById(R.id.txt_ttl_ofr);
        left_arrow_btn = (ImageButton) findViewById(R.id.left_arrow_btn);
        right_arrow_btn = (ImageButton) findViewById(R.id.right_arrow_btn);
        total_no_page_txt = (TextView) findViewById(R.id.total_no_page_txt);
        edit_ofr_no = (EditText) findViewById(R.id.edit_ofr_no);
        edit_ofrDate_f = (EditText) findViewById(R.id.edit_ofrDate_f);
        edit_ofrDate_t = (EditText) findViewById(R.id.edit_ofrDate_t);
        edit_ttlStone_f = (EditText) findViewById(R.id.edit_ttlStone_f);
        edit_ttlStone_t = (EditText) findViewById(R.id.edit_ttlStone_t);
        edit_ttlCarat_f = (EditText) findViewById(R.id.edit_ttlCarat_f);
        edit_ttlCarat_t = (EditText) findViewById(R.id.edit_ttlCarat_t);
        edit_ttlamt_f = (EditText) findViewById(R.id.edit_ttlamt_f);
        edit_ttlamt_t = (EditText) findViewById(R.id.edit_ttlamt_t);

        btn_home.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        left_arrow_btn.setOnClickListener(this);
        right_arrow_btn.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home: {
                Intent in = new Intent(this, activity_desboard.class);
                startActivity(in);
                break;
            }
            case R.id.btn_logout: {
                new Dialog.Logout_Dialog(this);
                break;
            }
            case R.id.left_arrow_btn: {
                break;
            }
            case R.id.right_arrow_btn: {
                break;
            }
            case R.id.btn_search: {
                break;
            }
            case R.id.btn_clear: {
                doClear();
                break;
            }
        }
    }

    private void doClear() {
        edit_ofr_no.setText("");
        edit_ofrDate_f.setText("");
        edit_ofrDate_t.setText("");
        edit_ttlStone_f.setText("");
        edit_ttlStone_t.setText("");
        edit_ttlCarat_f.setText("");
        edit_ttlCarat_t.setText("");
        edit_ttlamt_f.setText("");
        edit_ttlamt_t.setText("");

    }

    class GetOffer extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;
        String url;
        XmlHandler myXMLHandler;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(My_offers.this);
            pDialog.setTitle(GlobalApp.title);
            pDialog.setMessage("Please Wait");
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    GetOffer.this.cancel(true);
                }
            });
            pDialog.show();
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
            GlobalApp.ArrList_offer.clear();
            GlobalApp.ArrList_offer.addAll(itemsList);
            adapter_offerPage = new Adapter_offerPage(GlobalApp.ArrList_offer);
            rv_my_ofr.setAdapter(adapter_offerPage);
//            curent_page_no=GlobalApp.ArrList_offer.get(0).getPageno();
            total_page = GlobalApp.ArrList_offer.get(0).getTotalpages();
            txt_ttl_ofr.setText(GlobalApp.ArrList_offer.get(0).getTotalrecord());
            total_no_page_txt.setText("of " + GlobalApp.ArrList_offer.get(0).getTotalpages());
            Log.d("itemsList", "" + itemsList.size());
            pDialog.dismiss();
        }

    }

}
