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
import com.example.tvbinding.databinding.FragmentTvBinding;
import com.example.tvbinding.models.Channel_List_model;
import com.example.tvbinding.models.Channel_model;
import com.example.tvbinding.retrofit.Retrofit_client;
import com.example.tvbinding.retrofit.Retrofit_interface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tv_fragment extends Fragment {

    FragmentTvBinding binding;

    Retrofit_interface retrofitInterface;

    List<Channel_model> list;
    RecyclerView recyclerView;
    Channels_adapter adapter;

    String masterKey = "$2a$10$iqhpL8k6.Qaip9PxmRWUmOXxvUncJWyzT7QkUu1VKrLxNnsEk7LUG";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv, container, false);

        init();
        getChannels();


        return binding.getRoot();
    }


    private void init() {
        recyclerView = binding.channelsRecyclerview;
        retrofitInterface = Retrofit_client.getRetrofit().create(Retrofit_interface.class);
        list = new ArrayList<>();
        adapter = new Channels_adapter(list, getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }


    private void getChannels() {
        retrofitInterface.getCategory(masterKey).enqueue(new Callback<Channel_List_model>() {
            @Override
            public void onResponse(Call<Channel_List_model> call, Response<Channel_List_model> response) {

                list.addAll(response.body().getChannelModels());

                adapter.notifyDataSetChanged();
//                app.t(list.size() + " ____ ");


            }

            @Override
            public void onFailure(Call<Channel_List_model> call, Throwable t) {
                app.t("اتصال به سرور برقرار نشد");
            }
        });



//        retrofitInterface.getCategory("$2a$10$iqhpL8k6.Qaip9PxmRWUmOXxvUncJWyzT7QkUu1VKrLxNnsEk7LUG").enqueue(new Callback<Channel_List_model>() {
//            @Override
//            public void onResponse(Call<List<Channel_model>> call, Response<List<Channel_model>> response) {
//                list.addAll(response.body());
//
//                app.t(list.size() + " ____ ");
//            }
//
//            @Override
//            public void onFailure(Call<List<Channel_model>> call, Throwable t) {
//                app.t("اتصال به سرور برقرار نشد");
//            }
//        });
    }
}
