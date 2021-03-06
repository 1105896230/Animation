package com.example.animatorapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {
    private List<Sample> samples;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        initDatas();
        setupLayout();
        setupToolbar();
    }

    private void setupWindowAnimations() {
        Slide slideTransition = new Slide();//划出
        slideTransition.setSlideEdge(Gravity.LEFT);//从左侧
        slideTransition.setDuration(500);
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }

    private void initDatas() {
        samples = Arrays.asList(
                new Sample(R.color.sample_red, "Transitions"),
                new Sample(R.color.sample_blue, "Shared Elements"),
                new Sample(R.color.sample_green, "View animations"),
                new Sample(R.color.sample_yellow, "Circular Reveal Animation")
        );
    }

    private void setupLayout() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sample_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SamplesRecyclerAdapter samplesRecyclerAdapter = new SamplesRecyclerAdapter(this, samples);
        recyclerView.setAdapter(samplesRecyclerAdapter);
    }
}
