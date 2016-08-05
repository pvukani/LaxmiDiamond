package com.dnktechnologies.laxmidiamond;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TableRow;
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

import Adapter.Adapter_result_stone;
import Bean.Model;
import Dialog.Progress_Dialog;
import Handler.XmlHandler;

/**
 * Created by parth on 8/4/2016.
 */
public class Stone_Detail_page extends Activity {
    LinearLayout lout_stone_detail, lout_qc_detail, lout_param_detail;
    String[] stone_detail_Key, QC_detail, Parameter_detail, Comment;
    int width,height;
    String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stone_detail);

        lout_stone_detail = (LinearLayout) findViewById(R.id.lout_stone_detail);
        lout_qc_detail = (LinearLayout) findViewById(R.id.lout_qc_detail);
        lout_param_detail = (LinearLayout) findViewById(R.id.lout_param_detail);

        stone_detail_Key = getResources().getStringArray(R.array.d_detail_Key);
        QC_detail = getResources().getStringArray(R.array.QC_detail);
        Parameter_detail = getResources().getStringArray(R.array.Parameter_detail);
        Comment = getResources().getStringArray(R.array.Comment);

        link=GlobalApp.url+"GetSingleStoneDetail?StoneNo="+GlobalApp.stone_id+"&UserID="+GlobalApp.User_Id;
//        Log.i("link", "" + link);
        new GetStoneDetail().execute(link);

//

    }
    public void MakeView(int i,LinearLayout linearLayout,String[] stone_detail_Key) {


        LinearLayout child = new LinearLayout(Stone_Detail_page.this);
        LinearLayout.LayoutParams Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,30);
        Params.setMargins(5, 5, 5, 5);
        child.setLayoutParams(Params);
        child.setBackgroundColor(Color.parseColor("#c8e6ff"));

        LinearLayout.LayoutParams param_txt = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        TextView text1 = new TextView(Stone_Detail_page.this);
        text1.setLayoutParams(param_txt);
        text1.setText(GlobalApp.List_stone_detail_val.get(i));
        text1.setTextColor(Color.BLACK);

        TextView text2 = new TextView(Stone_Detail_page.this);
        text2.setText(stone_detail_Key[i]);
        text2.setTextSize(20);
        text2.setTypeface(Typeface.DEFAULT_BOLD);
        text2.setTextColor(Color.BLACK);
        text2.setLayoutParams(param_txt);

        child.addView(text1, 0);
        child.addView(text2, 0);

        linearLayout.addView(child);
    }
    public class GetStoneDetail extends AsyncTask<String,Void,String>
    {
        String url;
        XmlHandler myXMLHandler;
        Progress_Dialog progress_dialog = new Progress_Dialog(Stone_Detail_page.this);

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

            GlobalApp.List_stone_detail_val.clear();
            prepare_detail_val(itemsList);
            Log.i("my size", "" + GlobalApp.List_stone_detail_val.size());
            Log.i("16", "" + GlobalApp.List_stone_detail_val.get(15));

          progress_dialog.dismiss();

        }
        private void prepare_detail_val(ArrayList<Model> itemList) {
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getStone_no());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getLaboratory());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getLabreportno() );
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getLaserinscription());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getShape());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getWeightincarats());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getColor());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getClarity());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getCut());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getPolish());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getSymmetry());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getFluorescenceintensity());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getLiveraparate());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getLddiscount());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getLdrate());
            GlobalApp.List_stone_detail_val.add(itemList.get(0).getLdamount());


            for (int i = 0; i < stone_detail_Key.length; i++) {

                MakeView(i,lout_stone_detail,stone_detail_Key);
            }
            for (int i = 0; i < QC_detail.length; i++) {
              MakeView(i,lout_qc_detail,QC_detail);
        }
             for (int i = 0; i < Parameter_detail.length; i++) {
            MakeView(i, lout_param_detail, Parameter_detail);
          }

        }
    }



}
