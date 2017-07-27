package com.example.customeview.timepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 林其望 on 2017/7/26.
 * email 1105896230@qq.com
 * blog
 */

public class PickerView extends ScrollView{
    public int tag=0;
    private int scrollY;
    /**
     * 当前选中的position
     */
    private int position, tempPosition = -1;
    private int mTextHeight=DensityUtil.dip2px(getContext(),40);
    private int mGroupHeight=DensityUtil.dip2px(getContext(),150);
    /**
     * 数据源
     */
    private List<String> mData;
    /**
     * 两条线的画笔
     */
    private Paint mLinePaint;
    /**
     * TextView的父容器
     */
    private LinearLayout mTextGroup;
    /**
     * 数据源
     */
    private List<String> mHour;
    /**
     * 数据源
     */
    private List<String> mWeek;
    /**
     * 数据源
     */
    private List<String> mMonth;
    public PickerView(Context context) {
        this(context, null);
    }

    public PickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 这句话设置ScrollView滑动到极限的时候不显示提示（就是那个阴影）
         */
        setOverScrollMode(OVER_SCROLL_NEVER);
    }
    /**
     * 设置数据源
     */
    public void setData(List<String> hour) {
        tag=0;
        mHour = hour;
        init();
    }

    public void setData(List<String> month,List week) {
        tag=1;
        mMonth=month;
        mWeek=week;
        init();
    }

    private void init() {
        removeAllViews();
        /**
         * 初始化数据,首先添加Group
         */
        mTextGroup = new LinearLayout(getContext());
        mTextGroup.setOrientation(LinearLayout.VERTICAL);
        mTextGroup.setGravity(Gravity.CENTER);
        addView(mTextGroup);

        if (tag==0) {
            mTextGroup.addView(createTextView(""));
            for (int i = 0; i < mHour.size(); i++) {
                mTextGroup.addView(createTextView("" + mHour.get(i)));
            }
            mTextGroup.addView(createTextView(""));
            mTextGroup.addView(createTextView(""));
        }else if (tag==1){
            mTextGroup.addView(createTextView("",""));
            for (int i = 0; i < mMonth.size(); i++) {
                mTextGroup.addView(createTextView("" + mMonth.get(i),"" + mWeek.get(i)));
            }
            mTextGroup.addView(createTextView("",""));
            mTextGroup.addView(createTextView("",""));
        }
        drawLines();
    }

    private void drawLines(){
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(1);
        mLinePaint.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        setBackground(new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                /**
                 * 这里把两条线之间的距离设置为了两个TextView的高度
                 */
                canvas.drawLine(
                        0,
                        mTextHeight,
                        getMeasuredWidth(),
                        mTextHeight,
                        mLinePaint
                );
                canvas.drawLine(
                        0,
                        2*mTextHeight,
                        getMeasuredWidth(),
                        2*mTextHeight,
                        mLinePaint
                );
            }

            @Override
            public void setAlpha(int i) {

            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return 0;
            }
        });


    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        /**
         * 因为ScrollView没有停止滑动的监听,所以这里取巧
         * 在手指离开屏幕的30ms后判断是否和原来的scrollY一样
         * 如果一样则进入,如果不一样则设置为一样
         */
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            scrollY = getScrollY();
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (scrollY == getScrollY()) {
                        /**
                         * 获得每次松手后scrollY相对于TextView高度的偏移量
                         */
                        int offset = scrollY % (mTextHeight);
                        /**
                         * 如果偏移量大于TextView高度的一半
                         * 则进入到下一个
                         */
                        if (offset > mTextHeight) {
                            smoothScrollTo(0, scrollY - offset + (mTextHeight));
                        } else {
                            smoothScrollTo(0, scrollY - offset);
                        }
                    } else {
                        scrollY = getScrollY();
                        post(this);
                    }
                }
            }, 30);
        }
        return super.onTouchEvent(ev);
    }
    /**
     * ScrollView的滑动事件监听
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        /**
         * 计算出当前在两条线里的position
         */
        position = t/mTextHeight+1;
        /**
         * 因为此方法会在滑动的时候不停的调用,所以这里设置一个临时的变量来控制
         */
        if (tempPosition != position) {
            int size = mTextGroup.getChildCount();
            for (int i = 0; i < size; i++) {
                if (tag==0) {
                    TextView tv = (TextView) mTextGroup.getChildAt(i);
                    /**
                     * 因为我们在数据开头添加了一个空的数据,所以这里position要+1
                     */
                    if (position == i) {
                        tv.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                    } else {
                        tv.setTextColor(getResources().getColor(android.R.color.black));
                    }
                }else{
                    if(mTextGroup.getChildAt(i) instanceof RelativeLayout) {
                        RelativeLayout mTextGroupChildAt = (RelativeLayout) mTextGroup.getChildAt(i);
                        TextView tvmonth = (TextView) mTextGroupChildAt.getChildAt(0);
                        TextView tvweek = (TextView) mTextGroupChildAt.getChildAt(1);
                        if (position  == i) {
                            tvmonth.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                            tvweek.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                        } else {
                            tvmonth.setTextColor(getResources().getColor(android.R.color.black));
                            tvweek.setTextColor(getResources().getColor(android.R.color.black));
                        }
                    }
                }
            }
        }
        position-=1;
        tempPosition = position;
        if (mscrollChange!=null){
            mscrollChange.change(tempPosition);
        }
    }
    /**
     * 动态创建TextView
     */
    private View createTextView(String s) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.view_text,this,false);
        TextView textView=  view.findViewById(R.id.tv);
        textView.setText(s);
        return view;
    }
    private View createTextView(String s, String s1) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.view_text2,this,false);
        TextView tv_month= (TextView) view.findViewById(R.id.tv_month);
        tv_month.setText(s);
        TextView tv_week= (TextView) view.findViewById(R.id.tv_week);
        tv_week.setText(s1);
        return view;
    }
    /**
     * 设置position
     */
    public void setPosition(final int position) {
        this.position = position;
        if (position == 0) {
            post(new Runnable() {
                @Override
                public void run() {
                    scrollTo(0, 1);
                }
            });
            return;
        }
        post(new Runnable() {
            @Override
            public void run() {
                scrollTo(0, position * (mTextHeight));
            }
        });
    }


    private scrollChange mscrollChange;

    public void setMscrollChange(scrollChange mscrollChange) {
        this.mscrollChange = mscrollChange;
    }

    public interface scrollChange{

        public void change(int position);
    }
}
