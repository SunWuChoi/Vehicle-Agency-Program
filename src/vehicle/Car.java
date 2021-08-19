//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package vehicle;

// @author Sun Wu Choi
 
public class Car extends Vehicle {

    private int seats;
    
    public Car(String description, int mpg, int seats, String vin) {
        super(description, mpg, vin);
        this.seats = seats;
    }

    @Override
    public String toString() {
        return String.format("| %-6s| %-21s| %3d | %5d  %-5s|            | %11s |   %5s  |",this.getClass().getSimpleName(),super.getDescription(),super.getMpg(),this.seats,"seat",super.getVin(),super.isReserved());
    }

}
