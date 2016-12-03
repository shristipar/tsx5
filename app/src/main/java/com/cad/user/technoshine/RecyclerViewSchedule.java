package com.cad.user.technoshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by sony on 28-09-2015.
 */
public class RecyclerViewSchedule extends
        RecyclerView.Adapter<RecyclerViewSchedule.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        //  public TextView textView;

        public ViewHolder(View itmView) {
            super(itmView);

        }

    }

    // Pass in the contact array into the constructor
    public RecyclerViewSchedule(){
    }

    @Override
    public RecyclerViewSchedule.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.schedule, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    // Return the total count of items
    @Override
    public int getItemCount() {
        return 1;
    }

}
