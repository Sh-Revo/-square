public class Area {
    private double trapArea;
    private double max;
    private Point point1,point2, point3,point4;

    public Area(double largeBase, Position smallerBase, double rib) {
        trapArea=((largeBase+smallerBase.getDistanse())/2)*(Math.sqrt(rib*rib-Math.pow(largeBase-smallerBase.getDistanse(),2)/4));
    }

    public Area(double max, Point point1, Point point2, Point point3, Point point4) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        this.max = max;
    }

    public Area(double trapArea, double largeBase, Position smallerBase, double rib, double max, Point point1, Point point2, Point point3, Point point4) {
        this.trapArea = trapArea;
        this.max = max;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
    }

    public double getTrapArea() {
        return trapArea;
    }


    public double getMax() {
        return max;
    }


    public Point getPoint1() {
        return point1;
    }


    public Point getPoint2() {
        return point2;
    }


    public Point getPoint3() {
        return point3;
    }


    public Point getPoint4() {
        return point4;
    }

}
