
package com.douyu.tv.tv_tabindicatorviewpager.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.douyu.tv.tv_tabindicatorviewpager.R;
import com.douyu.tv.tv_tabindicatorviewpager.SmoothHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

public class TabIndicator extends LinearLayout implements TabButton.OnTabButtonChangeListener, TabButton.OnTabButtonClickListener {

    private Context mContext;
    private LinearLayout tabsContainer;
    private onTabChangeListener mOnTabChangeListener;
    private onTabClickListener mOnTabClickListener;
    private SmoothHorizontalScrollView smoothHorizontalScrollView;
    private int mCurrentIndex;

    private ArrayList<TabButton> mTabButtonList = new ArrayList<>();

    private List<String> mListData=new ArrayList<String>();
    private String mTitle="";
    public TabIndicator(Context context) {
        super(context);
        mContext = context;
        initUI();
    }

    public TabIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.tabIndicator);
        mTitle = typedArray.getString(R.styleable.tabIndicator_itemTitle);
        typedArray.recycle();
        mContext = context;
        mListData.add(mTitle);
        initUI();
    }

    public interface onTabChangeListener {
        void onTabChange(int index);
    }

    public interface onTabClickListener {
        void onTabClick(int index);
    }

    public void setOnTabChangeListener(onTabChangeListener onTabChangeListener) {
        mOnTabChangeListener = onTabChangeListener;
    }

    public void setOnTabClickListener(onTabClickListener onTabClickListener) {
        mOnTabClickListener = onTabClickListener;
    }

    private void initUI() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_indicator, this);
        tabsContainer= (LinearLayout) view.findViewById(R.id.tabcontint);
        smoothHorizontalScrollView=(SmoothHorizontalScrollView)view.findViewById(R.id.shScrollView);

        for (int i=0;i<mListData.size();i++){
            TabButton tabButton = (TabButton) LayoutInflater.from(mContext).inflate(R.layout.view_tabbutton, this, false);
            tabButton.setId(R.id.indicator_tab1 + i);
            tabButton.setTitle(mListData.get(i));
            tabButton.setOnTabButtonChangeListener(this);
            tabButton.setOnTabButtonClickListener(this);
            if (!(tabButton.getVisibility() == View.GONE)) {
                mTabButtonList.add(tabButton);
            }
            tabsContainer.addView(tabButton);
        }

    }

    public void updateUI(List<String> mListData){
        this.mListData.clear();
        tabsContainer.removeAllViews();
        mListData.add(mTitle);
        for (int i=0;i<mListData.size();i++){
            this.mListData.add(mListData.get(i));
        }
        for (int i=0;i<this.mListData.size();i++){
            TabButton tabButton = (TabButton) LayoutInflater.from(mContext).inflate(R.layout.view_tabbutton, this, false);
            tabButton.setId(R.id.indicator_tab1 + i);
            tabButton.setTitle(mListData.get(i));
            tabButton.setOnTabButtonChangeListener(this);
            tabButton.setOnTabButtonClickListener(this);
            if (!(tabButton.getVisibility() == View.GONE)) {
                mTabButtonList.add(tabButton);
            }
            tabsContainer.addView(tabButton);
        }
    }

    public int getVisibleChildCount() {
        return mTabButtonList.size();
    }

    public void requestTabFocus(int index) {
        mTabButtonList.get(index).requestFocus();
    }

    private void setTabSelectedTextColor(int position) {
        for (int i = 0; i < mTabButtonList.size(); i++) {
            if (position == i) {
                mTabButtonList.get(i).setSelectedTextColor();
            } else {
                mTabButtonList.get(i).setNormalTextColor();
            }
        }
    }

    public void setNoFocusState() {
        setTabSelectedTextColor(mCurrentIndex);
    }

    public void setCurrentIndex(int index) {
        mCurrentIndex = index;
    }

    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    //滑动到开始位置
    public void SmoothTo(){
        smoothHorizontalScrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothHorizontalScrollView.scrollTo(0,0);
            }
        },50);
    }

    @Override
    public void onTabButtonClick(View v) {
        for (int i = 0; i < mTabButtonList.size(); i++) {
            if (v == mTabButtonList.get(i)) {
                if(mOnTabClickListener!=null){
                    mOnTabClickListener.onTabClick(i);
                }
            }
        }
    }

    @Override
    public void onTabButtonChange(View v) {
        for (int i = 0; i < mTabButtonList.size(); i++) {
            if (v == mTabButtonList.get(i)) {
                setCurrentIndex(i);
                if(mOnTabChangeListener!=null){
                    mOnTabChangeListener.onTabChange(i);
                }
            }
        }
    }
}
