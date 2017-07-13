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

    public void AppBarLayout(View view) {
        startActivity(new Intent(this,AppbarLayoutActivity.class));
    }

    public void jianshu(View view) {
        startActivity(new Intent(this,JianShuActivity.class));
    }

    public void BottomSheetBehavior(View view) {
        startActivity(new Intent(this,BottomSheetBehaviorActivity.class));
    }

    public void SwipeDissMissBehavior(View view) {
        startActivity(new Intent(this,SwipeDissmissBehaviorActivity.class));
    }

    public void CustomBehavior(View view) {
        startActivity(new Intent(this,CustomBehaviorActivity.class));
    }

    public void CustomBehavior2(View view) {
        startActivity(new Intent(this,CustomBehavior2Activity.class));
    }

    public void snake(View view) {
        startActivity(new Intent(this,FABSimpleActivity.class));
    }

    public void textInput(View view) {
        startActivity(new Intent(this,TextInputSimpleActivity.class));
    }

    public void bottom(View view) {
        startActivity(new Intent(this,BottomNavigationActivity.class));
    }
}
