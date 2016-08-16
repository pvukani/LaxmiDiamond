package com.dnktechnologies.laxmidiamond;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import Adapter.adapter_shape;
import Adapter.*;
import Bean.Model;
import Dialog.Fancy_Dialog;
import Dialog.Ldofc_dailog;
import Dialog.Trade_Dialog;
import Handler.XmlHandler;

public class Single_stone_search extends AppCompatActivity implements View.OnClickListener {

    String parent = "parent";
    Bundle bundle;
    String pop_title = "", plus_iv_state = "double";
    HashMap<Integer, Boolean> Map_shape = new HashMap<Integer, Boolean>();
    HashMap<Integer, Boolean> Map_lab = new HashMap<Integer, Boolean>();

    HashMap<Integer, Boolean> Map_cut = new HashMap<Integer, Boolean>();
    HashMap<Integer, Boolean> Map_polish = new HashMap<Integer, Boolean>();
    HashMap<Integer, Boolean> Map_sym = new HashMap<Integer, Boolean>();
    HashMap<Integer, Boolean> Map_color = new HashMap<Integer, Boolean>();

    HashMap<Integer, Boolean> Map_clarity = new HashMap<Integer, Boolean>();
    HashMap<Integer, Boolean> Map_f_intencity = new HashMap<Integer, Boolean>();
    HashMap<Integer, Boolean> Map_f_color = new HashMap<Integer, Boolean>();

    String[] nm_cps, nm_f_color, nm_f_intensity, nm_color, nm_clarity, nm_shape, nm_lab;
    public static String[] recy_item;
    RecyclerView recy_img_shape, recView_lab, recView_cut, recView_polish,
            recView_sym, recView_color, recView_clarity, recView_f_intensity,
            recView_f_color;
    EditText edit_sss_country, edit_overtone, edit_color, edit_intensity, edit_tinge, edit_tbl_inclusion, edit_side_inclusion,
            edit_open_inclusion, edit_cavity, edit_natural, edit_inden_natural, edit_milky, edit_luster, edit_extra_facet, edit_girdle, edit_heart_arrow,
            edit_eye_clean, edit_culet, edit_graining, edit_trade, edit_carat_f, edit_carat_t, edit_usd_f, edit_usd_t, edit_total_usd_f, edit_total_usd_t,
            edit_discount_f, edit_discount_t, edit_stone_id, edit_certi_no, edit_lot_no;
    //edittext Advance
    EditText edit_diameter_f, edit_diameter_t, edit_total_depth_f, edit_total_depth_t, edit_total_f, edit_total_t, edit_crown_h_f, edit_crown_h_t,
            edit_crown_a_f, edit_crown_a_t, edit_pav_h_f, edit_pav_h_t, edit_pav_a_f, edit_pav_a_t, edit_girdle_f, edit_girdle_t, edit_ratio_f, edit_ratio_t;
    LinearLayout lout_adav, lout_for_white, lout_for_fancy;

    TypedArray arr_shape, arr_selec_shape,
            arr_selec_lab, arr_lab,
            arr_def_cps, arr_selected_cps,
            arr_def_clarity, arr_selected_clarity,
            arr_def_color, arr_select_color,
            arr_def_f_c, arr_select_f_c,
            arr_def_f_i, arr_select_f_i;
    ImageView img_carat_size;
    Button btn_ad_search, btn_reset, btn_search_stone, btn_home, btn_logout;
    CheckBox chb_white, chb_fancy, cb_no_bgm, cb_only_bgm, cb_only_brown, cb_only_green, cb_only_milky, chb_3x, chb_2x;
    adapter_shape Adapter_shape;
    adapter_shape Adapter_lab;
    Adapter_cps adapter_cut, adapter_polish, adapter_sym, adapter_color, adapter_clarity, adapter_f_color, adapter_f_intensity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_stone_search);

        FindViewById();
        if(GlobalApp.flag_fancy)
        {
            chb_white.setChecked(false);
            chb_fancy.setChecked(true);
            lout_for_white.setVisibility(View.VISIBLE);
            lout_for_fancy.setVisibility(View.GONE);

        }
        bundle = new Bundle();

        nm_cps = getResources().getStringArray(R.array.diamond_cut_polish_sym_names);
        nm_f_color = getResources().getStringArray(R.array.diamond_f_c_names);
        nm_f_intensity = getResources().getStringArray(R.array.diamond_f_i_names);
        nm_clarity = getResources().getStringArray(R.array.diamond_clarity_names);
        nm_shape = getResources().getStringArray(R.array.diamond_names);
        nm_lab = getResources().getStringArray(R.array.diamond_lab_names);
        nm_color = getResources().getStringArray(R.array.diamond_color_names);
        img_carat_size = (ImageView) findViewById(R.id.img_carat_size);

        arr_shape = getResources().obtainTypedArray(R.array.img_shape);
        arr_selec_shape = getResources().obtainTypedArray(R.array.img_shape_select);

        arr_lab = getResources().obtainTypedArray(R.array.img_lab);
        arr_selec_lab = getResources().obtainTypedArray(R.array.img_lab_select);

        arr_def_cps = getResources().obtainTypedArray(R.array.diamond_cut_polish_sym_default);
        arr_selected_cps = getResources().obtainTypedArray(R.array.diamond_cut_polish_sym_selected);

        arr_def_clarity = getResources().obtainTypedArray(R.array.diamond_clarity_default);
        arr_selected_clarity = getResources().obtainTypedArray(R.array.diamond_clarity_selected);

        arr_def_color = getResources().obtainTypedArray(R.array.diamond_color_default);
        arr_select_color = getResources().obtainTypedArray(R.array.diamond_color_selected);

        arr_def_f_c = getResources().obtainTypedArray(R.array.diamond_f_c_default);
        arr_select_f_c = getResources().obtainTypedArray(R.array.diamond_f_c_selected);

        arr_def_f_i = getResources().obtainTypedArray(R.array.diamond_f_i_default);
        arr_select_f_i = getResources().obtainTypedArray(R.array.diamond_f_i_selected);

//for shape
        recy_img_shape = (RecyclerView) findViewById(R.id.recView_shape);
        RecyclerView.LayoutManager lay_shape = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recy_img_shape.setLayoutManager(lay_shape);
        Adapter_shape = new adapter_shape(nm_shape, arr_shape, arr_selec_shape, Map_shape);
        recy_img_shape.setAdapter(Adapter_shape);
