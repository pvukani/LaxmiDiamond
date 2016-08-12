package Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dnktechnologies.laxmidiamond.GlobalApp;
import com.dnktechnologies.laxmidiamond.R;
import com.dnktechnologies.laxmidiamond.Stone_Detail_page;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Bean.Model;

/**
 * Created by parth on 8/11/2016.
 */
public class Adapter_grid extends RecyclerView.Adapter<Adapter_grid.Holder> {
    List<Model> arr_for_grid = new ArrayList<Model>();
    Activity activity;

    public Adapter_grid(Activity activity, List<Model> arr_for_grid) {
        this.arr_for_grid = arr_for_grid;
        this.activity = activity;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_forgrid, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        final int pos = position;
        holder.txt_color.setText(arr_for_grid.get(pos).getColor().toString());
        holder.txt_shap.setText(arr_for_grid.get(pos).getShape().toString());
        holder.txt_carat.setText(arr_for_grid.get(pos).getWeightincarats());
        if (Boolean.valueOf(arr_for_grid.get(pos).getImageexist().toString())) {
            Picasso.with(activity).load(arr_for_grid.get(pos).getImageurl()).into(holder.img_stone);
        } else {
            holder.img_stone.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.imagenotfound));
        }
        holder.txt_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalApp.stone_id = arr_for_grid.get(pos).getStone_no().toString();
                activity.startActivity(new Intent(activity, Stone_Detail_page.class));

            }
        });
        holder.lout_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.lout_root.isSelected())
                {
                    holder.lout_root.setBackground(ContextCompat.getDrawable(activity,R.drawable.border_line));
                    holder.cb_stone.setChecked(false);
                    holder.lout_root.setSelected(false);
                }
                else
                {
                    holder.lout_root.setBackgroundColor(ContextCompat.getColor(activity,R.color.my));
                    holder.cb_stone.setChecked(true);
                    holder.lout_root.setSelected(true);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return arr_for_grid.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txt_color, txt_shap, txt_carat, txt_detail;
        ImageView img_stone;
        LinearLayout lout_root;
        CheckBox cb_stone;
        public Holder(View itemView) {
            super(itemView);
            txt_color = (TextView) itemView.findViewById(R.id.txt_color);
            txt_shap = (TextView) itemView.findViewById(R.id.txt_shap);
            txt_carat = (TextView) itemView.findViewById(R.id.txt_carat);
            txt_detail = (TextView) itemView.findViewById(R.id.txt_detail);
            img_stone = (ImageView) itemView.findViewById(R.id.img_stone);

            lout_root= (LinearLayout) itemView.findViewById(R.id.lout_root);
            cb_stone= (CheckBox) itemView.findViewById(R.id.cb_stone);
        }
    }
}
