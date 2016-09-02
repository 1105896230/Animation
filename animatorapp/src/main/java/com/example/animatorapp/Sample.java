package com.example.animatorapp;

import android.databinding.BindingAdapter;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by lgvalle on 04/09/15.
 */

/**
 * 外部每一个List的一个对象 包含其颜色和名字
 */
public class Sample implements Serializable {

    final int color;
    private final String name;

    public Sample(@ColorRes int color, String name) {
        this.color = color;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @BindingAdapter("colorTint")
    public static void setColorTint(ImageView view, @ColorRes int color) {
        DrawableCompat.setTint(view.getDrawable(), view.getContext().getColor(color));
//        view.setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }
}
