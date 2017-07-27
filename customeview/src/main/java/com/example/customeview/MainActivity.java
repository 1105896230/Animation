package com.example.customeview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.customeview.recycleview.RecycleViewLayoutManagerActvity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void recucleView(View view) {
        startActivity(new Intent(this,RecycleViewLayoutManagerActvity.class));
    }
}
