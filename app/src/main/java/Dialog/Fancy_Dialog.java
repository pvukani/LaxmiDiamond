package Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dnktechnologies.laxmidiamond.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by parth on 7/2/2016.
 */
public class Fancy_Dialog extends Dialog {

    Activity activity;
    String[] list_item;
    RecyclerView recy_fancy_dialog;
    TextView txt_pop_title;
    String title;
    Button btn_pop_ok, btn_pop_close;
    EditText editText;
    fancy_adapter Fancy_adapter;
    public String selected_items="";

    public Fancy_Dialog(Activity activity, String title, String[] list_item,EditText editText) {
        super(activity);
        this.activity = activity;
        this.title = title;
        this.list_item = list_item;
        this.editText = editText;
    }

//    public Fancy_Dialog(Activity activity, String title) {
//        super(activity);
//        this.activity = activity;
//        this.title = title;
//
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.fancy_dialog);
        setCanceledOnTouchOutside(true);

        recy_fancy_dialog = (RecyclerView) findViewById(R.id.recy_fancy_data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false);
        recy_fancy_dialog.setLayoutManager(layoutManager);

        Fancy_adapter = new fancy_adapter(list_item);
        recy_fancy_dialog.setAdapter(Fancy_adapter);

        txt_pop_title = (TextView) findViewById(R.id.txt_popup_title);
        txt_pop_title.setText(this.title);
        btn_pop_ok = (Button) findViewById(R.id.btn_pop_ok);
        btn_pop_close = (Button) findViewById(R.id.btn_pop_close);
        Display display = this.activity.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        android.view.WindowManager.LayoutParams lp;
        lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = (int) (width - (width * 0.75));
        lp.height = (int) (height - (height * 0.35));
        getWindow().setAttributes(lp);

        btn_pop_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btn_pop_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Selected item",""+selected_items);
                dismiss();
                editText.setText(selected_items.replaceFirst(",",""));
                editText.setSingleLine(true);
            }
        });

    }

    public class fancy_adapter extends RecyclerView.Adapter<fancy_adapter.fancy_ViewHolder> {

        public String[] list_item;
        Map<Integer,Boolean> check_state=new HashMap<Integer,Boolean>();


        public fancy_adapter(String[] list_item) {
            this.list_item = list_item;
        }

        @Override
        public fancy_adapter.fancy_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fancy_dialog, parent, false);
            return new fancy_ViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return list_item.length;
        }

        @Override
        public void onBindViewHolder(final fancy_adapter.fancy_ViewHolder holder, final int position) {

            final int pos = position;
            holder.txt_fancy_item.setText(list_item[pos]);
            holder.lout_fancy_data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.checkbox_fancy.isChecked())
                    {
                        selected_items="";
                        check_state.put(pos,false);
                        for (int i = 0; i <list_item.length ; i++) {
                            if(check_state.containsKey(i) && check_state.get(i))
                            {
                                selected_items+=","+list_item[i];
                            }
                        }
                        holder.checkbox_fancy.setChecked(false);
                    }
                    else {
                        selected_items="";
                        check_state.put(pos,true);
                        for (int i = 0; i <list_item.length ; i++) {
                            if(check_state.containsKey(i) && check_state.get(i))
                            {
                                selected_items+=","+list_item[i];

                            }
                        }
                        holder.checkbox_fancy.setChecked(true);
                    }
                }
            });
        }

        public class fancy_ViewHolder extends RecyclerView.ViewHolder {
            TextView txt_fancy_item;
            CheckBox checkbox_fancy;
            LinearLayout lout_fancy_data;

            public fancy_ViewHolder(View itemView) {
                super(itemView);
                this.lout_fancy_data = (LinearLayout) itemView.findViewById(R.id.lout_fancy_data);
                this.txt_fancy_item = (TextView) itemView.findViewById(R.id.txt_fancy_item);
                this.checkbox_fancy = (CheckBox) itemView.findViewById(R.id.checkbox_fancy);
            }
        }
    }
}
