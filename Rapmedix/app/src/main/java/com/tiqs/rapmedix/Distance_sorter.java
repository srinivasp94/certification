package com.tiqs.rapmedix;

import java.util.Comparator;


class Distance_sorter implements Comparator<Doctor_list_helper> {

@Override
public int compare(Doctor_list_helper objA, Doctor_list_helper objB) {
    // TODO Auto-generated method stub

   float i=Math.round(Double.parseDouble(objB.getDoctor_distance()));
   float i2=Math.round(Double.parseDouble(objA.getDoctor_distance()));
   int b = Math.round(i);
   int b2 = Math.round(i2);

    return b2 - b;

}
}