package com.habitree.xueshu.punchcard.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class PunchCardFragment extends Fragment {


    public PunchCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_punch_card, container, false);
    }

    public static PunchCardFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(Constant.POSITION,position);
        PunchCardFragment fragment = new PunchCardFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
