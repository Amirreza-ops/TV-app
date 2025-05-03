package com.example.tvbinding.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.tvbinding.R;
import com.example.tvbinding.databinding.ActivityHomeBinding;
import com.example.tvbinding.fragments.Radio_fragment;
import com.example.tvbinding.fragments.Satellite_fragment;
import com.example.tvbinding.fragments.Tv_fragment;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Home_activity extends AppCompatActivity {

    ActivityHomeBinding binding;
    OnItemClick onItemClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        onItemClick = new OnItemClick();

        binding.setActivity(this);

        binding.setOnClick(onItemClick);


    }


    @BindingAdapter(value = {"android:activity", "android:textView"})
    public static void setBottomNav(ChipNavigationBar chipNavigationBar, Home_activity activity, TextView textView) {

        FragmentTransaction fragmentTransactionTV = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransactionTV.replace(R.id.frameLayout, new Tv_fragment());
        fragmentTransactionTV.commit();
        chipNavigationBar.setItemSelected(R.id.menu_tv, true);
        textView.setText("تلویزیون");

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                if (i == R.id.menu_tv) {

                    FragmentTransaction fragmentTransactionTV = activity.getSupportFragmentManager().beginTransaction();
                    fragmentTransactionTV.replace(R.id.frameLayout, new Tv_fragment());
                    fragmentTransactionTV.commit();
                    textView.setText("تلویزیون");

                } else if (i == R.id.menu_radio) {

                    FragmentTransaction fragmentTransactionRadio = activity.getSupportFragmentManager().beginTransaction();
                    fragmentTransactionRadio.replace(R.id.frameLayout, new Radio_fragment());
                    fragmentTransactionRadio.commit();
                    textView.setText("رادیو");

                } else if (i == R.id.menu_sat) {

                    FragmentTransaction fragmentTransactionSat = activity.getSupportFragmentManager().beginTransaction();
                    fragmentTransactionSat.replace(R.id.frameLayout, new Satellite_fragment());
                    fragmentTransactionSat.commit();
                    textView.setText("ماهواره");

                }
            }
        });
    }


    @BindingAdapter(value = {"android:drawer", "android:activity"})
    public static void setNavigation(NavigationView navigationView, DrawerLayout drawerLayout, Home_activity activity) {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_telegram:
                        drawerLayout.closeDrawers();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://t.me/alpo_dev"));
                        activity.startActivity(intent);

                        break;

                    case R.id.menu_rate:
                        drawerLayout.closeDrawers();
                        Intent intent1 = new Intent(Intent.ACTION_VIEW);
                        intent1.setData(Uri.parse("https://toplearn.com/"));
                        activity.startActivity(intent1);
                        break;

                    case R.id.menu_share:
                        drawerLayout.closeDrawers();
                        Intent intentShare = new Intent();
                        intentShare.setAction(Intent.ACTION_SEND);
                        intentShare.putExtra(Intent.EXTRA_TEXT, "برای دانلود این برنامه به لینک زیر برو\n" +
                                "https://toplearn.com/");
                        intentShare.setType("text/plain");
                        Intent intent2 = Intent.createChooser(intentShare, null);
                        activity.startActivity(intent2);
                        break;

                    case R.id.menu_aboutUs:
                        drawerLayout.closeDrawers();
                        Intent intent3 = new Intent(Intent.ACTION_VIEW);
                        intent3.setData(Uri.parse("https://toplearn.com/about-us"));
                        activity.startActivity(intent3);
                        break;

                    case R.id.menu_exit:
                        drawerLayout.closeDrawers();
                        activity.finish();

                        break;
                }
                return true;
            }
        });
    }


    public class OnItemClick{
        public void onMenu(View view, DrawerLayout drawerLayout){
            drawerLayout.openDrawer(Gravity.RIGHT);
        }
    }
}