//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package gui;

// @author Sun Wu Choi

import java.util.Scanner;

public class EmployeeUI implements UserInterface {

    private boolean flag = true;
    
    public EmployeeUI(){
        start();
    }
    
    @Override
    public void start() {
        while(flag){
            display();
            controller(selection8());
        }
    }

    @Override
    public void display() {
        System.out.println("========= Employee Menu =========");
        System.out.println("| 1 | View Current Rates        |");
        System.out.println("| 2 | View Available Vehicles   |");
        System.out.println("| 3 | Calc Estimated Rental Cost|");
        System.out.println("| 4 | Make a Reservation        |");
        System.out.println("| 5 | Cancel Reservation        |");
        System.out.println("| 6 | View Corporate Account    |");
        System.out.println("| 7 | Process Returned Vehicle  |");
        System.out.println("| 8 | Quit                      |");
        System.out.println("=================================");
    }

    @Override
    public void controller(int selection) {
        switch(selection){
            case 1: 
                viewRates();
                break;
            case 2: 
                viewAvailVehicle();
                break;
            case 3: 
                calcEstimatedCost();
                break;
            case 4: 
                reserve();
                break;
            case 5: 
                cancelReservation();
                break;
            case 6: 
                viewAccount();
                break;
            case 7: 
                processReturn();
                break;
            case 8: 
                exit();
                break;
        }
    }
    
    public int selection8() {
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
                case "8":
                    return 8;
                default:
                    System.out.println("Enter between 1 ~ 8");
            }
        }
    }
    
    public void viewRates(){
        printer(SystemController.getRateHeader());
        printer(SystemController.getCarRates());
        printer(SystemController.getSUVRates());
        printer(SystemController.getTruckRates());
        System.out.println("=====================================================================");
    }
    
    public void viewAvailVehicle(){
        printer(SystemController.viewAllVehicleHeader());
        printer(SystemController.viewAllAvailVehicle());
    }
    
    public void calcEstimatedCost(){
        printer(SystemController.calcEstimatedCost());
    }
    
    public void reserve(){
        printer(SystemController.reserve());
    }
    
    public void cancelReservation(){
        printer(SystemController.cancelReservation());
    }
    
    public void viewAccount(){
        printer(SystemController.viewAccount());
    }
    
    public void processReturn(){
        printer(SystemController.processReservation());
    }
    
    @Override
    public void printer (String[] input){
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }
    
    @Override
    public void exit() {
        this.flag = false;
    }
}
