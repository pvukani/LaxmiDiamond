package Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dnktechnologies.laxmidiamond.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bean.Model;

/**
 * Created by parth on 7/4/2016.
 */
public class Ldofc_dailog extends Dialog {
    Activity activity;
    Ldofc_Adapter ldofc_adapter;
    public List<Model> Ldofc_list;
    TextView txt_title;
    Button btn_ok,btn_close;
    EditText editText;
    public static String selected_items="";


    public Ldofc_dailog(Activity activity, List<Model> Ldofc_list,EditText editText) {
        super(activity);
        this.activity = activity;
        this.Ldofc_list = Ldofc_list;
        this.editText=editText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.fancy_dialog);
        setCanceledOnTouchOutside(true);

        txt_title= (TextView) findViewById(R.id.txt_popup_title);
        txt_title.setText("Model-List");
        btn_ok= (Button) findViewById(R.id.btn_pop_ok);
        btn_close= (Button) findViewById(R.id.btn_pop_close);
        RecyclerView recyclerView_counrtyList = (RecyclerView) findViewById(R.id.recy_fancy_data);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false);
        recyclerView_counrtyList.setLayoutManager(mLayoutManager);

        ldofc_adapter = new Ldofc_Adapter(this.activity,Ldofc_list);
        recyclerView_counrtyList.setAdapter(ldofc_adapter);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("selected item",""+selected_items);
                dismiss();
                editText.setText(selected_items.replaceFirst(",",""));
                editText.setSingleLine(true);
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Display display = this.activity.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        android.view.WindowManager.LayoutParams lp;
        lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width =(int) (width - (width * 0.75));

        lp.height = (int) (height - (height * 0.35));
        getWindow().setAttributes(lp);
    }

    class Ldofc_Adapter extends RecyclerView.Adapter<Ldofc_Adapter.MyViewHolder> {

        List<Model> Ldofc_list;
        Activity activity;
        Map<Integer,Boolean> check_state=new HashMap<Integer,Boolean>();

        public Ldofc_Adapter(Activity activity,List<Model> Ldofc_list) {
            this.activity=activity;
            this.Ldofc_list = Ldofc_list;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fancy_dialog, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            final int pos = position;
            holder.Country_name.setText(Ldofc_list.get(pos).getCountry_name());
            holder.lout_fancy_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.checkbox_fancy.isChecked())
                    {
                        selected_items="";
                        check_state.put(pos,false);
                        for (int i = 0; i <Ldofc_list.size() ; i++) {
                            if(check_state.containsKey(i) && check_state.get(i))
                            {
                                selected_items+=","+Ldofc_list.get(i).getCountry_name();
                            }
                        }
                        holder.checkbox_fancy.setChecked(false);
                    }
                    else {
                        selected_items="";
                        check_state.put(pos,true);
                        for (int i = 0; i <Ldofc_list.size() ; i++) {
                            if(check_state.containsKey(i) && check_state.get(i))
                            {
                                selected_items+=","+Ldofc_list.get(i).getCountry_name();

                            }
                        }
                        holder.checkbox_fancy.setChecked(true);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return Ldofc_list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView Country_name;
            LinearLayout lout_fancy_data;
            CheckBox checkbox_fancy;

            public MyViewHolder(View itemView) {
                super(itemView);
                this.Country_name = (TextView) itemView.findViewById(R.id.txt_fancy_item);
                this.lout_fancy_data= (LinearLayout) itemView.findViewById(R.id.lout_fancy_data);
                this.checkbox_fancy= (CheckBox) itemView.findViewById(R.id.checkbox_fancy);

            }
        }
    }
}
