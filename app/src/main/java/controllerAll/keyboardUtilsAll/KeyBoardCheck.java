package controllerAll.keyboardUtilsAll;

import android.graphics.Rect;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;

import controllerAll.keyboardUtilsAll.callBacksAll.OnKeyboardVisibilityListener;

public class KeyBoardCheck {

    private  ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;

    public void setKeyboardListener(final OnKeyboardVisibilityListener listener, final View rootView) {

            onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener(){

            private boolean returnCheck = false, wasOpened;

            private final int DefaultKeyboardDP = 100;
            private final int EstimatedKeyboardDP = DefaultKeyboardDP + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0);

            private final Rect r = new Rect();

            @Override
            public void onGlobalLayout() {
                // Convert the dp to pixels.
                int estimatedKeyboardHeight = (int) TypedValue
                        .applyDimension(TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP, rootView.getResources().getDisplayMetrics());

                // Conclude whether the keyboard is shown or not.
                rootView.getWindowVisibleDisplayFrame(r);
                int heightDiff = rootView.getRootView().getHeight() - (r.bottom - r.top);
                               boolean isShown = heightDiff >= estimatedKeyboardHeight;

                if (isShown == wasOpened) {
                    returnCheck = true;
                } else {
                    returnCheck = false;
                }

                if (!returnCheck) {
                    listener.onVisibilityChanged(true);
                } else {
                    listener.onVisibilityChanged(false);
                }
            }
        };
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    public void removeOnGlobalLayoutListener(View rootView, ViewTreeObserver.OnGlobalLayoutListener listener) {
    }
}