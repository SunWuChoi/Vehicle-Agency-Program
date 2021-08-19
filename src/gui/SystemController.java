//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019
package gui;

// @author Sun Wu Choi
import java.util.InputMismatchException;
import exceptions.*;

import java.util.Scanner;

import rate.*;
import account.*;
import java.util.ArrayList;
import reservation.Reservation;
import transaction.Transaction;
import transaction.Transactions;
import utilities.Utilities;
import vehicle.*;

public class SystemController {

    private static Rates rates;
    private static Vehicles vehicleList;
    private static Accounts accountList;
    private static Transactions transactionList;

    public static void initSystem(Rates r, Vehicles v, Accounts a, Transactions t) {
        SystemController.rates = r;
        SystemController.vehicleList = v;
        SystemController.accountList = a;
        SystemController.transactionList = t;
    }

    public static String[] getRateHeader() {
        return new String[]{
            "=====================================================================",
            "| Rate Type | Daily | Weekly | Monthly | Per Mile | Daily Insurance |",
            "====================================================================="
        };
    }

    public static String[] getCarRates() {
        String[] returnString = {
            String.format("| Car  Rates|%6.2f | %6.2f |  %6.2f |     %.2f |           %2.2f |",
            rates.getCarRates().getDaily_rate(),
            rates.getCarRates().getWeekly_rate(),
            rates.getCarRates().getMonthly_rate(),
            rates.getCarRates().getMilage_rate(),
            rates.getCarRates().getDaily_insurance_rate()
            )
        };
        return returnString;
    }

    public static String[] getSUVRates() {
        String[] returnString = {
            String.format("| SUV  Rates|%6.2f | %6.2f |  %6.2f |     %.2f |           %2.2f |",
            rates.getSUVRates().getDaily_rate(),
            rates.getSUVRates().getWeekly_rate(),
            rates.getSUVRates().getMonthly_rate(),
            rates.getSUVRates().getMilage_rate(),
            rates.getSUVRates().getDaily_insurance_rate()
            )
        };
        return returnString;
    }

    public static String[] getTruckRates() {
        String[] returnString = {
            String.format("|Truck Rates|%6.2f | %6.2f |  %6.2f |     %.2f |           %2.2f |",
            rates.getTruckRates().getDaily_rate(),
            rates.getTruckRates().getWeekly_rate(),
            rates.getTruckRates().getMonthly_rate(),
            rates.getTruckRates().getMilage_rate(),
            rates.getTruckRates().getDaily_insurance_rate()
            )
        };
        return returnString;
    }

    public static String[] viewAllVehicleHeader() {
        return new String[]{
            "==========================================================================================",
            "| Type  |     Make / Model     | MPG | Seat / load |   Storage  |     VIN     | reserved |",
            "=========================================================================================="
        };
    }
    
    public static String[] viewAllAvailVehicle(){
        int notReserved = 0;
        int size = 0;
        for(int i = 0; i < vehicleList.getSize(); i++){
            if(!vehicleList.getVehicle(i).isReserved()){
                notReserved++;
            }
        }
        
        
        
        String[] returnString = new String[notReserved + 1];

        for (int i = 0; i < vehicleList.getSize(); i++) {
            if(!vehicleList.getVehicle(i).isReserved()){
                returnString[size] = vehicleList.getVehicle(i).toString();
                size++;
            }
        }

        returnString[returnString.length - 1] = "==========================================================================================";

        return returnString;
    }

    public static String[] viewAllVehicle() {
        String[] returnString = new String[vehicleList.getSize() + 1];

        for (int i = 0; i < vehicleList.getSize(); i++) {
            returnString[i] = vehicleList.getVehicle(i).toString();
        }

        returnString[returnString.length - 1] = "==========================================================================================";

        return returnString;
    }
    
