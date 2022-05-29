package models.activity;

import lombok.Getter;
import lombok.Setter;
import models.BaseModel;
import models.destination.Destination;
import models.passenger.Passenger;

@Getter
@Setter
public class Activity extends BaseModel {

    private String name;
    private String description;
    private Integer cost;
    private Integer capacity;

    private Integer enrolled;

    private Destination atDestination;

    private Activity(){
        this.enrolled = 0;
        this.atDestination = null;
    }


    public static ActivityBuilder getBuilder(){
        return new ActivityBuilder();
    }

    public static class ActivityBuilder{

        Activity obj = new Activity();

        public ActivityBuilder setName(String name){
            obj.name = name;

            return this;
        }

        public ActivityBuilder setDescription(String description){
            obj.description = description;

            return this;
        }

        public ActivityBuilder setCost(Integer cost){
            obj.cost = cost;

            return this;
        }

        public ActivityBuilder setCapacity(Integer capacity){
            obj.capacity = capacity;

            return this;
        }

        public ActivityBuilder setDestination(Destination destination){
            obj.atDestination = destination;
            return this;
        }

        private Activity clone(Activity obj){

            Activity obj1 = new Activity();

            obj1.name = obj.name;
            obj1.description = obj.description;
            obj1.cost = obj.cost;
            obj1.capacity = obj.capacity;
            obj1.atDestination = obj.atDestination;
            obj1.enrolled = obj.enrolled;

            return obj1;
        }

        public Activity build(){

            if(obj.name == null)
                throw new NullPointerException("Name not set");

            if(obj.description == null)
                throw new NullPointerException("Description not set");

            if(obj.cost == null)
                throw new NullPointerException("Cost not set");

            if(obj.capacity == null)
                throw new NullPointerException("Capacity not set");

            return clone(obj);
        }
    }
}
