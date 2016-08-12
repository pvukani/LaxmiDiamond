package Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import Adapter.Adapter_result_stone;
import Bean.Model;
import Dialog.OK_Dialog;
import Dialog.Progress_Dialog;
import Handler.XmlHandler;

/**
 * Created by parth on 8/10/2016.
 */
public class Frag_Result_stone extends android.app.Fragment{
    RecyclerView rv_result_stone;
    Adapter_result_stone adapter_result_stone;
    String link;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_stonelist, container, false);
        FindViewByID();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_result_stone.setLayoutManager(layoutManager);

        link = GlobalApp.obtainLink();
//        Log.i("Result stone", "" + link);
        new GetResultStone().execute(link);
        return view;
    }

    private void FindViewByID() {
        rv_result_stone = (RecyclerView) view.findViewById(R.id.rv_stone);
    }

    class GetResultStone extends AsyncTask<String, Void, String> {

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
            GlobalApp.Arr_Result_stone.clear();
            GlobalApp.Arr_Result_stone.addAll(itemsList);
            GlobalApp.Arr_for_grid.clear();
            GlobalApp.Arr_for_grid.addAll(GlobalApp.Arr_Result_stone);
            if (null != GlobalApp.Arr_Result_stone.get(0).getErrormessage()) {
                new OK_Dialog(getActivity(), GlobalApp.msg_noResult);
                progress_dialog.dismiss();
            } else {
                adapter_result_stone = new Adapter_result_stone(getActivity(), GlobalApp.Arr_Result_stone);
                rv_result_stone.setAdapter(adapter_result_stone);
                progress_dialog.dismiss();
            }
//            Log.i("Arr_Result_stone", "" + GlobalApp.Arr_Result_stone.size());


        }

    }
}
