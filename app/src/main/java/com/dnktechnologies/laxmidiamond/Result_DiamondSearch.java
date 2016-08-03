package com.dnktechnologies.laxmidiamond;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

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
import Dialog.OK_Dialog;
import Dialog.Progress_Dialog;
import Handler.XmlHandler;

public class Result_DiamondSearch extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rv_result_stone;
    Adapter_result_stone adapter_result_stone;
    Button btn_home, btn_logout;
    String link;
    int pageNum = 1;

//    HorizontalScrollView header_scroll, scroll_row;
//    HorizontalScrollView scrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_searchdiamond);

        btn_home = (Button) findViewById(R.id.btn_home);
        btn_logout = (Button) findViewById(R.id.btn_logout);

        rv_result_stone = (RecyclerView) findViewById(R.id.rv_result_stone);

//        header_scroll = (HorizontalScrollView) findViewById(R.id.scroll_header);
//        scroll_row = (HorizontalScrollView) findViewById(R.id.scroll_row);


//        scrollview=  (HorizontalScrollView) findViewById(R.id.scrollview);
        obtainLink();
        Log.i("url", "" + link);
        if (GlobalApp.CheckInternetConnection(this)) {

            new GetResultStone().execute(link);
        }
        btn_home.setOnClickListener(this);
        btn_logout.setOnClickListener(this);

        /*scrollview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });*/
    }

    private void obtainLink() {
        link = GlobalApp.url + "GetSingleStoneSearch?"
                + "" + "PageNo=" + String.valueOf(pageNum)
                + "&ShapeIDList=" + Url_Content.shap + "&LabIDList="
                + Url_Content.lap + "&ColorIDList=" + Url_Content.color
                + "&ClarityIDList=" + Url_Content.clarity + "&CaratList="
                + Url_Content.carat_frm + "&CutIDList=" + Url_Content.cut
                + "&PolishIDList=" + Url_Content.polish
                + "&SymmetryIDList=" + Url_Content.sym + "&IsFancyColor="
                + Url_Content.isfancy + "&FancyColorIDList="
                + Url_Content.fancy_clr + "&FCIntensityIDList="
                + Url_Content.fancy_intcity + "&FCOvertoneIDList="
                + Url_Content.fancy_overtone + "&FluorIntensityIDList="
                + Url_Content.f_inten + "&FluorColorIDList="
                + Url_Content.f_color + "&StoneNoList="
                + Url_Content.ston_id + "&CertificateNoList="
                + Url_Content.cert_id + "&RapnetLotNoList="
                + Url_Content.rap_lot_no + "&CountryIDList="
                + Url_Content.Country_id_list + "&PricePerCtsFrom="
                + Url_Content.pricrt_from + "&PricePerCtsTo="
                + Url_Content.pricrt_to + "&AmountFrom="
                + Url_Content.tootal_amt_f + "&AmountTo="
                + Url_Content.total_amt_t + "&DiscountFrom="
                + Url_Content.discount_f + "&DiscountTo="
                + Url_Content.discount_t + "&DiameterFrom="
                + Url_Content.dia_frm + "&DiameterTo=" + Url_Content.dia_to
                + "&TotalDepthPerFrom=" + Url_Content.total_dp_frm
                + "&TotalDepthPerTo=" + Url_Content.total_dp_to
                + "&TablePerFrom=" + Url_Content.table_frm + "&TablePerTo="
                + Url_Content.table_to + "&CrownHeightFrom="
                + Url_Content.crown_h_frm + "&CrownHeightTo="
                + Url_Content.crown_h_to + "&CrownAngleFrom="
                + Url_Content.crown_a_frm + "&CrownAngleTo="
                + Url_Content.crown_a_to + "&PavilionHeightFrom="
                + Url_Content.pav_h_frm + "&PavilionHeightTo="
                + Url_Content.pav_h_to + "&PavilionAngleFrom="
                + Url_Content.pav_a_frm + "&PavilionAngleTo="
                + Url_Content.pav_a_to + "&GirdlePerFrom="
                + Url_Content.gridle_frm + "&GirdlePerTo="
                + Url_Content.gridle_to + "&GirdleIDList="
                + Url_Content.gridle_s + "&RatioFrom="
                + Url_Content.ratio_frm + "&RatioTo="
                + Url_Content.ratio_to + "&TingeIDList="
                + Url_Content.tinge_s + "&TableInclusionIDList="
                + Url_Content.table_inc_s + "&SideInclusionIDList="
                + Url_Content.side_inc_s + "&OpenInclusionIDList="
                + Url_Content.open_inc_s + "&ExtrafacetIDList="
                + Url_Content.extra_facet_s + "&NaturalIDList="
                + Url_Content.natural_s + "&IndentedNaturalIDList="
                + Url_Content.indented_natural_s + "&MilkyIDList="
                + Url_Content.milky_frm + "&LusterIDList="
                + Url_Content.luster_frm + "&HandAIDList="
                + Url_Content.hrt_arw_frm + "&CuletIDList="
                + Url_Content.culet_frm + "&GrainingIDList="
                + Url_Content.graining_s + "&EyeCleanIDList="
                + Url_Content.eye_c_s + "&CavityIDList="
                + Url_Content.cravity_s + "&BrokeragePer=" + ""
                + "&CommissionPer=" + "" + "&TradeShowID="
                + Url_Content.TradeShowID + "&NoBGM=" + Url_Content.no_bgm
                + "&SpecialDeal=" + Url_Content.SpecialDeal
                + "&FreshStock=" + Url_Content.FreshStock
                + "&RevisedPrice=" + Url_Content.RevisedPrice
                + "&FullStock=" + Url_Content.FullStock + "&Source="
                + "ANDROID" + "&UserID=" + GlobalApp.User_Id;
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
                new Logout_Dialog(this);
                break;
            }
        }
    }

    class GetResultStone extends AsyncTask<String, Void, String> {

        String url;
        XmlHandler myXMLHandler;
        Progress_Dialog progress_dialog = new Progress_Dialog(Result_DiamondSearch.this);

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
            GlobalApp.Arr_Result_stone.clear();
            GlobalApp.Arr_Result_stone.addAll(itemsList);
            if (null != GlobalApp.Arr_Result_stone.get(0).getErrormessage()) {
                new OK_Dialog(Result_DiamondSearch.this, GlobalApp.msg_noResult);
                progress_dialog.dismiss();
            } else {
                adapter_result_stone = new Adapter_result_stone(GlobalApp.Arr_Result_stone);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                rv_result_stone.setLayoutManager(layoutManager);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Result_DiamondSearch.this);
//                linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
//                linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
//                rv_result_stone.setLayoutManager(layoutManager);
                rv_result_stone.setAdapter(adapter_result_stone);
                progress_dialog.dismiss();
            }
//            Log.i("Arr_Result_stone", "" + GlobalApp.Arr_Result_stone.size());


        }

    }

}
