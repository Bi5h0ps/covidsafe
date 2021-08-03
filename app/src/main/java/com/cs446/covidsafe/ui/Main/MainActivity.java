package com.cs446.covidsafe.ui.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;

import com.cs446.covidsafe.BuildConfig;
import com.cs446.covidsafe.R;
import com.cs446.covidsafe.ui.Covid.CovidFragment;
import com.cs446.covidsafe.ui.Vaccines.VaccineFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    CovidFragment mCovidFragment;
    VaccineFragment mVaccineFragment;
    private FragmentManager fragmentManager;

    @BindView(R.id.rg)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();

        showFragment(1);
        radioGroup.check(R.id.rb_1);
        radioGroup.setOnCheckedChangeListener(this);
        // Initialize the SDK
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), BuildConfig.PLACES_API_KEY);
        }
    }

    private void showFragment(int page) {
        FragmentTransaction ft = fragmentManager.beginTransaction();

        hideFragments(ft);
        switch (page) {
            case 1:
                if (mCovidFragment != null)
                    ft.show(mCovidFragment);
                else {
                    mCovidFragment = new CovidFragment();
                    ft.add(R.id.fl_content_main, mCovidFragment);
                }
                break;
            case 2:
                if (mVaccineFragment != null)
                    ft.show(mVaccineFragment);
                else {
                    mVaccineFragment = new VaccineFragment();
                    ft.add(R.id.fl_content_main, mVaccineFragment);
                }
                break;
        }
        ft.commit();
    }

    public void hideFragments(FragmentTransaction ft) {
        if (mCovidFragment != null)
            ft.hide(mCovidFragment);
        if (mVaccineFragment != null)
            ft.hide(mVaccineFragment);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_1:
                showFragment(1);
                break;
            case R.id.rb_2:
                showFragment(2);
                break;
        }
    }
}