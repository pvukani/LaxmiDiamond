package Adapter;

/**
 * Created by parth on 7/1/2016.
 */

import android.content.res.TypedArray;
import android.graphics.Color;
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

public class adapter_shape extends RecyclerView.Adapter<adapter_shape.shape_adapter> {
    String arr_shape_name[];
    TypedArray tarr_img_shape, tarr_select_shape;
    public String select_item = "";
    // String selected_items = "";
    public Map<Integer, Boolean> check_state;


    public adapter_shape(String arr_shape_name[], TypedArray tarr_img_shape, TypedArray tarr_select_shape, HashMap<Integer, Boolean> check_state) {
        this.arr_shape_name = arr_shape_name;
        this.tarr_img_shape = tarr_img_shape;
        this.tarr_select_shape = tarr_select_shape;
        this.check_state = check_state;

    }

    @Override
    public adapter_shape.shape_adapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.column_shape, parent, false);
        return new shape_adapter(itemView);


    }

    @Override
    public void onBindViewHolder(final adapter_shape.shape_adapter holder, int position) {

        final int pos = position;

        holder.txt_shape_name.setText(arr_shape_name[pos]);
        holder.img_shape.setImageDrawable(tarr_img_shape.getDrawable(pos));

        if (check_state.size() != 0) {

            if (check_state.containsKey(pos) && check_state.get(pos)) {
                holder.img_shape.setImageDrawable(tarr_select_shape.getDrawable(pos));
                holder.lout_col_shape.setSelected(true);
                check_state.put(pos, true);
            } else {
                check_state.put(pos, false);
                holder.lout_col_shape.setSelected(false);
                holder.img_shape.setImageDrawable(tarr_img_shape.getDrawable(pos));
            }
        }
        holder.lout_col_shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout view = (LinearLayout) v;
                // ImageView iv = (ImageView) v.findViewById(R.id.image_shape);
                if (view.isSelected()) {
                    view.setSelected(false);
                    check_state.put(pos, false);

                } else {
                    check_state.put(pos, true);
                    view.setSelected(true);

                }
                notifyDataSetChanged();
            }
        });

        StringBuffer selected_item_buffer = new StringBuffer();

        for (int i = 0; i < arr_shape_name.length; i++) {

            if (check_state.containsKey(i) && check_state.get(i)) {
                selected_item_buffer.append(","
                        + arr_shape_name[i]);
            }
        }
        if (selected_item_buffer.length() == 0) {
            select_item = "";

        } else {
            select_item = removeFirstChar(selected_item_buffer.toString());
        }

    }

    public String removeFirstChar(String s) {
        return s.substring(1);
    }

    @Override
    public int getItemCount() {
        return arr_shape_name.length;
    }

    public class shape_adapter extends RecyclerView.ViewHolder {
        public LinearLayout lout_col_shape;
        TextView txt_shape_name;
        ImageView img_shape;

        public shape_adapter(View itemView) {
            super(itemView);

            this.lout_col_shape = (LinearLayout) itemView.findViewById(R.id.lout_shape);
            this.txt_shape_name = (TextView) itemView.findViewById(R.id.shape_name);
            this.img_shape = (ImageView) itemView.findViewById(R.id.image_shape);
        }


    }

}