    public static String[] calcEstimatedCost(){
        Scanner sc = new Scanner(System.in);
        String rentalPeriod = null;
        String letter = null;
        int type = 0;
        double num = 0;
        String estimatedMileageInput = null;
        double estimatedMileage = 0;
        char insuranceInput;
        char primeInput;
        double dailyRate = 0;
        double weeklyRate = 0;
        double monthlyRate = 0;
        double insuranceRate = 0;
        double milageRate = 0;
        boolean insurance = false;
        boolean prime = false;
        boolean error = true;

        System.out.println("Enter Vehicle type");
        System.out.println("1. Car");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        type = Utilities.selection3();
        
        while (error) {
            try {
                System.out.printf("Enter estimated rental period (3D, 2W, 4M): ");
                
                rentalPeriod = sc.next();
                error = !Utilities.validateRentalPeriod(rentalPeriod);

            } catch (InputMismatchException e) {
                System.out.println("Enter numbers and letter ex(3d, 2W, 4M)");
            }
        }
        num = Double.parseDouble(Utilities.rentalPeriodParse(rentalPeriod)[1]);
        letter = Utilities.rentalPeriodParse(rentalPeriod)[0];
        
        error = true;
        while (error) {
            try {
                System.out.printf("Enter estimated miles to drive: ");
                
                estimatedMileageInput = sc.next();
                error = !Utilities.onlyInteger(estimatedMileageInput);
                
            } catch (InputMismatchException e) {
                System.out.println("Enter only positive non zero numbers");
            }
        }
        
        estimatedMileage = Double.parseDouble(estimatedMileageInput);

        insuranceInput = Utilities.getYesNoResponse("Set up a daily insurance?",sc);

        if (insuranceInput == 'y' || insuranceInput == 'Y') {
            insurance = true;   // default insurance is set to false so only checks for yes
        }
        
        primeInput = Utilities.getYesNoResponse("Prime account?",sc);

        if (primeInput == 'y' || primeInput == 'Y') {
            prime = true;   // default insurance is set to false so only checks for yes
        }
        
        if(prime){
            if(estimatedMileage >= 100){
                estimatedMileage -= 100;
            } else {
                estimatedMileage = 0;
            }
        }
        
        switch(type){
            case 1:
                dailyRate = rates.getCarRates().getDaily_rate();
                weeklyRate = rates.getCarRates().getWeekly_rate();
                monthlyRate = rates.getCarRates().getMonthly_rate();
                insuranceRate = rates.getCarRates().getDaily_insurance_rate();
                milageRate = rates.getCarRates().getMilage_rate();
                break;
            case 2:
                dailyRate = rates.getSUVRates().getDaily_rate();
                weeklyRate = rates.getSUVRates().getWeekly_rate();
                monthlyRate = rates.getSUVRates().getMonthly_rate();
                insuranceRate = rates.getSUVRates().getDaily_insurance_rate();
                milageRate = rates.getSUVRates().getMilage_rate();
                break;
            case 3:
                dailyRate = rates.getTruckRates().getDaily_rate();
                weeklyRate = rates.getTruckRates().getWeekly_rate();
                monthlyRate = rates.getTruckRates().getMonthly_rate();
                insuranceRate = rates.getTruckRates().getDaily_insurance_rate();
                milageRate = rates.getTruckRates().getMilage_rate();
                break;
        }
        
        if(!insurance){
            insuranceRate = 0;
        }
        
        switch(letter){
            case "D":
                return new String[]{String.format("%.2f dollars expected",(num*(dailyRate + insuranceRate) + estimatedMileage * milageRate))};
            case "W":
                return new String[]{String.format("%.2f dollars expected",(num*(weeklyRate) + num * 7 * insuranceRate + estimatedMileage * milageRate))};
            case "M":
                return new String[]{String.format("%.2f dollars expected",(num*(monthlyRate) + num * 30 * insuranceRate + estimatedMileage * milageRate))};
        }
        return new String[]{"Calculation error"};
    }

