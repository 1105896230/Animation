package com.example.animatorapp;

import android.support.annotation.ColorRes;

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

}
