package Controllers;

import dtos.*;
import models.activity.Activity;
import models.destination.Destination;
import models.passenger.Passenger;
import models.travelPackage.Package;
import services.PackageService;
import statuses.GeneralStatus;
import statuses.PackageStatus;

import java.util.List;

public class PackageController {

    private final PackageService packageService;

    public PackageController(){
        this.packageService = PackageService.getInstance();
    }

    public ResponseDTO<PackageResponseDTO> registerPackage(PackageRequestDTO request) {

        ResponseDTO<PackageResponseDTO> response = new ResponseDTO<>();
        Package travelPackage;

        try {
            travelPackage = packageService.registerPackage(
                    request.getName(),
                    request.getCapacity()
            );
        } catch (Exception e) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(e.getMessage());

            return response;
        }


        if (travelPackage == null)
        {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(PackageStatus.EXISTS);
        }
        else
        {
            PackageDTO packageDTO = new PackageDTO();
            packageDTO.setName(travelPackage.getName());
            packageDTO.setCapacity(travelPackage.getCapacity());

            response.setStatus(ResponseStatus.SUCCESS);
            response.setData(new PackageResponseDTO());
            response.getData().setPackageDTO(packageDTO);
        }

        return response;
    }

    public ResponseDTO<PackagePassengerAdditionResponseDTO> addPassenger(
            PackagePassengerAdditionRequestDTO request){

        String result = packageService.addPassengerToPackage(
                                            request.getPassengerNumber(),
                                            request.getPackageName());

        ResponseDTO<PackagePassengerAdditionResponseDTO> response
                    = new ResponseDTO<>();

        if(result.equals(GeneralStatus.SUCCESS))
        {
            List<Passenger> passengerList = packageService
                                .getPackageByName(request.getPackageName())
                                .getPassengerList();

            response.setStatus(ResponseStatus.SUCCESS);
            response.setData(new PackagePassengerAdditionResponseDTO());
            response.getData().setPassengerList(passengerList);
        }
        else
        {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(result);
        }

        return response;
    }

    public ResponseDTO<PackageDestinationAdditionResponseDTO> addDestination(
            PackageDestinationAdditionRequestDTO request
    )
    {
        String result = packageService.addDestinationToPackage(
                                request.getDestinationName(),
                                request.getPackageName());

        ResponseDTO<PackageDestinationAdditionResponseDTO> response
                    = new ResponseDTO<>();

        if(result.equals(GeneralStatus.SUCCESS))
        {
            List<Destination> destinationList = packageService
                                .getPackageByName(request.getPackageName())
                                .getDestinationList();

            response.setStatus(ResponseStatus.SUCCESS);
            response.setData(new PackageDestinationAdditionResponseDTO());
            response.getData().setDestinationList(destinationList);
        }
        else
        {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(result);
        }

        return response;
    }

    public void printDestinationsAndActivities(PackageRequestDTO request)
    {

        Package travelPackage = packageService.getPackageByName(request.getName());

        if(travelPackage == null)
        {
            System.out.println(PackageStatus.NOTFOUND);
            return;
        }

        System.out.println("Package Name: "+travelPackage.getName());
        System.out.println();
        System.out.println("Destinations: ");

        for(Destination destination : travelPackage.getDestinationList())
        {
            System.out.println("  "+destination.getName());
            System.out.println("    Activities:");
            for(Activity activity : destination.getActivityList())
            {
                System.out.println("    Name: "+activity.getName());
                System.out.println("    cost: "+activity.getCost());
                System.out.println("    Capacity: "+activity.getCapacity());
                System.out.println("    Description: "+activity.getDescription());
                System.out.println();
            }
        }

    }

    public void printPassengers(PackageRequestDTO request){

        Package travelPackage = packageService.getPackageByName(request.getName());

        if(travelPackage == null)
        {
            System.out.println(PackageStatus.NOTFOUND);
            return;
        }

        System.out.println("Package Name: "+travelPackage.getName());
        System.out.println("Capacity: "+travelPackage.getCapacity());
        System.out.println("Passenger Enrolled: "+travelPackage.getPassengerList().size());
        System.out.println("Passengers: ");

        for(Passenger passenger : travelPackage.getPassengerList())
        {
            System.out.println("  Name: "+passenger.getName());
            System.out.println("  Number: "+passenger.getNumber());
            System.out.println();
        }
    }
}
