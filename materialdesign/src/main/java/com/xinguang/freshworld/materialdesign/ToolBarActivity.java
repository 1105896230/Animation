package com.xinguang.freshworld.materialdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ToolBarActivity extends AppCompatActivity {

    Toolbar tool_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        tool_bar= (Toolbar) findViewById(R.id.tool_bar);
        initToolBar();
    }

    private void initToolBar() {
        //设置NavigationIcon
        tool_bar.setNavigationIcon(R.mipmap.ic_launcher);
        // 设置navigation button 点击事件
        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
