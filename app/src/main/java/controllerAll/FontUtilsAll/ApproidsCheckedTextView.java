package controllerAll.FontUtilsAll;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

public class ApproidsCheckedTextView extends CheckedTextView {

    public ApproidsCheckedTextView(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public ApproidsCheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public ApproidsCheckedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {


        setIncludeFontPadding(false);
        Typeface customFont = FontCache.getTypeface("fontMuliSemiBold.ttf", context);
        if(isBold())
        setTypeface(customFont,Typeface.BOLD);
        else
            setTypeface(customFont);

        setIncludeFontPadding(false);
    }


    boolean isBold()
    {
        if(getTypeface()!=null){
            if(getTypeface().getStyle()==Typeface.BOLD){
           return true;
            }
        }
        return false;
    }
}