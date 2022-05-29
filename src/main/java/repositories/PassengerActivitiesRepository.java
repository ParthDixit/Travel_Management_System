package repositories;

import models.activity.Activity;
import models.activity.ActivityRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassengerActivitiesRepository {

    private final Map<Integer, List<ActivityRecord>> passengerActivitiesMap;
    private static PassengerActivitiesRepository passengerActivitiesRepository;

    private PassengerActivitiesRepository(){
        this.passengerActivitiesMap = new HashMap<>();
    }

    public static PassengerActivitiesRepository getInstance(){

        if(passengerActivitiesRepository == null)
        {
            synchronized (PassengerActivitiesRepository.class)
            {
                if(passengerActivitiesRepository == null)
                    passengerActivitiesRepository = new PassengerActivitiesRepository();
            }
        }

        return passengerActivitiesRepository;
    }

    public ActivityRecord save(Integer passengerNumber, ActivityRecord record)
    {
        String activityName = record.getName();

        if(passengerActivitiesMap.containsKey(passengerNumber))
        {
            for(ActivityRecord activityRecord : passengerActivitiesMap.get(passengerNumber))
                if(activityRecord.getName().equals(activityName))
                    return null;

            passengerActivitiesMap.get(passengerNumber).add(record);
        }

        else
            passengerActivitiesMap.put(passengerNumber, new ArrayList<>(List.of(record)));

        return record;
    }

    public List<ActivityRecord> findPassengerActivities(Integer passengerNumber){

        if(!passengerActivitiesMap.containsKey(passengerNumber))
            return null;

        return passengerActivitiesMap.get(passengerNumber);
    }

}
