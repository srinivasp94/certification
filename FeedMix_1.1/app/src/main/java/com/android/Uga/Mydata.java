package com.android.Uga;

import java.util.ArrayList;

public class Mydata{
        public int getIndex() {
                return index;
        }

        public void setIndex(int index) {
                this.index = index;
        }

        private int index;

        public double getQuant() {
                return quant;
        }

        private  double quant;



        public Mydata(int index,double quant){
                this.index = index;
                this.quant = quant;
        }

        //Not using this function
/*
        public boolean equals(Object o){
                return o instanceof Mydata && index == ((Mydata) o).index;
        }
*/

        public static int getIndexByIndex(int index, ArrayList<Mydata> objList){
                for (int i = 0; i < objList.size(); i++) {
                        if(index == objList.get(i).index){
                                return i;
                        }
                }
                return -1;
        }
        public boolean hasIndex(int index,ArrayList<Mydata>list){
                if(index<list.size()){
                        return true;

                }else {
                        return false;
                }
        }
}
