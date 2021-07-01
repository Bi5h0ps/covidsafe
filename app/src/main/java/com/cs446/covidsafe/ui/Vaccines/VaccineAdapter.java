package com.cs446.covidsafe.ui.Vaccines;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.cs446.covidsafe.ui.Vaccines.VaccineAlert.VaccineAlertFragment;
import com.cs446.covidsafe.ui.Vaccines.VaccineInfo.VaccineInfoFragment;
import com.cs446.covidsafe.ui.Vaccines.VaccineLocation.VaccineLocationFragment;

public class VaccineAdapter extends FragmentStateAdapter {
    public VaccineAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 1:
                return new VaccineLocationFragment();
            case 2:
                return new VaccineAlertFragment();
        }
        return new VaccineInfoFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
