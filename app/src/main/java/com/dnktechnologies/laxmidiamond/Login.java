package com.dnktechnologies.laxmidiamond;


import Bean.Model;
import Dialog.*;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.crashlytics.android.Crashlytics;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import Handler.XmlHandler;
import io.fabric.sdk.android.Fabric;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Login extends AppCompatActivity {

    EditText reg_dialog_prifix;
    Button btn_login, btn_register;
    Button btn_login_login, btn_login_cancel;
    Button getbtn_register_register, getBtn_register_cancel;
    Dialog dialog;
    EditText login_edittext_username, login_edittext_password;
    EditText reg_editText_compName;
    EditText reg_editText_fName;
    EditText reg_editText_LName;
    EditText reg_editText_UserName;
    EditText reg_editText_Pass;
    EditText reg_editText_conform_Pass;
    EditText reg_editText_email;
    EditText reg_editText_phone;
    EditText reg_editText_city;
    EditText reg_editText_country;
    EditText reg_editText_state;
    Button btn_register_register, btn_register_cancel;
    String userId;
    RecyclerView recy_register_statelist, recy_register_countrylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);




        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalApp.CheckInternetConnection(Login.this)) ;
                {
                    register_dialog();
                }

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (GlobalApp.CheckInternetConnection(Login.this)) {
                    login_dialog();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void register_dialog() {
        {
            dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCanceledOnTouchOutside(true);
            dialog.setContentView(R.layout.register_dialog);
            Display display = this.getWindowManager().getDefaultDisplay();
            int width = display.getWidth();

            android.view.WindowManager.LayoutParams lp;
            lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = (int) (width - (width * 0.60));
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(lp);
            dialog.show();


            reg_dialog_prifix = (EditText) dialog.findViewById(R.id.spin_prefix);
            reg_editText_compName = (EditText) dialog.findViewById(R.id.edittext_register_compName);
            reg_editText_fName = (EditText) dialog.findViewById(R.id.edittext_register_fName);
            reg_editText_LName = (EditText) dialog.findViewById(R.id.edittext_register_lName);
            reg_editText_UserName = (EditText) dialog.findViewById(R.id.edittext_register_userName);
            reg_editText_Pass = (EditText) dialog.findViewById(R.id.edittext_register_Pass);
            reg_editText_conform_Pass = (EditText) dialog.findViewById(R.id.edittext_register_ConforPass);
            reg_editText_email = (EditText) dialog.findViewById(R.id.edittext_register_email);
            reg_editText_phone = (EditText) dialog.findViewById(R.id.edittext_register_phone);
            reg_editText_country = (EditText) dialog.findViewById(R.id.edittext_register_country);
            reg_editText_state = (EditText) dialog.findViewById(R.id.edittext_register_state);
            recy_register_countrylist = (RecyclerView) findViewById(R.id.recycler_view_countryList);
            new GetCountry_List().execute(GlobalApp.url + "GetCountry?OnlyLDOfficeCountry=0");

            reg_editText_city = (EditText) dialog.findViewById(R.id.edittext_register_city);
            getbtn_register_register = (Button) dialog.findViewById(R.id.btn_register_register);
            getBtn_register_cancel = (Button) dialog.findViewById(R.id.btn_register_cancel);

            //for prefix dropdown
            reg_dialog_prifix.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    prefixdialog(reg_dialog_prifix);
                }
            });

            //for counrty dropdown
            reg_editText_country.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (GlobalApp.CheckInternetConnection(Login.this)) {
                        new Country_Dialog(Login.this, reg_editText_country).show();
                    }
                }


            });


            //for state dropdown
            reg_editText_state.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new GetState_List().execute(GlobalApp.url + "GetState?CountryID=" + Country_Dialog.CountryPos);
                    Log.d("size", "" + GlobalApp.Arr_state_list.size());
