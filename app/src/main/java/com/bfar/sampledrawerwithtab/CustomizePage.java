package com.bfar.sampledrawerwithtab;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.bfar.sampledrawerwithtab.fragments.FragmentInterface;

/**
 * Created by rmara on 3/1/15.
 */
public class CustomizePage extends ViewPager{

    private String TAG = this.getClass().getSimpleName();
    private boolean enabled;


    public CustomizePage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }



    @Override
    public void setCurrentItem(int item) {

        Log.e(TAG,"index:"+item);
        switch (item){
            /*case 0:
                MySingleton.getInstance().getFragmentCategoryBus().post("hello from pager");
                break;*/
            case 1:
                MySingleton.getInstance().getFragmentCategoryBus().post("hello from pager");
                break;
            case 2:
                MySingleton.getInstance().getFragmentHomeBus().post("hello from pager");
                break;
        }

    }
    public void loadNextFragment(int item){
        super.setCurrentItem(item);
    }
}