    public static String[] reserve() {
        Scanner sc = new Scanner(System.in);
        String acctnum = null;
        String vin = null;
        String rentalPeriod = null;
        char insuranceInput;
        boolean insurance = false;
        String vehicleType = null;
        int type = 0;
        Reservation resv;

        boolean error = true;
        Vehicle v;

        error = true;
        while (error) {
            try {
                System.out.printf("Enter VIN: ");
                vin = sc.next();
                
                error = !Utilities.validVINReserve(vin, vehicleList);
                
            } catch (InputMismatchException e) {
                System.out.println("Enter only integers and upper case alphabets");
            } catch (InputSizeMismatch e) {
                System.out.println("Enter 11 Letters (Integers and upper case alphabets)");
            } catch (DoNotExistException e){
                System.out.println("No vehicle with vin " + vin + " found");
                char yn = Utilities.getYesNoResponse("Would you like to exit??",sc);
                if(yn == 'y' || yn == 'Y'){
                    return new String[]{"Reservation process canceled"};
                }
            } catch (AlreadyExistException e){
                System.out.println("Vehicle with vin " + vin + " is already reserved");
                char yn = Utilities.getYesNoResponse("Would you like to continue??",sc);
                if(yn == 'n' || yn == 'N'){
                    return new String[]{"Reservation process canceled"};
                }
            }
        }
        
        error = true;
        while (error) {
            try {
                System.out.printf("Enter 5 integer account number: ");
                acctnum = sc.next();
                
                error = !Utilities.validAccountNumberinDB(acctnum, accountList);
                
            } catch (InputMismatchException e) {
                System.out.println("Enter only integers");
            } catch (InputSizeMismatch e) {
                System.out.println("Enter 5 integers");
            } catch (DoNotExistException e){
                System.out.println("No account found with account number " + acctnum);
            }
        }
        
        error = true;
        while (error) {
            try {
                System.out.printf("Enter rental period in days: ");
                
                rentalPeriod = sc.next();
                error = !Utilities.onlyInteger(rentalPeriod);
                
            } catch (InputMismatchException e) {
                System.out.println("Enter only positive non zero numbers");
            }
        }
        
        insuranceInput = Utilities.getYesNoResponse("Set up a daily insurance?",sc);

        if (insuranceInput == 'y' || insuranceInput == 'Y') {
            insurance = true;   // default insurance is set to false so only checks for yes
        }
        
        v = vehicleList.getVehicle(vin);
        
        vehicleType = v.getClass().getSimpleName();
        
        type = Utilities.vehicleType(vehicleType);
        
        switch(type){
            case 1: 
                v.setCost(rates.getCarRates().cloneAsCostType());
                break;
            case 2:
                v.setCost(rates.getSUVRates().cloneAsCostType());
                break;
            case 3:
                v.setCost(rates.getTruckRates().cloneAsCostType());
                break;
        }
        
        
        resv = new Reservation(acctnum, type, v.getDescription(), rentalPeriod, insurance);
        

        vehicleList.getVehicle(vin).setReservation(resv);
        accountList.getAccount(acctnum).addReservedVehicle(v);
        return new String[]{"New Reservation on " + 
                vehicleType + " " +
                v.getDescription() +
                " - " +
                vin +
                " for " + 
                Utilities.rentalPeriodtoReadable(Utilities.rentalPeriodDivide(rentalPeriod)) +
                " by account " +
                acctnum
        };
    }
    
    public static String[] cancelReservation(){
        Scanner sc = new Scanner(System.in);
        String acctnum = null;
        String vin = null;
        boolean error = true;
        
        while (error) {
            try {
                System.out.printf("Enter VIN: ");
                vin = sc.next();
                
                error = !Utilities.validVINCancel(vin, vehicleList);
                
            } catch (InputMismatchException e) {
                System.out.println("Enter only integers and upper case alphabets");
            } catch (InputSizeMismatch e) {
                System.out.println("Enter 11 Letters (Integers and upper case alphabets)");
            } catch (DoNotExistException e){
                System.out.println("No vehicle with vin " + vin + " found");
                char yn = Utilities.getYesNoResponse("Would you like to continue??",sc);
                if(yn == 'n' || yn == 'N'){
                    return new String[]{"Reservation cancel process canceled"};
                }
            } catch (NoReservationException e){
                System.out.println("Vehicle with vin " + vin + " is not reserved");
                char yn = Utilities.getYesNoResponse("Would you like to continue??",sc);
                if(yn == 'n' || yn == 'N'){
                    return new String[]{"Reservation cancel process canceled"};
                }
            }
        }
        
        error = true;
        while (error) {
            try {
                System.out.printf("Enter 5 integer account number: ");
                acctnum = sc.next();
                
                error = !Utilities.validAccountNumberinDB(acctnum, accountList);
                
            } catch (InputMismatchException e) {
                System.out.println("Enter only integers");
            } catch (InputSizeMismatch e) {
                System.out.println("Enter 5 integers");
            } catch (DoNotExistException e){
                System.out.println("No account found with account number " + acctnum);
            }
        }
        
        accountList.getAccount(acctnum).removeReservedVehicle(vin);
        Vehicle v = vehicleList.getVehicle(vin);
        
        if(v.getReservation().getAcctNum().equals(acctnum)){
            v.cancelReservation();
        
            return new String[]{
                "Reservation on " + 
                v.getClass().getSimpleName() + " " +
                v.getDescription() + " by account " + 
                acctnum + " canceled" 
            };
        } else {
            return new String[]{
                "There is no such reservation on account " +
                 acctnum + " with VIN " + v.getVin()
            };
        }
    }
    
