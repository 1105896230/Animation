package com.example.animatorapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;

import com.example.animatorapp.databinding.ActivityAnimations2Binding;

import java.util.ArrayList;
import java.util.List;

public class AnimationsActivity2 extends BaseActivity {
    private static final int DELAY = 100;
    private Scene scene0;
    private Scene scene1;
    private Scene scene2;
    private Scene scene3;
    private Scene scene4;
    private final List<View> viewsToAnimate = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        setupToolbar();
        setupWindowAnimations();
    }
    private void bindData() {
        ActivityAnimations2Binding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_animations2);
        Sample sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setAnimationsSample(sample);
    }

    private void setupWindowAnimations() {
        getWindow().setEnterTransition(TransitionInflater.from(this).inflateTransition(
                R.transition.slide_from_bottom));
        getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                getWindow().getEnterTransition().removeListener(this);

            }
        });
    }

}
