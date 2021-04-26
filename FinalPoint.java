public class FinalPoint implements Comparable<FinalPoint> {

    private double distance;
    private DataPoint data;

    public FinalPoint(double distance, DataPoint d) {
        this.distance = distance;
        this.data = d;
    }

    public double getDistance() {
        return distance;
    }

    public DataPoint getData() {
        return data;
    }

    public String toString() {
        return "Distance: " + distance;
    }

    @Override
    public int compareTo(FinalPoint o) {
        return (int)(this.getDistance() - o.getDistance());
    }
}
