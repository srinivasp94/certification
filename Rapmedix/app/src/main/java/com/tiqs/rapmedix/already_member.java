package com.tiqs.rapmedix;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class already_member extends Fragment {

    String old_user_url,check_kit_id_url,vendor_login;
    ArrayList<String> image_title=new ArrayList<>();
    ArrayList<String> id=new ArrayList<>();
    AutoCompleteTextView actv,kit_id,name_inputt,mobile_input;
    ArrayAdapter adapter;
    Button submit,submit2;
    RelativeLayout root_view;
    String vendor_id="",vendor_name0="";
    EditText editText_email;
    LinearLayout email,details_page;

    int i=0;
    String kit_idd="",vendor_idd="",membership_id="";
    TextView Login,vendor_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.already_login_page, container, false);

        old_user_url =Constants.getmembershipvendors;
        check_kit_id_url =Constants.getmembershipvendorkits;
        vendor_login =Constants.corporateuseractivate;
        JSONObject  jsonObject = new JSONObject();

        final SharedPreferences sp = getActivity().getSharedPreferences(MyFirebaseInstanceIDService.pref, getActivity().MODE_PRIVATE);
        final String Notification = sp.getString("Notification", "Error");

        actv = (AutoCompleteTextView)v. findViewById(R.id.name_input);
        kit_id = (AutoCompleteTextView)v. findViewById(R.id.kid_id);
        name_inputt = (AutoCompleteTextView)v. findViewById(R.id.name_inputt);
        mobile_input = (AutoCompleteTextView)v. findViewById(R.id.mobile_input);

        submit = (Button) v. findViewById(R.id.submit);
        submit2 = (Button) v. findViewById(R.id.submit_2);

        email = (LinearLayout) v. findViewById(R.id.email);
        details_page = (LinearLayout) v. findViewById(R.id.details_page);

        editText_email = (EditText) v. findViewById(R.id.email_input);
        root_view = (RelativeLayout) v. findViewById(R.id.root_view);
        Login = (TextView) v. findViewById(R.id.Login);
        vendor_name = (TextView) v. findViewById(R.id.Vendor_Name);

        new GetContacts().execute();


        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String vendor_idd=adapterView.getItemAtPosition(i).toString();
                int idd =image_title.indexOf(vendor_idd);
                Log.e("idd",vendor_idd+"idd"+idd);

                vendor_id=id.get(idd);
                vendor_name0=image_title.get(idd);

            }
        });

      actv.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void afterTextChanged(Editable editable)
          {
              details_page.setVisibility(View.GONE);


          }
      });

       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (actv.getText().toString().trim().equals("") || kit_id.getText().toString().trim().equals("")) {
                   Snackbar.make(root_view, "Fields are empty", Snackbar.LENGTH_LONG)
                           .setAction("CLOSE", new View.OnClickListener() {
                               @Override
                               public void onClick(View view)

                               {

                               }
                           })
                           .show();
               } else {

                   JSONObject jsonObject = new JSONObject();
                   try {
                       jsonObject.put("vendor_id", vendor_id);
                       jsonObject.put("kitid", kit_id.getText().toString().trim());

                       call_custom_asynch(jsonObject, check_kit_id_url);

                   } catch (JSONException e) {
                       e.printStackTrace();
                   }


               }
           }
       });

        submit2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                   if (name_inputt.getText().toString().trim().equals("") || mobile_input.getText().toString().trim().equals(""))
                   {
                       Snackbar.make(root_view, "Fields are empty", Snackbar.LENGTH_LONG)
                               .setAction("CLOSE", new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view)

                                   {

                                   }
                               })
                               .show();
                   } /*else if (!editText_email.getText().toString().trim().isEmpty())
                   {
                       if (!Utils.isValidEmailAddress(editText_email.getText().toString().trim()))
                       {
                       editText_email.setError("Invalid Email ID");
                       }
                   }*/
                   else
                   {

                       JSONObject jsonObject = new JSONObject();
                       try {
                           jsonObject.put("vendor_id", vendor_idd);
                           jsonObject.put("kitid", kit_idd);
                           jsonObject.put("membership_id", membership_id);
                           jsonObject.put("name", actv.getText().toString().trim());
                           jsonObject.put("mobile", mobile_input.getText().toString().trim());
                           jsonObject.put("device_token", Notification);
                           jsonObject.put("device_type", "1");
                           jsonObject.put("email_id", editText_email.getText().toString().trim());

                           //   Log.e("tag",""+jsonObject);

                           call_custom_asynch_vendorLogin(jsonObject, vendor_login);

                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
               }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(getActivity(),member_login.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        return v;
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


    public static already_member newInstance(String text) {

        already_member f = new already_member();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);


        return f;
    }
    private  void call_custom_asynch(JSONObject jo, String url)
    {
        CustomAsync ca=new CustomAsync(getActivity(), jo, url, new OnAsyncCompleteRequest() {

            @Override
            public void asyncResponse(String result)
            {
                // TODO Auto-generated method stub
                if(result==null||result.equals(""))
                {
                    Snackbar.make(root_view, "Please Retry", Snackbar.LENGTH_LONG)
                            .setAction("CLOSE", new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View view)
                                {
                                }
                            })
                            .show();
                //    Toast.makeText(getActivity(), "Please Retry", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        final JSONObject j=new JSONObject(result);
                        String status=j.getString("status");
                        if(status.equals("success"))
                        {
                            i++;


                            kit_idd=j.getString("id").toString();
                             vendor_idd=j.getString("adviser_id").toString();
                             membership_id=j.getString("membership_id").toString();
                            String kit_name=j.getString("kit_name").toString();
                            String kitstatus=j.getString("kitstatus").toString();

                           // Toast.makeText(getActivity(), vendor_idd+""+kit_idd+""+status, Toast.LENGTH_SHORT).show();

                          //  Log.e("tag",vendor_idd+""+kit_idd+""+status);

                           /* actv.setText("");
                            kit_id.setText("");
                            vendor_name.setVisibility(View.VISIBLE);
                            vendor_name.setText(vendor_name0);
                            email.setVisibility(View.VISIBLE);

                            actv.setHint("Name");
                            kit_id.setHint("Mobile Number");
                            editText_email.setHint("Email ID");*/

                            details_page.setVisibility(View.VISIBLE);
                            editText_email.addTextChangedListener(new TextWatcher() {
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
 /* String s=editText_email.getText().toString().trim();
                                    if (isEmailValid(s))
                                    {

                                    }
                                    else
                                    {
                                        editText_email.setError("Email ID is invalid");
                                    }*/
                                }
                            });

                            mobile_input.addTextChangedListener(new TextWatcher() {
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
                                    String s=mobile_input.getText().toString();
                                    if (s.length()==10)
                                    {

                                        JSONObject jsonObject = new JSONObject();

                                        try {
                                            jsonObject.put("mobile",s);
                                            mobile_verification(jsonObject,Constants.mobile_verification);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            });

                        }
                        else{

                            Snackbar.make(root_view, ""+status, Snackbar.LENGTH_LONG)
                                    .setAction("CLOSE", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view)

                                        {

                                        }
                                    })
                                    .show();
                          //  Toast.makeText(getActivity(), ""+status, Toast.LENGTH_SHORT).show();

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

    public boolean isValidEmail(CharSequence char0)
    {
        return !TextUtils.isEmpty(char0)&& android.util.Patterns.EMAIL_ADDRESS.matcher(char0).matches();
    }


    private  void call_custom_asynch_vendorLogin(JSONObject jo, String url)
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
                            String otp=j.getString("otp");
                            Log.e("tag",uid+""+mobile+""+otp);

                            Toast.makeText(getActivity(), ""+uid, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), Otp_page.class);
                            intent.putExtra("uid",uid);
                            intent.putExtra("mobile",mobile);
                            intent.putExtra("otp",otp);
                            startActivity(intent);
                            getActivity().finish();


                            Toast.makeText(getActivity(),""+status, Toast.LENGTH_SHORT).show();



                        }else if (status.equals("This member already added"))
                        {
                            Snackbar.make(root_view, "This mobile  already added please enter new number ", Snackbar.LENGTH_LONG)
                                    .setAction("CLOSE", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view)

                                        {

                                        }
                                    })
                                    .show();
                        }
                        else{

                            Snackbar.make(root_view, ""+status, Snackbar.LENGTH_LONG)
                                    .setAction("CLOSE", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view)

                                        {

                                        }
                                    })
                                    .show();
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
    private  void mobile_verification(JSONObject jo, String url)
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

                        }
                        else{

                            Snackbar.make(root_view, "This mobile  already added please enter new number", Snackbar.LENGTH_LONG)
                                    .setAction("CLOSE", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view)

                                        {

                                        }
                                    })
                                    .show();
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

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(old_user_url);

            Log.e("tag", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("0");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {

                        JSONObject c = contacts.getJSONObject(i);
                        String id0=c.getString("id").toString();
                        String name0=c.getString("name").toString();

                        id.add(id0);
                        image_title.add(name0);

                     /*   String id = c.getString("name");
                        String name = c.getString("name");
                        String email = c.getString("email");
                        String address = c.getString("address");
                        String gender = c.getString("gender");

                        // Phone node is JSON Object
                        JSONObject phone = c.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("name", name);
                        contact.put("email", email);
                        contact.put("mobile", mobile);

                        // adding contact to contact list
                        contactList.add(contact);*/
                    }
                } catch (final JSONException e) {
                    Log.e("tag", "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e("tag", "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */

            adapter= new ArrayAdapter<String>
                    (getActivity(),android.R.layout.simple_list_item_1,image_title);
            actv.setAdapter(adapter);
        }

    }
}

