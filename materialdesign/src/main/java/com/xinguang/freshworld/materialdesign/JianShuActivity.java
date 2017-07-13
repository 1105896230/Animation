package com.xinguang.freshworld.materialdesign;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinguang.freshworld.materialdesign.model.JsEntry;

import java.util.ArrayList;
import java.util.List;

public class JianShuActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AppBarLayout mAppBarLayout;
    private View mLine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_shu);
        initView();
    }

    /**
     * 模拟首页数据
     * @return
     */
    private List<JsEntry> mockData(){
        List<JsEntry> data = new ArrayList<>();
        JsEntry jsEntry = new JsEntry();
        jsEntry.comment = 50;
        jsEntry.award = 3;
        jsEntry.like = 460;
        jsEntry.seek = 12504;
        jsEntry.time = "15小时前";
        jsEntry.title = "这些情商的技巧，你是不是都掌握了？";
        jsEntry.authorName = "JayChou";
        jsEntry.label = "心理";
        jsEntry.cover ="http://upload-images.jianshu.io/upload_images/2785318-5306a632b46a8c27.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/1020/q/80";
        JsEntry jsEntry2 = new JsEntry();
        jsEntry2.comment = 150;
        jsEntry2.award = 33;
        jsEntry2.like = 1460;
        jsEntry2.seek = 170444;
        jsEntry2.time = "10小时前";
        jsEntry2.title = "除了阴谋，《锦绣未央》里还有哪些温情？";
        jsEntry2.authorName = "菇凉似梦";
        jsEntry2.label = "文化.艺术";
        jsEntry2.cover = "http://upload-images.jianshu.io/upload_images/2881988-b217e714eb05f88e.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/1020/q/80";
        for (int i=0;i<100;i++){
            if(i % 2 == 0){
                data.add(jsEntry);
            }else{
                data.add(jsEntry2);
            }
        }
        return data;
    }

    private void initView() {
        mAppBarLayout = (AppBarLayout) findViewById(R.id.jianshu_appbar_layout);
        mLine = findViewById(R.id.line_divider);
        mRecyclerView = (RecyclerView) findViewById(R.id.vertical_recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        MyAdapter myAdapter = new MyAdapter();
        mRecyclerView.setAdapter(myAdapter);
        myAdapter.setData(mockData());
        myAdapter.notifyDataSetChanged();


        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(Math.abs(verticalOffset) >= mAppBarLayout.getTotalScrollRange()){
                    mLine.setVisibility(View.VISIBLE);
                }else{
                    mLine.setVisibility(View.GONE);
                }
            }
        });
    }
    public static class MyAdapter extends RecyclerView.Adapter{
        private List<JsEntry> mData;

        public void setData(List<JsEntry> data) {
            mData = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.jianshu_label_item,null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            JsEntry jsEntry = mData.get(position);
            viewHolder.title.setText(jsEntry.title);
            viewHolder.name.setText(jsEntry.authorName);
            viewHolder.label.setText(jsEntry.label);
            viewHolder.time.setText(jsEntry.time);
            viewHolder.comment.setText(String.format(viewHolder.comment.getContext().getResources().getString(R.string.js_comment),jsEntry.seek,jsEntry.comment,jsEntry.like,jsEntry.award));
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0:mData.size();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView time;
        private TextView comment;
        private TextView label;
        private TextView name;
        private ImageView cover;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_content);
            time = (TextView) itemView.findViewById(R.id.publish_time);
            comment = (TextView) itemView.findViewById(R.id.js_comment);
            label = (TextView) itemView.findViewById(R.id.js_label);
            name = (TextView) itemView.findViewById(R.id.author_name);
            cover = (ImageView) itemView.findViewById(R.id.cover);
        }
    }
}
