package com.example.admin.appclean.presentation.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.appclean.presentation.ui.Fragment.OnboardHolderFragment;


public class OnboardAdapter extends FragmentPagerAdapter {


    //Crea el objeto para ser instanciado despues
    OnboardHolderFragment onboardHolderFragment;

    public OnboardAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return onboardHolderFragment.newInstance(position + 1);

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }

}