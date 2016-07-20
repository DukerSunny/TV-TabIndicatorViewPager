
package com.douyu.tv.tv_tabindicatorviewpager.indicator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.douyu.tv.tv_tabindicatorviewpager.R;


public class TabButton extends RelativeLayout implements View.OnFocusChangeListener, View.OnClickListener {


    private String mTitle;
    private TextView mTitleView;
    private Context mContext;

    private OnTabButtonChangeListener mOnTabButtonChangeListener;
    private OnTabButtonClickListener mOnTabButtonClickListener;

    public TabButton(Context context) {
        super(context);
    }

    public TabButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initUI();
    }

    public interface OnTabButtonChangeListener {
        void onTabButtonChange(View v);
    }

    public interface OnTabButtonClickListener {
        void onTabButtonClick(View v);
    }

    public void setOnTabButtonChangeListener(OnTabButtonChangeListener onTabButtonChangeListener) {
        mOnTabButtonChangeListener = onTabButtonChangeListener;
    }

    public void setOnTabButtonClickListener(OnTabButtonClickListener OnTabButtonClickListener) {
        mOnTabButtonClickListener = OnTabButtonClickListener;
    }

    private void initUI() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_indicator_item, this);
        setOnFocusChangeListener(this);
        setOnClickListener(this);
        mTitleView = (TextView) view.findViewById(R.id.tab_text);
        mTitleView.setText(mTitle);
    }

    public void setSelectedTextColor() {
        mTitleView.setTextColor(getResources().getColor(R.color.white));
        mTitleView.setTypeface(null, Typeface.BOLD);
        this.setBackgroundResource(R.drawable.shape_rect_fill_blue);
    }

    public void setNormalTextColor() {
        mTitleView.setTextColor(getResources().getColor(R.color.live_lev_text_nomarl));
        mTitleView.setTypeface(null, Typeface.BOLD);
        this.setBackgroundResource(R.drawable.sel_indicator);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
        mTitleView.setText(mTitle);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v == this) {
            if (hasFocus) {
                if(mOnTabButtonChangeListener!=null){
                    mOnTabButtonChangeListener.onTabButtonChange(v);
                }
                mTitleView.setTextColor(Color.WHITE);
                mTitleView.setTypeface(null, Typeface.BOLD);
            } else {
                mTitleView.setTextColor(getResources().getColor(R.color.live_lev_text_nomarl));
                mTitleView.setTypeface(null, Typeface.BOLD);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(mOnTabButtonClickListener!=null){
            mOnTabButtonClickListener.onTabButtonClick(v);
        }
    }
}
