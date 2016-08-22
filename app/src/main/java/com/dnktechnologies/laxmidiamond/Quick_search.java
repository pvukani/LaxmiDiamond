package com.dnktechnologies.laxmidiamond;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import Bean.Model;
import Custom.Custom_Font_TextView;
import Dialog.Progress_Dialog;
import Dialog.*;
import Handler.XmlHandler;

public class Quick_search extends AppCompatActivity implements View.OnClickListener {
    Button btn_home, btn_logout;
    RecyclerView rv_result_stone;
    String link;
    TextView txt_header_1, txt_header_2, txt_header_3, txt_header_4, txt_header_5, txt_header_6, txt_header_7, txt_header_8,
            txt_header_9, txt_header_10, txt_header_11, txt_header_12, txt_header_13;

    String header_tag, clr, clarity;
    String header_arr[];
    LinearLayout lout_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_search);

        FindViewByID();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rv_result_stone.setLayoutManager(layoutManager);
        link = GlobalApp.url + "QuickSearch" + "?UserID=" + GlobalApp.User_Id;
        new GetQuick().execute(link);

        btn_home.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
    }

    private void FindViewByID() {
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_home = (Button) findViewById(R.id.btn_home);
        txt_header_1 = (TextView) findViewById(R.id.txt_header_1);
        txt_header_2 = (TextView) findViewById(R.id.txt_header_2);
        txt_header_3 = (TextView) findViewById(R.id.txt_header_3);
        txt_header_4 = (TextView) findViewById(R.id.txt_header_4);
        txt_header_5 = (TextView) findViewById(R.id.txt_header_5);
        txt_header_6 = (TextView) findViewById(R.id.txt_header_6);
        txt_header_7 = (TextView) findViewById(R.id.txt_header_7);
        txt_header_8 = (TextView) findViewById(R.id.txt_header_8);
        txt_header_9 = (TextView) findViewById(R.id.txt_header_9);
        txt_header_10 = (TextView) findViewById(R.id.txt_header_10);
        txt_header_11 = (TextView) findViewById(R.id.txt_header_11);
        txt_header_12 = (TextView) findViewById(R.id.txt_header_12);
        txt_header_13 = (TextView) findViewById(R.id.txt_header_13);
        rv_result_stone = (RecyclerView) findViewById(R.id.rv_result_stone);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_home: {
                Intent in = new Intent(this, activity_desboard.class);
                startActivity(in);
                break;
            }
            case R.id.btn_logout: {
                new Logout_Dialog(this);
                break;
            }
        }

    }

    public class GetQuick extends AsyncTask<String, Void, String> {
        String url;
        XmlHandler myXMLHandler;
        Progress_Dialog progress_dialog = new Progress_Dialog(Quick_search.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress_dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                url = params[0];
                SAXParserFactory spf = SAXParserFactory.newInstance();
                SAXParser sp = spf.newSAXParser();
                XMLReader xr = sp.getXMLReader();
                myXMLHandler = new XmlHandler();
                xr.setContentHandler(myXMLHandler);
                URL _url = new URL(url);
                xr.parse(new InputSource(_url.openStream()));

            } catch (ParserConfigurationException pce) {
                Log.e("SAX XML", "sax parse error", pce);
            } catch (SAXException se) {
                Log.e("SAX XML", "sax error", se);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<Model> item_quick = myXMLHandler.getItemsList();
            GlobalApp.Ar_List_quick.clear();
            GlobalApp.Ar_List_quick.addAll(item_quick);
            rv_result_stone.setAdapter(new Adapter_quick(GlobalApp.Ar_List_quick));
            progress_dialog.dismiss();
        }
    }

    public class Adapter_quick extends RecyclerView.Adapter<Adapter_quick.Holder> {
        List<Model> list = new ArrayList<Model>();


        public Adapter_quick(List<Model> list) {
            this.list = list;
        }

        @Override
        public Adapter_quick.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quick, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(final Holder holder, int position) {
            final int pos = position;
            holder.txt_color.setText(list.get(pos).getColor());
            holder.txt_clarity.setText(list.get(pos).getClarity());

            holder.txt_step1.setText(list.get(pos).getSTEP1());
            holder.txt_step2.setText(list.get(pos).getSTEP2());
            holder.txt_step3.setText(list.get(pos).getSTEP3());
            holder.txt_step4.setText(list.get(pos).getSTEP4());
            holder.txt_step5.setText(list.get(pos).getSTEP5());
            holder.txt_step6.setText(list.get(pos).getSTEP6());
            holder.txt_step7.setText(list.get(pos).getSTEP7());
            holder.txt_step8.setText(list.get(pos).getSTEP8());
            holder.txt_step9.setText(list.get(pos).getSTEP9());
            holder.txt_step10.setText(list.get(pos).getSTEP10());
            holder.txt_step11.setText(list.get(pos).getSTEP11());
            holder.txt_step12.setText(list.get(pos).getSTEP12());
            holder.txt_step13.setText(list.get(pos).getSTEP13());

            holder.txt_step1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    header_tag = txt_header_1.getText().toString();
                    header_arr = header_tag.split("\n");
                    clr = list.get(pos).getColor();
                    clarity = list.get(pos).getClarity();
                    Log.d("Data", "" + header_arr[0] + " " + header_arr[2] + " " + clr + " " + clarity);
                    if (!holder.txt_step1.getText().toString().equals("0")) {
                        getQuickData(header_arr[0], header_arr[2], clr, clarity);
                    }

                }
            });
            holder.txt_step2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    header_tag = txt_header_2.getText().toString();
                    header_arr = header_tag.split("\n");
                    clr = list.get(pos).getColor();
                    clarity = list.get(pos).getClarity();
                    Log.d("Data", "" + header_arr[0] + " " + header_arr[2] + " " + clr + " " + clarity);
                    if (!holder.txt_step2.getText().toString().equals("0")) {
                        getQuickData(header_arr[0], header_arr[2], clr, clarity);
                    }
                }
            });
            holder.txt_step3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    header_tag = txt_header_3.getText().toString();
                    header_arr = header_tag.split("\n");
                    clr = list.get(pos).getColor();
                    clarity = list.get(pos).getClarity();
                    Log.d("Data", "" + header_arr[0] + " " + header_arr[2] + " " + clr + " " + clarity);
                    if (!holder.txt_step3.getText().toString().equals("0")) {
                        getQuickData(header_arr[0], header_arr[2], clr, clarity);
                    }
                }
            });
            holder.txt_step4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    header_tag = txt_header_4.getText().toString();
                    header_arr = header_tag.split("\n");
                    clr = list.get(pos).getColor();
                    clarity = list.get(pos).getClarity();
                    Log.d("Data", "" + header_arr[0] + " " + header_arr[2] + " " + clr + " " + clarity);
                    if (!holder.txt_step4.getText().toString().equals("0")) {
                        getQuickData(header_arr[0], header_arr[2], clr, clarity);
                    }
                }
            });
            holder.txt_step5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    header_tag = txt_header_5.getText().toString();
                    header_arr = header_tag.split("\n");
                    clr = list.get(pos).getColor();
                    clarity = list.get(pos).getClarity();
                    Log.d("Data", "" + header_arr[0] + " " + header_arr[2] + " " + clr + " " + clarity);
                    if (!holder.txt_step5.getText().toString().equals("0")) {
                        getQuickData(header_arr[0], header_arr[2], clr, clarity);
                    }
                }
            });
            holder.txt_step6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    header_tag = txt_header_6.getText().toString();
                    header_arr = header_tag.split("\n");
                    clr = list.get(pos).getColor();
                    clarity = list.get(pos).getClarity();
                    Log.d("Data", "" + header_arr[0] + " " + header_arr[2] + " " + clr + " " + clarity);
                    if (!holder.txt_step6.getText().toString().equals("0")) {
                        getQuickData(header_arr[0], header_arr[2], clr, clarity);
                    }
                }
            });
            holder.txt_step7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    header_tag = txt_header_7.getText().toString();
                    header_arr = header_tag.split("\n");
                    clr = list.get(pos).getColor();
                    clarity = list.get(pos).getClarity();
                    Log.d("Data", "" + header_arr[0] + " " + header_arr[2] + " " + clr + " " + clarity);
                    if (!holder.txt_step7.getText().toString().equals("0")) {
                        getQuickData(header_arr[0], header_arr[2], clr, clarity);
                    }
                }
            });
            holder.txt_step8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    header_tag = txt_header_8.getText().toString();
                    header_arr = header_tag.split("\n");
                    clr = list.get(pos).getColor();
                    clarity = list.get(pos).getClarity();
                    Log.d("Data", "" + header_arr[0] + " " + header_arr[2] + " " + clr + " " + clarity);
                    if (!holder.txt_step8.getText().toString().equals("0")) {
                        getQuickData(header_arr[0], header_arr[2], clr, clarity);
                    }
                }
            });
            holder.txt_step9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    header_tag = txt_header_9.getText().toString();
                    header_arr = header_tag.split("\n");
                    clr = list.get(pos).getColor();
                    clarity = list.get(pos).getClarity();
                    Log.d("Data", "" + header_arr[0] + " " + header_arr[2] + " " + clr + " " + clarity);
                    if (!holder.txt_step9.getText().toString().equals("0")) {
                        getQuickData(header_arr[0], header_arr[2], clr, clarity);
                    }
                }
            });
            holder.txt_step10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    header_tag = txt_header_10.getText().toString();
                    header_arr = header_tag.split("\n");
                    clr = list.get(pos).getColor();
                    clarity = list.get(pos).getClarity();
                    Log.d("Data", "" + header_arr[0] + " " + header_arr[2] + " " + clr + " " + clarity);
                    if (!holder.txt_step10.getText().toString().equals("0")) {
                        getQuickData(header_arr[0], header_arr[2], clr, clarity);
                    }
                }
            });
            holder.txt_step11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    header_tag = txt_header_11.getText().toString();
                    header_arr = header_tag.split("\n");
                    clr = list.get(pos).getColor();
                    clarity = list.get(pos).getClarity();
                    Log.d("Data", "" + header_arr[0] + " " + header_arr[2] + " " + clr + " " + clarity);
                    if (!holder.txt_step11.getText().toString().equals("0")) {
                        getQuickData(header_arr[0], header_arr[2], clr, clarity);
                    }
                }
            });
            holder.txt_step12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    header_tag = txt_header_12.getText().toString();
                    header_arr = header_tag.split("\n");
                    clr = list.get(pos).getColor();
                    clarity = list.get(pos).getClarity();
                    Log.d("Data", "" + header_arr[0] + " " + header_arr[2] + " " + clr + " " + clarity);
                    if (!holder.txt_step12.getText().toString().equals("0")) {
                        getQuickData(header_arr[0], header_arr[2], clr, clarity);
                    }
                }
            });
            holder.txt_step13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    header_tag = txt_header_13.getText().toString();
                    header_arr = header_tag.split("\n");
                    clr = list.get(pos).getColor();
                    clarity = list.get(pos).getClarity();
                    Log.d("Data", "" + header_arr[0] + " " + header_arr[2] + " " + clr + " " + clarity);
                    Log.i("val", "" + holder.txt_step13.getText().toString());
                    if (!holder.txt_step13.getText().toString().equals("0")) {
                        getQuickData(header_arr[0], header_arr[2], clr, clarity);
                    }

                }
            });

        }

        public void getQuickData(String from, String to, String clr, String clarity) {
            String link = GlobalApp.url + "QuickSearchDetail?FromCarat=" + from + "&ToCarat=" + to + "&Color=" + clr + "&Clarity=" + clarity + "&UserID=" + GlobalApp.User_Id;
            Log.d("Link", "" + link);
            new GetDialogData().execute(link);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public boolean isNonZero(String str) {
            if (str.equals("0")) {
                return false;
            } else {
                return true;
            }
        }

        public class Holder extends RecyclerView.ViewHolder {

            TextView txt_color, txt_clarity, txt_step1, txt_step2, txt_step3, txt_step4, txt_step5, txt_step6, txt_step7,
                    txt_step8, txt_step9, txt_step10, txt_step11, txt_step12, txt_step13;

            public Holder(View itemView) {
                super(itemView);

                txt_color = (TextView) itemView.findViewById(R.id.txt_color);
                txt_clarity = (TextView) itemView.findViewById(R.id.txt_clarity);
                txt_step1 = (TextView) itemView.findViewById(R.id.txt_step1);
                txt_step2 = (TextView) itemView.findViewById(R.id.txt_step2);
                txt_step3 = (TextView) itemView.findViewById(R.id.txt_step3);
                txt_step4 = (TextView) itemView.findViewById(R.id.txt_step4);
                txt_step5 = (TextView) itemView.findViewById(R.id.txt_step5);
                txt_step6 = (TextView) itemView.findViewById(R.id.txt_step6);
                txt_step7 = (TextView) itemView.findViewById(R.id.txt_step7);
                txt_step8 = (TextView) itemView.findViewById(R.id.txt_step8);
                txt_step9 = (TextView) itemView.findViewById(R.id.txt_step9);
                txt_step10 = (TextView) itemView.findViewById(R.id.txt_step10);
                txt_step11 = (TextView) itemView.findViewById(R.id.txt_step11);
                txt_step12 = (TextView) itemView.findViewById(R.id.txt_step12);
                txt_step13 = (TextView) itemView.findViewById(R.id.txt_step13);

            }
        }

    }

    public class GetDialogData extends AsyncTask<String, Void, String> {

        String url;
        XmlHandler myXMLHandler;
        Progress_Dialog progress_dialog = new Progress_Dialog(Quick_search.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress_dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                url = params[0];
                SAXParserFactory spf = SAXParserFactory.newInstance();
                SAXParser sp = spf.newSAXParser();
                XMLReader xr = sp.getXMLReader();
                myXMLHandler = new XmlHandler();
                xr.setContentHandler(myXMLHandler);
                URL _url = new URL(url);
                xr.parse(new InputSource(_url.openStream()));

            } catch (ParserConfigurationException pce) {
                Log.e("SAX XML", "sax parse error", pce);
            } catch (SAXException se) {
                Log.e("SAX XML", "sax error", se);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progress_dialog.dismiss();
            ArrayList<Model> itemsList = myXMLHandler.getItemsList();
            GlobalApp.Arr_quick_data.clear();
            GlobalApp.Arr_quick_data.addAll(itemsList);
            Log.d("upv", "" + GlobalApp.Arr_quick_data.size());
            new Qs_dialog(Quick_search.this).show();

        }
    }

    public class Qs_dialog extends Dialog implements View.OnClickListener {
        Activity activity;
        Button btn_qs_search, btn_qs_cancel;
        TextView txt_qs_title;

        public Qs_dialog(Activity activity) {
            super(activity);
            this.activity = activity;

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.qs_popup);

            Dia_FindViewById();
            for (int i = 0; i < GlobalApp.Arr_quick_data.size(); i++) {
                LinearLayout parent = new LinearLayout(Quick_search.this);
                parent.setOrientation(LinearLayout.HORIZONTAL);
                parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                for (int j = 0; j < 3; j++) {
                    final TextView txt = new TextView(Quick_search.this);
                    TableRow.LayoutParams txt_params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    txt.setLayoutParams(txt_params);
                    txt.setGravity(Gravity.CENTER);
                    txt.setTextSize(getResources().getDimension(R.dimen.Btn_TxtSize_720dp));
                    switch (j) {
                        case 0: {
                            txt.setText(GlobalApp.Arr_quick_data.get(i).getShape_color().toString());
                            break;
                        }
                        case 1: {
                            txt.setText(GlobalApp.Arr_quick_data.get(i).getColumn1().toString());
                            if(GlobalApp.Arr_quick_data.get(i).getIsfirstrow().toString().equals("1"))
                            {
                                txt.setOnTouchListener(new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View v, MotionEvent event) {
                                        return true;
                                    }
                                });
                            }
                            txt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (v.isSelected()) {
                                        txt.setBackgroundColor(getResources().getColor(R.color.white));
                                        v.setSelected(false);
                                    } else {
                                        txt.setBackgroundColor(getResources().getColor(R.color.my));
                                        v.setSelected(true);
                                    }
                                }
                            });
                            break;
                        }
                        case 2: {
                            txt.setText(GlobalApp.Arr_quick_data.get(i).getColumn2().toString());
                            if(GlobalApp.Arr_quick_data.get(i).getIsfirstrow().toString().equals("1"))
                            {
                                txt.setOnTouchListener(new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View v, MotionEvent event) {
                                        return true;
                                    }
                                });
                            }
                            txt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (v.isSelected()) {
                                        txt.setBackgroundColor(getResources().getColor(R.color.white));
                                        v.setSelected(false);
                                    } else {
                                        txt.setBackgroundColor(getResources().getColor(R.color.my));
                                        v.setSelected(true);
                                    }
                                }
                            });
                            break;
                        }
                    }
                    parent.addView(txt);
                }
                lout_root.addView(parent);
            }

            btn_qs_search.setOnClickListener(this);
            btn_qs_cancel.setOnClickListener(this);
            txt_qs_title.setText(header_arr[0] + "-" + header_arr[2]);
        }

        private void Dia_FindViewById() {
            btn_qs_search = (Button) findViewById(R.id.btn_qs_search);
            btn_qs_cancel = (Button) findViewById(R.id.btn_qs_close);
            txt_qs_title = (TextView) findViewById(R.id.txt_qs_title);
            lout_root = (LinearLayout) findViewById(R.id.lout_Qroot);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_qs_close: {
                    dismiss();
                    break;
                }
                case R.id.btn_qs_search: {

                    break;
                }


            }
        }
    }
}
