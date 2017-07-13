package com.xinguang.freshworld.materialdesign;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

public class BottomSheetDialogActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_dialog);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
         BottomSheetDialog dialog = new BottomSheetDialog(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_view, null, false);
        dialog.setContentView(inflate);
        dialog.show();


    }

}
