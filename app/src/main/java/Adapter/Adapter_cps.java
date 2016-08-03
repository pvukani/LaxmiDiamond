package Adapter;

/**
 * Created by parth on 7/1/2016.
 */

import android.content.res.TypedArray;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dnktechnologies.laxmidiamond.GlobalApp;
import com.dnktechnologies.laxmidiamond.R;

import java.util.HashMap;
import java.util.Map;

public class Adapter_cps extends RecyclerView.Adapter<Adapter_cps.cps_adapter> {

    TypedArray tarr_img_shape, tarr_select_shape;
    public String selected_items = "";
    public Map<Integer, Boolean> check_state;
    String[] strings;

    public Adapter_cps(TypedArray tarr_img_shape, TypedArray tarr_select_shape, String[] strings, HashMap<Integer, Boolean> check_state) {

        this.strings = strings;
        this.tarr_img_shape = tarr_img_shape;
        this.tarr_select_shape = tarr_select_shape;
        this.check_state = check_state;
    }

    @Override
    public Adapter_cps.cps_adapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cps, parent, false);
        return new cps_adapter(itemView);
    }

    @Override
    public void onBindViewHolder(final Adapter_cps.cps_adapter holder, final int position) {
        final int pos = position;
        holder.img_shape.setImageDrawable(tarr_img_shape.getDrawable(pos));
        if(check_state.size()!=0)
        {
            if (check_state.containsKey(pos) && check_state.get(pos)) {
                holder.img_shape.setImageDrawable(tarr_select_shape.getDrawable(pos));
                holder.img_shape.setSelected(true);
                check_state.put(pos, true);
            } else {
                holder.img_shape.setImageDrawable(tarr_img_shape.getDrawable(pos));
                holder.img_shape.setSelected(false);
                check_state.put(pos, false);
            }
        }

        holder.img_shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView iv = (ImageView) v;
                if (iv.isSelected()) {
                    iv.setSelected(false);
                    check_state.put(pos, false);
                } else {
                    iv.setSelected(true);
                    check_state.put(pos, true);
                }
                notifyDataSetChanged();
            }
        });

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (check_state.containsKey(i) && check_state.get(i)) {
                builder.append("," + strings[i]);
            }
        }
        selected_items = builder.length() != 0 ? builder.toString().substring(1) : "";
    }

    @Override
    public int getItemCount() {
        return tarr_img_shape.length();
    }

    public class cps_adapter extends RecyclerView.ViewHolder {
        ImageView img_shape;

        public cps_adapter(View itemView) {
            super(itemView);
            this.img_shape = (ImageView) itemView.findViewById(R.id.img_cps);
        }


    }
}
