package services;

import models.activity.Activity;
import models.activity.ActivityRecord;
import repositories.PassengerActivitiesRepository;
import statuses.PassengerStatus;

import java.util.List;

public class PassengerActivitiesService {

    private static PassengerActivitiesService passengerActivitiesService;
    private final PassengerActivitiesRepository passengerActivitiesRepository;

    private PassengerActivitiesService(){
        this.passengerActivitiesRepository = PassengerActivitiesRepository.getInstance();
    }

    public static PassengerActivitiesService getInstance(){

        if(passengerActivitiesService == null)
        {
            synchronized (PassengerActivitiesService.class)
            {
                if(passengerActivitiesService == null)
                    passengerActivitiesService = new PassengerActivitiesService();
            }
        }

        return passengerActivitiesService;
    }

    public ActivityRecord registerPassengerActivity(Integer passengerNumber, ActivityRecord activityRecord)
    {
        return passengerActivitiesRepository.save(passengerNumber, activityRecord);
    }

    public List<ActivityRecord> getActivitiesOfPassenger(Integer passengerNumber)
    {

        return passengerActivitiesRepository.findPassengerActivities(passengerNumber);
    }
}
