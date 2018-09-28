package com.tiqs.rapmedix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tiqs.rapmedix.firebase.receivers.MyFirebaseInstanceIDService;
import com.tiqs.rapmedix.intrfc.OnAsyncCompleteRequest;
import com.tiqs.rapmedix.utils.Constants;
import com.tiqs.rapmedix.utils.CustomAsync;
import com.tiqs.rapmedix.utils.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class new_user extends Fragment
{
    EditText mobile_number,name,email1;
    LinearLayout mobile;
    Button submit;
    User user_data;
    RelativeLayout parentLayout;
    String valid_email,emailPattern ;
    String login_page_url;
    TextView Login;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_login_page_weighted, container, false);
       parentLayout =(RelativeLayout) v.findViewById(R.id.root_view);
        mobile_number = (EditText)v. findViewById(R.id.mobile_number_input);
        name = (EditText) v.findViewById(R.id.name_input);
        email1 = (EditText) v.findViewById(R.id.email_input);
        submit = (Button) v.findViewById(R.id.submit);
        Login = (TextView) v.findViewById(R.id.Login);

        final SharedPreferences sp = getActivity().getSharedPreferences(MyFirebaseInstanceIDService.pref, getActivity().MODE_PRIVATE);
        final String Notification = sp.getString("Notification", "Error");
       // Log.e("aa", "ccc" + Notification);
        login_page_url =Constants.login;


       email1.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
           {

           }

           @Override
           public void afterTextChanged(Editable editable)
           {
     /*          String s=email1.getText().toString().trim();
       if (Utils.isValidEmailAddress(s))
       {

       }
               else
       {
           email1.setError("Email ID is invalid");
       }*/
           }
       });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               /* DataBase_Helper dataBaseHelper =new DataBase_Helper(getActivity());
                    user_data.setId("khusaki");
                    user_data.setName("khusaki tech");
                   user_data.setEmail("gjgjg@gmail.com");
                   user_data.setMobile("9908816530");
                   user_data.setCity("hyderab");
                   user_data.setUid("001123");
                   dataBaseHelper.insertData(user_data);*/

                    if (name.getText().toString().trim().equals("") || mobile_number.getText().toString().trim().equals("")) {

                            Snackbar.make(v, "Fields are empty", Snackbar.LENGTH_LONG)
                                .setAction("CLOSE", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view)

                                    {

                                    }
                                })
                                .show();
                    }

                   /* else if (!email1.getText().toString().trim().isEmpty())
                    {
                        if (!Utils.isValidEmailAddress(email1.getText().toString().trim()))
                        {
                            Log.e("log for email","email invalid0");
                            email1.setError("Email ID is invalid");

                        }
                    }*/
                        else
                        {
                            Log.e("log for email","email invalid");


                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("name", name.getText().toString().trim());
                            jsonObject.put("mobile", mobile_number.getText().toString().trim());
                            jsonObject.put("email_id", email1.getText().toString().trim());
                            jsonObject.put("device_token", Notification);
                            jsonObject.put("device_type", "1");

                            call_custom_asynch(jsonObject,login_page_url);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                    }



        });

/*Login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view)
    {
        getActivity().finish();

        Intent intent=new Intent(getActivity(),member_login.class);
        startActivity(intent);
    }
});*/
        return v;

    }

    public static boolean isEmailValid1(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    private  void call_custom_asynch(JSONObject jo, String url)
    {
        CustomAsync ca=new CustomAsync(getActivity(), jo, url, new OnAsyncCompleteRequest() {

            @Override
            public void asyncResponse(String result) {
                // TODO Auto-generated method stub
                if(result==null||result.equals(""))
                {
                    Toast.makeText(getActivity(), "Please Retry", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        JSONObject j=new JSONObject(result);
                        String status=j.getString("status");
                        if(status.equals("success"))
                        {
                            String uid=j.getString("user_id");
                            String mobile=j.getString("mobile");
                            String namee=j.getString("name");
                            String otp=j.getString("otp");

//Log.e("otp","otp  "+otp+"  mob  "+mobile+"  uid  "+uid);
                            Toast.makeText(getContext(), ""+uid, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), Otp_page.class);
                            intent.putExtra("uid",uid);
                            intent.putExtra("mobile",mobile);
                            intent.putExtra("otp",otp);
                            intent.putExtra("name",namee);
                            startActivity(intent);
                            getActivity().finish();

                        }
                        else{

                            Toast.makeText(getActivity(), ""+status, Toast.LENGTH_SHORT).show();
                        }



                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        });
        ca.execute();
    }



    public static new_user newInstance(String text) {

        new_user f = new new_user();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}