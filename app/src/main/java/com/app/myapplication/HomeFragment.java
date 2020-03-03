package com.app.myapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    View myFragment;

    ViewPager viewPager;
    TabLayout tabLayout;
    ImageButton leftNav;
    ImageButton rightNav;

    private int[] tabIcons = {
            R.drawable.ic_action_left,
            R.drawable.ic_action_right,
            R.drawable.ic_add_black_24dp
    };


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment getInstance()    {
        return new HomeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = myFragment.findViewById(R.id.viewPager);
        tabLayout = myFragment.findViewById(R.id.tabLayout);

        leftNav = (ImageButton) myFragment.findViewById(R.id.left_nav);
        rightNav = (ImageButton) myFragment.findViewById(R.id.right_nav);


        leftNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    viewPager.setCurrentItem(tab);
                } else if (tab == 0) {
                    viewPager.setCurrentItem(tab);
                }
            }
        });

        // Images right navigatin
        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                tab++;
                viewPager.setCurrentItem(tab);
            }
        });

        return myFragment;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        setUpTabIcon();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getActivity(), "Click " + tab.getPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpTabIcon() {
        setTab(0,R.drawable.img_tab1);
        setTab(1,R.drawable.img_tab2);
        setTab(2,R.drawable.img_tab3);
        setTab(3,R.drawable.img_tab4);
    }

    private void setTab(int i, int button_shape) {
       View v= tabLayout.getTabAt(i).setCustomView(R.layout.layout_item_tab).getCustomView();
       v.setBackgroundResource(button_shape);
    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new BlankFragmentOne(), "One");
        adapter.addFragment(new BlankFragmentTwo(), "Two");
        adapter.addFragment(new BlankFragmentThree(), "Three");
        adapter.addFragment(new BlankFragmentFour(), "Four");

        viewPager.setAdapter(adapter);
    }
}
