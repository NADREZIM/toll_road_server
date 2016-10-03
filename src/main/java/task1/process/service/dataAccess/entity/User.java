package task1.process.service.dataAccess.entity;

/**
 * Created by User on 28.09.2016.
 */
public class User {
    private int ordinalNumber;
    private String email;
    private Trip trip;

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(int ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Trip getTrip() {
        if(trip == null){
            trip = new Trip();
        }
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public String toString() {
        return "User{" +
                "ordinalNumber=" + ordinalNumber +
                ", email='" + email + '\'' +
                ", trip=" + trip +
                '}';
    }


}
