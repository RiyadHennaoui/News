package com.riyad.p5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<AbsFragment> myFragments = new ArrayList<>(4);

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        myFragments.add(TopStoriesFragment.newInstance("home"));
        myFragments.add(new MostPopularFragment());
        myFragments.add(TopStoriesFragment.newInstance("business"));
        myFragments.add(TopStoriesFragment.newInstance("sports"));
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }

    @Override
    public int getCount() {
        return myFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return myFragments.get(position).getTitle();
    }
}