//for lab
        recView_lab = (RecyclerView) findViewById(R.id.recView_lab);
        RecyclerView.LayoutManager lay_lab = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recView_lab.setLayoutManager(lay_lab);
        Adapter_lab = new adapter_shape(nm_lab, arr_lab, arr_selec_lab, Map_lab);
        recView_lab.setAdapter(Adapter_lab);
//for cut
        recView_cut = (RecyclerView) findViewById(R.id.recView_cut);
        RecyclerView.LayoutManager lay_cut = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recView_cut.setLayoutManager(lay_cut);
        adapter_cut = new Adapter_cps(arr_def_cps, arr_selected_cps, nm_cps, Map_cut);
        recView_cut.setAdapter(adapter_cut);
//for polish
        recView_polish = (RecyclerView) findViewById(R.id.recView_polish);
        RecyclerView.LayoutManager lay_polish = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recView_polish.setLayoutManager(lay_polish);
        adapter_polish = new Adapter_cps(arr_def_cps, arr_selected_cps, nm_cps, Map_polish);
        recView_polish.setAdapter(adapter_polish);
//for sym
        recView_sym = (RecyclerView) findViewById(R.id.recView_sym);
        RecyclerView.LayoutManager lay_sym = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recView_sym.setLayoutManager(lay_sym);
        adapter_sym = new Adapter_cps(arr_def_cps, arr_selected_cps, nm_cps, Map_sym);
        recView_sym.setAdapter(adapter_sym);
//for clarity
        recView_clarity = (RecyclerView) findViewById(R.id.recView_clarity);
        RecyclerView.LayoutManager lay_clarity = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recView_clarity.setLayoutManager(lay_clarity);
        adapter_clarity = new Adapter_cps(arr_def_clarity, arr_selected_clarity, nm_clarity, Map_clarity);
        recView_clarity.setAdapter(adapter_clarity);
//for color
        recView_color = (RecyclerView) findViewById(R.id.recView_color);
        RecyclerView.LayoutManager lay_color = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recView_color.setLayoutManager(lay_color);
        adapter_color = new Adapter_cps(arr_def_color, arr_select_color, nm_color, Map_color);
        recView_color.setAdapter(adapter_color);
//for f_intensity
        recView_f_intensity = (RecyclerView) findViewById(R.id.recView_f_intensity);
        RecyclerView.LayoutManager lay_f_inten = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recView_f_intensity.setLayoutManager(lay_f_inten);
        adapter_f_intensity = new Adapter_cps(arr_def_f_i, arr_select_f_i, nm_f_intensity, Map_f_intencity);
        recView_f_intensity.setAdapter(adapter_f_intensity);
