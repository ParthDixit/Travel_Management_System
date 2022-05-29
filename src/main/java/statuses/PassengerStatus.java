package statuses;

public interface PassengerStatus {

    String NOTFOUND = "Passenger not found.";
    String EXISTS = "Passenger already exists.";

    String PACKAGE_ALREADY_ENROLLED = "Passenger already enrolled to a package.";
    String PACKAGE_NOT_ENROLLED = "Passenger not enrolled to any package.";
    String ACTIVITY_ALREADY_ENROLLED = "Passenger already enrolled to the activity.";
    String INSUFFICIENT_BALANCE = "Passenger has insufficient balance.";
}
