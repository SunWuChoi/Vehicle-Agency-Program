//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package vehicle;

// @author Sun Wu Choi

import java.util.LinkedList;

 
public class Vehicles {
    LinkedList<Vehicle> vehicleList;
    
    public Vehicles(){
        this.vehicleList = new LinkedList<Vehicle>();
    }
    
    public Vehicles(Vehicle[] list){
        vehicleList = new LinkedList<Vehicle>();
        for(int i = 0; i < list.length; i++){
            vehicleList.add(list[i]);
        }   
    }
    
    public void add(Vehicle vehicle){
        vehicleList.add(vehicle);
    }
    
    public boolean remove(String VIN){
        int size = vehicleList.size();
        for(int i = 0; i < size; i++){
            if(vehicleList.get(i).getVin().equals(VIN)){
                System.out.println(vehicleList.get(i).getClass() + " " + vehicleList.get(i).getDescription() + " with VIN " + vehicleList.get(i).getVin() + " Removed");
                vehicleList.remove(i);
                return true;
            }
        } 
        System.out.println("No such vehicle with VIN " + VIN + " found");
        return false;
    }
    
    public Vehicle getVehicle(String VIN){
        int size = vehicleList.size();
        
        for(int i = 0; i < size; i++){
            if(vehicleList.get(i).getVin().equals(VIN)){
                return vehicleList.get(i);
            }
        }
        return null;
    }
    
    public int getSize(){
        return vehicleList.size();
    }
    
    public Vehicle getVehicle(int i){
        return vehicleList.get(i);
    }
}
