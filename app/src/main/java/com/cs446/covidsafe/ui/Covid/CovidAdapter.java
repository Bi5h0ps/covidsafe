package com.cs446.covidsafe.ui.Covid;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.cs446.covidsafe.ui.Covid.CovidInfo.CovidInfoFragment;
import com.cs446.covidsafe.ui.Covid.CovidStats.CovidStatsFragment;
import com.cs446.covidsafe.ui.Covid.CovidUpdates.CovidUpdatesFragment;

public class CovidAdapter extends FragmentStateAdapter {
    public CovidAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 1:
                return new CovidStatsFragment();
            case 2:
                return new CovidUpdatesFragment();
        }
        return new CovidInfoFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
