public class Interval implements Comparable<Interval>{
    private int id;
    private double xLow, yLow,xHigh, yHigh, max;


    public Interval(int id, double xLow, double yLow, double xHigh, double yHigh) {
        this.id = id;
        this.xLow = xLow;
        this.xHigh = xHigh;
        this.yLow = yLow;
        this.yHigh = yHigh;
        this.max = yHigh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getXLow() {
        return xLow;
    }

    public void setXLow(double xLow) {
        this.xLow = xLow;
    }

    public double getXHigh() {
        return xHigh;
    }

    public void setXHigh(double xHigh) {
        this.xHigh = xHigh;
    }

    public double getYLow() {
        return yLow;
    }

    public void setYLow(double yLow) {
        this.yLow = yLow;
    }

    public double getYHigh() {
        return yHigh;
    }

    public void setYHigh(double yHigh) {
        this.yHigh = yHigh;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", x_low=" + xLow +
                ", x_high=" + xHigh +
                ", y_low=" + yLow +
                ", y_high=" + yHigh +
                ", max=" + max +
                '}';
    }

    @Override
    public int compareTo(Interval interval) {
        if(this.getYLow() < interval.getYLow())
            return -1;
        else if(this.getYLow() > interval.getYLow())
            return 1;
        else
            return 0;
    }
}
