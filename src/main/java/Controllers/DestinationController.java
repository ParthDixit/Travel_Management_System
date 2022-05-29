package Controllers;

import dtos.*;
import models.activity.Activity;
import models.destination.Destination;
import services.DestinationService;
import statuses.DestinationStatus;
import statuses.GeneralStatus;

import java.util.List;

public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(){
        this.destinationService = DestinationService.getInstance();
    }

    public ResponseDTO<DestinationResponseDTO> registerDestination(
            DestinationRequestDTO request
    ){

        ResponseDTO<DestinationResponseDTO> response = new ResponseDTO<>();
        Destination destination;

        try {
            destination = destinationService.registerDestination(request.getName());
        }catch (Exception e)
        {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(e.getMessage());

            return response;
        }

        if(destination == null)
        {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(DestinationStatus.EXISTS);
        }
        else
        {
            DestinationDTO destinationDTO = new DestinationDTO();
            destinationDTO.setName(destination.getName());

            response.setStatus(ResponseStatus.SUCCESS);
            response.setData(new DestinationResponseDTO());
            response.getData().setDestinationDTO(destinationDTO);
        }

        return response;
    }

    public ResponseDTO<DestinationActivityAdditionResponseDTO> addActivity(
            DestinationActivityAdditionRequestDTO request
    ){

        String result = destinationService.addActivityToDestination(
                                    request.getActivityName(),
                                    request.getDestinationName());

        ResponseDTO<DestinationActivityAdditionResponseDTO> response
                        = new ResponseDTO<>();

        if(result.equals(GeneralStatus.SUCCESS))
        {
            List<Activity> activityList = destinationService
                    .getDestinationByName(request.getDestinationName())
                    .getActivityList();

            response.setStatus(ResponseStatus.SUCCESS);
            response.setData(new DestinationActivityAdditionResponseDTO());
            response.getData().setActivityList(activityList);
        }
        else
        {
            response.setStatus(ResponseStatus.FAILURE);
            response.setMessage(result);
        }

        return response;
    }
}
