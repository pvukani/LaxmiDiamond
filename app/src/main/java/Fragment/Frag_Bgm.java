package Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dnktechnologies.laxmidiamond.GlobalApp;
import com.dnktechnologies.laxmidiamond.R;
import com.dnktechnologies.laxmidiamond.Url_Content;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import Adapter.Adapter_result_stone;
import Bean.Model;
import Custom.ObservableScrollView;
import Dialog.Progress_Dialog;
import Handler.XmlHandler;

/**
 * Created by parth on 8/10/2016.
 */
public class Frag_Bgm extends android.app.Fragment {
    RecyclerView rv_bgmStone;
    String link;
    Adapter_result_stone ad_bgm_stone;
    View view;
//    HashMap<Integer,Boolean> MAP_Res_Stone;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_stonelist, container, false);
//        MAP_Res_Stone=new HashMap<Integer,Boolean>();
        FindViewByID();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_bgmStone.setLayoutManager(layoutManager);
        Url_Content.clear_parameter();
        Url_Content.isfancy = "-1";
        Url_Content.no_bgm = "2";
        link = GlobalApp.obtainLink();
        Log.i("bgm", "" + link);
        Log.i("currrnt", "BGM");
        new GetBgmStone().execute(link);
        return view;
    }

    private void FindViewByID() {
        rv_bgmStone = (RecyclerView) view.findViewById(R.id.rv_stone);
//        header_scroll= (ObservableScrollView) view.findViewById(R.id.header_scroll);
    }

    public class GetBgmStone extends AsyncTask<String, Void, String> {
        String url;
        XmlHandler myXMLHandler;
        Progress_Dialog progress_dialog = new Progress_Dialog(getActivity());

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
            Log.i("Size_bgm", "" + itemsList.size());
            GlobalApp.Arr_Bgm_stone.clear();
            GlobalApp.Arr_Bgm_stone.addAll(itemsList);
            GlobalApp.Arr_for_grid.clear();
            GlobalApp.Arr_for_grid.addAll(GlobalApp.Arr_Bgm_stone);
            GlobalApp.map_result.clear();
            ad_bgm_stone = new Adapter_result_stone(getActivity(), GlobalApp.Arr_Bgm_stone,GlobalApp.map_result);
            rv_bgmStone.setAdapter(ad_bgm_stone);
            progress_dialog.dismiss();

        }
    }
}
