package models.passenger;

import Enums.PassengerType;
import lombok.Getter;
import lombok.Setter;
import models.BaseModel;
import models.travelPackage.Package;

import java.security.InvalidParameterException;

@Getter
@Setter
public class Passenger extends BaseModel {

    private String name;
    private Integer number;
    private Integer balance;

    private PassengerType passengerType;
    private Package enrolledPackage;

    private Passenger(){    }


    public static PassengerBuilder getBuilder(){return new PassengerBuilder();}

    public static class PassengerBuilder {

        private Passenger obj = new Passenger();

        public PassengerBuilder setName(String name){
            obj.name = name;

            return this;
        }
        public PassengerBuilder setNumber(Integer number){
            obj.number = number;

            return this;
        }

        public PassengerBuilder setBalance(Integer balance){
            obj.balance = balance;
            return this;
        }

        public PassengerBuilder setPassengerType(PassengerType passengerType){
            obj.passengerType = passengerType;
            return this;
        }

        public PassengerBuilder setPackage(Package travelPackage){
            obj.enrolledPackage = travelPackage;
            return this;
        }

        private boolean checkName(String name)
        {
            if(name == null || name.length() == 0)
                return false;

            for(char c : name.toCharArray())
                if(!(('a'<=c && c<='z') || ('A'<=c && c<='Z') || c==' '))
                    return false;

            return true;
        }

        private Passenger clone(Passenger obj)
        {
            Passenger obj1 = new Passenger();

            obj1.name = obj.name.trim();
            obj1.number = obj.number;
            obj1.balance = obj.balance;
            obj1.passengerType = obj.passengerType;

            return obj1;
        }

        public Passenger build(){

            if(!checkName(obj.name))
                throw  new InvalidParameterException("Enter Valid Name.");

            if(obj.number == null)
                throw new NullPointerException("Number not set.");

            if(obj.passengerType == null)
                throw new NullPointerException("Passenger type not set.");

            if(obj.balance == null)
                throw new NullPointerException("Balance not set.");

            if(obj.balance < 0)
                throw new InvalidParameterException("Balance cannot be less than 0.");

            return clone(obj);
        }

    }

}
