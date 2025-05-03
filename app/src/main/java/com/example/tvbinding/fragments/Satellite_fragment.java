package com.example.tvbinding.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.tvbinding.R;
import com.example.tvbinding.databinding.FragmentSatelliteBinding;

public class Satellite_fragment extends Fragment {

    FragmentSatelliteBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_satellite, container, false);

        return binding.getRoot();
    }
}
