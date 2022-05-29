package repositories;

import models.destination.Destination;

import java.util.HashMap;
import java.util.Map;

public class DestinationRepository {

    private final Map<String, Destination> destinationMap;
    private static DestinationRepository destinationRepository;

    private DestinationRepository(){
        destinationMap = new HashMap<>();
    }

    public static DestinationRepository getInstance(){

        if(destinationRepository == null)
        {
            synchronized (DestinationRepository.class)
            {
                if(destinationRepository == null)
                    destinationRepository = new DestinationRepository();
            }
        }

        return destinationRepository;
    }

    public Destination save(Destination destination){

        if(destinationMap.containsKey(destination.getName()))
            return null;

        destinationMap.put(destination.getName(), destination);

        return destination;
    }

    public Destination findDestinationByName(String name)
    {
        if(!destinationMap.containsKey(name))
            return null;

        return destinationMap.get(name);
    }
}
