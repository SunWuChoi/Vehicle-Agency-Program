//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package gui;

// @author Sun Wu Choi

import java.util.Scanner;

 
public class ManagerUI implements UserInterface{
    
    private boolean flag = true;
   
    public ManagerUI(){
        start();
    }
    
    @Override
    public void start() {
        while(flag){
            display();
            controller(selection7());
        }
    }

    @Override
    public void display() {
        System.out.println("===================== Manager  Menu =====================");
        System.out.println("| 1 | View/Update Rates     | 2 | View All Vehicles     |");
        System.out.println("| 3 | Add/Update Account    | 4 | View All Reservations |");
        System.out.println("| 5 | View All Accounts     | 6 | View Transactions     |");
        System.out.println("| 7 | Quit                                              |");
        System.out.println("=========================================================");
    }

    @Override
    public void controller(int selection) {
        switch(selection){
            case 1: 
                viewUpdateRates();
                break;
            case 2: 
                viewAllVehicle();
                break;
            case 3: 
                addUpdateAccount();
                break;
            case 4: 
                viewAllReservation();
                break;
            case 5: 
                viewAllAccount();
                break;
            case 6: 
                viewAllTransaction();
                break;
            case 7: 
                exit();
                break;
        }
    }
    
    public void printer (String[] input){
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }
    
    public void viewUpdateRates(){
        printer(SystemController.getRateHeader());
        printer(SystemController.getCarRates());
        printer(SystemController.getSUVRates());
        printer(SystemController.getTruckRates());
        System.out.println("=====================================================================");
        System.out.println("| 1 | Change Car Rate        | 2 | Change SUV Rate                  |");
        System.out.println("| 3 | Change Truck Rate      | 4 | Exit                             |");
        System.out.println("=====================================================================");
    
        switch (selection4()) {
            case 1:
                System.out.println("=====================================================================");
                System.out.println("1. Car - Update daily rate");
                System.out.println("2. Car - Update weekly rate");
                System.out.println("3. Car - Update monthly rate");
                System.out.println("4. Car - Update milage charge rate");
                System.out.println("5. Car - Update daily insurance rate");
                System.out.println("=====================================================================");
                printer(SystemController.updateCarRates(selection5()));
                break;
            case 2:
                System.out.println("=====================================================================");
                System.out.println("1. SUV - Update daily rate");
                System.out.println("2. SUV - Update weekly rate");
                System.out.println("3. SUV - Update monthly rate");
                System.out.println("4. SUV - Update milage charge rate");
                System.out.println("5. SUV - Update daily insurance rate");
                System.out.println("=====================================================================");
                printer(SystemController.updateSUVRates(selection5()));
                break;
            case 3:
                System.out.println("=====================================================================");
                System.out.println("1. Truck - Update daily rate");
                System.out.println("2. Truck - Update weekly rate");
                System.out.println("3. Truck - Update monthly rate");
                System.out.println("4. Truck - Update milage charge rate");
                System.out.println("5. Truck - Update daily insurance rate");
                System.out.println("=====================================================================");
                printer(SystemController.updateTruckRates(selection5()));
                break;

        }
    }

    public void viewAllVehicle(){
        printer(SystemController.viewAllVehicleHeader());
        printer(SystemController.viewAllVehicle());
    }
    
    public void addUpdateAccount(){
        printer(SystemController.addUpdateAccount());
    }
    
    public void viewAllAccount(){
        printer(SystemController.viewAllAccountHeader());
        printer(SystemController.viewAllAccount());
    }
    
    public void viewAllReservation(){
        printer(SystemController.viewAllReservationHeader());
        printer(SystemController.viewAllReservation());
    }
    
    public void viewAllTransaction(){
        printer(SystemController.viewAllTransaction());
    }
    
    public int selection4() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            switch (sc.next()) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
                default:
                    System.out.println("Enter between 1 ~ 3");
            }
        }
    }

    public int selection5() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            switch (sc.next()) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
                case "5":
                    return 5;
                default:
                    System.out.println("Enter between 1 ~ 5");
            }
        }
    }
    
    public int selection7() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            switch (sc.next()) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
                case "5":
                    return 5;
                case "6":
                    return 6;
                case "7":
                    return 7;
                default:
                    System.out.println("Enter between 1 ~ 7");
            }
        }
    }

    @Override
    public void exit() {
        this.flag = false;
    }

}
