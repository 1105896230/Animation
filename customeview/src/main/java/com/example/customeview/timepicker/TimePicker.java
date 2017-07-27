package com.example.customeview.timepicker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by 林其望 on 2017/7/26.
 * email 1105896230@qq.com
 * blog
 */

public class TimePicker extends LinearLayout {


    private static final int MAX_SIZE=7;
    private ArrayList<String> months=new ArrayList<>();
    private ArrayList<String> week=new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    private String[] weeks={"星期天","星期一","星期二","星期三","星期四","星期五","星期六"};
    private String[] hourse={"01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00",
            "13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00"};

    private int h;
    private Date now;

    private Date selectDate;
    PickerView pv;
    PickerView pvhour;
    private int currentH;
    private TextView mTitle;


    public TimePicker(Context context) {
        super(context);
        init(context);
    }

    public TimePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.time_picker_view,this,true);
        pv= (PickerView) findViewById(R.id.time);
        pvhour=(PickerView) findViewById(R.id.time_hour);
        mTitle=findViewById(R.id.title);
        initDatas();
        initViews();
    }

    private void initViews() {
        pv.setData(months,week);
        pv.setPosition(0);
        if (h==24){
            currentH=0;
        }else {
            //因为小时不是从0计数只带加1 原本是h-1+1
            currentH=h;
        }
        pvhour.setData(Arrays.asList(hourse));
        pvhour.setPosition(currentH);

        pvhour.setMscrollChange(new PickerView.scrollChange() {
            @Override
            public void change(int position) {
                currentH=position;
                showTitle();
            }
        });
        pv.setMscrollChange(new PickerView.scrollChange() {
            @Override
            public void change(int position) {
                onItemSelected(position);
            }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimePickerListener!=null)mTimePickerListener.cancel();
            }
        });
        findViewById(R.id.btn_check).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimePickerListener!=null)mTimePickerListener.click(getTime());
            }
        });
    }

    private void initDatas(){
        Calendar rightNow = Calendar.getInstance();
        now=rightNow.getTime();
        h=rightNow.get(Calendar.HOUR_OF_DAY);
        months.clear();
        week.clear();
        for (int i=0;i<MAX_SIZE;i++){
            if (i<2){
                months.add("");
            }else {
                months.add(getMonth(rightNow));
            }
            if (i==0){
                week.add("今天");
            }else if (i==1){
                week.add("明天");
            }else {
                week.add(weeks[rightNow.get(Calendar.DAY_OF_WEEK)-1]);
            }
            rightNow.add(Calendar.DAY_OF_MONTH,1);
        }
    }

    private String getMonth(Calendar calendar){
        return dateFormat.format(calendar.getTime());
    }


    public void onItemSelected(int index) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH,index);
        selectDate=calendar.getTime();
        showTitle();
    }
    private void showTitle(){
        if (selectDate==null)return;
        mTitle.setText(format(selectDate)+" "+hourse[currentH]);
    }
    private String format(Date selectDate){
        return dateFormat1.format(selectDate);
    }


    public String getTime(){
        return mTitle.getText().toString();
    }

    public static PopupWindow newViews(Context context,TimePickerListener listener ){
        PopupWindow popWnd =new PopupWindow(context);
        TimePicker timePicker = new TimePicker(context);
        timePicker.setmTimePickerListener(listener);
        popWnd.setContentView(timePicker);
        popWnd.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        return popWnd;
    }

    private TimePickerListener mTimePickerListener;

    public void setmTimePickerListener(TimePickerListener mTimePickerListener) {
        this.mTimePickerListener = mTimePickerListener;
    }

    public interface TimePickerListener{
        void click(String time);

        void cancel();
    }
}

