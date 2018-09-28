package com.tiqs.rapmedix;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Utils {
  // isValidEmailAddress: Check the email address is OK
  public static boolean isValidEmailAddress(String emailAddress) {
    String emailRegEx;
    Pattern pattern;
    // Regex for a valid email address
    emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
    // Compare the regex with the email address
    pattern = Pattern.compile(emailRegEx);
    Matcher matcher = pattern.matcher(emailAddress);
    if (!matcher.find()) {
      return false;
    }
    return true;
  }

}