package Custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

/**
 * Created by parth on 8/6/2016.
 */
public class ObservableScrollView extends HorizontalScrollView {

    public interface OnScrollChangedListener {
        public void onScrollChanged(int deltaX, int deltaY);
    }

    private OnScrollChangedListener mOnScrollChangedListener;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(l - oldl, t - oldt);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollChangedListener = listener;
    }
}
