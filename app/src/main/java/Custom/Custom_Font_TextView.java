package Custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by parth on 6/16/2016.
 */
public class Custom_Font_TextView extends TextView {
    public Custom_Font_TextView(Context context) {

        super(context);
        init();
    }

    public Custom_Font_TextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public Custom_Font_TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init() {
        if (isInEditMode()) {
        }
        else
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
}

