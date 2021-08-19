//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package transaction;

// @author Sun Wu Choi
 
public class Transaction {
    private String acctNum;
    private String company;
    private String vehicleType;
    private String rentalPeriod;
    private String rentalCost;

    public Transaction(String acctNum, String company, String vehicleType, String rentalPeriod, String rentalCost) {
        this.acctNum = acctNum;
        this.company = company;
        this.vehicleType = vehicleType;
        this.rentalPeriod = rentalPeriod;
        this.rentalCost = rentalCost;
    }
    
    // No setters because transaction should not be changed
    
    public String getAcctNum() {
        return acctNum;
    }

    public String getCompany() {
        return company;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public String getRentalCost() {
        return rentalCost;
    }
    
    @Override
    public String toString() {
        return vehicleType + ", " + rentalPeriod + " day rental, $" + rentalCost;
    }
    
}


