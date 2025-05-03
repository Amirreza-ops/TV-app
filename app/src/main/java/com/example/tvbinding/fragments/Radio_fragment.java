package com.example.tvbinding.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvbinding.R;
import com.example.tvbinding.adapters.Channels_adapter;
import com.example.tvbinding.app.app;
import com.example.tvbinding.databinding.FragmentRadioBinding;
import com.example.tvbinding.models.Channel_List_model;
import com.example.tvbinding.models.Channel_model;
import com.example.tvbinding.retrofit.Retrofit_client;
import com.example.tvbinding.retrofit.Retrofit_interface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Radio_fragment extends Fragment {

    FragmentRadioBinding binding;
    RecyclerView recyclerView;
    List<Channel_model> list;
    Channels_adapter adapter;
    String masterKey = "$2a$10$iqhpL8k6.Qaip9PxmRWUmOXxvUncJWyzT7QkUu1VKrLxNnsEk7LUG";

    Retrofit_interface retrofitInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_radio, container, false);

        init();
        getRadioChannels();

        return binding.getRoot();
    }


    private void init(){
        recyclerView = binding.recyclerView;
        retrofitInterface = Retrofit_client.getRetrofit().create(Retrofit_interface.class);
        list = new ArrayList<>();
        adapter = new Channels_adapter(list, getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }

    private void getRadioChannels(){

        retrofitInterface.getRadioChannels(masterKey).enqueue(new Callback<Channel_List_model>() {
            @Override
            public void onResponse(Call<Channel_List_model> call, Response<Channel_List_model> response) {
                list.addAll(response.body().getChannelModels());

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Channel_List_model> call, Throwable t) {
                app.t("اتصال به سرور برقرار نشد");
            }
        });

    }
}
