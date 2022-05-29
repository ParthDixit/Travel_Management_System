package repositories;

import models.activity.Activity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ActivityRepositoryTest {

    Activity activity = Activity.getBuilder().setName("jumping")
            .setCapacity(10).setCost(100).setDescription("jump").build();

    ActivityRepository repository = ActivityRepository.getInstance();

    @Test
    @Order(1)
    void save() {

        assertEquals(activity, repository.save(activity));
        assertNull(repository.save(activity));
    }

    @Test
    @Order(2)
    void findActivityByName() {
        Activity returned = repository.findActivityByName(activity.getName());

        assertEquals(activity.getName(), returned.getName());
        assertNull(repository.findActivityByName("Not present"));
    }

    @Test
    @Order(3)
    void findActivitiesByAvailability() {

        List<Activity> activityList = repository.findActivitiesByAvailability();
        assertNotNull(activityList);
    }
}