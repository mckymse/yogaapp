package com.example.contacttracing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity {
    private MeowBottomNavigation bottomNavigation;
    private ViewPager2 pager;
    private ScreenSlidePagerAdapter adapter;
    private final int NUM_PAGES=4;
    private final static int ID_HOME = 1;
    private final static int ID_EXPLORE = 2;
    private final static int ID_GROWTH = 3;
    private final static int ID_ACCOUNT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        pager =findViewById(R.id.pager);
        adapter = new ScreenSlidePagerAdapter(this);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        pager.setAdapter(adapter);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_explore));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_growth));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_person));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case ID_HOME:
                        pager.setCurrentItem(0);
                        break;
                    case ID_EXPLORE:
                        pager.setCurrentItem(1);
                        break;
                    case ID_GROWTH :
                        pager.setCurrentItem(2);
                        break;
                    case ID_ACCOUNT:
                        pager.setCurrentItem(3);
                        break;
                    default:
                        pager.setCurrentItem(0);
                        break;
                }
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });
    }
    // An equivalent ViewPager2 adapter class
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new Home();
                case 1:
                    return new Explore();
                case 2:
                    return new Statistics();
                case 3:
                    return new Account();
                default:
                    return new Home();
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}