    public static String[] viewAccount(){
        Scanner sc = new Scanner(System.in);
        String acctnum = null;
        Vehicle v;
        int reserved = 0;
        
        boolean error = true;
        while (error) {
            try {
                System.out.printf("Enter 5 integer account number to view: ");
                acctnum = sc.next();
                
                error = !Utilities.validAccountNumberinDB(acctnum, accountList);
                
            } catch (InputMismatchException e) {
                System.out.println("Enter only integers");
            } catch (InputSizeMismatch e) {
                System.out.println("Enter 5 integers");
            } catch (DoNotExistException e){
                System.out.println("No account found with account number " + acctnum);
                char yn = Utilities.getYesNoResponse("Would you like to continue??",sc);
                if(yn == 'n' || yn == 'N'){
                    return new String[]{"Account view process canceled"};
                }
            }
        }
        
        reserved = accountList.getAccount(acctnum).getReservedVehicles().getSize();
        
        String[] returnString = new String[7+reserved+1];
        
        returnString[0] = "===============================================================";
        returnString[1] = "| AcctNum |            Company Name            | resv | Prime |";
        returnString[2] = "===============================================================";
        returnString[3] = accountList.getAccount(acctnum).toString();
        returnString[4] = "================================================================================";
        returnString[5] = "| Type  |     Make / Model     |     VIN     | Account |   Length  | Insurance |";
        returnString[6] = "================================================================================";
        
        
        for (int i = 0; i < reserved; i++) {

            v = accountList.getAccount(acctnum).getReservedVehicles().getVehicle(i);
            returnString[7 + i] = String.format("| %-6s| %-21s| %11s |  %5s  | %4s days |   %5s   |",
                    v.getClass().getSimpleName(),
                    v.getDescription(),
                    v.getVin(),
                    v.getReservation().getAcctNum(),
                    v.getReservation().getRentalPeriod(),
                    v.getReservation().isInsuranceSelected()
            );

        }

        returnString[returnString.length - 1] = "================================================================================";
        
        return returnString;
    }
    
    public static String[] viewAllReservationHeader(){
        return new String[]{
            "================================================================================",
            "| Type  |     Make / Model     |     VIN     | Account |   Length  | Insurance |",
            "================================================================================"
        };
    }
    
    public static String[] viewAllReservation() {
        int size = vehicleList.getSize();

        int reserved = 0;
        for (int i = 0; i < size; i++) {
            if (vehicleList.getVehicle(i).isReserved()) {
                reserved++;
            }
        }

        String[] returnString = new String[reserved+1];

        reserved = 0;

        for (int i = 0; i < size; i++) {
            if (vehicleList.getVehicle(i).isReserved()) {
                Vehicle v = vehicleList.getVehicle(i);
                returnString[reserved] = String.format("| %-6s| %-21s| %11s |  %5s  | %4s days |   %5s   |",
                        v.getClass().getSimpleName(),
                        v.getDescription(),
                        v.getVin(),
                        v.getReservation().getAcctNum(),
                        v.getReservation().getRentalPeriod(),
                        v.getReservation().isInsuranceSelected()
                );
                reserved++;
            }
        }
        returnString[returnString.length - 1] = "================================================================================";
        return returnString;
    }

