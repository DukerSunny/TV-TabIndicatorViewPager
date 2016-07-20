/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.douyu.tv.tv_tabindicatorviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.douyu.tv.tv_tabindicatorviewpager.indicator.TabIndicator;

import java.util.ArrayList;
import java.util.List;

/*
 * MainActivity class that loads MainFragment
 */
public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private TabIndicator mTabIndicator;
    private List<String> mOneTabData = new ArrayList<String>();

    private ViewPager mViewPager;
    private boolean isFocusOnPage;
//    private ArrayList<BaseFragment> mFragmentList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i=0;i<20;i++){
            mOneTabData.add("全部"+(i*10+1));
        }
        mTabIndicator=(TabIndicator)findViewById(R.id.main_indicator);
        mViewPager = (ViewPager) this.findViewById(R.id.main_viewpager);

        mTabIndicator.updateUI(mOneTabData);
        mTabIndicator.setOnTabChangeListener(new TabIndicator.onTabChangeListener() {
            @Override
            public void onTabChange(int index) {
                mViewPager.setCurrentItem(index);
//                Toast.makeText(getActivity(), "position:" + index, Toast.LENGTH_SHORT).show();
            }
        });
        mTabIndicator.setOnTabClickListener(new TabIndicator.onTabClickListener() {
            @Override
            public void onTabClick(int index) {
                mTabIndicator.setNoFocusState();
            }
        });


        //参照Android TV Launcher 代码  https://github.com/JackyAndroid/AndroidTVLauncher
//        mViewPager.setPageTransformer(true, new DefaultTransformer());
//        FixViewpagerScrollerSpeed.setViewPagerScrollSpeed(mViewPager);
    }

    public void requestTabFocus() {
        mTabIndicator.requestTabFocus(mTabIndicator.getCurrentIndex());
    }

    public ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            mTabIndicator.setCurrentIndex(position);
            if (isFocusOnPage) {
                mTabIndicator.setNoFocusState();
//                mFragmentList.get(mTabIndicator.getCurrentIndex()).requestInitFocus();
            }
        }
    };
}
