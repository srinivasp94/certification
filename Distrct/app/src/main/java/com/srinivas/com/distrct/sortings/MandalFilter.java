package com.srinivas.com.distrct.sortings;

import com.srinivas.com.distrct.models.educationModels;

import java.util.Comparator;

/**
 * Created by pegasys on 7/31/2018.
 */

public class MandalFilter implements Comparator<educationModels> {
    @Override
    public int compare(educationModels educationModels, educationModels t1) {
        return educationModels.name.compareTo(t1.name);
    }
}
