package Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.dnktechnologies.laxmidiamond.Login;
import com.dnktechnologies.laxmidiamond.R;

/**
 * Created by parth on 6/28/2016.
 */
public class Logout_Dialog extends Dialog {
    Activity activity;

    public Logout_Dialog(final Activity activity) {
        super(activity);
        this.activity = activity;

        final Dialog dialog2 = new Dialog(activity);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.setContentView(R.layout.logout_dialog);

        Button btn_logout_yes = (Button) dialog2.findViewById(R.id.btn_Logout_dialog_yes);
        Button btn_logout_no = (Button) dialog2.findViewById(R.id.btn_Logout_dialog_no);

        dialog2.setCanceledOnTouchOutside(false);
        Display display = activity.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();

        android.view.WindowManager.LayoutParams lp;
        lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog2.getWindow().getAttributes());
        lp.width = (int) (width - (width * 0.80));
//                (int) (width - (width * 0.07));
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog2.getWindow().setAttributes(lp);
        dialog2.show();

        btn_logout_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),Login.class);
                activity.startActivity(in);
                dialog2.dismiss();


            }
        });
        btn_logout_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        dialog2.show();
    }
}
