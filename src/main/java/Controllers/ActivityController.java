package Controllers;

import dtos.*;
import models.activity.Activity;
import models.activity.ActivityRecord;
import services.ActivityService;
import services.PassengerActivitiesService;
import statuses.ActivityStatus;
import statuses.GeneralStatus;

import java.util.List;

public class ActivityController {

    private final ActivityService activityService;
    private final PassengerActivitiesService passengerActivitiesService;

    public ActivityController(){

        this.activityService = ActivityService.getInstance();
        this.passengerActivitiesService = PassengerActivitiesService.getInstance();
    }

    public ResponseDTO<ActivityResponseDTO> registerActivity(ActivityRequestDTO request)
    {

        ResponseDTO<ActivityResponseDTO> response = new ResponseDTO<>();

        Activity activity;

        try {
            activity = activityService.registerActivity(
                                    request.getName(),
                                    request.getDescription(),
                                    request.getCost(),
                                    request.getCapacity()
            );
        }catch (Exception e)
        {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(e.getMessage());

            return response;
        }

        if(activity == null)
        {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(ActivityStatus.EXISTS);
        }
        else
        {
            ActivityDTO activityDTO = new ActivityDTO();
            activityDTO.setName(activity.getName());
            activityDTO.setCapacity(activity.getCapacity());
            activityDTO.setCost(activity.getCost());

            response.setStatus(ResponseStatus.SUCCESS);
            response.setData(new ActivityResponseDTO());
            response.getData().setActivityDTO(activityDTO);
        }

        return response;
    }

    public ResponseDTO<ActivityPassengerAdditionResponseDTO> addPassenger(
                                ActivityPassengerAdditionRequestDTO request
        ){


            String result = activityService.addPassengerToActivity(
                    request.getPassengerNumber(),
                    request.getActivityName());

            ResponseDTO<ActivityPassengerAdditionResponseDTO> response
                    = new ResponseDTO<>();

            if(result.equals(GeneralStatus.SUCCESS))
            {
                List<ActivityRecord> activityRecordList= passengerActivitiesService
                                    .getActivitiesOfPassenger(request.getPassengerNumber());

                response.setStatus(ResponseStatus.SUCCESS);
                response.setData(new ActivityPassengerAdditionResponseDTO());
                response.getData().setActivityRecordList(activityRecordList);
            }
            else
            {
                response.setStatus(ResponseStatus.FAILURE);
                response.setMessage(result);
            }

            return response;
    }

    public void printAvailableActivities(){

        List<Activity> activityList = activityService.getAvailableActivities();

        if(activityList == null)
        {
            System.out.println("No activity available");
            return;
        }

        System.out.println("Activities:");

        for(Activity activity : activityList)
        {
            System.out.println("Name: "+activity.getName());
            System.out.println("Description: "+activity.getDescription());
            System.out.println("Cost: "+activity.getCost());
            System.out.println("Space Available: "+(activity.getCapacity() - activity.getEnrolled()));
            System.out.println();
        }
    }

}
