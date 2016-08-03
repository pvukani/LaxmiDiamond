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
public class Register_Dialog extends Dialog
{
    Activity activity;


    public Register_Dialog(Activity activity)
    {
        super(activity);

        this.activity = activity;

        final Dialog dialog2 = new Dialog(activity);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.setContentView(R.layout.register_dialog);

        dialog2.setCanceledOnTouchOutside(false);
        Display display = activity.getWindowManager().getDefaultDisplay();
//        int width = display.getWidth();

        android.view.WindowManager.LayoutParams lp;
        lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog2.getWindow().getAttributes());
        lp.width =WindowManager.LayoutParams.WRAP_CONTENT;
//                (int) (width - (width * 0.07));
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog2.getWindow().setAttributes(lp);

        dialog2.show();
    }
}
