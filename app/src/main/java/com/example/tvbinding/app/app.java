package com.example.tvbinding.app;

import android.util.Log;
import android.widget.Toast;

public class app {
    public static final String TAG = "TV_Binding";

    public static void l(String message){
        Log.e(TAG, message);
    }

    public static void t(String message){
        Toast.makeText(application.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