    public static String[] updateCarRates(int input) {
        Scanner sc = new Scanner(System.in);
        double current;

        switch (input) {
            case 1:
                current = rates.getCarRates().getDaily_rate();
                System.out.println("Current Car daily rate is " + current);
                System.out.printf("Enter new daily rate: ");
                rates.getCarRates().setDaily_rate(sc.nextDouble());
                return new String[]{String.format("Car daily rate changed from %6.2f to %6.2f", current, rates.getCarRates().getDaily_rate())};
            case 2:
                current = rates.getCarRates().getWeekly_rate();
                System.out.println("Current Car weekly rate is " + current);
                System.out.printf("Enter new weekly rate: ");
                rates.getCarRates().setWeekly_rate(sc.nextDouble());
                return new String[]{String.format("Car weekly rate changed from %6.2f to %6.2f", current, rates.getCarRates().getWeekly_rate())};
            case 3:
                current = rates.getCarRates().getMonthly_rate();
                System.out.println("Current Car monthly rate is " + current);
                System.out.printf("Enter new monthly rate: ");
                rates.getCarRates().setMonthly_rate(sc.nextDouble());
                return new String[]{String.format("Car monthy rate changed from %6.2f to %6.2f", current, rates.getCarRates().getMonthly_rate())};
            case 4:
                current = rates.getCarRates().getMilage_rate();
                System.out.println("Current Car per mile rate is " + current);
                System.out.printf("Enter new milage rate: ");
                rates.getCarRates().setMilage_rate(sc.nextDouble());
                return new String[]{String.format("Car per mile rate changed from %6.2f to %6.2f", current, rates.getCarRates().getMilage_rate())};
            case 5:
                current = rates.getCarRates().getDaily_insurance_rate();
                System.out.println("Current Car daily insurance rate is " + current);
                System.out.printf("Enter new milage rate: ");
                rates.getCarRates().setDaily_insurance_rate(sc.nextDouble());
                return new String[]{String.format("Car daily insurance rate changed from %6.2f to %6.2f", current, rates.getCarRates().getDaily_insurance_rate())};
            default:
                return new String[]{"Failed to update rate"};
        }
    }

    public static String[] updateSUVRates(int input) {
        Scanner sc = new Scanner(System.in);
        double current;

        switch (input) {
            case 1:
                current = rates.getSUVRates().getDaily_rate();
                System.out.println("Current SUV daily rate is " + current);
                System.out.printf("Enter new daily rate: ");
                rates.getSUVRates().setDaily_rate(sc.nextDouble());
                return new String[]{String.format("SUV daily rate changed from %6.2f to %6.2f", current, rates.getSUVRates().getDaily_rate())};
            case 2:
                current = rates.getSUVRates().getWeekly_rate();
                System.out.println("Current SUV weekly rate is " + current);
                System.out.printf("Enter new weekly rate: ");
                rates.getSUVRates().setWeekly_rate(sc.nextDouble());
                return new String[]{String.format("SUV weekly rate changed from %6.2f to %6.2f", current, rates.getSUVRates().getWeekly_rate())};
            case 3:
                current = rates.getSUVRates().getMonthly_rate();
                System.out.println("Current SUV monthly rate is " + current);
                System.out.printf("Enter new monthly rate: ");
                rates.getSUVRates().setMonthly_rate(sc.nextDouble());
                return new String[]{String.format("SUV monthly rate changed from %6.2f to %6.2f", current, rates.getSUVRates().getMonthly_rate())};
            case 4:
                current = rates.getSUVRates().getMilage_rate();
                System.out.println("Current SUV per mile rate is " + current);
                System.out.printf("Enter new milage rate: ");
                rates.getSUVRates().setMilage_rate(sc.nextDouble());
                return new String[]{String.format("SUV per mile rate changed from %6.2f to %6.2f", current, rates.getSUVRates().getMilage_rate())};
            case 5:
                current = rates.getSUVRates().getDaily_insurance_rate();
                System.out.println("Current SUV daily insurance rate is " + current);
                System.out.printf("Enter new daily insurance rate: ");
                rates.getSUVRates().setDaily_insurance_rate(sc.nextDouble());
                return new String[]{String.format("SUV per mile rate changed from %6.2f to %6.2f", current, rates.getSUVRates().getDaily_insurance_rate())};
            default:
                return new String[]{"Failed to update rate"};
        }
    }

