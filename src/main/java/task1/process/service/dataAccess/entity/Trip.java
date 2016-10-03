package task1.process.service.dataAccess.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 28.09.2016.
 */
public class Trip {
    private Date exitTime;
    private Date entryTime;
    private List<Way> ways;
    private double finalFare = 0.;

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public List<Way> getWays() {
        if(ways == null){
            ways = new ArrayList<Way>();
        }
        return ways;
    }

    public double getFare() {
        return finalFare;
    }

    public void setFare(double fare) {
        this.finalFare = fare;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "exitTime=" + exitTime +
                ", entryTime=" + entryTime +
                ", ways=" + ways +
                ", finalFare=" + finalFare +
                '}';
    }
}
