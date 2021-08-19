//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package vehicle;

// @author Sun Wu Choi
 
public class SUV extends Vehicle {

    private int seats;
    private int cargo;
    
    public SUV(String description, int mpg, int seats, int cargo, String vin) {
        super(description, mpg, vin);
        this.seats = seats;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return String.format("| %-6s| %-21s| %3d | %5d  %-5s|  %9s | %11s |   %5s  |",this.getClass().getSimpleName(),super.getDescription(),super.getMpg(),this.seats,"seat",this.cargo+" cu. ft.",super.getVin(),super.isReserved());
    }
    
}
