package utilities;

import account.Accounts;
import exceptions.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import vehicle.Vehicle;
import vehicle.Vehicles;

//@author Sun Wu Choi
//@code help C. Dierbach
public class Utilities {

    // supporting validation methods
    public static boolean validateCreditCard(String credit_card)
            throws InvalidNumberCharsException, NonDigitFoundException {
        // ---------------------------------------------------------------------
        if (credit_card.length() != 16) {
            throw new InvalidNumberCharsException();
        } else if (containsNonDigit(credit_card)) {
            throw new NonDigitFoundException();
        }

        return true;
    }

    public static boolean containsNonDigit(String str) {
        // returns true if str contains only digit characters
        // otherwise, returns false
        // ---------------------------------------------------------------------
        boolean non_digit_found = false;
        int index = 0;

        while (index < str.length() && !non_digit_found) {
            if (!Character.isDigit(str.charAt(index))) {
                non_digit_found = true;
            } else {
                index = index + 1;
            }
        }

        // no nondigits found if reach end of string
        return index < str.length();
    }

    public static char getYesNoResponse(String prompt_mesg, Scanner input) {
        // reprompts user until valid response entered
        // returns either 'Y' or 'N'

        String yesno_response;
        char YN_char = ' ';  // init
        boolean valid_entry = false;

        while (!valid_entry) {
            System.out.print(prompt_mesg + " (y/n): ");
            yesno_response = input.next();
            YN_char = Character.toUpperCase(yesno_response.charAt(0));

            if (yesno_response.length() != 1
                    || YN_char != 'Y' && YN_char != 'N') {
                System.out.println("Invalid Entry - Please reenter");
            } else {
                valid_entry = true;
            }
        }

        return YN_char;
    }

    public static boolean validateRentalPeriod(String rental_period)
            throws InvalidRentalPeriodFormatException, InputMismatchException {
        // returns true if rental_period formatted as <periodtype><length>
        // where <periodtype> = 'D', 'W', or 'M', <length> an integer value
        // otherwise, return false. Throws InvalidRentalPeriodFormatException
        // if rental_period found improperly formatted

        String digits_part;

        // verifies that rental period of proper form
        
        char last_char = rental_period.charAt(rental_period.length()-1);

        if (Character.toUpperCase(last_char) != 'D'
                && Character.toUpperCase(last_char) != 'W'
                && Character.toUpperCase(last_char) != 'M') {
            throw new InputMismatchException();
        } else {
            digits_part = rental_period.substring(0, rental_period.length()-1);
            if (Utilities.containsNonDigit(digits_part)) {
                throw new InvalidRentalPeriodFormatException();
            }
        }

        // rental period verified OK
        return true;
    }
    
    public static String[] rentalPeriodParse(String rental_period)
            throws InvalidRentalPeriodFormatException, InputMismatchException {

        String digits_part;

        // verifies that rental period of proper form
        char last_char = rental_period.charAt(rental_period.length()-1);

        if (Character.toUpperCase(last_char) != 'D'
                && Character.toUpperCase(last_char) != 'W'
                && Character.toUpperCase(last_char) != 'M') {
            throw new InputMismatchException();
        } else {
            digits_part = rental_period.substring(0, rental_period.length()-1);
            if (Utilities.containsNonDigit(digits_part)) {
                throw new InvalidRentalPeriodFormatException();
            }
        }

        // rental period verified OK
        return new String[]{Character.toString(last_char),digits_part};
    }

    public static boolean onlyInteger(String input)
            throws InputMismatchException {
        int size = 0;
        int zero = 0;
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new InputMismatchException();
            }
            size++;
        }
        for (char c : input.toCharArray()) {
            if (c == '0') {
                zero++;
            }
        }
        if (zero == size) {
            throw new InputMismatchException();
        }
        return true;
    }

    public static boolean validAccountNumberinDB(String acctnum, Accounts accountList)
            throws InputMismatchException, DoNotExistException, InputSizeMismatch {
        int size = 0;
        for (char c : acctnum.toCharArray()) {
            if (Character.isDigit(c)) {
                size++;
            } else {
                throw new InputMismatchException();
            }
        }

        if (size == 5) {
            if (accountList.getAccount(acctnum) == null) {
                throw new DoNotExistException();
            } else {
                return true;
            }
        } else {
            throw new InputSizeMismatch();
        }
    }

    public static boolean validNewAccountNumber(String acctnum, Accounts accountList)
            throws InputMismatchException, AlreadyExistException, InputSizeMismatch {
        int size = 0;
        for (char c : acctnum.toCharArray()) {
            if (Character.isDigit(c)) {
                size++;
            } else {
                throw new InputMismatchException();
            }
        }

        if (size == 5) {
            if (accountList.getAccount(acctnum) != null) {
                throw new AlreadyExistException();
            } else {
                return true;
            }
        } else {
            throw new InputSizeMismatch();
        }
    }

    public static boolean validVINReserve(String vin, Vehicles vehicleList)
            throws InputMismatchException, InputSizeMismatch, DoNotExistException, AlreadyExistException {
        int size = 0;
        for (char c : vin.toCharArray()) {
            if (Character.isUpperCase(c) || Character.isDigit(c)) {
                size++;
            } else {
                throw new InputMismatchException();
            }
        }

        if (size == 11) {
            if (vehicleList.getVehicle(vin) == null) {
                throw new DoNotExistException();
            }
            if (vehicleList.getVehicle(vin).isReserved()) {
                throw new AlreadyExistException();
            }
        } else {
            throw new InputSizeMismatch();
        }
        return true;
    }
    
    public static boolean validVINCancel(String vin, Vehicles vehicleList)
            throws InputMismatchException, InputSizeMismatch, DoNotExistException, NoReservationException {
        int size = 0;
        for (char c : vin.toCharArray()) {
            if (Character.isUpperCase(c) || Character.isDigit(c)) {
                size++;
            } else {
                throw new InputMismatchException();
            }
        }

        if (size == 11) {
            if (vehicleList.getVehicle(vin) == null) {
                throw new DoNotExistException();
            }
            if (!vehicleList.getVehicle(vin).isReserved()) {
                throw new NoReservationException();
            }
        } else {
            throw new InputSizeMismatch();
        }
        return true;
    }

    public static int[] rentalPeriodDivide(String rentalPeriod) {
        int days = Integer.parseInt(rentalPeriod);
        int m = 0;
        int w = 0;
        int d = days;

        m = d / 30;
        d = d % 30;
        w = d / 7;
        d = d % 7;

        int[] returnArray = new int[]{d, w, m};

        return returnArray;
    }

    public static String rentalPeriodtoReadable(int[] rental) {
        String returnString = null;

        if (rental[0] != 0) {
            returnString = Integer.toString(rental[0]) + " month ";
        }
        if (rental[1] != 0) {
            returnString += Integer.toString(rental[1]) + " week ";
        }
        if (rental[2] != 0) {
            returnString += Integer.toString(rental[2]) + " day ";
        }

        return returnString;
    }

    public static int selection3() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            switch (sc.next()) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                default:
                    System.out.println("Enter between 1 ~ 3");
            }
        }
    }
    
    public static boolean accountVehicleConnected(String acctnum, Vehicle v) 
            throws NoReservationException{
        if(v.isReserved()){
            if(v.getReservation().getAcctNum().equals(acctnum)){
                return true;
            } else {
                return false;
            }
        } else {
            throw new NoReservationException();
        }
    }
    
    
    public static int vehicleType(String str) {
        switch (str) {
            case "Car":
                return 1;
            case "SUV":
                return 2;
            case "Truck":
                return 3;
            default:
                return 0;
        }
    }

}