    public static String[] updateTruckRates(int input) {
        Scanner sc = new Scanner(System.in);
        double current;

        switch (input) {
            case 1:
                current = rates.getTruckRates().getDaily_rate();
                System.out.println("Current Truck daily rate is " + current);
                System.out.printf("Enter new daily rate: ");
                rates.getTruckRates().setDaily_rate(sc.nextDouble());
                return new String[]{String.format("Truck daily rate changed from %6.2f to %6.2f", current, rates.getTruckRates().getDaily_rate())};
            case 2:
                current = rates.getTruckRates().getWeekly_rate();
                System.out.println("Current Truck weekly rate is " + rates.getTruckRates().getWeekly_rate());
                System.out.printf("Enter new weekly rate: ");
                rates.getTruckRates().setWeekly_rate(sc.nextDouble());
                return new String[]{String.format("Truck weekly rate changed from %6.2f to %6.2f", current, rates.getTruckRates().getWeekly_rate())};
            case 3:
                current = rates.getTruckRates().getMonthly_rate();
                System.out.println("Current Truck monthly rate is " + rates.getTruckRates().getMonthly_rate());
                System.out.printf("Enter new monthly rate: ");
                rates.getTruckRates().setMonthly_rate(sc.nextDouble());
                return new String[]{String.format("Truck monthly rate changed from %6.2f to %6.2f", current, rates.getTruckRates().getMonthly_rate())};
            case 4:
                current = rates.getTruckRates().getMilage_rate();
                System.out.println("Current Truck per milage rate is " + rates.getTruckRates().getMilage_rate());
                System.out.printf("Enter new milage rate: ");
                rates.getTruckRates().setMilage_rate(sc.nextDouble());
                return new String[]{String.format("Truck per mile rate changed from %6.2f to %6.2f", current, rates.getTruckRates().getMilage_rate())};
            case 5:
                current = rates.getTruckRates().getDaily_insurance_rate();
                System.out.println("Current Truck daily insurance rate is " + rates.getTruckRates().getDaily_insurance_rate());
                System.out.printf("Enter new daily insurance rate: ");
                rates.getTruckRates().setDaily_insurance_rate(sc.nextDouble());
                return new String[]{String.format("Truck daily insurance rate changed from %6.2f to %6.2f", current, rates.getTruckRates().getDaily_insurance_rate())};
            default:
                return new String[]{"Failed to update rate"};
        }
    }

    public static String[] addUpdateAccount() {
        Scanner sc = new Scanner(System.in);
        String acctnum = null;
        String company = null;
        String leftover = null;
        char primeInput;
        boolean prime = false;
        boolean error = true;
        boolean newAcc = true;

        while (error) {
            try {
                System.out.printf("Enter 5 integer account number to add or update: ");
                acctnum = sc.next();
                
                error = !Utilities.validNewAccountNumber(acctnum, accountList);
                
            } catch (InputMismatchException e) {
                System.out.println("Enter only integers");
            } catch (InputSizeMismatch e) {
                System.out.println("Enter 5 integers");
            } catch (AlreadyExistException e) {
                System.out.println("Updating account number " + acctnum);
                newAcc = false;
                error = false;
            }
        }

        System.out.printf("Enter company name: ");
        leftover = sc.nextLine();   //consumes the enter from the last sc.next() so it doesn't skip 
        company = sc.nextLine();


        primeInput = Utilities.getYesNoResponse("Is this account a prime user?", sc);


        if (primeInput == 'y' || primeInput == 'Y') {
            prime = true;
        }

        if (newAcc) {
            Account newAccount = new Account(acctnum, company, prime);
            accountList.add(newAccount);
            return new String[]{"New account added"};
        } else {
            accountList.getAccount(acctnum).setCompany(company);
            accountList.getAccount(acctnum).setPrime(prime);
            return new String[]{"Account " + acctnum + " has been updated"};
        }
    }

    public static String[] viewAllAccountHeader() {
        return new String[]{
            "===============================================================",
            "| AcctNum |            Company Name            | resv | Prime |",
            "==============================================================="
        };
    }

    public static String[] viewAllAccount() {
        String[] returnString = new String[accountList.getSize() + 1];

        for (int i = 0; i < accountList.getSize(); i++) {
            returnString[i] = accountList.getAccount(i).toString();
        }

        returnString[returnString.length - 1] = "===============================================================";

        return returnString;
    }
    
