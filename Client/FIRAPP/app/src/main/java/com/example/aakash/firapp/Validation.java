package com.example.aakash.firapp;

import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by hp on 3/11/2016.
 */

    import android.widget.EditText;
    import java.util.regex.Pattern;

    public class Validation {

        // Regular Expression
        // you can change the expression based on your need
        private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        private static final String PHONE_REGEX = "^(\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$";
        private static final String PASS_REGEX="\\S*(\\S*([a-zA-Z]\\S*[0-9])|([0-9]\\S*[a-zA-Z]{4,8}))\\S*";
        private static final String FIRID_REGEX="\\S*(\\S*([a-zA-Z]\\S*[0-9])|([0-9]\\S*[a-zA-Z]))\\S*";


        // Error Messages
        private static final String REQUIRED_MSG = "This is Required Field";
        private static final String EMAIL_MSG = "Invalid Email";
        private static final String PHONE_MSG = "+91 ##########";
        private static final String PASS_MSG = "password containing at least one letter, at least one digit and no spaces";
        private static final String ID_MSG = "FIR ID  Containing at least one letter, at least one digit and no spaces";

        // call this method when you need to check email validation
        public static boolean isEmailAddress(EditText editText, boolean required) {
            return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
        }

        // call this method when you need to check phone number validation
        public static boolean isPhoneNumber(EditText editText, boolean required) {
            return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
        }
        public static boolean isPass(EditText editText, boolean required) {
            return isValid(editText, PASS_REGEX, PASS_MSG, required);
        }

        public static boolean isFirid(EditText editText, boolean required) {
            return isValid(editText, FIRID_REGEX, ID_MSG, required);
        }


        // return true if the input field is valid, based on the parameter passed
        public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

            String text = editText.getText().toString().trim();
            // clearing the error, if it was previously set by some other values
            editText.setError(null);

            // text required and editText is blank, so return false
            if ( required && !hasText(editText) ) return false;

            // pattern doesn't match so returning false
            if (required && !Pattern.matches(regex, text)) {
                editText.setError(errMsg);
                return false;
            };

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










