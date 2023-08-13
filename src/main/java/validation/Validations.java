package validation;

import exception.ValidationException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class Validations {
    static private final String nameRegex = "^[A-Za-z\\s]+$";
    static private final String passWord = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$";
    static private final String addressPattern = "^[a-zA-Z0-9\\s.,'#\\-]+(\\s[A-Za-z0-9\\-#]+)?$";
    static private final String vehicleNumberPattern = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$";



    public static void rejectIfStringNullOrEmpty(String strInput) throws ValidationException {

        if(strInput == null || "".equals(strInput.trim()) ){
            throw new ValidationException("Invalid String input");
        }

    }


    public boolean stringValidation(String name) throws InvalidEntryException {
        Matcher match;
        try {
            Pattern pat = Pattern.compile(nameRegex);
            if (name == null) {
                return false;
            }
            match = pat.matcher(name);
        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid name");
        }
        return match.matches();
    }

    public boolean numberValidation(Long number) throws InvalidEntryException {
        Matcher match;
        try {

            String num = Long.toString(number);

            //  Pattern pat = Pattern.compile(numberRegex);
            if (num.length() == 10) {
                return true;
            } else {
                return false;
            }


        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid Number ");
        }


    }

    public boolean passWordValidation(String s) throws InvalidEntryException {
        Matcher match;
        try {
            //if(s == null) return false;
            Pattern pt = Pattern.compile(passWord);
            match = pt.matcher(s);
            return match.matches();
        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid Password");
        }
    }
    public boolean WorkshopType(int i ){
        if(i == 2 || i == 3 || i ==4){
            return true;
        }else{
            return false;
        }
    }
    public  boolean addressValidation(String address) throws InvalidEntryException {
        Matcher match;
        try {
            Pattern pat = Pattern.compile(addressPattern);

            match = pat.matcher(address);
        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid address");
        }
        return match.matches();


    }
    public boolean vehicleNumberValidation(String num ) throws  InvalidEntryException{
        Matcher match;
        try {
            Pattern pat = Pattern.compile(vehicleNumberPattern);

            match = pat.matcher(num);
        } catch (Exception e) {
            throw new InvalidEntryException(e, "Invalid address");
        }
        return match.matches();

    }
    public boolean vehicleYearValidation(int yr) throws InvalidEntryException {
        try {
            String year = Integer.toString(yr);
            if (year.length() != 4) return false;
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            if (yr <= currentYear)
                return true;
            else return false;

        } catch (Exception e) {
            throw new InvalidEntryException(e);
        }
    }



}