    public static String[] processReservation(){
        Scanner sc = new Scanner(System.in);
        String vin = null;
        String acctnum = null;
        String actualMileDrivenInput = null;
        double actualMileDriven;
        VehicleRates rate;
        double fee = 0;
        int[] daysDriven = new int[3];
        int totalDaysDriven = 0;
        boolean error = true;
        String[] returnString = new String[4];
        int days;
        int weeks;
        int months;
        
        Vehicle v;
        
        while (error) {
            try {
                System.out.printf("Enter VIN: ");
                vin = sc.next();
                
                error = !Utilities.validVINCancel(vin, vehicleList);
                
            } catch (InputMismatchException e) {
                System.out.println("Enter only integers and upper case alphabets");
            } catch (InputSizeMismatch e) {
                System.out.println("Enter 11 Letters (Integers and upper case alphabets)");
            } catch (DoNotExistException e){
                System.out.println("No vehicle with vin " + vin + " found");
                char yn = Utilities.getYesNoResponse("Would you like to exit??",sc);
                if(yn == 'y' || yn == 'Y'){
                    return new String[]{"Reservation return process canceled"};
                }
            } catch (NoReservationException e){
                System.out.println("Vehicle with vin " + vin + " is not reserved");
                char yn = Utilities.getYesNoResponse("Would you like to continue??",sc);
                if(yn == 'n' || yn == 'N'){
                    return new String[]{"Reservation return process canceled"};
                }
            }
        }
        
        v = vehicleList.getVehicle(vin);
        acctnum = v.getReservation().getAcctNum();
        
        
        error = true;
        while (error) {
            try {
                System.out.printf("Enter actual miles driven: ");
                
                actualMileDrivenInput = sc.next();
                error = !Utilities.onlyInteger(actualMileDrivenInput);
                
            } catch (InputMismatchException e) {
                System.out.println("Enter only positive non zero numbers");
            }
        }
        actualMileDriven = Double.parseDouble(actualMileDrivenInput);
        
        daysDriven = Utilities.rentalPeriodDivide(vehicleList.getVehicle(vin).getReservation().getRentalPeriod());
        
        if(accountList.getAccount(v.getReservation().getAcctNum()).isPrime()){
            if(actualMileDriven >= 100){
                actualMileDriven -= 100;
            } else {
                actualMileDriven = 0;
            }
        }
        
        days = daysDriven[0];
        weeks = daysDriven[1];
        months = daysDriven[2];
        
        totalDaysDriven = months * 30 + weeks * 7 + days;
        
        fee = 
                days * v.getCostWhenReserved().getMonthly_rate() + 
                weeks * v.getCostWhenReserved().getWeekly_rate() +
                months * v.getCostWhenReserved().getDaily_rate() +
                totalDaysDriven * v.getCostWhenReserved().getDaily_insurance_rate() +
                actualMileDriven * v.getCostWhenReserved().getMilage_rate();
        
        returnString = new String[]{
                "Calculating total fee for " + v.getClass().getSimpleName() + " " + v.getDescription(),
                "Driven " + totalDaysDriven + " days for " + actualMileDriven + " mile",
                "Insurance - " + v.getReservation().isInsuranceSelected() + 
                " / Prime user - " + accountList.getAccount(acctnum).isPrime(),
                "Total fee: $ " + fee
        };
        
        transactionList.add(new Transaction(
                acctnum, 
                accountList.getAccount(acctnum).getCompany(), 
                v.getClass().getSimpleName(), 
                Integer.toString(totalDaysDriven), 
                Double.toString(fee)
        ));
        
        accountList.getAccount(acctnum).removeReservedVehicle(vin);
        v.cancelReservation();
        
        return returnString;
    }

    public static String[] viewAllTransaction() {
        int size = transactionList.getSize();
        int acctsize = accountList.getSize();
        String acctnum = null;

        ArrayList<String> returnString = new ArrayList<String>();

        for (int i = 0; i < acctsize; i++) {
            acctnum = accountList.getAccount(i).getAcctNum();
            if (transactionList.getTransaction(acctnum) != null) {
                returnString.add(accountList.getAccount(i).getCompany() + " (acct # " + acctnum + ")");
                for (int j = 0; j < size; j++) {
                    if (transactionList.getTransaction(j).getAcctNum().equals(acctnum)) {
                        returnString.add(transactionList.getTransaction(j).toString());
                    }
                }
            }
        }
        String[] finalReturnString = new String[returnString.size()];
        for (int i = 0; i < returnString.size(); i++) {
            finalReturnString[i] = returnString.get(i);
        }

        return finalReturnString;

    }
}
