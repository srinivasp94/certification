package com.android.Uga;

import com.android.Uga.Pojo.Choose_diet_pojo;
import com.android.Uga.Pojo.MyData;

import java.util.ArrayList;
import java.util.Arrays;

import Adapter.Mydiet_adapter;

public class IngredientSession {

    public static ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();
    public static ArrayList<Integer> selectedIndexes1 = new ArrayList<Integer>();
    public static ArrayList<Integer> selectedIndexes123 = new ArrayList<Integer>();
    public static ArrayList<Integer> ids = new ArrayList<Integer>();
    public static boolean isLbs;
    public static boolean is_selected = false;

    public static void addIndex(int i,Double quantity){
            if (!selectedIndexes.contains(i)) {
                selectedIndexes.add(i);
            }
                if (!selectedIndexes1.contains(i)) {
                    selectedIndexes1.add(i);
                }
                AppConstants.list.get(i).setQuntity(quantity);



    }
    public static void removeIndex(int i,Double quantity){
        if(selectedIndexes.contains(i)) {
            selectedIndexes.remove(selectedIndexes.indexOf(i));
        }
        if(ids.contains(i)) {
            ids.remove(ids.indexOf(i));
        }
        if(selected.indexOf(AppConstants.list.get(i))!=-1) {
            selected.remove(selected.indexOf(AppConstants.list.get(i)));
        }
  }








    static ArrayList<Choose_diet_pojo>  selected = new ArrayList<Choose_diet_pojo>();

}
