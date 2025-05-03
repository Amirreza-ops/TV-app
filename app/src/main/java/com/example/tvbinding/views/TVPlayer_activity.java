package com.example.tvbinding.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.devbrackets.android.exomedia.ui.widget.controls.VideoControls;
import com.devbrackets.android.exomedia.ui.widget.controls.VideoControlsTv;
import com.example.tvbinding.R;
import com.example.tvbinding.app.app;
import com.example.tvbinding.databinding.ActivityTvplayerBinding;

public class TVPlayer_activity extends AppCompatActivity {

    ActivityTvplayerBinding binding;

    static String play_link;

    OnRotate onRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tvplayer);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tvplayer);

        play_link = getIntent().getStringExtra("play_link");

        onRotate = new OnRotate();

        binding.setOnClickItem(onRotate);


    }


    @BindingAdapter("android:play")
    public static void setTVPlayer(VideoView videoView, ProgressBar progressBar){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            videoView.setFocusedByDefault(false);
        }
        videoView.setMedia(Uri.parse(play_link));
        videoView.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared() {
                progressBar.setVisibility(View.GONE);
                videoView.start();
            }
        });
    }


    public class OnRotate{
        public void onClick(View view){
            if (getResources().getConfiguration().orientation== Surface.ROTATION_90){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            if (getResources().getConfiguration().orientation==Surface.ROTATION_180){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }
    }


}