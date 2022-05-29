package services;

import Enums.PassengerType;
import models.activity.Activity;
import models.activity.ActivityRecord;
import models.destination.Destination;
import models.passenger.Passenger;
import repositories.ActivityRepository;
import repositories.PassengerActivitiesRepository;
import statuses.ActivityStatus;
import statuses.GeneralStatus;
import statuses.PassengerStatus;

import java.util.List;

public class ActivityService {

    private static ActivityService activityService;
    private final ActivityRepository activityRepository;
    private final PassengerService passengerService;
    private final PassengerActivitiesService passengerActivitiesService;
    private final DiscountService discountService;


    private ActivityService(){

        this.activityRepository = ActivityRepository.getInstance();
        this.passengerService = PassengerService.getInstance();
        this.passengerActivitiesService = PassengerActivitiesService.getInstance();
        this.discountService = DiscountService.getInstance();
    }

    public static ActivityService getInstance(){
        if(activityService == null)
            synchronized (ActivityService.class)
            {
                if(activityService == null)
                    activityService = new ActivityService();
            }
        return activityService;
    }

    public Activity registerActivity (  String name,
                                        String description,
                                        Integer cost,
                                        Integer capacity){

        Activity.ActivityBuilder builder = Activity.getBuilder()
                                            .setName(name).setDescription(description)
                                            .setCost(cost).setCapacity(capacity);

        Activity activity = builder.build();

        return activityRepository.save(activity);
    }

    public String addPassengerToActivity(Integer passengerNumber, String activityName){

        Passenger passenger = passengerService.getPassengerByNumber(passengerNumber);
        Activity activity = getActivityByName(activityName);

        if(passenger == null)
            return PassengerStatus.NOTFOUND;

        if(activity == null)
            return ActivityStatus.NOTFOUND;

        if(activity.getAtDestination() == null)
            return ActivityStatus.NOT_ASSIGNED;

        if(passenger.getEnrolledPackage() == null)
            return PassengerStatus.PACKAGE_NOT_ENROLLED;

        Destination activityDestination = activity.getAtDestination();

        boolean found = false;

        for(Destination packageDestination : passenger.getEnrolledPackage().getDestinationList())
            if (packageDestination == activityDestination) {
                found = true;
                break;
            }

        if(!found)
            return ActivityStatus.NOT_AVAILABLE;

        if(activity.getCapacity().equals(activity.getEnrolled()))
            return ActivityStatus.FILLED;

        Integer cost = activity.getCost();
        int passengerBalance = passenger.getBalance();
        PassengerType type = passenger.getPassengerType();

        Integer discountedPrice = discountService.discount(type, cost);

        if(passengerBalance < discountedPrice)
            return PassengerStatus.INSUFFICIENT_BALANCE;


        ActivityRecord activityRecord = new ActivityRecord();
        activityRecord.setName(activityName);
        activityRecord.setDestination(activity.getAtDestination().getName());
        activityRecord.setPricePaid(discountedPrice);

        ActivityRecord record = passengerActivitiesService.registerPassengerActivity(passengerNumber, activityRecord);

        if(record == null)
            return ActivityStatus.EXISTS;

        passenger.setBalance(passengerBalance-discountedPrice);
        activity.setEnrolled(activity.getEnrolled()+1);

        return GeneralStatus.SUCCESS;
    }

    public Activity getActivityByName(String name){

        return activityRepository.findActivityByName(name);
    }

    public List<Activity> getAvailableActivities(){

        return activityRepository.findActivitiesByAvailability();
    }

}
