package models.travelPackage;

import lombok.Getter;
import lombok.Setter;
import models.BaseModel;
import models.destination.Destination;
import models.passenger.Passenger;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Package extends BaseModel {

    private String name;
    private Integer capacity;
    private List<Destination> destinationList;

    private List<Passenger> passengerList;

    private Package(){
        this.destinationList = new ArrayList<>();
        this.passengerList = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }



    public static PackageBuilder getBuilder(){
        return new PackageBuilder();
    }

    public static class PackageBuilder{
        Package obj = new Package();

        public PackageBuilder setName(String name){
            obj.name = name;
            return this;
        }

        public PackageBuilder setCapacity(Integer capacity){
            obj.capacity = capacity;
            return this;
        }

        public PackageBuilder setDestinationList(List<Destination> destinationList){
            obj.destinationList = destinationList;
            return this;
        }

        public PackageBuilder setPassenger(List<Passenger> passengerList){
            obj.passengerList = passengerList;
            return this;
        }

        private Package clone(Package obj){
            Package obj1 = new Package();

            obj1.name = obj.name;
            obj1.capacity = obj.capacity;
            obj1.destinationList = new ArrayList<>(obj.destinationList);
            obj1.passengerList = new ArrayList<>(obj.passengerList);

            return obj1;
        }

        public Package build(){

            if(obj.name == null)
                throw new NullPointerException("Name not set.");

            if(obj.capacity == null)
                throw new NullPointerException("Capacity not set.");

            if(obj.capacity < 0)
                throw new InvalidParameterException("Capacity cannot be less than 0.");

            return clone(obj);
        }
    }
}
