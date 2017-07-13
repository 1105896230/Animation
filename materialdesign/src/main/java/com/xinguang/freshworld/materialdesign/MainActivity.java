package com.xinguang.freshworld.materialdesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bottoDialog(View view) {
         startActivity(new Intent(this,BottomSheetDialogActivity.class));
    }

    public void toolBar(View view) {
        startActivity(new Intent(this,ToolBarActivity.class));

    }

    public void CoordinatorLayout(View view) {
        startActivity(new Intent(this,CoordinatorLayout.class));
    }
}
