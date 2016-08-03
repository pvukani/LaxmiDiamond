package Custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by parth on 6/21/2016.
 */
public class Custom_Button extends Button {

    public Custom_Button(Context context) {
        super(context);
        init();
    }

    public Custom_Button(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Custom_Button(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Custom_Button(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    public void init()
    {
        if (isInEditMode())
        {
        }
        else {
            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "century_gothic.ttf");
            setTypeface(typeface);
        }
    }
}
