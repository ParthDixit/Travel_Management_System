
import Controllers.*;
import Enums.PassengerType;
import dtos.*;
import models.destination.Destination;
import models.passenger.Passenger;
import models.travelPackage.Package;

public class Client {

    public static void main(String[] args) {

        //Passenger
        PassengerController passengerController = new PassengerController();
        PackageController packageController = new PackageController();
        DestinationController destinationController = new DestinationController();
        ActivityController activityController = new ActivityController();

        PassengerRequestDTO passenger= new PassengerRequestDTO();
        passenger.setName("John Halo");
        passenger.setNumber(1);
        passenger.setBalance(50);
        passenger.setPassengerType(PassengerType.STANDARD);


        ResponseDTO<PassengerResponseDTO> dto = passengerController.registerPassenger(passenger);
        System.out.println(dto.getStatus());
        passengerController.printPassengerDetails(passenger);
        System.out.println();

        //Package
        PackageRequestDTO packageRequestDTO = new PackageRequestDTO();
        packageRequestDTO.setName("Combo");
        packageRequestDTO.setCapacity(10);

        ResponseDTO<PackageResponseDTO> packageResponseDTO = packageController.registerPackage(packageRequestDTO);

        if(packageResponseDTO.getStatus() == ResponseStatus.SUCCESS)
            System.out.println(packageResponseDTO.getData().getPackageDTO().getName());
        else
            System.out.println(packageResponseDTO.getMessage());

        System.out.println();

        //Destination
        DestinationRequestDTO destinationRequestDTO = new DestinationRequestDTO();
        destinationRequestDTO.setName("Reach");

        ResponseDTO<DestinationResponseDTO> destinationResponseDTO = destinationController
                                    .registerDestination(destinationRequestDTO);


        if(destinationResponseDTO.getStatus() == ResponseStatus.SUCCESS)
            System.out.println(destinationResponseDTO.getData().getDestinationDTO().getName());
        else
            System.out.println(destinationResponseDTO.getMessage());

        System.out.println();

        //Activity

        ActivityRequestDTO activityRequestDTO = new ActivityRequestDTO();
        activityRequestDTO.setName("Boating");
        activityRequestDTO.setCost(25);
        activityRequestDTO.setCapacity(5);
        activityRequestDTO.setDescription("boating in river");

        ResponseDTO<ActivityResponseDTO> activityResponseDTO = activityController
                                                .registerActivity(activityRequestDTO);

        if(activityResponseDTO.getStatus() == ResponseStatus.SUCCESS)
            System.out.println(activityResponseDTO.getData().getActivityDTO().getName());
        else
            System.out.println(activityResponseDTO.getMessage());

        System.out.println();

        // Enroll Package
        PackagePassengerAdditionRequestDTO requestDTO = new PackagePassengerAdditionRequestDTO();
        requestDTO.setPackageName("Combo");
        requestDTO.setPassengerNumber(1);

        ResponseDTO<PackagePassengerAdditionResponseDTO> responseDTO = packageController
                                                .addPassenger(requestDTO);

        if(responseDTO.getStatus() == ResponseStatus.SUCCESS)
            packageController.printPassengers(packageRequestDTO);
        else
            System.out.println(destinationResponseDTO.getMessage());

        System.out.println();

        //add Destination

        PackageDestinationAdditionRequestDTO requestDTO1 = new PackageDestinationAdditionRequestDTO();
        requestDTO1.setPackageName("Combo");
        requestDTO1.setDestinationName("Reach");

        ResponseDTO<PackageDestinationAdditionResponseDTO> responseDTO1 = packageController
                                                                            .addDestination(requestDTO1);

        if(responseDTO1.getStatus() == ResponseStatus.SUCCESS)
            packageController.printDestinationsAndActivities(packageRequestDTO);
        else
            System.out.println(responseDTO1.getMessage());

        System.out.println();

        // add activity

        DestinationActivityAdditionRequestDTO requestDTO2 = new DestinationActivityAdditionRequestDTO();
        requestDTO2.setDestinationName("Reach");
        requestDTO2.setActivityName("Boating");

        ResponseDTO<DestinationActivityAdditionResponseDTO> responseDTO2 = destinationController
                                                                            .addActivity(requestDTO2);

        if(responseDTO2.getStatus() == ResponseStatus.SUCCESS)
            packageController.printDestinationsAndActivities(packageRequestDTO);
        else
            System.out.println(responseDTO2.getMessage());

        System.out.println();

        //add passenger to activity

        ActivityPassengerAdditionRequestDTO requestDTO3 = new ActivityPassengerAdditionRequestDTO();
        requestDTO3.setActivityName("Boating");
        requestDTO3.setPassengerNumber(1);

        ResponseDTO<ActivityPassengerAdditionResponseDTO> responseDTO3 = activityController
                                                                        .addPassenger(requestDTO3);

        if(responseDTO3.getStatus() == ResponseStatus.SUCCESS)
            passengerController.printPassengerDetails(passenger);
        else
            System.out.println(responseDTO3.getMessage());

        System.out.println();

        //climbing activity
        ActivityRequestDTO activityRequestDTO1 = new ActivityRequestDTO();
        activityRequestDTO1.setName("climbing");
        activityRequestDTO1.setCost(25);
        activityRequestDTO1.setCapacity(10);
        activityRequestDTO1.setDescription("climbing on mountain.");

        ResponseDTO<ActivityResponseDTO> activityResponseDTO1 = activityController
                .registerActivity(activityRequestDTO1);

        if(activityResponseDTO1.getStatus() == ResponseStatus.SUCCESS)
            System.out.println(activityResponseDTO1.getData().getActivityDTO().getName());
        else
            System.out.println(activityResponseDTO1.getMessage());

        System.out.println();

        //add climbing activity
        DestinationActivityAdditionRequestDTO requestDTO4 = new DestinationActivityAdditionRequestDTO();
        requestDTO4.setDestinationName("Reach");
        requestDTO4.setActivityName("climbing");

        ResponseDTO<DestinationActivityAdditionResponseDTO> responseDTO4 = destinationController
                .addActivity(requestDTO4);

        if(responseDTO4.getStatus() == ResponseStatus.SUCCESS)
            packageController.printDestinationsAndActivities(packageRequestDTO);
        else
            System.out.println(responseDTO4.getMessage());

        System.out.println();

        //enroll to climbing
        ActivityPassengerAdditionRequestDTO requestDTO5 = new ActivityPassengerAdditionRequestDTO();
        requestDTO5.setActivityName("climbing");
        requestDTO5.setPassengerNumber(1);

        ResponseDTO<ActivityPassengerAdditionResponseDTO> responseDTO5 = activityController
                .addPassenger(requestDTO5);

        if(responseDTO5.getStatus() == ResponseStatus.SUCCESS)
            passengerController.printPassengerDetails(passenger);
        else
            System.out.println(responseDTO5.getMessage());

        System.out.println();

        activityController.printAvailableActivities();

    }
}