//                    new State_Dialog(Login.this, reg_editText_state).show();


                }
            });


            getbtn_register_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (register()) {
                        if (reg_editText_Pass.getText().toString().equals(reg_editText_conform_Pass.getText().toString())) {

                        } else {

                        }
                    }
                }
            });

            getBtn_register_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


        }

    }

    class GetCountry_List extends AsyncTask<String, Void, String> {
        String url;
        XmlHandler myXMLHandler;
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("Size", "in pre");
            pDialog = new ProgressDialog(Login.this);
            pDialog.setTitle(GlobalApp.title);
            pDialog.setMessage("Please Wait");
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    GetCountry_List.this.cancel(true);
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
                Log.d("Size", "in onback");
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
            Log.d("Size", "in post");
            ArrayList<Model> itemsList = myXMLHandler.getItemsList();
            GlobalApp.arr_model_list.clear();
            GlobalApp.arr_model_list.addAll(itemsList);
            Log.d("Size", "" + GlobalApp.arr_model_list.size());
            pDialog.dismiss();
        }
    }

    class GetState_List extends AsyncTask<String, Void, String> {
        String url;
        XmlHandler myXMLHandler;
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setTitle(GlobalApp.title);
            pDialog.setMessage("Please Wait");
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    GetState_List.this.cancel(true);
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
            pDialog.dismiss();
            ArrayList<Model> itemsList = myXMLHandler.getItemsList();
            GlobalApp.Arr_state_list.clear();
            GlobalApp.Arr_state_list.addAll(itemsList);
            new State_Dialog(Login.this, reg_editText_state).show();
        }


    }

    //service for Login
    public class GetLogin extends AsyncTask<String, Void, String> {
        Progress_Dialog pDialog =new Progress_Dialog(Login.this);
        XmlHandler myXMLHandler;
        String url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           /* pDialog = new ProgressDialog(Login.this);
            pDialog.setTitle(GlobalApp.title);
            pDialog.setMessage("Please Wait...");
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    GetLogin.this.cancel(true);
                }
            });
           */ pDialog.show();
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
            ArrayList<Model> itemsList_login = myXMLHandler.getItemsList();
            GlobalApp.User_Id=itemsList_login.get(0).getUser_id();
            Log.i("GlobalApp.User_Id",""+GlobalApp.User_Id);
            if (null!= itemsList_login.get(0).getErrormessage())
            {
                new OK_Dialog(Login.this, GlobalApp.msg_unauto);
               pDialog.dismiss();
                dialog.dismiss();
            }
            else
            {
                Intent in_desboard = new Intent(Login.this, activity_desboard.class);
                startActivity(in_desboard);
            }

        }


    }

    // login validation
    public boolean loginvalidation() {
        if (GlobalApp.isBlank(login_edittext_username)) {
            new OK_Dialog(Login.this, GlobalApp.msg_login_uname);
            return false;
        } else if (GlobalApp.isBlank(login_edittext_password)) {
            new OK_Dialog(Login.this, GlobalApp.msg_login_pass);
            return false;
        }
        return true;

    }

    // register validation
    boolean register() {
        try {
            if (GlobalApp.isBlank(reg_editText_compName)) {
                new OK_Dialog(Login.this, GlobalApp.msg_reg_compName);
                return false;
            }
            if (GlobalApp.isBlank(reg_dialog_prifix)) {
                new OK_Dialog(Login.this, GlobalApp.msg_reg_prefix);
                return false;
            }
            if (GlobalApp.isBlank(reg_editText_fName)) {
                new OK_Dialog(Login.this, GlobalApp.msg_reg_fname);
                return false;
            }
            if (GlobalApp.isBlank(reg_editText_LName)) {
                new OK_Dialog(Login.this, GlobalApp.msg_reg_lName);
                return false;
            }
            if (GlobalApp.isBlank(reg_editText_UserName)) {
                new OK_Dialog(Login.this, GlobalApp.msg_reg_User);
                return false;
            }
            if (GlobalApp.isBlank(reg_editText_Pass)) {
                new OK_Dialog(Login.this, GlobalApp.msg_reg_pass);
                return false;
            }
            if (GlobalApp.isBlank(reg_editText_conform_Pass)) {
                new OK_Dialog(Login.this, GlobalApp.msg_reg_retype);
                return false;
            }
            if (!reg_editText_Pass.getText().toString().trim().equals(reg_editText_conform_Pass.getText().toString().trim())) {
                new OK_Dialog(Login.this, GlobalApp.msg_reg_wrongpass);
                return false;
            }
            if (GlobalApp.isBlank(reg_editText_email)) {
                new OK_Dialog(Login.this, GlobalApp.msg_reg_email);
                return false;
            }
            if (reg_editText_email != null) {
                String mail = reg_editText_email.getText().toString().trim();
                Pattern p = Pattern.compile(("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"));
                Matcher m = p.matcher(mail);
                final boolean b = m.matches();
                if (!b) {
                    new OK_Dialog(Login.this, GlobalApp.msg_reg_wrong_email);
                    return false;
                }
            }
            if (GlobalApp.isBlank(reg_editText_phone)) {
                new OK_Dialog(Login.this, GlobalApp.msg_reg_phone);
                return false;
            }
            if (GlobalApp.isBlank(reg_editText_country)) {
                new OK_Dialog(Login.this, GlobalApp.msg_reg_country);
                return false;
            }
            if (GlobalApp.isBlank(reg_editText_state)) {
                new OK_Dialog(Login.this, GlobalApp.msg_reg_phone);
                return false;
            }
            if (GlobalApp.isBlank(reg_editText_city)) {
                new OK_Dialog(Login.this, GlobalApp.msg_reg_city);
                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private void prefixdialog(final EditText reg_dialog_prifix) {
        final Dialog dialog1 = new Dialog(this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog1.setCanceledOnTouchOutside(true);
        dialog1.setContentView(R.layout.prefix);
        dialog1.setCanceledOnTouchOutside(true);
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();

        android.view.WindowManager.LayoutParams lp;
        lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog1.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//                (int) (width - (width * 0.07));
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog1.getWindow().setAttributes(lp);
        dialog1.show();
        final TextView mr = (TextView) dialog1.findViewById(R.id.mr);
        final TextView ms = (TextView) dialog1.findViewById(R.id.ms);
        final TextView miss = (TextView) dialog1.findViewById(R.id.miss);
        final TextView mrs = (TextView) dialog1.findViewById(R.id.mrs);

        mr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg_dialog_prifix.setText(mr.getText().toString());
                dialog1.dismiss();
            }
        });
        ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg_dialog_prifix.setText(ms.getText().toString());
                dialog1.dismiss();
            }
        });
        miss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg_dialog_prifix.setText(miss.getText().toString());
                dialog1.dismiss();
            }
        });
        mrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg_dialog_prifix.setText(mrs.getText().toString());
                dialog1.dismiss();
            }
        });

    }

    public void login_dialog() {

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.login_dialog);
        Display display = Login.this.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();

        android.view.WindowManager.LayoutParams lp;
        lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//                (int) (width - (width * 0.07));
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        login_edittext_username = (EditText) dialog.findViewById(R.id.login_editText_username);
        login_edittext_password = (EditText) dialog.findViewById(R.id.login_editText_password);
        login_edittext_username.setText("surat");
        login_edittext_password.setText("it@2233");

        btn_login_login = (Button) dialog.findViewById(R.id.btn_login_login);
        btn_login_cancel = (Button) dialog.findViewById(R.id.btn_login_Cancel);
//        pDialog1=(ProgressBar)dialog.findViewById(R.id.progressBar);

        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalApp.CheckInternetConnection(Login.this)) {
                    if (loginvalidation()) {

                        new GetLogin().execute(GlobalApp.url + "UserLogin?UserName=" + login_edittext_username.getText().toString().trim() + "&PassWord=" + login_edittext_password.getText().toString().trim() + "&Source=android");
                        Log.d("url", "" + GlobalApp.url + "UserLogin?UserName=" + login_edittext_username.getText().toString().trim() + "&PassWord=" + login_edittext_password.getText().toString().trim() + "&Source=android");
                    }
                } else {
                    new OK_Dialog(Login.this, GlobalApp.msg_connection_Prob);
                }
            }
        });

        btn_login_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}

