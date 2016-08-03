package Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.dnktechnologies.laxmidiamond.R;

/**
 * Created by parth on 6/22/2016.
 */
public class OK_Dialog extends Dialog
{
    Activity activity;


    public OK_Dialog(Activity activity, String txt)
    {
        super(activity);

        this.activity = activity;

        final Dialog dialog2 = new Dialog(activity);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.setContentView(R.layout.ok_dialog);

        Button btn_ok = (Button)dialog2.findViewById(R.id.btn_ok_dialog_ok);
        TextView msg = (TextView)dialog2.findViewById(R.id.txt_ok_dialog_issue);
        msg.setText(txt);

        dialog2.setCanceledOnTouchOutside(false);
        Display display = activity.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();

        android.view.WindowManager.LayoutParams lp;
        lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog2.getWindow().getAttributes());
        lp.width =(int) (width - (width * 0.80));
//                (int) (width - (width * 0.07));
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog2.getWindow().setAttributes(lp);
        dialog2.show();

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
    }
}
