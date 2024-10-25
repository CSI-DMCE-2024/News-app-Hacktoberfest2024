package com.example.news;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsetsController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.news.adapters.ViewPagerAdapter;
import com.example.news.fragments.HomeFragment;
import com.example.news.fragments.PersonalFragment;
import com.example.news.fragments.SearchFragment;
import com.example.news.fragments.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private NotificationsFragment notificationsFragment;
    private PersonalFragment personalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(getResources().getColor(R.color.white, this.getTheme()));

        // Ensure the status bar icons are black if the background is light
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().getDecorView().getWindowInsetsController().setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        // Initialize fragments
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        notificationsFragment = new NotificationsFragment();
        personalFragment = new PersonalFragment();

        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set up ViewPager
        setupViewPager(viewPager);

        // Set up BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    int itemId = item.getItemId();
                    if(itemId == R.id.menu_home){
                        viewPager.setCurrentItem(0);
                        return true;
                    }
                    else if(itemId == R.id.menu_search){
                        viewPager.setCurrentItem(1);
                        return true;
                    }
                    else if(itemId == R.id.menu_subscription){
                        viewPager.setCurrentItem(2);
                        return true;
                    }
                    else if(itemId == R.id.menu_personal){
                        viewPager.setCurrentItem(3);
                        return true;
                    }
                    else return false;
                });

        // Sync ViewPager changes with BottomNavigationView
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), ViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(homeFragment);
        adapter.addFragment(searchFragment);
        adapter.addFragment(notificationsFragment);
        adapter.addFragment(personalFragment);
        viewPager.setAdapter(adapter);
    }
}
