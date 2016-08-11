package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dnktechnologies.laxmidiamond.GlobalApp;
import com.dnktechnologies.laxmidiamond.R;
import com.dnktechnologies.laxmidiamond.Url_Content;

import Adapter.Adapter_result_stone;

/**
 * Created by parth on 8/10/2016.
 */
public class Frag_Result_stone extends android.app.Fragment {
    RecyclerView rv_result_stone;
    Adapter_result_stone adapter_result_stone;
    String link;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_stonelist, container, false);
//        FindViewByID();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_result_stone.setLayoutManager(layoutManager);

        link = GlobalApp.obtainLink();
        Log.i("bgm", "" + link);
//        new GetBgmStone().execute(link);
        return view;
    }
}
