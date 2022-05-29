package repositories;

import models.activity.ActivityRecord;
import models.destination.Destination;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PassengerActivitiesRepositoryTest {

    Integer passengerNumber = 1;
    PassengerActivitiesRepository repository = PassengerActivitiesRepository.getInstance();
    ActivityRecord activityRecord = new ActivityRecord();

    @Test
    @Order(1)
    void save() {

        activityRecord.setName("jumping");
        activityRecord.setDestination("london");
        activityRecord.setPricePaid(10);

        assertEquals(activityRecord, repository.save(1, activityRecord));
    }

    @Test
    @Order(2)
    void findPassengerActivities() {

        List<ActivityRecord> activityRecordList = repository.findPassengerActivities(1);
        assertNotNull(activityRecordList);
        assertNull(repository.findPassengerActivities(123));
    }
}