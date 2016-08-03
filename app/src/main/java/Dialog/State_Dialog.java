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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dnktechnologies.laxmidiamond.GlobalApp;
import com.dnktechnologies.laxmidiamond.R;

import java.util.List;

import Bean.Model;

/**
 * Created by parth on 6/27/2016.
 */
public class State_Dialog extends Dialog {

    Activity act;
    EditText reg_editText_state;
    State_adapter State_adapter;

    public State_Dialog(Activity act, EditText reg_editText_state) {
        super(act);
        this.act = act;
        this.reg_editText_state = reg_editText_state;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.countrylist_dialog);
        setCanceledOnTouchOutside(true);

        RecyclerView recyclerView_stateList = (RecyclerView) findViewById(R.id.recycler_view_countryList);
        RecyclerView.LayoutManager state_layout = new LinearLayoutManager(act, LinearLayoutManager.VERTICAL, false);
        recyclerView_stateList.setLayoutManager(state_layout);
        TextView which_list= (TextView) findViewById(R.id.which_list);
        which_list.setText("Select State");
        State_adapter = new State_adapter(GlobalApp.Arr_state_list);
        recyclerView_stateList.setAdapter(State_adapter);


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

    public class State_adapter extends RecyclerView.Adapter<State_adapter.MyViewHolder> {

        List<Model> arr_state_list;

        public State_adapter(List<Model> arr_state_list) {
            this.arr_state_list = arr_state_list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item_state = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_country_list, parent, false);
        return new MyViewHolder(item_state);
    }

        @Override
        public int getItemCount() {
            return arr_state_list.size();
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder,final int position) {
            final int pos = position;
            holder.state_name.setText(arr_state_list.get(pos).getStatename());
            Log.d("check", "after set recycler");
            holder.lout_state_raw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reg_editText_state.setText(arr_state_list.get(pos).getStatename());
                    dismiss();
                }
            });

        }
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout lout_state_raw;
            public TextView state_name;


            public MyViewHolder(View itemView) {
                super(itemView);
                this.lout_state_raw = (LinearLayout) itemView.findViewById(R.id.lout_country_raw);
                this.state_name = (TextView) itemView.findViewById(R.id.txt_row_countryList);

            }
        }
    }



}
