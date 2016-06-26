package com.example.animationdemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.view.animation.BounceInterpolator;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(
                    new Explode().setInterpolator(new BounceInterpolator()).setDuration(5000));
            getWindow().setExitTransition(new Fade().setDuration(5000));
            getWindow().setReturnTransition(new Slide().setDuration(5000));
//            重新进入
            getWindow().setReenterTransition(new Slide().setDuration(5000));
//            设置在B进入时播放的动画，共享元素以A中的位置作为起始，B中的位置为结束来播放动画。
            getWindow().setSharedElementReenterTransition(
                    new ChangeTransform().setInterpolator(new BounceInterpolator())
                            .setDuration(5000));
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);
    }

    public void toSecond(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(new Intent(this, SecondActivity.class),
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        super.onBackPressed();
    }
}
