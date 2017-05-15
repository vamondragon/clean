package com.example.admin.appclean.presentation.ui.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.appclean.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class OnboardHolderFragment extends Fragment {


    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";


    int[] bgs = new int[]{R.drawable.ic_flight_24dp, R.drawable.ic_mail_24dp, R.drawable.ic_explore_24dp};
    String[] titleArray = new String[]{"Pasado", "Presente", "Futuro"};
    String titleValue = "";


    TextView tituloPagina;
    ImageView imagenPantalla;

    public OnboardHolderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static OnboardHolderFragment newInstance(int sectionNumber) {
        OnboardHolderFragment fragment = new OnboardHolderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        titleValue = titleArray[getArguments().getInt(ARG_SECTION_NUMBER) - 1];


        View rootView = inflater.inflate(R.layout.fragment_onboarding, container, false);

        tituloPagina = (TextView) rootView.findViewById(R.id.section_label);
        tituloPagina.setText(getString(R.string.section_format, titleValue));

        imagenPantalla = (ImageView) rootView.findViewById(R.id.section_img);
        imagenPantalla.setBackgroundResource(bgs[getArguments().getInt(ARG_SECTION_NUMBER) - 1]);


        return rootView;
    }


}