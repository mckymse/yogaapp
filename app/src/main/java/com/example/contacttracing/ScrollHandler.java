package com.example.contacttracing;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class ScrollHandler extends CoordinatorLayout.Behavior<MeowBottomNavigation> {
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull MeowBottomNavigation child, @NonNull View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull MeowBottomNavigation child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        //return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull MeowBottomNavigation child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        //super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);

        if(dy<0){
            showBottomNavigationView(child);
        }else if(dy > 0){
            hideBottomNavigationView(child);
        }

    }

    private void hideBottomNavigationView(MeowBottomNavigation view){
        view.animate().translationY(view.getHeight());
    }

    private void showBottomNavigationView(MeowBottomNavigation view){
        view.animate().translationY(0);
    }
}
