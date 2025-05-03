package com.example.tvbinding.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvbinding.R;
import com.example.tvbinding.databinding.ItemsChannelBinding;
import com.example.tvbinding.models.Channel_model;
import com.example.tvbinding.views.RadioPlayer_activity;
import com.example.tvbinding.views.TVPlayer_activity;

import java.util.List;

public class Channels_adapter extends RecyclerView.Adapter<Channels_adapter.MyViewHolder> {

    List<Channel_model> list;
    Context context;

    public Channels_adapter(List<Channel_model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        ItemsChannelBinding binding = DataBindingUtil.inflate(inflater, R.layout.items_channel, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.binding.setModel(list.get(position));

        OnItem onItem = new OnItem();

        holder.binding.setOnClickItem(onItem);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ItemsChannelBinding binding;

        public MyViewHolder(@NonNull ItemsChannelBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }

    public class OnItem {
        public void onItemClick(View view, String play_link, String type) {
            if (type.equals("Radio")) {
                Intent intent = new Intent(context, RadioPlayer_activity.class);
                intent.putExtra("play_link", play_link);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, TVPlayer_activity.class);
                intent.putExtra("play_link", play_link);
                context.startActivity(intent);
            }
        }
    }
}
