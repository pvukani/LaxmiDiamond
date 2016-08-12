package com.dnktechnologies.laxmidiamond;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import Bean.Model;
import Dialog.*;

/**
 * Created by parth on 6/21/2016.
 */
public class GlobalApp {
    public static String stone_id;
    public static List<Model> Arr_for_grid=new ArrayList<Model>();
    public static ArrayList<String> List_stone_detail_val=new ArrayList<String>();
    public static ArrayList<String> List_QC_detail_val=new ArrayList<String>();
    public static ArrayList<String> List_param_detail_val=new ArrayList<String>();
    public static ArrayList<String> List_comment_detail_val=new ArrayList<String>();
    public static String link;
    static String url = "http://ws.laxmidiamond.com/lswebservice.asmx/";
    static String msg_connection_Prob = "Connction not Found";
    public static String msg_noResult = "Your Search Result Has 0\nStone.Please Searh Again\nMore Precisely";
    static String msg_reg_compName = "Company Name not Shound be Empty";
    static String msg_reg_fname = "First Name not Shound be Empty";
    static String msg_reg_lName = "Last Name not Shound be Empty";
    static String msg_reg_User = "User Name not Shound be Empty";
    static String msg_reg_pass = "Password not Shound be Empty";
    static String msg_reg_wrongpass = "Password Mismatch";
    static String msg_reg_email = "Email not Shound be Empty";
    static String msg_reg_phone = "Phone No. not Shound be Empty";
    static String msg_reg_country = "Counry not Shound be Empty";
    static String msg_reg_state = "State not Shound be Empty";
    static String msg_reg_city = "City not Shound be Empty";
    static String msg_sure = "Are U Sure?";
    static String msg_login_uname = "UserName not Shound be Empty";
    static String msg_login_pass = "Password not Shound be Empty";
    public static String title = "LaxmiDiamond";


    public  static  String User_Id = "";
    public static List<Model> arr_model_list = new ArrayList<Model>();

    public static List<Model> Arr_Result_stone = new ArrayList<Model>();
    public static List<Model> Arr_Bgm_stone=new ArrayList<Model>();
    public static List<Model> Arr_revised_stone=new ArrayList<Model>();
    public static List<Model> Arr_newArr_stone=new ArrayList<Model>();

    public static List<Model> Arr_Ldofc_list = new ArrayList<Model>();
    public static List<Model> Arr_trade_list=new ArrayList<Model>();
    public static String msg_reg_retype = "Plz Enter Conform password";
    public static String msg_reg_wrong_email = "Incorrect Email";
    public static List<Model> Arr_state_list = new ArrayList<Model>();
    public static List<Model> Arr_Login = new ArrayList<Model>();
    public static List<Model> ArrList_offer=new ArrayList<Model>();
    public static List<Model> Ar_List_quick=new ArrayList<Model>();
    public static String msg_unauto = "Sorry...Ur Not Authorised";
    public static String msg_reg_prefix = "PLZ Select prefix";


