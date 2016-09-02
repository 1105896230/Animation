package com.example.animatorapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.animatorapp.databinding.ActivityTranslateBinding;

import static com.example.animatorapp.BaseActivity.EXTRA_SAMPLE;

public class TransLateActivity1 extends AppCompatActivity {
    private Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
    }

    private void bindData() {
        ActivityTranslateBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_translate);
        sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setTransition1Sample(sample);
    }
}
