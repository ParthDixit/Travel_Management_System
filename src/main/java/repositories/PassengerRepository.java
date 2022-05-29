package repositories;

import models.passenger.Passenger;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PassengerRepository {

    private static PassengerRepository passengerRepository;
    Map<Integer, Passenger> passengerMap;

    private PassengerRepository(){
        this.passengerMap = new HashMap<>();
    }

    public static PassengerRepository getInstance(){

        if(passengerRepository == null)
        {
            synchronized (PassengerRepository.class){

                if(passengerRepository == null)
                    passengerRepository = new PassengerRepository();
            }
        }

        return passengerRepository;
    }


    public Passenger save(Passenger passenger){

        if(passengerMap.containsKey(passenger.getNumber()))
            return null;
            //throw new RuntimeException("Passenger already registered.");

        passengerMap.put(passenger.getNumber(), passenger);

        return passenger;
    }

    public Passenger findPassengerByNumber(Integer number){

        if(passengerMap.containsKey(number))
            return passengerMap.get(number);

        return null;
    }

}
