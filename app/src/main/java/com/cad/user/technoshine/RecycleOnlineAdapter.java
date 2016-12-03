package com.cad.user.technoshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class RecycleOnlineAdapter extends
        RecyclerView.Adapter<RecycleOnlineAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View itmView) {
            super(itmView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }

    }
    private int[] imglist;

    // Pass in the contact array into the constructor
    public RecycleOnlineAdapter(int[] contacts) {
        imglist = contacts;
    }

    @Override
    public RecycleOnlineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_item_card_big, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecycleOnlineAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ImageView im=viewHolder.imageView;

        // Set item views based on the data model
        im.setImageResource(imglist[position]);
        im.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        im.setPadding(10,10,10,10);
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return imglist.length;
    }
}