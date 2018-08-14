package com.android.Uga;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by New android on 14-07-2016.
 */
public class tab1Fragement extends BaseContainerFragment  {
View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.tab1, null);

        return view;
    }


    }
