package task1.process.service.dataAccess.entity;

/**
 * Created by User on 28.09.2016.
 */
public class Way {
    private int startPoint;
    private int endPoint;
    private double localFare;

    public Way() {
    }

    public Way(int startPoint, int endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.localFare = 0;
    }
    public Way(int startPoint, int endPoint, double localFare) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.localFare = localFare;
    }

    public double getLocalFare() {
        return localFare;
    }

    public void setLocalFare(double localFare) {
        this.localFare = localFare;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        return "Way{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", localFare=" + localFare +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Way way = (Way) o;

        if (endPoint != way.endPoint) return false;
        if (startPoint != way.startPoint) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = startPoint;
        result = 31 * result + endPoint;
        temp = Double.doubleToLongBits(localFare);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
