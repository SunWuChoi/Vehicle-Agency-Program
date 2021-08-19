//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package vehicle;

// @author Sun Wu Choi

import reservation.Reservation;
import rate.VehicleRates.Cost;
 
public abstract class Vehicle {
    private String description;
    private int mpg;
    private String vin;
    private Reservation reservation;
    private Cost costWhenReserved;

    public Vehicle(String description, int mpg, String vin) {
        this.description = description;
        this.mpg = mpg;
        this.vin = vin;
        this.reservation = null;
        this.costWhenReserved = null;
    }
    
    public boolean isReserved(){
        return reservation != null;
    }
    
    public void setReservation(Reservation reservation){
        this.reservation = reservation;
    }
    
    public void setCost(Cost cost){
        this.costWhenReserved = cost;
    }
    
    public void cancelReservation(){
        this.reservation = null;
    }
    
    public String getDescription() {
        return description;
    }

    public int getMpg() {
        return mpg;
    }

    public String getVin() {
        return vin;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Cost getCostWhenReserved() {
        return costWhenReserved;
    }

    @Override
    public abstract String toString();

}
