package com.dnktechnologies.laxmidiamond;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import Adapter.Adapter_grid;
import Adapter.Adapter_result_stone;
import Bean.Model;
import Custom.ActionItem;
import Custom.QuickAction;
import Dialog.Logout_Dialog;
import Dialog.OK_Dialog;
import Dialog.Progress_Dialog;
import Fragment.*;
import Handler.XmlHandler;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by parth on 8/10/2016.
 */
public class Stone_List extends Activity implements View.OnClickListener, asyncRes {

    Button btn_home, btn_logout, btn_placeOrder, btn_addtoTrack, btn_addTocart, btn_gridView, btn_emailStone;
    Adapter_grid adapter_grid;
    Adapter_result_stone adapter_result_stone;
    LinearLayout lout_header, lout_footer;
    RecyclerView rv_stone;
    GridLayoutManager gridLayoutManager;
    RecyclerView.LayoutManager layoutManager;
    Button page_no_select_btn;
    ImageButton left_arrow_btn, right_arrow_btn;
    String link;
    //for Quich search
    ActionItem actionItem;
    ActionItem nextItem = null;
    ArrayList<String> array_page = new ArrayList<String>();
    int ttl_pages;
    TextView total_no_page_txt;
    QuickAction quickAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stone_list);

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);

        FindViewByID();
        whichStone();


        btn_home.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_placeOrder.setOnClickListener(this);
        btn_addtoTrack.setOnClickListener(this);
        btn_addTocart.setOnClickListener(this);
        btn_gridView.setOnClickListener(this);
        btn_emailStone.setOnClickListener(this);
        left_arrow_btn.setOnClickListener(this);
        right_arrow_btn.setOnClickListener(this);
    }

    private void FindViewByID() {
        btn_home = (Button) findViewById(R.id.btn_home);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_placeOrder = (Button) findViewById(R.id.btn_placeOrder);
        btn_addtoTrack = (Button) findViewById(R.id.btn_addtoTrack);
        btn_addTocart = (Button) findViewById(R.id.btn_addTocart);
        btn_gridView = (Button) findViewById(R.id.btn_gridView);
        btn_emailStone = (Button) findViewById(R.id.btn_emailStone);
        lout_header = (LinearLayout) findViewById(R.id.lout_header);
        lout_footer = (LinearLayout) findViewById(R.id.lout_footer);
        right_arrow_btn = (ImageButton) findViewById(R.id.right_arrow_btn);
        left_arrow_btn = (ImageButton) findViewById(R.id.left_arrow_btn);
        rv_stone = (RecyclerView) findViewById(R.id.rv_stone);
        page_no_select_btn = (Button) findViewById(R.id.page_no_select_btn);
        total_no_page_txt = (TextView) findViewById(R.id.total_no_page_txt);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (v.getId()) {
            case R.id.btn_home: {
                Intent in = new Intent(Stone_List.this, activity_desboard.class);
                startActivity(in);
                break;
            }
            case R.id.btn_logout: {
                new Logout_Dialog(this);
                break;
            }
            case R.id.btn_gridView: {

                if (btn_gridView.isSelected()) {
                    btn_gridView.setSelected(false);
                    btn_gridView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.list_view, 0, 0);
                    btn_gridView.setText("Photo View");
                    lout_header.setVisibility(View.VISIBLE);
                    lout_footer.setVisibility(View.VISIBLE);
                    rv_stone.setLayoutManager(layoutManager);
                    adapter_result_stone = new Adapter_result_stone(this, GlobalApp.Arr_for_grid, GlobalApp.map_result);
                    rv_stone.setAdapter(adapter_result_stone);

                } else {
                    btn_gridView.setSelected(true);
                    btn_gridView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.grid_view, 0, 0);
                    btn_gridView.setText("List View");
                    lout_header.setVisibility(View.GONE);
                    lout_footer.setVisibility(View.GONE);
                    rv_stone.setLayoutManager(gridLayoutManager);
                    adapter_grid = new Adapter_grid(this, GlobalApp.Arr_for_grid, GlobalApp.map_result);
                    rv_stone.setAdapter(adapter_grid);
                }
                break;
            }
            case R.id.right_arrow_btn: {
                Log.i("is","ok");
                if (GlobalApp.current_page < ttl_pages) {
                    GlobalApp.current_page++;
                    link = GlobalApp.obtainLink();
                    new GetStone(new asyncRes() {
                        @Override
                        public void processFinish(ArrayList<Model> models) {
                            SetAdapter(models);
                        }
                    }).execute(link);
                    page_no_select_btn.setText(String.valueOf(GlobalApp.current_page));
                }

                break;
            }
            case R.id.left_arrow_btn: {
                Log.i("is1","ok1");
                if (GlobalApp.current_page > 1) {
                    GlobalApp.current_page--;
                    link = GlobalApp.obtainLink();
                    new GetStone(new asyncRes() {
                        @Override
                        public void processFinish(ArrayList<Model> models) {
                            SetAdapter(models);
                        }
                    }).execute(link);
                    page_no_select_btn.setText(String.valueOf(GlobalApp.current_page));
                }
                break;
            }
        }
    }

    public void whichStone() {
        String Parent = getIntent().getExtras().getString("parent");

        switch (Parent) {
            case "BGM": {
                Url_Content.clear_parameter();
                Url_Content.isfancy = "-1";
                Url_Content.no_bgm = "2";
                link = GlobalApp.obtainLink();
                new GetStone(new asyncRes() {
                    @Override
                    public void processFinish(ArrayList<Model> models) {

                        GlobalApp.Arr_Bgm_stone.clear();
                        GlobalApp.Arr_Bgm_stone.addAll(models);
                        SetAdapter(GlobalApp.Arr_Bgm_stone);

                    }
                }).execute(link);

                break;
            }
            case "NARR": {
                Url_Content.clear_parameter();
                Url_Content.isfancy = "-1";
                Url_Content.FreshStock = "1";
                link = GlobalApp.obtainLink();
                Log.i("bgm", "" + link);
                new GetStone(new asyncRes() {
                    @Override
                    public void processFinish(ArrayList<Model> models) {

                        GlobalApp.Arr_newArr_stone.clear();
                        GlobalApp.Arr_newArr_stone.addAll(models);
                        SetAdapter(GlobalApp.Arr_newArr_stone);

                    }
                }).execute(link);
                Log.i("which", "new R");
                break;
            }
            case "RPR": {
                Url_Content.clear_parameter();
                Url_Content.isfancy = "-1";
                Url_Content.RevisedPrice = "1";
                link = GlobalApp.obtainLink();
                Log.i("Revised price", "" + link);
                new GetStone(new asyncRes() {
                    @Override
                    public void processFinish(ArrayList<Model> models) {

                        GlobalApp.Arr_revised_stone.clear();
                        GlobalApp.Arr_revised_stone.addAll(models);
                        SetAdapter(GlobalApp.Arr_revised_stone);

                    }
                }).execute(link);
                Log.i("which", "revised PR");
                break;
            }
            case "STONE": {
                link = GlobalApp.obtainLink();
                new GetStone(new asyncRes() {
                    @Override
                    public void processFinish(ArrayList<Model> models) {

                        GlobalApp.Arr_Result_stone.clear();
                        GlobalApp.Arr_Result_stone.addAll(models);
                        SetAdapter(GlobalApp.Arr_Result_stone);

                    }
                }).execute(link);
                Log.i("which", "Result");
                break;
            }
            case "CART": {
                Log.i("Which", "CArt");
                break;
            }
        }

    }

    public void ForQuickAction(ArrayList<String> array_page) {
        actionItem = new ActionItem(array_page);

        final QuickAction quickAction = new QuickAction(Stone_List.this, QuickAction.VERTICAL);
        for (int i = 0; i < array_page.size(); i++) {

            nextItem = new ActionItem(1, array_page.get(i));

            quickAction.addActionItem(nextItem);
        }

        quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                ActionItem actionItem = quickAction.getActionItem(pos);
                GlobalApp.current_page = Integer.parseInt(actionItem.getTitle());

                link = GlobalApp.obtainLink();
                new GetStone(new asyncRes() {
                    @Override
                    public void processFinish(ArrayList<Model> models) {
                        SetAdapter(models);

                    }
                }).execute(link);
                page_no_select_btn.setText(String.valueOf(GlobalApp.current_page));
                Toast.makeText(getApplicationContext(), actionItem.getTitle() + " selected", Toast.LENGTH_SHORT).show();

            }
        });
        page_no_select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quickAction.show(v);
                quickAction.setAnimStyle(QuickAction.ANIM_REFLECT);
            }
        });
    }

    public void SetAdapter(List<Model> models) {
        GlobalApp.Arr_for_grid.clear();
        GlobalApp.Arr_for_grid.addAll(models);
        GlobalApp.map_result.clear();
        adapter_result_stone = new Adapter_result_stone(Stone_List.this, models, GlobalApp.map_result);
        rv_stone.setLayoutManager(layoutManager);
        rv_stone.setAdapter(adapter_result_stone);
        ttl_pages = Integer.parseInt(models.get(0).getTotalpage().toString());
        total_no_page_txt.setText("Of " + ttl_pages);
        array_page.clear();
        for (int i = 0; i < ttl_pages; i++) {
            array_page.add(String.valueOf(i + 1));
        }

        ForQuickAction(array_page);
    }

    @Override
    public void processFinish(ArrayList<Model> models) {
        Log.i("xx", "xxx");
    }

    public class GetStone extends AsyncTask<String, Void, String> {
        String url;
        XmlHandler myXMLHandler;
        Progress_Dialog progress_dialog = new Progress_Dialog(Stone_List.this);
        public asyncRes asyncRes = null;

        public GetStone(com.dnktechnologies.laxmidiamond.asyncRes asyncRes) {
            this.asyncRes = asyncRes;
        }

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
            ArrayList<Model> itemsList = myXMLHandler.getItemsList();
            Log.i("Size_bgm", "" + itemsList.size());

            try {
                asyncRes.processFinish(itemsList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            progress_dialog.dismiss();
        }
    }
}