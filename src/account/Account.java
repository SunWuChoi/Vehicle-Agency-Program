//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package account;

// @author Sun Wu Choi

import vehicle.Vehicle;
import vehicle.Vehicles;

 
public class Account {
    private String acctNum;
    private String company;
    private Vehicles reservedVehicles;
    private boolean prime;

    public Account(String acctNum, String company, boolean prime) {
        this.acctNum = acctNum;
        this.company = company;
        this.prime = prime;
        this.reservedVehicles = new Vehicles();
    }

    public String getAcctNum() {
        return acctNum;
    }

    public String getCompany() {
        return company;
    }

    public boolean isPrime() {
        return prime;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPrime(boolean prime) {
        this.prime = prime;
    }
    
    public void addReservedVehicle(Vehicle vehicle){
        reservedVehicles.add(vehicle);
    }
    
    public void removeReservedVehicle(String vin){
        reservedVehicles.remove(vin);
    }

    public Vehicles getReservedVehicles() {
        return reservedVehicles;
    }
    
    public String toString(){
        return String.format("|  %-5s  | %-35s|  %2d  | %-5s |",this.acctNum,this.company,this.reservedVehicles.getSize(),this.prime);
    }
}
