//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package vehicle;

// @author Sun Wu Choi
 
public class Truck extends Vehicle{

    private int Load;
    
    public Truck(String description, int mpg, int load, String vin) {
        super(description, mpg, vin);
        this.Load = load;
    }

    @Override
    public String toString() {
        return String.format("| %-6s| %-21s| %3d | %5d  %-5s|            | %11s |   %5s  |",this.getClass().getSimpleName(),super.getDescription(),super.getMpg(),this.Load,"lbs.",super.getVin(),super.isReserved());
    }
    
}
