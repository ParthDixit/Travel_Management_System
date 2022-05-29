package statuses;

public interface ActivityStatus {

    String NOTFOUND = "Activity not found.";
    String EXISTS = "Activity already exists.";
    String FILLED = "Activity completely filled.";
    String ALREADY_ASSIGNED = "Activity already assigned to a destination.";
    String NOT_ASSIGNED = "Activity not assigned to a destination.";
    String NOT_AVAILABLE = "Activity not available.";

}
