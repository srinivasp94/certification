package com.srinivas.com.distrct.sortings;

import com.srinivas.com.distrct.models.educationModels;

import java.util.Comparator;

/**
 * Created by pegasys on 7/31/2018.
 */

public class NameFilter implements Comparator<educationModels> {

    @Override
    public int compare(educationModels educationModels, educationModels t1) {
        return educationModels.title.compareTo(t1.title);
    }
}
