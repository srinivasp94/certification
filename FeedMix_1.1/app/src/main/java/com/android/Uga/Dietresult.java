package com.android.Uga;

/**
 * Created by New android on 19-08-2016.
 */
public class Dietresult {
    int protien =-1;
    int energy =-1;
    int ca     =-1;
    int avp =-1;
    int cap = -1;
    int meth =-1;
    int lys =-1;
    int fiber =-1;
    int sodium =-1;
    public boolean isGood(){
        return (protien==0 && energy ==0 && ca==0 && avp ==0 && cap == 0 && meth ==0&& lys==0 && fiber==0 && sodium==0 );
    }
    public String protienText(){
        if(protien==0){
            return "Good";
        }else if(protien==-1){
            return  "Low";
       }else{
            return "High";
        }
    }

    public String energyText(){
        if(energy==0){
            return "Good";
        }else if(energy==-1){
            return  "Low";
        }else{
            return "High";
        }
    }
    public String caText(){
        if(ca==0){
            return "Good";
        }else if(ca==-1){
            return  "Low";
        }else{
            return "High";
        }
    }
    public String capText(){
        if(cap==0){
            return "Good";
        }else if(cap==-1){
            return  "Low";
        }else{
            return "High";
        }
    }
    public String avpText(){
        if(avp==0){
            return "Good";
        }else if(avp==-1){
            return  "Low";
        }else{
            return "High";
        }
    }
    public String methText(){
        if(meth==0){
            return "Good";
        }else if(meth==-1){
            return  "Low";
        }else{
            return "High";
        }
    }
    public String lysText(){
        if(lys==0){
            return "Good";
        }else if(lys==-1){
            return  "Low";
        }else{
            return "High";
        }
    }
    public String fiberText(){
        if(fiber==0){
            return "Good";
        }else if(fiber==-1){
            return  "Low";
        }else{
            return "High";
        }
    }
    public String sodiumText(){
        if(sodium==0){
            return "Good";
        }else if(sodium==-1){
            return  "Low";
        }else{
            return "High";
        }
    }

}
