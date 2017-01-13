package asyral.com.playapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import asyral.com.playapp.R;
import asyral.com.playapp.data.Category;
import asyral.com.playapp.holder.HolderCategory;

/**
 * Created by guerra on 11/01/17.
 * AdapterCategory
 */

public class AdapterCategory extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Category> categoryArray;

    public AdapterCategory() {
        this.categoryArray = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_category, parent, false);
        return new HolderCategory(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HolderCategory mHol = (HolderCategory) holder;
        Category category = categoryArray.get(position);
        mHol.setData(category);
        mHol.setClick(category);
    }

    @Override
    public int getItemCount() {
        return categoryArray.size();
    }

    public void addData(ArrayList<Category> newCategoryArray) {
        categoryArray.addAll(newCategoryArray);
        notifyDataSetChanged();
    }

}
