package services;

import Enums.PassengerType;
import models.passenger.Passenger;
import repositories.PassengerRepository;

public class PassengerService {

    private static PassengerService passengerService;
    private final PassengerRepository passengerRepository;

    private PassengerService(){
        this.passengerRepository = PassengerRepository.getInstance();
    }

    public static PassengerService getInstance()
    {
        if(passengerService == null)
        {
            synchronized (PassengerService.class)
            {
                if(passengerService == null)
                    passengerService = new PassengerService();
            }
        }

        return passengerService;
    }

    public Passenger registerPassenger(String name,
                                       Integer number,
                                       Integer balance,
                                       PassengerType type){

        Passenger.PassengerBuilder builder = Passenger.getBuilder()
                                                .setName(name).setNumber(number)
                                                .setBalance(balance)
                                                .setPassengerType(type);

        Passenger passenger = builder.build();

        return passengerRepository.save(passenger);
    }

    public Passenger getPassengerByNumber(Integer number)
    {
        return passengerRepository.findPassengerByNumber(number);
    }
}
