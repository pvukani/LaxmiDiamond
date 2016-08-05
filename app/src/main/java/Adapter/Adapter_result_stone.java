package Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dnktechnologies.laxmidiamond.GlobalApp;
import com.dnktechnologies.laxmidiamond.R;
import com.dnktechnologies.laxmidiamond.Stone_Detail_page;

import java.util.ArrayList;
import java.util.List;

import Bean.Model;

/**
 * Created by parth on 7/12/2016.
 */
public class Adapter_result_stone extends RecyclerView.Adapter<Adapter_result_stone.ViewAdapter> {
    List<Model> result_list;
    Activity activity;

    public Adapter_result_stone(Activity activity,List<Model> result_list)
    {
        this.activity=activity;
        this.result_list=result_list;
    }

    @Override
    public Adapter_result_stone.ViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_result_searchstone, parent, false);
        return new Adapter_result_stone.ViewAdapter(itemView);
    }

    @Override
    public int getItemCount() {
        return this.result_list.size();
    }

    @Override
    public void onBindViewHolder(final Adapter_result_stone.ViewAdapter holder,int position) {
        final int pos=position;
        if((pos+1)%2==0)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#C8E6FF"));
        }
        holder.txt_srno.setText(String.valueOf(pos+1));
        holder.txt_status.setText("AVAILABLE");
        holder.txt_stoneid.setText(result_list.get(pos).getStone_no());
        holder.txt_shape.setText(result_list.get(pos).getShape());
        holder.txt_color.setText(result_list.get(pos).getColor());
        holder.txt_clarity.setText(result_list.get(pos).getClarity());
        holder.txt_weight.setText(result_list.get(pos).getWeightincarats());
        holder.txt_cut.setText(result_list.get(pos).getCut());
        holder.txt_pol.setText(result_list.get(pos).getPolish());
        holder.txt_sym.setText(result_list.get(pos).getSymmetry());
        holder.txt_flo.setText(result_list.get(pos).getFluorescenceintensity());
        holder.txt_lab.setText(result_list.get(pos).getLaboratory());
        holder.txt_rap_price.setText(result_list.get(pos).getLiveraparate());
        holder.txt_Disc.setText(result_list.get(pos).getLddiscount());
        holder.txt_pricects.setText(result_list.get(pos).getLdrate());
        holder.txt_totAmount.setText(result_list.get(pos).getLdamount());
        holder.txt_measurement.setText(result_list.get(pos).getMeasurement());
        holder.txt_table.setText(result_list.get(pos).getTablediameterper());
        holder.txt_depth.setText(result_list.get(pos).getTotaldepthper());
        holder.txt_location.setText(result_list.get(pos).getLocationname());
        holder.txt_HandA.setText(result_list.get(pos).getHanda());
        holder.txt_blck_incl.setText(result_list.get(pos).getBlackinclusion());
        holder.txt_colorshade.setText(result_list.get(pos).getTinge());
        holder.txt_milky.setText(result_list.get(pos).getMilky());
        holder.txt_luster.setText(result_list.get(pos).getLuster());
        holder.txt_eyec.setText(result_list.get(pos).getEyeclean());
        holder.txt_ratio.setText("");
        holder.txt_cr_ang.setText(result_list.get(pos).getCrownangle());
        holder.txt_cr_hgt.setText(result_list.get(pos).getCrownheight());
        holder.txt_pv_ang.setText(result_list.get(pos).getPavillionangle());
        holder.txt_pv_hgt.setText(result_list.get(pos).getPavillionheight());
        holder.txt_key2syml.setText(result_list.get(pos).getKeytosymbols());

        holder.img_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalApp.stone_id=result_list.get(pos).getStone_no().toString();
                Log.i("Value",""+GlobalApp.stone_id);
                activity.startActivity(new Intent(activity,Stone_Detail_page.class));
            }
        });
    }

    public class ViewAdapter extends RecyclerView.ViewHolder {
        TextView txt_srno;
        TextView txt_status,txt_stoneid,txt_shape,txt_color,txt_clarity,txt_weight,txt_cut,txt_pol,txt_sym,txt_flo,
                txt_lab,txt_rap_price,txt_Disc,txt_pricects,txt_totAmount,txt_measurement,txt_table,txt_depth,txt_location,
                txt_HandA,txt_blck_incl,txt_colorshade,txt_milky,txt_luster,txt_eyec,txt_ratio,txt_cr_ang,txt_cr_hgt,txt_pv_ang,
                txt_pv_hgt,txt_key2syml;
        ImageView img_detail;
        public ViewAdapter(View itemView) {
            super(itemView);
            this.img_detail=(ImageView)itemView.findViewById(R.id.img_detail);
            this.txt_srno= (TextView) itemView.findViewById(R.id.txt_srno);
            this.txt_status= (TextView) itemView.findViewById(R.id.txt_status);
            this.txt_stoneid= (TextView) itemView.findViewById(R.id.txt_stoneid);
            this.txt_shape= (TextView) itemView.findViewById(R.id.txt_shape);
            this.txt_color=(TextView) itemView.findViewById(R.id.txt_color);
            this.txt_clarity= (TextView) itemView.findViewById(R.id.txt_clarity);
            this.txt_weight= (TextView) itemView.findViewById(R.id.txt_weight);
            this.txt_cut= (TextView) itemView.findViewById(R.id.txt_cut);
            this.txt_pol= (TextView) itemView.findViewById(R.id.txt_pol);
            this.txt_sym= (TextView) itemView.findViewById(R.id.txt_sym);
            this.txt_flo= (TextView) itemView.findViewById(R.id.txt_flo);
            this.txt_lab= (TextView) itemView.findViewById(R.id.txt_lab);
            this.txt_rap_price= (TextView) itemView.findViewById(R.id.txt_rap_price);
            this.txt_Disc= (TextView) itemView.findViewById(R.id.txt_Disc);
            this.txt_pricects= (TextView) itemView.findViewById(R.id.txt_pricects);
            this.txt_totAmount= (TextView) itemView.findViewById(R.id.txt_totAmount);
            this.txt_measurement=(TextView) itemView.findViewById(R.id.txt_mesurement);
            this.txt_table=(TextView) itemView.findViewById(R.id.txt_table);
            this.txt_depth=(TextView) itemView.findViewById(R.id.txt_depth);
            this.txt_location=(TextView) itemView.findViewById(R.id.txt_location);
            this.txt_HandA=(TextView) itemView.findViewById(R.id.txt_HandA);
            this.txt_blck_incl=(TextView) itemView.findViewById(R.id.txt_black_inc);
            this.txt_colorshade=(TextView) itemView.findViewById(R.id.txt_color_shade);
            this.txt_milky=(TextView) itemView.findViewById(R.id.txt_milky);
            this.txt_luster=(TextView) itemView.findViewById(R.id.txt_luster);
            this.txt_eyec=(TextView) itemView.findViewById(R.id.txt_eyeC);
            this.txt_ratio=(TextView) itemView.findViewById(R.id.txt_ratio);
            this.txt_cr_ang=(TextView) itemView.findViewById(R.id.txt_cr_ang);
            this.txt_cr_hgt=(TextView) itemView.findViewById(R.id.txt_cr_hgt);
            this.txt_pv_ang=(TextView) itemView.findViewById(R.id.txt_pv_angl);
            this.txt_pv_hgt=(TextView) itemView.findViewById(R.id.txt_pv_hgt);
            this.txt_key2syml=(TextView) itemView.findViewById(R.id.txt_key2syml);
        }
    }
}
