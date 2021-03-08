package examplesAll.imagesExamples.aspectRatio;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

public class RelativeLayoutRundown extends RelativeLayout {

    // some other necessary things
    public RelativeLayoutRundown(Context context) {
        super(context);
    }

    public RelativeLayoutRundown(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeLayoutRundown(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RelativeLayoutRundown(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //int width = getMeasuredWidth();
        int width = widthMeasureSpec;
        Log.e("checkapp","width: "+width);

        //force a 16:9 aspect ratio
        int height = Math.round(width * .5625f);
        Log.e("checkapp","height: "+height);
        //setMeasuredDimension(width, height);
        super.onMeasure(width, height);
    }
}