package com.example.customeview.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.customeview.R;

/**
 * Created by 林其望 on 2017/7/14.
 */
public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampleViewHolder> {

    private final LayoutInflater layoutInflater;

    public SampleAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView sampleView = (TextView) layoutInflater.inflate(R.layout.view_sample, parent, false);
        return new SampleViewHolder(sampleView);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        holder.tv.setText(Integer.toString(position));
    }

    @Override
    public int getItemCount() {
        return 31;
    }

    class SampleViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public SampleViewHolder(View itemView) {
            super(itemView);
            this.tv = (TextView) itemView;
        }
    }
}

