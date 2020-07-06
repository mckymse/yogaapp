package com.example.contacttracing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
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
    private final int NUM_PAGES=4;
    private final static int ID_HOME = 1;
    private final static int ID_EXPLORE = 2;
    private final static int ID_GROWTH = 3;
    private final static int ID_ACCOUNT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_explore));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_growth));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_person));

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigation.getLayoutParams();
        layoutParams.setBehavior(new ScrollHandler());

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lay,new Home()).commit();
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });


        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // your codes
                Fragment select_Fragment = null;
                switch (item.getId()){
                    case ID_HOME:
                        select_Fragment = new Home();
                        break;
                    case ID_EXPLORE:
                        select_Fragment = new Explore();
                        break;
                    case ID_GROWTH:
                        select_Fragment = new Statistics();
                        break;
                    case ID_ACCOUNT:
                        select_Fragment = new Account();
                        break;
                    default:
                        select_Fragment = new Home();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_lay, select_Fragment).commit();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });
    }
}


