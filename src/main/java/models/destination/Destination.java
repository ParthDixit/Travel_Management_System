package models.destination;

import lombok.Getter;
import lombok.Setter;
import models.BaseModel;
import models.activity.Activity;
import models.travelPackage.Package;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Destination extends BaseModel {

    private String name;
    private List<Activity> activityList;

    private Destination(){
        this.activityList = new ArrayList<>();
    }

    public static DestinationBuilder getBuilder(){
        return new DestinationBuilder();
    }
    public static class DestinationBuilder{

        Destination obj = new Destination();

        public DestinationBuilder setName(String name){
            obj.name = name;
            return this;
        }

        public DestinationBuilder setActivityList (List<Activity> activityList){
            obj.activityList = activityList;
            return this;
        }

        private Destination clone(Destination obj){

            Destination obj1 = new Destination();

            obj1.name = obj.name;
            obj.activityList = new ArrayList<>(obj.activityList);

            return obj1;
        }

        public Destination build(){

            if(obj.name == null)
                throw new NullPointerException("Name not set");

            return clone(obj);
        }
    }
}
