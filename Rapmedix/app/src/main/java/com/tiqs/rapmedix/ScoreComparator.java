package com.tiqs.rapmedix;

import java.util.Comparator;


class ScoreComparator implements Comparator<Doctor_list_helper> {

@Override
public int compare(Doctor_list_helper objA, Doctor_list_helper objB) {
    // TODO Auto-generated method stub
   return Integer.parseInt(objB.getExperience()) - Integer.parseInt(objA.getExperience());
}
}