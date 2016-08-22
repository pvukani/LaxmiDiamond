package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import com.dnktechnologies.laxmidiamond.R;


/**
 * Created by parth on 8/16/2016.
 */
public class Frag_Cart extends android.app.Fragment {

    RecyclerView rv_cart;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_stonelist,container,false);
        FindViewById();
        return view;
    }

    private void FindViewById() {
        rv_cart= (RecyclerView) view.findViewById(R.id.rv_stone);
    }
}
