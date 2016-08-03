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
public class Trade_Dialog extends Dialog {
    Activity activity;
    Trade_Adapter trade_adapter;
    public List<Model> trade_List;
    TextView txt_title;
    Button btn_ok,btn_close;
    EditText editText;
    public String selected_items="";


    public Trade_Dialog(Activity activity, List<Model> trade_List,EditText editText) {
        super(activity);
        this.activity = activity;
        this.trade_List = trade_List;
        this.editText=editText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.fancy_dialog);
        setCanceledOnTouchOutside(true);

        txt_title = (TextView) findViewById(R.id.txt_popup_title);
        txt_title.setText("TradeShow");
        btn_ok = (Button) findViewById(R.id.btn_pop_ok);
        btn_close= (Button) findViewById(R.id.btn_pop_close);
        RecyclerView recyclerView_tradeList = (RecyclerView) findViewById(R.id.recy_fancy_data);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false);
        recyclerView_tradeList.setLayoutManager(mLayoutManager);

        trade_adapter = new Trade_Adapter(this.activity, trade_List);
        recyclerView_tradeList.setAdapter(trade_adapter);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Log.d("Selected item",""+selected_items);
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
        lp.height = (int) (height - (height * 0.6));
        getWindow().setAttributes(lp);
    }

    class Trade_Adapter extends RecyclerView.Adapter<Trade_Adapter.MyViewHolder> {

        List<Model> trade_list;
        Activity activity;
        Map<Integer,Boolean> check_state=new HashMap<Integer,Boolean>();
        public Trade_Adapter(Activity activity, List<Model> trade_list) {
            this.activity = activity;
            this.trade_list = trade_list;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fancy_dialog, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            final int pos = position;
            holder.trade_name.setText(trade_list.get(pos).getTitle());
            holder.lout_fancy_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.checkbox_fancy.isChecked())
                    {
                        selected_items="";
                        check_state.put(pos,false);
                        for (int i = 0; i <trade_list.size() ; i++) {
                            if(check_state.containsKey(i) && check_state.get(i))
                            {
                                selected_items+=","+trade_list.get(i).getTitle();
                            }
                        }
                        holder.checkbox_fancy.setChecked(false);
                    }
                    else {
                        selected_items="";
                        check_state.put(pos,true);
                        for (int i = 0; i <trade_list.size() ; i++) {
                            if(check_state.containsKey(i) && check_state.get(i))
                            {
                                selected_items+=","+trade_list.get(i).getTitle();

                            }
                        }
                        holder.checkbox_fancy.setChecked(true);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return trade_list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView trade_name;
            LinearLayout lout_fancy_data;
            CheckBox checkbox_fancy;

            public MyViewHolder(View itemView) {
                super(itemView);
                this.trade_name = (TextView) itemView.findViewById(R.id.txt_fancy_item);
                this.lout_fancy_data= (LinearLayout) itemView.findViewById(R.id.lout_fancy_data);
                this.checkbox_fancy= (CheckBox) itemView.findViewById(R.id.checkbox_fancy);

            }
        }
    }
}
