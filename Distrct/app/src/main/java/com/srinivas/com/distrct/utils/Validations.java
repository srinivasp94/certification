package com.srinivas.com.distrct.utils;

import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by pegasys on 6/4/2018.
 */

public class Validations {

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "[0-9]{10,13}";
    private static final String PIN_REGEX = "[0-9]{6}";
    private static final String TEXT_REGEX = "[A-Za-z]";

    // Error Messages
    private static final String REQUIRED_MSG = "This Field is Required";
    private static final String EMAIL_MSG = "Invalid Email";
    private static final String PHONE_MSG = "invalid Mobile Number";
    private static final String PIN_MSG = "invalid PIN Number";
    private static final String PASS_MSG = "Password Does not Match";

    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isvalid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }

    public static boolean isPhone(EditText editText, boolean required) {
        return isvalid(editText, PHONE_REGEX, PHONE_MSG, required);
    }

    public static boolean isTextEntered(EditText editText, boolean required) {
        return isvalid(editText, TEXT_REGEX, REQUIRED_MSG, required);
    }

    private static boolean isvalid(EditText editText, String textRegex, String requiredMsg, boolean required) {
        String str = editText.getText().toString().trim();
        editText.setError(null);


        // text required and editText is blank, so return false
        if (required && !hasText(editText)) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(textRegex, requiredMsg)) {
            editText.setError(requiredMsg);
            return false;
        }
        return true;

    }


    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }

}
