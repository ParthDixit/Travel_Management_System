package repositories;

import models.activity.Activity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityRepository {

    private final Map<String, Activity> activityMap;
    private static ActivityRepository activityRepository;

    private ActivityRepository(){
        this.activityMap = new HashMap<>();
    }

    public static ActivityRepository getInstance(){

        if(activityRepository == null)
            synchronized (ActivityRepository.class)
            {
                if(activityRepository == null)
                    activityRepository = new ActivityRepository();
            }

        return activityRepository;
    }

    public Activity save(@NotNull Activity activity){

        if(activityMap.containsKey(activity.getName()))
            return null;
            //throw new RuntimeException("Activity already registered.");

        activityMap.put(activity.getName(), activity);

        return activity;
    }

    public Activity findActivityByName(String name){

        if(activityMap.containsKey(name))
            return activityMap.get(name);

        return null;
    }

    public List<Activity> findActivitiesByAvailability()
    {
        List<Activity> activityList = new ArrayList<>();

        for(Activity activity : activityMap.values())
            if(activity.getCapacity() > activity.getEnrolled())
                activityList.add(activity);

        return activityList;
    }
}
