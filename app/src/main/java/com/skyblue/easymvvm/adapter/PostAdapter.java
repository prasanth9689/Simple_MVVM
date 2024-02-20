package com.skyblue.easymvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.skyblue.easymvvm.R;
import com.skyblue.easymvvm.databinding.ItemPostBinding;
import com.skyblue.easymvvm.model.PostModel;

import java.util.List;

import org.jetbrains.annotations.NotNull;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private List<PostModel> postModelList;

    public void addHolidayList(List<PostModel> currencyList) {
        this.postModelList = currencyList;
    }

    @Override
    public int getItemCount() {
        return postModelList != null ? postModelList.size() : 0;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_post, parent, false);

        return new MyViewHolder(binding);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemPostBinding binding;

        MyViewHolder(ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.binding.setModel( postModelList.get(position) );
    }
}