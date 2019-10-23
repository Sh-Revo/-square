public class Position {
    private Point point;
    private double distanse;

    public Position(Point point, double distanse) {
        this.point = point;
        this.distanse = distanse;
    }

    public Position(Point localPoint, Point point){
        this.point=point;
        distanse=Math.sqrt(Math.pow(localPoint.getX()-point.getX(),2)+Math.pow(localPoint.getY()-point.getY(),2));
        distanse= Math.round(distanse*1000);
        distanse=distanse/1000;
    }
    public Position(Point localPoint,Segment point2){
        distanse=Math.sqrt(Math.pow(localPoint.getX()-point2.getX(),2)+Math.pow(localPoint.getY()-point2.getY(),2));
        distanse= Math.round(distanse*1000);
        distanse=distanse/1000;
    }

    public Point getPoint() {
        return point;
    }


    public double getDistanse() {
        return distanse;
    }

}
