package services;

import models.activity.Activity;
import models.destination.Destination;
import models.travelPackage.Package;
import repositories.ActivityRepository;
import repositories.DestinationRepository;
import repositories.PackageRepository;
import statuses.ActivityStatus;
import statuses.DestinationStatus;
import statuses.GeneralStatus;

public class DestinationService {

    private static DestinationService destinationService;
    private final DestinationRepository destinationRepository;
    private final ActivityService activityService;

    private DestinationService(){
        this.destinationRepository = DestinationRepository.getInstance();
        this.activityService = ActivityService.getInstance();
    }

    public static DestinationService getInstance(){

        if(destinationService == null)
        {
            synchronized (DestinationService.class)
            {
                if(destinationService == null)
                    destinationService = new DestinationService();
            }
        }

        return destinationService;
    }

    public Destination registerDestination(String name){

        Destination.DestinationBuilder builder = Destination.getBuilder()
                                            .setName(name);

        Destination destination = builder.build();

        return destinationRepository.save(destination);
    }

    public Destination getDestinationByName(String name){
        return destinationRepository.findDestinationByName(name);
    }

    public String addActivityToDestination(String activityName, String destinationName){

        Activity activity = activityService.getActivityByName(activityName);
        Destination destination = getDestinationByName(destinationName);

        if(activity == null)
            return ActivityStatus.NOTFOUND;

        if(destination == null)
            return DestinationStatus.NOTFOUND;

        if(activity.getAtDestination() != null)
            return ActivityStatus.ALREADY_ASSIGNED;

        if(destination.getActivityList().contains(activity))
            return ActivityStatus.EXISTS;


        destination.getActivityList().add(activity);
        activity.setAtDestination(destination);

        return GeneralStatus.SUCCESS;
    }
}
