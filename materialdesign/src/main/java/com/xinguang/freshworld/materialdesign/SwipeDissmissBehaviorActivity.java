package com.xinguang.freshworld.materialdesign;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toolbar;

public class SwipeDissmissBehaviorActivity extends AppCompatActivity {

    public static final String TAG="SwipeDissmissBehaviorActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_dissmiss_behavior);
        FrameLayout mSwipeLayout = (FrameLayout) findViewById(R.id.swipe_layout);
        SwipeDismissBehavior swipe = new SwipeDismissBehavior();

        /**
         * //设置滑动的方向，有3个值
         *
         * 1，SWIPE_DIRECTION_ANY 表示向左像右滑动都可以，
         * 2，SWIPE_DIRECTION_START_TO_END，只能从左向右滑
         * 3，SWIPE_DIRECTION_END_TO_START，只能从右向左滑
         */
        swipe.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY);

//        swipe.setStartAlphaSwipeDistance(0f);
//
//        swipe.setSensitivity(0.2f);

        swipe.setListener(new SwipeDismissBehavior.OnDismissListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDismiss(View view) {
                Log.e(TAG,"------>onDissmiss");
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onDragStateChanged(int state) {
                Log.e(TAG,"------>onDragStateChanged");
            }
        });

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mSwipeLayout.getLayoutParams();
        if(layoutParams!=null){
            layoutParams.setBehavior(swipe);
        }


    }
}
