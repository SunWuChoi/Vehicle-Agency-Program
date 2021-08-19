//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package rate;

// @author Sun Wu Choi
 
public class Rates {
    private VehicleRates[] rates = new VehicleRates[3];
    
    public Rates(VehicleRates Car, VehicleRates SUV, VehicleRates Truck){
        rates[0] = Car;
        rates[1] = SUV;
        rates[2] = Truck;
    }
    
    public Rates(Rates ratesInput){
        rates[0] = ratesInput.getCarRates();
        rates[1] = ratesInput.getSUVRates();
        rates[2] = ratesInput.getTruckRates();
    }
    
    public VehicleRates getCarRates(){
        return rates[0];
    }
    
    public VehicleRates getSUVRates(){
        return rates[1];
    }
    
    public VehicleRates getTruckRates(){
        return rates[2];
    }
}