    public static boolean CheckInternetConnection(Activity context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
//            Log.e("Test", "is available");
            return true;
        } else {
            new OK_Dialog(context, msg_connection_Prob);
//            Log.e("Test", "is not available");
            return false;
        }
    }


    public static boolean isBlank(EditText editText) {
        if (editText.getText().toString().trim().equals("")) {
            return true;
        }
        return false;
    }

    public static void takeDefaultAction(EditText editText, Context context,
                                         String text) {

        int ecolor = Color.RED; // whatever color you want
        ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(text);
        ssbuilder.setSpan(fgcspan, 0, text.length(), 0);
        editText.setError(text);
        editText.requestFocus();
    }
    public static String obtainLink() {
        link = GlobalApp.url + "GetSingleStoneSearch?"
                + "" + "PageNo=1"
                + "&ShapeIDList=" + Url_Content.shap + "&LabIDList="
                + Url_Content.lap + "&ColorIDList=" + Url_Content.color
                + "&ClarityIDList=" + Url_Content.clarity + "&CaratList="
                + Url_Content.carat_frm + "&CutIDList=" + Url_Content.cut
                + "&PolishIDList=" + Url_Content.polish
                + "&SymmetryIDList=" + Url_Content.sym + "&IsFancyColor="
                + Url_Content.isfancy + "&FancyColorIDList="
                + Url_Content.fancy_clr + "&FCIntensityIDList="
                + Url_Content.fancy_intcity + "&FCOvertoneIDList="
                + Url_Content.fancy_overtone + "&FluorIntensityIDList="
                + Url_Content.f_inten + "&FluorColorIDList="
                + Url_Content.f_color + "&StoneNoList="
                + Url_Content.ston_id + "&CertificateNoList="
                + Url_Content.cert_id + "&RapnetLotNoList="
                + Url_Content.rap_lot_no + "&CountryIDList="
                + Url_Content.Country_id_list + "&PricePerCtsFrom="
                + Url_Content.pricrt_from + "&PricePerCtsTo="
                + Url_Content.pricrt_to + "&AmountFrom="
                + Url_Content.tootal_amt_f + "&AmountTo="
                + Url_Content.total_amt_t + "&DiscountFrom="
                + Url_Content.discount_f + "&DiscountTo="
                + Url_Content.discount_t + "&DiameterFrom="
                + Url_Content.dia_frm + "&DiameterTo=" + Url_Content.dia_to
                + "&TotalDepthPerFrom=" + Url_Content.total_dp_frm
                + "&TotalDepthPerTo=" + Url_Content.total_dp_to
                + "&TablePerFrom=" + Url_Content.table_frm + "&TablePerTo="
                + Url_Content.table_to + "&CrownHeightFrom="
                + Url_Content.crown_h_frm + "&CrownHeightTo="
                + Url_Content.crown_h_to + "&CrownAngleFrom="
                + Url_Content.crown_a_frm + "&CrownAngleTo="
                + Url_Content.crown_a_to + "&PavilionHeightFrom="
                + Url_Content.pav_h_frm + "&PavilionHeightTo="
                + Url_Content.pav_h_to + "&PavilionAngleFrom="
                + Url_Content.pav_a_frm + "&PavilionAngleTo="
                + Url_Content.pav_a_to + "&GirdlePerFrom="
                + Url_Content.gridle_frm + "&GirdlePerTo="
                + Url_Content.gridle_to + "&GirdleIDList="
                + Url_Content.gridle_s + "&RatioFrom="
                + Url_Content.ratio_frm + "&RatioTo="
                + Url_Content.ratio_to + "&TingeIDList="
                + Url_Content.tinge_s + "&TableInclusionIDList="
                + Url_Content.table_inc_s + "&SideInclusionIDList="
                + Url_Content.side_inc_s + "&OpenInclusionIDList="
                + Url_Content.open_inc_s + "&ExtrafacetIDList="
                + Url_Content.extra_facet_s + "&NaturalIDList="
                + Url_Content.natural_s + "&IndentedNaturalIDList="
                + Url_Content.indented_natural_s + "&MilkyIDList="
                + Url_Content.milky_frm + "&LusterIDList="
                + Url_Content.luster_frm + "&HandAIDList="
                + Url_Content.hrt_arw_frm + "&CuletIDList="
                + Url_Content.culet_frm + "&GrainingIDList="
                + Url_Content.graining_s + "&EyeCleanIDList="
                + Url_Content.eye_c_s + "&CavityIDList="
                + Url_Content.cravity_s + "&BrokeragePer=" + ""
                + "&CommissionPer=" + "" + "&TradeShowID="
                + Url_Content.TradeShowID + "&NoBGM=" + Url_Content.no_bgm
                + "&SpecialDeal=" + Url_Content.SpecialDeal
                + "&FreshStock=" + Url_Content.FreshStock
                + "&RevisedPrice=" + Url_Content.RevisedPrice
                + "&FullStock=" + Url_Content.FullStock + "&Source="
                + "ANDROID" + "&UserID=" + GlobalApp.User_Id;

        return link;
    }


}
