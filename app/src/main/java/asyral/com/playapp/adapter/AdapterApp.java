package asyral.com.playapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;

import asyral.com.playapp.R;
import asyral.com.playapp.api.Response.Entry;
import asyral.com.playapp.holder.HolderApp;

/**
 * Created by guerra on 12/01/17.
 * AdapterApp
 */

public class AdapterApp extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Entry> entryArray;
    private int lastPosition = -1;

    public AdapterApp() {
        this.entryArray = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_app, parent, false);
        return new HolderApp(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HolderApp mHol = (HolderApp) holder;
        Entry entry = entryArray.get(position);
        mHol.setData(entry);
        mHol.setClick(entry);
        //setAnimation(mHol.cardApp, position);
    }

    @Override
    public int getItemCount() {
        return entryArray.size();
    }

    public void addData(ArrayList<Entry> newEntryArray) {
        entryArray.addAll(newEntryArray);
        notifyDataSetChanged();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.slide_up_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}