public class Segment {

    private double X,Y;
    private double a;
    private double b;

    public Segment(){

    }

    public Segment(double kA, double kB, double kC) {

        a=-kC/kA;
        b=-kC/kB;
    }

    public Segment(double a1, double b1,double a2, double b2) {

        X=(a2*b1*b2-a1*b1*b2)/(a2*b1-a1*b2);
        X=Math.round(X*1000);
        X=X/1000;
        Y=-((a1*b1-a1*X)/b1);
        Y=Math.round(Y*1000);
        Y=Y/1000;
    }


    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
}
