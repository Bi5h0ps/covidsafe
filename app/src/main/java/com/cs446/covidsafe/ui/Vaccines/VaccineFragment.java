package com.cs446.covidsafe.ui.Vaccines;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs446.covidsafe.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VaccineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VaccineFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAB_1_NAME = "Vaccine Info";
    private static final String TAB_2_NAME = "Vaccine Locations";
    private static final String TAB_3_NAME = "Vaccine Alerts";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager2)
    ViewPager2 mViewPager2;
    VaccineAdapter mFragmentStateAdapter;

    public VaccineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VaccineFragment.
     */
    public static VaccineFragment newInstance(String param1, String param2) {
        VaccineFragment fragment = new VaccineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vaccine, container, false);
        ButterKnife.bind(this, view);

        FragmentManager fm = getChildFragmentManager();
        mFragmentStateAdapter = new VaccineAdapter(fm, getLifecycle());
        mViewPager2.setUserInputEnabled(false);
        mViewPager2.setAdapter(mFragmentStateAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText(TAB_1_NAME));
        mTabLayout.addTab(mTabLayout.newTab().setText(TAB_2_NAME));
        mTabLayout.addTab(mTabLayout.newTab().setText(TAB_3_NAME));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                mTabLayout.selectTab(mTabLayout.getTabAt(position));
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}