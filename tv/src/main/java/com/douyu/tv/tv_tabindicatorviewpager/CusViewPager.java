package com.douyu.tv.android.view;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;

/**
 * User: duwenbin
 * Date: 2016-07-14 20:06  create
 */
public class CusViewPager extends ViewPager {
    public boolean isCanmove() {
        return canmove;
    }

    public void setCanmove(boolean canmove) {
        this.canmove = canmove;
    }

    private boolean canmove=true;   //为false时截取在View的边缘地带时再按会移动到下一页问题

    public CusViewPager(Context context) {
        super(context);
    }

    public CusViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean executeKeyEvent(KeyEvent event) {
        boolean handled = false;
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    if(isCanmove()) {
                        handled = arrowScroll(FOCUS_LEFT);
                    }
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    if(isCanmove()) {
                        handled = arrowScroll(FOCUS_RIGHT);
                    }
                    break;
                case KeyEvent.KEYCODE_TAB:
                    if(isCanmove()) {
                        if (Build.VERSION.SDK_INT >= 11) {
                            // The focus finder had a bug handling FOCUS_FORWARD and FOCUS_BACKWARD
                            // before Android 3.0. Ignore the tab key on those devices.
                            if (KeyEventCompat.hasNoModifiers(event)) {
                                handled = arrowScroll(FOCUS_FORWARD);
                            } else if (KeyEventCompat.hasModifiers(event, KeyEvent.META_SHIFT_ON)) {
                                handled = arrowScroll(FOCUS_BACKWARD);
                            }
                        }
                    }
                    break;
            }
        }
        return handled;
//        return super.executeKeyEvent(event);
    }
}
