//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019
package gui;

// @author Sun Wu Choi
import account.*;
import java.util.Scanner;
import rate.*;
import reservation.Reservation;
import transaction.Transactions;
import utilities.Utilities;
import vehicle.*;

public class Start {

    // Data section
    private static double[] CarRatesData = {24.95, 149.95, 514.95, 0.15, 14.95};
    private static double[] SUVRatesData = {29.95, 189.95, 679.95, 0.15, 14.95};
    private static double[] TruckRatesData = {34.95, 224.95, 797.95, 0.26, 22.95};

    private static Vehicle[] vehicleData = {
        new Car("Chevrolet Camaro", 30, 2, "KH4GM4565GD"),
        new Car("Ford Fusion", 34, 4, "AB4FG5689GM"),
        new Car("Ford Fusion Hybrid", 32, 4, "KU4EG3245RW"),
        new Car("Chevrolet Impala", 30, 4, "RK3BM4256YH"),
        new SUV("Honda Odyssey", 28, 7, 6, "VM9RE2635TD"),
        new SUV("Dodge Caravan", 25, 5, 4, "QK3FL4273ME"),
        new SUV("Ford Expedition", 20, 5, 3, "JK2RT9364HY"),
        new Truck("Ten-Foot", 12, 2810, "EJ5KU2435BC"),
        new Truck("Seventeen-Foot", 10, 5930, "KG4DM5472RK"),
        new Truck("Twenty-Four-Foot", 8, 6500, "EB2WR3082QB"),
        new Truck("Twenty-Four-Foot", 8, 6500, "TY3GH4290EK")
    };

    private static Account[] accountData = {
        new Account("00001", "Google Artificial Intelligence", true),
        new Account("00002", "Samsung Electronics", true),
        new Account("00003", "Apple", false),
        new Account("00004", "Amazon", true),
        new Account("00005", "Ebay", false),
        new Account("00006", "Huawei", true),
        new Account("00007", "Tesla", true)
    };

    private static CarRates CarRate;
    private static SUVRates SUVRate;
    private static TruckRates TruckRate;
    private static Rates rates;
    private static Vehicles vehicleList;
    private static Accounts accountList;
    private static Transactions transactionList;

    public static void main(String[] args) {

        init();

        SystemController.initSystem(rates, vehicleList, accountList, transactionList);

        menuEntry();
        
    }

    public static void init() {
        // initial rate data lists
        CarRate = new CarRates(CarRatesData[0], CarRatesData[1], CarRatesData[2], CarRatesData[3], CarRatesData[4]);
        SUVRate = new SUVRates(SUVRatesData[0], SUVRatesData[1], SUVRatesData[2], SUVRatesData[3], SUVRatesData[4]);
        TruckRate = new TruckRates(TruckRatesData[0], TruckRatesData[1], TruckRatesData[2], TruckRatesData[3], TruckRatesData[4]);

        // initial rate data
        rates = new Rates(CarRate, SUVRate, TruckRate);
        
        // initial vehicle data
        vehicleList = new Vehicles(vehicleData);
        
        // initial account data
        accountList = new Accounts(accountData);
        
        // initialiize transactions
        transactionList = new Transactions();
    }

    public static void menuEntry() {

        Scanner sc = new Scanner(System.in);
        String selection = null;

        while (true) {
            System.out.println("===================== Entry   Menu ======================");
            System.out.println("| 1 | Employee Menu   | 2 | Manager  Menu   | 3 | Exit  |");
            System.out.println("=========================================================");
            System.out.print("Selection: ");
            switch (selection3()) {
                case 1:
                    EmployeeUI employeeUI = new EmployeeUI();
                    break;
                case 2:
                    ManagerUI managerUI = new ManagerUI();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Please enter between 1 ~ 3");
            }
        }
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
                    System.out.printf("Selection: ");
            }
        }
    }
}
