package services;

import models.destination.Destination;
import models.passenger.Passenger;
import models.travelPackage.Package;
import repositories.PackageRepository;
import statuses.DestinationStatus;
import statuses.GeneralStatus;
import statuses.PackageStatus;
import statuses.PassengerStatus;

public class PackageService {

    private static PackageService packageService;
    private final PackageRepository packageRepository;
    private final PassengerService passengerService;

    private final DestinationService destinationService;

    private PackageService(){
        this.packageRepository = PackageRepository.getInstance();
        this.passengerService = PassengerService.getInstance();
        this.destinationService = DestinationService.getInstance();
    }

    public static PackageService getInstance(){
        if(packageService == null)
        {
            synchronized (PackageService.class)
            {
                if (packageService == null)
                    packageService = new PackageService();
            }
        }

        return packageService;
    }

    public Package registerPackage(String name, Integer capacity){

        Package.PackageBuilder builder = Package.getBuilder()
                                            .setName(name)
                                            .setCapacity(capacity);

        Package travelPackage = builder.build();

        return packageRepository.save(travelPackage);
    }

    public Package getPackageByName(String name){
        return packageRepository.findPackageByName(name);
    }

    public String addPassengerToPackage(Integer passengerNumber, String packageName){

        Passenger passenger = passengerService.getPassengerByNumber(passengerNumber);
        Package travelPackage = getPackageByName(packageName);

        if(passenger == null)
            return PassengerStatus.NOTFOUND;

        if(travelPackage == null)
            return PackageStatus.NOTFOUND;

        if(passenger.getEnrolledPackage() != null)
            return PassengerStatus.PACKAGE_ALREADY_ENROLLED;

        if(travelPackage.getPassengerList().contains(passenger))
            return PackageStatus.EXISTS;

        if(travelPackage.getCapacity() == travelPackage.getPassengerList().size())
            return PackageStatus.FILLED;


        travelPackage.getPassengerList().add(passenger);
        passenger.setEnrolledPackage(travelPackage);

        return GeneralStatus.SUCCESS;
    }

    public String addDestinationToPackage(String destinationName, String packageName){

        Destination destination = destinationService.getDestinationByName(destinationName);
        Package travelPackage = getPackageByName(packageName);

        if(destination == null)
            return DestinationStatus.NOTFOUND;

        if(travelPackage == null)
            return PackageStatus.NOTFOUND;

        if(travelPackage.getDestinationList().contains(destination))
            return DestinationStatus.EXISTS;

        travelPackage.getDestinationList().add(destination);

        return GeneralStatus.SUCCESS;
    }
}
