package edu.ccrm.util;

public class Validators {
    public static boolean isValidEmail(String email){
        return email!=null && email.contains("@");
    }
}