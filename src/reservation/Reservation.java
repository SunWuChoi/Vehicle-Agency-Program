//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package reservation;

// @author Sun Wu Choi
 
public class Reservation {
    private String acctNum;
    private int vehicleType;
    private String description;
    private String rentalPeriod;
    private boolean insuranceSelected;
    
    public Reservation(String acctNum, int vehicleType, String description, String rentalPeriod, boolean insuranceSelected){
        this.acctNum = acctNum;
        this.vehicleType = vehicleType;
        this.description = description;
        this.rentalPeriod = rentalPeriod;
        this.insuranceSelected = insuranceSelected;
    }

    public String getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(String acctNum) {
        this.acctNum = acctNum;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public boolean isInsuranceSelected() {
        return insuranceSelected;
    }

    public void setInsuranceSelected(boolean insuranceSelected) {
        this.insuranceSelected = insuranceSelected;
    }

    @Override
    public String toString() {
        return "Reservation{" + "acctNum=" + acctNum + ", vehicleType=" + vehicleType + ", description=" + description + ", rentalPeriod=" + rentalPeriod + ", insuranceSelected=" + insuranceSelected + '}';
    }    
}
