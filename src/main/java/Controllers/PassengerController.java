package Controllers;

import Enums.PassengerType;
import dtos.*;
import models.activity.ActivityRecord;
import models.passenger.Passenger;
import services.PassengerActivitiesService;
import statuses.PassengerStatus;
import services.PassengerService;

import java.util.List;

public class PassengerController {

    private final PassengerService passengerService;
    private final PassengerActivitiesService passengerActivitiesService;

    public PassengerController(){

        this.passengerService = PassengerService.getInstance();
        this.passengerActivitiesService = PassengerActivitiesService.getInstance();
    }

    public ResponseDTO<PassengerResponseDTO> registerPassenger(
          PassengerRequestDTO request
    ) {

        ResponseDTO<PassengerResponseDTO> response = new ResponseDTO<>();
        Passenger passenger;

        try {
            passenger = passengerService.registerPassenger(
                    request.getName(),
                    request.getNumber(),
                    request.getBalance(),
                    request.getPassengerType()
            );
        } catch (Exception e) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(e.getMessage());

            return response;
        }

        if (passenger == null)
        {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(PassengerStatus.EXISTS);
        }
        else
        {
            PassengerDTO passengerDTO = new PassengerDTO();
            passengerDTO.setName(passengerDTO.getName());
            passengerDTO.setNumber(passengerDTO.getNumber());

            response.setStatus(ResponseStatus.SUCCESS);
            response.setData(new PassengerResponseDTO());
            response.getData().setPassengerDTO(passengerDTO);
        }

        return response;
    }

    public ResponseDTO<PassengerResponseDTO> updateBalance(PassengerRequestDTO request)
    {
        Passenger passenger = passengerService.getPassengerByNumber(request.getNumber());

        ResponseDTO<PassengerResponseDTO> response = new ResponseDTO<>();

        if(passenger == null)
        {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(PassengerStatus.NOTFOUND);
        }
        else
        {
            passenger.setBalance(passenger.getBalance()+ request.getBalance());

            PassengerDTO passengerDTO = new PassengerDTO();
            passengerDTO.setName(passengerDTO.getName());
            passengerDTO.setNumber(passengerDTO.getNumber());

            response.setStatus(ResponseStatus.SUCCESS);
            response.setData(new PassengerResponseDTO());
            response.getData().setPassengerDTO(passengerDTO);
        }

        return response;
    }

    public void printPassengerDetails(PassengerRequestDTO request)
    {
        Passenger passenger = passengerService.getPassengerByNumber(request.getNumber());

        if(passenger == null)
        {
            System.out.println(PassengerStatus.NOTFOUND);
            return;
        }

        System.out.println("Passenger Name: "+passenger.getName());
        System.out.println("Passenger Number: "+passenger.getNumber());
        System.out.print("Balance: ");

        if(passenger.getPassengerType() == PassengerType.PREMIUM)
            System.out.append("Not applicable");
        else
            System.out.append(passenger.getBalance().toString());

        System.out.println();
        System.out.println();

        List<ActivityRecord> activityRecordList = passengerActivitiesService
                                    .getActivitiesOfPassenger(passenger.getNumber());

        if(activityRecordList == null)
        {
            System.out.println("No Passenger Activity on Record.");
            return;
        }

        for(ActivityRecord activityRecord : activityRecordList)
        {
            System.out.println("    Activity Name: "+activityRecord.getName());
            System.out.println("    Activity Destination: "+activityRecord.getDestination());
            System.out.println("    Price paid: "+activityRecord.getPricePaid());
            System.out.println();
        }
    }
}
