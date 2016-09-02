package com.example.animatorapp;
//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
//                  佛祖镇楼                  BUG辟易
//          佛曰:
//                  写字楼里写字间，写字间里程序员；
//                  程序人员写程序，又拿程序换酒钱。
//                  酒醒只在网上坐，酒醉还来网下眠；
//                  酒醉酒醒日复日，网上网下年复年。
//                  但愿老死电脑间，不愿鞠躬老板前；
//                  奔驰宝马贵者趣，公交自行程序员。
//                  别人笑我忒疯癫，我笑自己命太贱；
//                  不见满街漂亮妹，哪个归得程序员？

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animatorapp.databinding.ItemSampleLayoutBinding;

import java.util.List;

/**
 * Created by "林其望".
 * DATE: 2016:09:02:15:42
 * email:1105896230@qq.com
 */

public class SamplesRecyclerAdapter extends RecyclerView.Adapter<SamplesRecyclerAdapter.BindingHolder> {

    Context context;
    List<Sample> list;

    public SamplesRecyclerAdapter(Context context, List<Sample> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSampleLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_sample_layout,
                parent,
                false);
        return new BindingHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(final BindingHolder holder, int position) {
        final Sample sample = list.get(holder.getAdapterPosition());
        holder.binding.setSample(sample);
        holder.binding.sampleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.getAdapterPosition()) {
                    case 0:
                        transitionToActivity(TransLateActivity1.class, sample);
                        break;
                    case 1:
                        //共享元素
                        transitionToActivity(SharedElementActivity.class, holder, sample);
                        break;
                    case 2:
                        //修改物体的位置等
                        transitionToActivity(AnimationsActivity1.class, sample);
                        break;
                }
            }
        });
    }

    private void transitionToActivity(Class target, Sample sample) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants((Activity) context, true);
        startActivity(target, pairs, sample);
    }

    private void transitionToActivity(Class target, BindingHolder viewHolder, Sample sample) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants((Activity) context, false,
                new Pair<>(viewHolder.binding.sampleIcon, "square_blue"),
                new Pair<>(viewHolder.binding.sampleName, "sample_blue_title"));
        startActivity(target, pairs, sample);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, Sample sample) {
        Intent i = new Intent(context, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, pairs);
        i.putExtra("sample", sample);
        context.startActivity(i, transitionActivityOptions.toBundle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        public ItemSampleLayoutBinding binding;

        public BindingHolder(View itemView, ItemSampleLayoutBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }


}
