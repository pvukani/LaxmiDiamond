package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dnktechnologies.laxmidiamond.R;

import java.util.List;

import Bean.Model;

/**
 * Created by parth on 7/16/2016.
 */
public class Adapter_offerPage extends RecyclerView.Adapter<Adapter_offerPage.Holder> {
    List<Model> arrList_offer;

    public Adapter_offerPage(List<Model> arrList_offer) {
        this.arrList_offer = arrList_offer;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_offer_page,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Adapter_offerPage.Holder holder, int position) {
        final int pos=position;
        holder.txt_ofr_no.setText(arrList_offer.get(pos).getOfferno());
        holder.txt_ofr_date.setText(arrList_offer.get(pos).getOfferdate());
        holder.txt_ttl_stone.setText(arrList_offer.get(pos).getTotalstone());
        holder.txt_ttl_carat.setText(arrList_offer.get(pos).getTotalcarat());
        holder.txt_avg_disc.setText(arrList_offer.get(pos).getOfferavgdiscount());
        holder.txt_avg_ctc.setText(arrList_offer.get(pos).getOfferavgrate());
        holder.txt_ttl_usd.setText(arrList_offer.get(pos).getOffertotalamount());
        holder.txt_status.setText(arrList_offer.get(pos).getOfferstatus());
    }

    @Override
    public int getItemCount() {
        return arrList_offer.size();
    }

    public class Holder extends RecyclerView.ViewHolder
    {
        TextView txt_ofr_no,txt_ofr_date,txt_ttl_stone,txt_ttl_carat,txt_avg_disc,txt_avg_ctc,txt_ttl_usd,txt_status;
        public Holder(View itemView)
        {
            super(itemView);
            txt_ofr_no= (TextView) itemView.findViewById(R.id.txt_ofr_no);
            txt_ofr_date= (TextView) itemView.findViewById(R.id.txt_ofr_date);
            txt_ttl_stone= (TextView) itemView.findViewById(R.id.txt_ttl_stone);
            txt_ttl_carat= (TextView) itemView.findViewById(R.id.txt_ttl_carat);
            txt_avg_disc= (TextView) itemView.findViewById(R.id.txt_avg_disc);
            txt_avg_ctc= (TextView) itemView.findViewById(R.id.txt_avg_ctc);
            txt_ttl_usd= (TextView) itemView.findViewById(R.id.txt_ttl_usd);
            txt_status= (TextView) itemView.findViewById(R.id.txt_status);
        }
    }
}