//for f_color
        recView_f_color = (RecyclerView) findViewById(R.id.recView_f_color);
        RecyclerView.LayoutManager lay_f_color = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recView_f_color.setLayoutManager(lay_f_color);
        adapter_f_color = new Adapter_cps(arr_def_f_c, arr_select_f_c, nm_f_color, Map_f_color);
        recView_f_color.setAdapter(adapter_f_color);

        edit_sss_country.setOnClickListener(this);
        edit_intensity.setOnClickListener(this);
        edit_overtone.setOnClickListener(this);
        edit_color.setOnClickListener(this);
        edit_tinge.setOnClickListener(this);
        edit_tbl_inclusion.setOnClickListener(this);
        edit_side_inclusion.setOnClickListener(this);
        edit_open_inclusion.setOnClickListener(this);
        edit_cavity.setOnClickListener(this);
        edit_natural.setOnClickListener(this);
        edit_inden_natural.setOnClickListener(this);
        edit_milky.setOnClickListener(this);
        edit_luster.setOnClickListener(this);
        edit_extra_facet.setOnClickListener(this);
        edit_girdle.setOnClickListener(this);
        edit_heart_arrow.setOnClickListener(this);
        edit_eye_clean.setOnClickListener(this);
        edit_culet.setOnClickListener(this);
        edit_graining.setOnClickListener(this);
        edit_trade.setOnClickListener(this);
        img_carat_size.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_ad_search.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        btn_search_stone.setOnClickListener(this);
        chb_white.setOnClickListener(this);
        chb_fancy.setOnClickListener(this);
        cb_only_bgm.setOnClickListener(this);
        cb_no_bgm.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        GlobalApp.flag_fancy=false;
    }

    private void FindViewById() {

        edit_sss_country = (EditText) findViewById(R.id.edit_sss_country);
        edit_overtone = (EditText) findViewById(R.id.edit_overtone);
        edit_intensity = (EditText) findViewById(R.id.edit_intensity);
        edit_color = (EditText) findViewById(R.id.edit_color);
        edit_tinge = (EditText) findViewById(R.id.edit_tinge);
        edit_tbl_inclusion = (EditText) findViewById(R.id.edit_tbl_inclusion);
        edit_side_inclusion = (EditText) findViewById(R.id.edit_side_inclusion);
        edit_open_inclusion = (EditText) findViewById(R.id.edit_open_inclusion);
        edit_cavity = (EditText) findViewById(R.id.edit_cavity);
        edit_natural = (EditText) findViewById(R.id.edit_natural);
        edit_inden_natural = (EditText) findViewById(R.id.edit_inden_natural);
        edit_milky = (EditText) findViewById(R.id.edit_milky);
        edit_luster = (EditText) findViewById(R.id.edit_luster);
        edit_extra_facet = (EditText) findViewById(R.id.edit_extra_facet);
        edit_girdle = (EditText) findViewById(R.id.edit_girdle);
        edit_heart_arrow = (EditText) findViewById(R.id.edit_heart_arrow);
        edit_eye_clean = (EditText) findViewById(R.id.edit_eye_clean);
        edit_culet = (EditText) findViewById(R.id.edit_culet);
        edit_graining = (EditText) findViewById(R.id.edit_graining);
        edit_trade = (EditText) findViewById(R.id.edit_trade);
        edit_carat_f = (EditText) findViewById(R.id.edit_carat_f);
        edit_carat_t = (EditText) findViewById(R.id.edit_carat_t);
        edit_usd_f = (EditText) findViewById(R.id.edit_usd_f);
        edit_usd_t = (EditText) findViewById(R.id.edit_usd_t);
        edit_total_usd_f = (EditText) findViewById(R.id.edit_total_usd_f);
        edit_total_usd_t = (EditText) findViewById(R.id.edit_total_usd_t);
        edit_discount_f = (EditText) findViewById(R.id.edit_discount_f);
        edit_discount_t = (EditText) findViewById(R.id.edit_discount_t);
        edit_stone_id = (EditText) findViewById(R.id.edit_stone_id);
        edit_certi_no = (EditText) findViewById(R.id.edit_certi_no);
        edit_lot_no = (EditText) findViewById(R.id.edit_lot_no);
        edit_diameter_f = (EditText) findViewById(R.id.edit_diameter_f);
        edit_diameter_t = (EditText) findViewById(R.id.edit_diameter_t);
        edit_total_depth_f = (EditText) findViewById(R.id.edit_total_depth_f);
        edit_total_depth_t = (EditText) findViewById(R.id.edit_total_depth_t);
        edit_total_f = (EditText) findViewById(R.id.edit_total_f);
        edit_total_t = (EditText) findViewById(R.id.edit_total_t);
        edit_crown_h_f = (EditText) findViewById(R.id.edit_crown_h_f);
        edit_crown_h_t = (EditText) findViewById(R.id.edit_crown_h_t);
        edit_crown_a_f = (EditText) findViewById(R.id.edit_crown_a_f);
        edit_crown_a_t = (EditText) findViewById(R.id.edit_crown_a_t);
        edit_pav_h_f = (EditText) findViewById(R.id.edit_pav_h_f);
        edit_pav_h_t = (EditText) findViewById(R.id.edit_pav_h_t);
        edit_pav_a_f = (EditText) findViewById(R.id.edit_pav_a_f);
        edit_pav_a_t = (EditText) findViewById(R.id.edit_pav_a_t);
        edit_girdle_f = (EditText) findViewById(R.id.edit_girdle_f);
        edit_girdle_t = (EditText) findViewById(R.id.edit_girdle_t);
        edit_ratio_f = (EditText) findViewById(R.id.edit_ratio_f);
        edit_ratio_t = (EditText) findViewById(R.id.edit_ratio_t);
        lout_for_white = (LinearLayout) findViewById(R.id.lout_for_white);
        lout_for_fancy = (LinearLayout) findViewById(R.id.lout_for_fancy);
        btn_home = (Button) findViewById(R.id.btn_home);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_ad_search = (Button) findViewById(R.id.btn_ad_search);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_search_stone = (Button) findViewById(R.id.btn_search_stone);
        lout_adav = (LinearLayout) findViewById(R.id.lout_adav);
        //all checkbox
        cb_only_milky = (CheckBox) findViewById(R.id.cb_only_milky);
        cb_no_bgm = (CheckBox) findViewById(R.id.cb_no_bgm);
        cb_only_bgm = (CheckBox) findViewById(R.id.cb_only_bgm);
        cb_only_green = (CheckBox) findViewById(R.id.cb_only_green);
        cb_only_brown = (CheckBox) findViewById(R.id.cb_only_brown);
        chb_white = (CheckBox) findViewById(R.id.chb_white);
        chb_fancy = (CheckBox) findViewById(R.id.chb_fancy);
        chb_3x = (CheckBox) findViewById(R.id.chb_3x);
        chb_2x = (CheckBox) findViewById(R.id.chb_2x);


    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {

            case R.id.edit_intensity: {
                pop_title = "Fancy Intensity";
                recy_item = getResources().getStringArray(R.array.fancy_intensity);
                new Fancy_Dialog(this, pop_title, recy_item, edit_intensity).show();
                break;
            }
            case R.id.edit_overtone: {
                pop_title = "Fancy Overtone";
                recy_item = getResources().getStringArray(R.array.fancy_overtone);
                new Fancy_Dialog(this, pop_title, recy_item, edit_overtone).show();
                break;
            }
            case R.id.edit_color: {
                pop_title = "Fancy Color";
                recy_item = getResources().getStringArray(R.array.fancy_color_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_color).show();
                break;
            }

            case R.id.edit_tinge: {
                pop_title = "Tinge";
                recy_item = getResources().getStringArray(R.array.tinge_arr);
                new Fancy_Dialog(this, pop_title, recy_item, edit_tinge).show();
                break;
            }
            case R.id.edit_tbl_inclusion: {
                pop_title = "Table inclusion";
                recy_item = getResources().getStringArray(R.array.table_inclusion_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_tbl_inclusion).show();
                break;
            }
            case R.id.edit_side_inclusion: {
                pop_title = "Side Inclusion";
                recy_item = getResources().getStringArray(R.array.side_inclusion_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_side_inclusion).show();
                break;
            }
            case R.id.edit_open_inclusion: {
                pop_title = "Open Inclusion";
                recy_item = getResources().getStringArray(R.array.open_inclusion_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_open_inclusion).show();
                break;
            }
            case R.id.edit_cavity: {
                pop_title = "Cavity";
                recy_item = getResources().getStringArray(R.array.Cravity_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_cavity).show();
                break;
            }
            case R.id.edit_natural: {
                pop_title = "Naturat";
                recy_item = getResources().getStringArray(R.array.natural_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_natural).show();
                break;
            }
            case R.id.edit_inden_natural: {
                pop_title = "Indented Natural";
                recy_item = getResources().getStringArray(R.array.Intended_natural_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_inden_natural).show();
                break;
            }
            case R.id.edit_milky: {
                pop_title = "Milky";
                recy_item = getResources().getStringArray(R.array.milky_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_milky).show();
                break;
            }
            case R.id.edit_luster: {
                pop_title = "Luster";
                recy_item = getResources().getStringArray(R.array.luster_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_luster).show();
                break;
            }
            case R.id.edit_extra_facet: {
                pop_title = "Extra Facet";
                recy_item = getResources().getStringArray(R.array.extra_facet_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_extra_facet).show();
                break;
            }
            case R.id.edit_girdle: {
                pop_title = "Girdle";
                recy_item = getResources().getStringArray(R.array.gridle_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_girdle).show();
                break;
            }
            case R.id.edit_heart_arrow: {
                pop_title = "Heart & Arrow";
                recy_item = getResources().getStringArray(R.array.heart_arrow_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_heart_arrow).show();
                break;
            }
            case R.id.edit_eye_clean: {
                pop_title = "Eye Clean";
                recy_item = getResources().getStringArray(R.array.eye_clean_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_eye_clean).show();
                break;
            }
            case R.id.edit_culet: {
                pop_title = "Culet";
                recy_item = getResources().getStringArray(R.array.culet_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_culet).show();
                break;
            }
            case R.id.edit_graining: {
                pop_title = "Graining";
                recy_item = getResources().getStringArray(R.array.graining_array);
                new Fancy_Dialog(this, pop_title, recy_item, edit_graining).show();
                break;
            }
            case R.id.edit_sss_country: {
                if (GlobalApp.CheckInternetConnection(this)) {
                    new GetCountry().execute(GlobalApp.url + "GetCountry?OnlyLDOfficeCountry=1");
                }
                break;
            }
            case R.id.img_carat_size: {
                pop_title = "Carat";
                plus_button();
                if (plus_iv_state == "single") {
                    recy_item = getResources().getStringArray(R.array.size_list);
                    new Fancy_Dialog(this, pop_title, recy_item, edit_carat_f).show();

                }

                break;
            }
            case R.id.edit_trade: {
                if (GlobalApp.CheckInternetConnection(this)) {
                    new GetTrade().execute("http://ws.laxmidiamond.com/lswebservice.asmx/GetTradeShow");
                }
                break;
            }
            case R.id.btn_home: {
                Intent in = new Intent(getApplicationContext(), activity_desboard.class);
                startActivity(in);
                break;
            }
            case R.id.btn_logout: {
                new Dialog.Logout_Dialog(this);
                break;
            }
            case R.id.btn_ad_search: {
                lout_adav.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.btn_reset: {
                doreset();
                break;
            }
            case R.id.chb_white: {
                if (chb_white.isChecked()) {
                    lout_for_white.setVisibility(View.GONE);
                    lout_for_fancy.setVisibility(View.VISIBLE);
                    chb_fancy.setChecked(false);
                } else {
                    chb_fancy.setChecked(true);
                    lout_for_fancy.setVisibility(View.GONE);
                    lout_for_white.setVisibility(View.VISIBLE);
                }
                break;
            }
            case R.id.chb_fancy: {
                if (chb_fancy.isChecked()) {
                    lout_for_white.setVisibility(View.VISIBLE);
                    lout_for_fancy.setVisibility(View.GONE);
                    chb_white.setChecked(false);
                } else {
                    chb_white.setChecked(true);
                    lout_for_white.setVisibility(View.GONE);
                    lout_for_fancy.setVisibility(View.VISIBLE);

                }
                break;
            }
            case R.id.cb_no_bgm: {
                if (cb_no_bgm.isChecked()) {
                    cb_only_bgm.setChecked(false);
                }
                break;
            }
            case R.id.cb_only_bgm: {
                if (cb_only_bgm.isChecked()) {
                    cb_no_bgm.setChecked(false);
                }
                break;
            }
            case R.id.btn_search_stone: {
                getUrlContent();//according to web service
                Intent in = new Intent(this, Stone_List.class);
                bundle.putString(parent, "STONE");
                in.putExtras(bundle);
                startActivity(in);
                break;
            }


        }
    }

    //get replaced str
    public String getReplacedStr(String name[], String value[], String givenstr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < name.length; i++) {
            if (givenstr.contains(name[i])) {
                stringBuilder.append("," + value[i]);
            }
        }
        return RemoveFirstChar(stringBuilder.toString());
    }

    //remove First char
    private String RemoveFirstChar(String Value) {
        return Value.substring(1);
    }

    private void getUrlContent() {
        Url_Content.clear_parameter();
        //shape
        if (!Adapter_shape.select_item.equals("")) {
            Url_Content.shap = Adapter_shape.select_item.replace("ROUND", "223").
                    replace("CUSHION", "237").replace("PRINCESS", "225").replace("MARQUISE", "229").
                    replace("PEAR", "228").replace("HEART", "230").replace("OVAL", "224").
                    replace("EMERALD", "226").replace("RADIANT", "231").replace("OTHER", "252");

        } else {
            Url_Content.shap = "";
        }
        //lab
        if (!Adapter_lab.select_item.equals("")) {
            Url_Content.lap = Adapter_lab.select_item.replace("GIA", "160").replace("IGI", "162").replace("HRD", "163").replace("EGL", "164");
        } else {
            Url_Content.lap = "";
        }
        //color
        if (!adapter_color.selected_items.equals("")) {
            Url_Content.color = adapter_color.selected_items.replace("D", "41").replace("E", "42").replace("F", "43").replace("G", "44")
                    .replace("H", "45").replace("I", "46").replace("J", "47").replace("K", "48").replace("L", "49").replace("M", "50")
                    .replace("N", "51").replace("O-P", "52").replace("Other", "99999");
        } else {
            Url_Content.color = "";
        }
        //clarity
        if (!adapter_clarity.selected_items.equals("")) {
            Url_Content.clarity = adapter_clarity.selected_items.replace("FL", "29").replace("IF", "30").replace("VVS1", "31").replace("VVS2", "32")
                    .replace("VS1", "33").replace("VS2", "34").replace("SI1", "35").replace("SI2", "36").replace("SI3", "37").replace("I1", "38")
                    .replace("I2", "39").replace("I3", "40");
        } else {
            Url_Content.clarity = "";
        }
        //cut
        if (!adapter_cut.selected_items.equals("")) {
            Url_Content.cut = adapter_cut.selected_items.replace("ID", "71").replace("EX", "72").replace("VG", "73").replace("GD", "74")
                    .replace("FR", "75").replace("PR", "76");
        } else {
            Url_Content.cut = "";
        }
        //polish
        if (!adapter_polish.selected_items.equals("")) {
            Url_Content.polish = adapter_polish.selected_items.replace("ID", "207").replace("EX", "208").replace("VG", "209").replace("GD", "211")
                    .replace("FR", "213").replace("PR", "214");
        } else {
            Url_Content.polish = "";
        }
        //sym
        if (!adapter_sym.selected_items.equals("")) {
            Url_Content.sym = adapter_sym.selected_items.replace("ID", "279").replace("EX", "280").replace("VG", "281").replace("GD", "283")
                    .replace("FR", "285").replace("PR", "286");
        } else {
            Url_Content.sym = "";
        }
        //f_color
        if (!adapter_f_color.selected_items.equals("")) {
            Url_Content.f_color = adapter_f_color.selected_items.replace("BLU", "138").replace("YLW", "139").replace("GRN", "143")
                    .replace("WHT", "140");
        } else {
            Url_Content.f_color = "";
        }
        //f_intensity
        if (!adapter_f_intensity.selected_items.equals("")) {
            Url_Content.f_inten = adapter_f_intensity.selected_items.replace("NON", "128").replace("VSL", "136").replace("FNT", "129").replace("MED", "130")
                    .replace("STG", "132").replace("VST", "134");
        } else {
            Url_Content.f_inten = "";
        }
        //carat List
        if (!edit_carat_f.getText().toString().trim().equals("")) {
            Url_Content.carat_frm = edit_carat_f.getText().toString().trim();
        } else {
            Url_Content.carat_frm = "";
        }
        //fancy
        if (chb_fancy.isChecked()) {
            Url_Content.isfancy = "1";
        }
        //fancy color Id
        /*if (!edit_color.getText().toString().trim().equals("")) {

            String arr_fancy_color[] = getResources().getStringArray(R.array.fancy_color_array);
            String arr_fancy_color_val[] = getResources().getStringArray(R.array.fancy_color_array_val);
            String Ans = edit_color.getText().toString().trim();

            Url_Content.fancy_clr = getReplacedStr(arr_fancy_color,arr_fancy_color_val,Ans);

        } else {
            Url_Content.fancy_clr = "";
        }
        //fancy inten
        if(!edit_intensity.getText().toString().trim().equals(""))
        {
            String arr_fancy_in_name[]=getResources().getStringArray(R.array.fancy_intensity);
            String arr_fancy_in_val[]=getResources().getStringArray(R.array.fancy_intensity_val);
            String Ans=edit_intensity.getText().toString().trim();

            Url_Content.fancy_intcity=getReplacedStr(arr_fancy_in_name,arr_fancy_in_val,Ans);
        }
        else
        {
            Url_Content.fancy_intcity="";
        }
        //overtone
        if(!edit_overtone.getText().toString().trim().equals(""))
        {
            String overtone_nm[]=getResources().getStringArray(R.array.fancy_overtone);
            String overtone_val[]=getResources().getStringArray(R.array.fancy_overtone_val);
            String Ans=edit_overtone.getText().toString().trim();

            Url_Content.fancy_overtone=getReplacedStr(overtone_nm,overtone_val,Ans);
        }
        else
        {
            Url_Content.fancy_overtone="";
        }*/
        //tinge
        if (!edit_tinge.getText().toString().trim().equals("")) {
            String tinge_nm[] = getResources().getStringArray(R.array.tinge_arr);
            String tinge_val[] = getResources().getStringArray(R.array.tinge_arr_val);
            String Ans = edit_tinge.getText().toString().trim();

            Url_Content.tinge_s = getReplacedStr(tinge_nm, tinge_val, Ans);
        } else {
            Url_Content.tinge_s = "";
        }
        //tbl inclusion
        if (!edit_tbl_inclusion.getText().toString().trim().equals("")) {
            String tinge_nm[] = getResources().getStringArray(R.array.tinge_arr);
            String tinge_val[] = getResources().getStringArray(R.array.tinge_arr_val);
            String Ans = edit_tinge.getText().toString().trim();

            Url_Content.tinge_s = getReplacedStr(tinge_nm, tinge_val, Ans);
        } else {
            Url_Content.tinge_s = "";
        }
        //side in
        if (!edit_side_inclusion.getText().toString().trim().equals("")) {
            String side_in_nm[] = getResources().getStringArray(R.array.side_inclusion_array);
            String side_in_val[] = getResources().getStringArray(R.array.side_inclusion_val);
            String Ans = edit_side_inclusion.getText().toString().trim();

            Url_Content.side_inc_s = getReplacedStr(side_in_nm, side_in_val, Ans);
        } else {
            Url_Content.side_inc_s = "";
        }
        //open inc
        if (!edit_open_inclusion.getText().toString().trim().equals("")) {
            String open_in_nm[] = getResources().getStringArray(R.array.open_inclusion_array);
            String open_in_val[] = getResources().getStringArray(R.array.open_inclusion_val);
            String Ans = edit_open_inclusion.getText().toString().trim();

            Url_Content.open_inc_s = getReplacedStr(open_in_nm, open_in_val, Ans);
        } else {
            Url_Content.open_inc_s = "";
        }
        //cavity
        if (!edit_cavity.getText().toString().trim().equals("")) {
            String cavity_nm[] = getResources().getStringArray(R.array.Cravity_array);
            String cavity_val[] = getResources().getStringArray(R.array.cavity_val);
            String Ans = edit_cavity.getText().toString().trim();

            Url_Content.cravity_s = getReplacedStr(cavity_nm, cavity_val, Ans);
        } else {
            Url_Content.cravity_s = "";
        }
        //natural
        if (!edit_natural.getText().toString().trim().equals("")) {
            String natural_nm[] = getResources().getStringArray(R.array.natural_array);
            String natural_val[] = getResources().getStringArray(R.array.natural_val);
            String Ans = edit_natural.getText().toString().trim();

            Url_Content.natural_s = getReplacedStr(natural_nm, natural_val, Ans);
        } else {
            Url_Content.natural_s = "";
        }
        //Indented Natural
        if (!edit_inden_natural.getText().toString().trim().equals("")) {
            String in_natural_nm[] = getResources().getStringArray(R.array.Intended_natural_array);
            String in_natural_val[] = getResources().getStringArray(R.array.Intended_natural_val);
            String Ans = edit_inden_natural.getText().toString().trim();

            Url_Content.indented_natural_s = getReplacedStr(in_natural_nm, in_natural_val, Ans);
        } else {
            Url_Content.indented_natural_s = "";
        }
        //milky
        if (!edit_milky.getText().toString().trim().equals("")) {
            String milky_nm[] = getResources().getStringArray(R.array.milky_array);
            String milky_val[] = getResources().getStringArray(R.array.milky_val);
            String Ans = edit_milky.getText().toString().trim();

            Url_Content.milky_frm = getReplacedStr(milky_nm, milky_val, Ans);
        } else {
            Url_Content.milky_frm = "";
        }
        //luster
        if (!edit_luster.getText().toString().trim().equals("")) {
            String luster_nm[] = getResources().getStringArray(R.array.luster_array);
            String luster_val[] = getResources().getStringArray(R.array.luster_val);
            String Ans = edit_luster.getText().toString().trim();

            Url_Content.luster_frm = getReplacedStr(luster_nm, luster_val, Ans);
        } else {
            Url_Content.luster_frm = "";
        }
        //Extra facet
        if (!edit_extra_facet.getText().toString().trim().equals("")) {
            String ex_facet_nm[] = getResources().getStringArray(R.array.extra_facet_array);
            String ex_facet_val[] = getResources().getStringArray(R.array.extra_facet_val);
            String Ans = edit_extra_facet.getText().toString().trim();

            Url_Content.extra_facet_s = getReplacedStr(ex_facet_nm, ex_facet_val, Ans);
        } else {
            Url_Content.extra_facet_s = "";
        }
        //girdle
        if (!edit_girdle.getText().toString().trim().equals("")) {
            String girdle_nm[] = getResources().getStringArray(R.array.gridle_array);
            String girdle_val[] = getResources().getStringArray(R.array.gridle_val);
            String Ans = edit_girdle.getText().toString().trim();

            Url_Content.gridle_s = getReplacedStr(girdle_nm, girdle_val, Ans);
        } else {
            Url_Content.gridle_s = "";
        }
        //H&A
        if (!edit_heart_arrow.getText().toString().trim().equals("")) {
            String hAndA_nm[] = getResources().getStringArray(R.array.heart_arrow_array);
            String hAndA_val[] = getResources().getStringArray(R.array.heart_arrow_val);
            String Ans = edit_heart_arrow.getText().toString().trim();

            Url_Content.hrt_arw_frm = getReplacedStr(hAndA_nm, hAndA_val, Ans);
        } else {
            Url_Content.hrt_arw_frm = "";
        }
        //eye clean
        if (!edit_eye_clean.getText().toString().trim().equals("")) {
            String eyeClean_nm[] = getResources().getStringArray(R.array.eye_clean_array);
            String eyeClean_val[] = getResources().getStringArray(R.array.eye_clean_val);
            String Ans = edit_eye_clean.getText().toString().trim();

            Url_Content.eye_c_s = getReplacedStr(eyeClean_nm, eyeClean_val, Ans);
        } else {
            Url_Content.eye_c_s = "";
        }
        //culet
        if (!edit_culet.getText().toString().trim().equals("")) {
            String culet_nm[] = getResources().getStringArray(R.array.culet_array);
            String culet_val[] = getResources().getStringArray(R.array.culet_val);
            String Ans = edit_culet.getText().toString().trim();

            Url_Content.culet_frm = getReplacedStr(culet_nm, culet_val, Ans);
        } else {
            Url_Content.culet_frm = "";
        }
        //graining
        if (!edit_graining.getText().toString().trim().equals("")) {
            String graining_nm[] = getResources().getStringArray(R.array.graining_array);
            String graining_val[] = getResources().getStringArray(R.array.graining_val);
            String Ans = edit_graining.getText().toString().trim();

            Url_Content.graining_s = getReplacedStr(graining_nm, graining_val, Ans);
        } else {
            Url_Content.graining_s = "";
        }

        //stoneId
        if (!edit_stone_id.getText().toString().trim().equals("")) {
            Url_Content.ston_id = edit_stone_id.getText().toString().trim();
        } else {
            Url_Content.ston_id = "";
        }

    }


    public void plus_button() {
        if (plus_iv_state == "double") {
            img_carat_size.setImageResource(R.drawable.advancesearch_sizeclose);
            edit_carat_f.setText("");
            edit_carat_t.setText("");
            edit_carat_t.setVisibility(View.GONE);
            edit_carat_f.setHint(" Size");
            plus_iv_state = "single";
            edit_carat_f.setCursorVisible(false);
            edit_carat_f.setFocusable(false);
            edit_carat_f.setFocusableInTouchMode(false);
        } else {
            img_carat_size.setImageResource(R.drawable.advancesearch_sizeplus);
            edit_carat_f.setText("");
            edit_carat_t.setText("");
            edit_carat_t.setVisibility(View.VISIBLE);
            edit_carat_f.setHint(" From");
            edit_carat_f.setCursorVisible(true);
            edit_carat_f.setFocusable(true);
            edit_carat_f.setFocusableInTouchMode(true);
            plus_iv_state = "double";
        }
    }

    public void doreset() {
        Adapter_lab.notifyDataSetChanged();
        Adapter_shape.notifyDataSetChanged();
        adapter_cut.notifyDataSetChanged();
        adapter_polish.notifyDataSetChanged();
        adapter_sym.notifyDataSetChanged();
        adapter_color.notifyDataSetChanged();
        adapter_clarity.notifyDataSetChanged();
        adapter_f_color.notifyDataSetChanged();
        adapter_f_intensity.notifyDataSetChanged();
        Map_lab.clear();
        Map_shape.clear();
        Map_cut.clear();
        Map_polish.clear();
        Map_sym.clear();
        Map_color.clear();
        Map_clarity.clear();
        Map_f_intencity.clear();
        Map_f_color.clear();
        img_carat_size.setImageResource(R.drawable.advancesearch_sizeplus);
        edit_carat_f.setText("");
        edit_carat_t.setText("");
        edit_carat_f.setHint("From");
        edit_carat_t.setVisibility(View.VISIBLE);
        edit_carat_f.setCursorVisible(true);
        edit_carat_f.setFocusable(true);
        edit_carat_f.setFocusableInTouchMode(true);
        plus_iv_state = "double";
        //for all check box
        chb_white.setChecked(false);
        chb_fancy.setChecked(false);
        cb_no_bgm.setChecked(false);
        cb_only_bgm.setChecked(false);
        cb_only_brown.setChecked(false);
        cb_only_milky.setChecked(false);
        cb_only_green.setChecked(false);
        chb_3x.setChecked(false);
        chb_2x.setChecked(false);

        //for all edittext
        edit_overtone.setText("");
        edit_color.setText("");
        edit_intensity.setText("");
        edit_usd_f.setText("");
        edit_usd_t.setText("");
        edit_total_usd_f.setText("");
        edit_total_usd_t.setText("");
        edit_discount_f.setText("");
        edit_discount_t.setText("");
        edit_stone_id.setText("");
        edit_lot_no.setText("");
        edit_certi_no.setText("");
        edit_sss_country.setText("");
        edit_trade.setText("");
        edit_diameter_f.setText("");
        edit_diameter_t.setText("");
        edit_total_depth_f.setText("");
        edit_total_depth_t.setText("");
        edit_total_f.setText("");
        edit_total_t.setText("");
        edit_crown_h_f.setText("");
        edit_crown_h_t.setText("");
        edit_crown_a_f.setText("");
        edit_crown_a_t.setText("");
        edit_pav_h_f.setText("");
        edit_pav_h_t.setText("");
        edit_pav_a_f.setText("");
        edit_pav_a_t.setText("");
        edit_girdle_f.setText("");
        edit_girdle_t.setText("");
        edit_ratio_f.setText("");
        edit_ratio_t.setText("");
        edit_tinge.setText("");
        edit_tbl_inclusion.setText("");
        edit_side_inclusion.setText("");
        edit_open_inclusion.setText("");
        edit_cavity.setText("");
        edit_natural.setText("");
        edit_inden_natural.setText("");
        edit_milky.setText("");
        edit_luster.setText("");
        edit_extra_facet.setText("");
        edit_girdle.setText("");
        edit_heart_arrow.setText("");
        edit_eye_clean.setText("");
        edit_culet.setText("");
        edit_graining.setText("");

    }

    class GetCountry extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;
        String url;
        XmlHandler myXMLHandler;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Single_stone_search.this);
            pDialog.setTitle(GlobalApp.title);
            pDialog.setMessage("Please Wait");
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    GetCountry.this.cancel(true);
                }
            });
            pDialog.show();
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
            GlobalApp.Arr_Ldofc_list.clear();
            GlobalApp.Arr_Ldofc_list.addAll(itemsList);
            Log.d("Size", "" + GlobalApp.Arr_Ldofc_list.size());
            new Ldofc_dailog(Single_stone_search.this, GlobalApp.Arr_Ldofc_list, edit_sss_country).show();
            pDialog.dismiss();
        }
    }

    class GetTrade extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;
        String url;
        XmlHandler myXMLHandler;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Single_stone_search.this);
            pDialog.setTitle(GlobalApp.title);
            pDialog.setMessage("Please Wait");
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    GetTrade.this.cancel(true);
                }
            });
            pDialog.show();
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
            GlobalApp.Arr_trade_list.clear();
            GlobalApp.Arr_trade_list.addAll(itemsList);
            Log.d("Size", "" + GlobalApp.Arr_trade_list.size());
            new Trade_Dialog(Single_stone_search.this, GlobalApp.Arr_trade_list, edit_trade).show();
            pDialog.dismiss();
        }

    }


    /*class myAsynk extends AsyncTask<Void, Void, Void> {
        Progress_Dialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new Progress_Dialog(Single_stone_search.this);
            pDialog.setTitle(GlobalApp.title);
            pDialog.setMessage("Please Wait");
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    myAsynk.this.cancel(true);
                }
            });
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            if (!Adapter_lab.select_item.equals("")) {
                url_Content.lap = Adapter_lab.select_item;
            } else {
                url_Content.lap = "";

            }
            if (!adapter_cut.selected_items.equals("")) {
                url_Content.cut = adapter_cut.selected_items;
            } else {
                url_Content.cut = "";

            }
            if (!adapter_polish.selected_items.equals("")) {
                url_Content.polish = adapter_polish.selected_items;
            } else {
                url_Content.polish = "";

            }
            if (!adapter_sym.selected_items.equals("")) {
                url_Content.sym = adapter_sym.selected_items;
            } else {
                url_Content.sym = "";

            }
            if (!adapter_f_color.selected_items.equals("")) {
                url_Content.f_color = adapter_f_color.selected_items;
            } else {
                url_Content.f_color = "";

            }
            if (!adapter_f_intensity.selected_items.equals("")) {
                url_Content.f_inten = adapter_f_intensity.selected_items;
            } else {
                url_Content.f_inten = "";
            }
            if (!adapter_clarity.selected_items.equals("")) {
                url_Content.clarity = adapter_clarity.selected_items;
            } else {
                url_Content.clarity = "";
            }
            if (!edit_usd_f.getText().toString().trim().equals("")) {
                url_Content.pricrt_from = edit_usd_f.getText().toString().trim();
            } else {
                url_Content.pricrt_from = "";
            }
            if (!edit_usd_t.getText().toString().trim().equals("")) {
                url_Content.pricrt_to = edit_usd_t.getText().toString().trim();
            } else {
                url_Content.pricrt_to = "";
            }
            if (!edit_total_usd_f.getText().toString().trim().equals("")) {
                url_Content.total_amt_f = edit_total_usd_f.getText().toString().trim();
            } else {
                url_Content.total_amt_f = "";
            }
            if (!edit_total_usd_t.getText().toString().trim().equals("")) {
                url_Content.total_amt_t = edit_total_usd_t.getText().toString().trim();
            } else {
                url_Content.total_amt_t = "";
            }
            //discount
            if (!edit_discount_f.getText().toString().trim().equals("")) {
                url_Content.discount_f = edit_discount_f.getText().toString().trim();
            } else {
                url_Content.discount_f = "";
            }
            if (!edit_discount_t.getText().toString().trim().equals("")) {
                url_Content.discount_t = edit_discount_t.getText().toString().trim();
            } else {
                url_Content.discount_t = "";
            }
            //stone id
            if (!edit_stone_id.getText().toString().trim().equals("")) {
                url_Content.ston_id = edit_stone_id.getText().toString().trim();
            } else {
                url_Content.ston_id = "";
            }
            //certi no
            if (!edit_certi_no.getText().toString().trim().equals("")) {
                url_Content.cert_id = edit_certi_no.getText().toString().trim();
            } else {
                url_Content.cert_id = "";
            }
            //raplet
            if (!edit_lot_no.getText().toString().trim().equals("")) {
                url_Content.rap_lot_no = edit_lot_no.getText().toString().trim();
            } else {
                url_Content.rap_lot_no = "";
            }
            if (!edit_sss_country.getText().toString().trim().equals("")) {
                url_Content.Country_id_list = edit_sss_country.getText().toString().trim();
            } else {
                url_Content.Country_id_list = "";
            }
            if (!edit_trade.getText().toString().trim().equals("")) {
                url_Content.trade_shw = edit_trade.getText().toString().trim();
            } else {
                url_Content.trade_shw = "";
            }
            if (!edit_diameter_f.getText().toString().trim().equals("")) {
                url_Content.dia_frm = edit_diameter_f.getText().toString().trim();
            } else {
                url_Content.dia_frm = "";
            }
            if (!edit_diameter_t.getText().toString().trim().equals("")) {
                url_Content.dia_to = edit_diameter_t.getText().toString().trim();
            } else {
                url_Content.dia_to = "";
            }
            if (!edit_total_depth_f.getText().toString().trim().equals("")) {
                url_Content.total_dp_frm = edit_total_depth_f.getText().toString().trim();
            } else {
                url_Content.total_dp_frm = "";
            }
            if (!edit_total_depth_t.getText().toString().trim().equals("")) {
                url_Content.total_dp_to = edit_total_depth_t.getText().toString().trim();
            } else {
                url_Content.total_dp_to = "";
            }
            if (!edit_total_f.getText().toString().trim().equals("")) {
                url_Content.table_frm = edit_total_f.getText().toString().trim();
            } else {
                url_Content.table_frm = "";
            }
            if (!edit_total_t.getText().toString().trim().equals("")) {
                url_Content.table_to = edit_total_t.getText().toString().trim();
            } else {
                url_Content.table_to = "";
            }
            if (!edit_crown_h_f.getText().toString().trim().equals("")) {
                url_Content.crown_h_frm = edit_crown_h_f.getText().toString().trim();
            } else {
                url_Content.crown_h_frm = "";
            }
            if (!edit_crown_h_t.getText().toString().trim().equals("")) {
                url_Content.crown_h_to = edit_crown_h_t.getText().toString().trim();
            } else {
                url_Content.crown_h_to = "";
            }
            if (!edit_crown_a_f.getText().toString().trim().equals("")) {
                url_Content.crown_a_frm = edit_crown_a_f.getText().toString().trim();
            } else {
                url_Content.crown_a_frm = "";
            }
            if (!edit_crown_a_t.getText().toString().trim().equals("")) {
                url_Content.crown_a_to = edit_crown_a_t.getText().toString().trim();
            } else {
                url_Content.crown_h_to = "";
            }
            if (!edit_pav_h_f.getText().toString().trim().equals("")) {
                url_Content.pav_h_frm = edit_pav_h_f.getText().toString().trim();
            } else {
                url_Content.pav_h_frm = "";
            }
            if (!edit_pav_h_t.getText().toString().trim().equals("")) {
                url_Content.pav_h_to = edit_pav_h_t.getText().toString().trim();
            } else {
                url_Content.pav_h_to = "";
            }
            if (!edit_pav_a_f.getText().toString().trim().equals("")) {
                url_Content.pav_a_frm = edit_pav_a_f.getText().toString().trim();
            } else {
                url_Content.pav_a_frm = "";
            }
            if (!edit_pav_a_t.getText().toString().trim().equals("")) {
                url_Content.pav_a_to = edit_pav_a_t.getText().toString().trim();
            } else {
                url_Content.pav_a_to = "";
            }
            if (!edit_girdle_f.getText().toString().trim().equals("")) {
                url_Content.gridle_frm = edit_girdle_f.getText().toString().trim();
            } else {
                url_Content.gridle_frm = "";
            }
            if (!edit_girdle_t.getText().toString().trim().equals("")) {
                url_Content.gridle_to = edit_girdle_t.getText().toString().trim();
            } else {
                url_Content.gridle_to = "";
            }
            if (!edit_ratio_f.getText().toString().trim().equals("")) {
                url_Content.ratio_frm = edit_ratio_f.getText().toString().trim();
            } else {
                url_Content.ratio_frm = "";
            }
            if (!edit_ratio_t.getText().toString().trim().equals("")) {
                url_Content.ratio_to = edit_ratio_t.getText().toString().trim();
            } else {
                url_Content.ratio_to = "";
            }
            if (!edit_tinge.getText().toString().trim().equals("")) {
                url_Content.tinge_s = edit_tinge.getText().toString().trim();
            } else {
                url_Content.tinge_s = "";
            }
            if (!edit_tbl_inclusion.getText().toString().trim().equals("")) {
                url_Content.table_inc_s = edit_tbl_inclusion.getText().toString().trim();
            } else {
                url_Content.table_inc_s = "";
            }
            if (!edit_side_inclusion.getText().toString().trim().equals("")) {
                url_Content.side_inc_s = edit_side_inclusion.getText().toString().trim();
            } else {
                url_Content.side_inc_s = "";
            }
            if (!edit_open_inclusion.getText().toString().trim().equals("")) {
                url_Content.open_inc_s = edit_open_inclusion.getText().toString().trim();
            } else {
                url_Content.open_inc_s = "";
            }
            if (!edit_natural.getText().toString().trim().equals("")) {
                url_Content.natural_s = edit_natural.getText().toString().trim();
            } else {
                url_Content.natural_s = "";
            }
            if (!edit_inden_natural.getText().toString().trim().equals("")) {
                url_Content.indented_natural_s = edit_inden_natural.getText().toString().trim();
            } else {
                url_Content.indented_natural_s = "";
            }
            if (!edit_milky.getText().toString().trim().equals("")) {
                url_Content.only_milky = edit_milky.getText().toString().trim();
            } else {
                url_Content.only_milky = "";
            }
            if (!edit_luster.getText().toString().trim().equals("")) {
                url_Content.luster_s = edit_luster.getText().toString().trim();
            } else {
                url_Content.luster_s = "";
            }

            if (!edit_extra_facet.getText().toString().trim().equals("")) {
                url_Content.extra_facet_s = edit_extra_facet.getText().toString().trim();
            } else {
                url_Content.extra_facet_s = "";
            }
            if (!edit_girdle.getText().toString().trim().equals("")) {
                url_Content.gridle_s = edit_girdle.getText().toString().trim();
            } else {
                url_Content.gridle_s = "";
            }
            if (!edit_heart_arrow.getText().toString().trim().equals("")) {
                url_Content.hrt_arrw_s = edit_heart_arrow.getText().toString().trim();
            } else {
                url_Content.hrt_arrw_s = "";
            }
            if (!edit_eye_clean.getText().toString().trim().equals("")) {
                url_Content.eye_c_s = edit_eye_clean.getText().toString().trim();
            } else {
                url_Content.eye_c_s = "";
            }
            if (!edit_culet.getText().toString().trim().equals("")) {
                url_Content.culet_s = edit_culet.getText().toString().trim();
            } else {
                url_Content.culet_s = "";
            }
            if (!edit_graining.getText().toString().trim().equals("")) {
                url_Content.graining_s = edit_graining.getText().toString().trim();
            } else {
                url_Content.graining_s = "";
            }

            return null;
        }

    }*/

}

