package com.habitree.xueshu.supervision.fragment;


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
public class SupervisionFragment extends Fragment {


    public SupervisionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supervision, container, false);
    }

    public static SupervisionFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt(Constant.POSITION, position);
        SupervisionFragment fragment = new SupervisionFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
