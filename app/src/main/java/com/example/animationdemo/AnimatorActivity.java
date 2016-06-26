package com.example.animationdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class AnimatorActivity extends AppCompatActivity {
    Button button4;
    Button btn5;
    private Animator circularReveal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        button4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        button4.setOnClickListener(new View.OnClickListener() {

            private Animator circularReveal;

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (circularReveal != null && circularReveal.isRunning()) return;
                if (btn5.getVisibility() == View.VISIBLE) {
                    circularReveal = ViewAnimationUtils.createCircularReveal(btn5,
                            btn5.getWidth() / 2, btn5.getHeight() / 2, btn5.getWidth(), 0);
                    circularReveal.setInterpolator(new LinearInterpolator());
                    circularReveal.setDuration(3000);
                    circularReveal.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            btn5.setVisibility(View.GONE);
                        }
                    });
                    circularReveal.start();
                } else if (btn5.getVisibility() == View.GONE) {
                    circularReveal = ViewAnimationUtils.createCircularReveal(btn5,
                            btn5.getWidth() / 2, btn5.getHeight() / 2, 0, btn5.getWidth());
                    circularReveal.setInterpolator(new LinearInterpolator());
                    circularReveal.setDuration(3000);
                    circularReveal.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            btn5.setVisibility(View.VISIBLE);
                        }
                    });
                    circularReveal.start();
                }
            }
        });
    }
}
