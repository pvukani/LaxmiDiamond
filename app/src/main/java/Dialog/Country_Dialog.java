package Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dnktechnologies.laxmidiamond.GlobalApp;
import com.dnktechnologies.laxmidiamond.R;

import java.util.List;

import Bean.Model;

/**
 * Created by parth on 6/24/2016.
 */
public class Country_Dialog extends Dialog {

    public Activity act;
    public static String CountryPos;
//    String c_name;
    EditText reg_editText_country;


    Country_Adapter country_adapter;

    public Country_Dialog(Activity act, EditText reg_editText_country) {
        super(act);
        this.act = act;
        this.reg_editText_country = reg_editText_country;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.countrylist_dialog);
        setCanceledOnTouchOutside(true);
        RecyclerView recyclerView_counrtyList = (RecyclerView) findViewById(R.id.recycler_view_countryList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(act, LinearLayoutManager.VERTICAL, false);
        recyclerView_counrtyList.setLayoutManager(mLayoutManager);

        country_adapter = new Country_Adapter(GlobalApp.arr_model_list);
        recyclerView_counrtyList.setAdapter(country_adapter);
        Display display = act.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        android.view.WindowManager.LayoutParams lp;
        lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = (int) (width - (width * 0.70));
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);



    }

    class Country_Adapter extends RecyclerView.Adapter<Country_Adapter.MyViewHolder> {
        private List<Model> model_List;

        public Country_Adapter(List<Model> model_List) {
            this.model_List = model_List;
        }



        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_country_list, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            final int pos = position;
            holder.which_list.setText("Model List");
//            CountryPos=model_List.get(pos).getCountry_id();
//            c_name=model_List.get(pos).getCountry_name();
            holder.Country_name.setText(model_List.get(pos).getCountry_name());
            holder.lout_country_raw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    reg_editText_country.setText(model_List.get(pos).getCountry_name());
                    dismiss();
//                    Log.d("Select", " " + model_List.get(pos).getCountry_id() + " " + model_List.get(pos).getCountry_name());
                    CountryPos= model_List.get(pos).getCountry_id();
                    Log.d("Select", " "+CountryPos);
                }
            });


        }

        @Override
        public int getItemCount() {
            return model_List.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout lout_country_raw;
            public TextView Country_name;
            public TextView which_list;
            public MyViewHolder(View itemView) {
                super(itemView);

                this.lout_country_raw = (LinearLayout) itemView.findViewById(R.id.lout_country_raw);
                this.which_list= (TextView) findViewById(R.id.which_list);
                Country_name = (TextView) itemView.findViewById(R.id.txt_row_countryList);
                //txt_Country_name = (TextView) itemView.findViewById(R.id.edittext_register_country);

            }
        }

    }
}