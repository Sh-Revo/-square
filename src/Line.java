public class Line {
    private double a,b,c;
    private double x,y;

    public Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Line (double x1, double x2, double y1, double y2){
        a=1/(y2-y1);
        a=Math.round(a*1000);
        a=a/1000;
        b=1/(x2-x1);
        b=Math.round(b*1000);
        b=b/1000;
        c=y1/(y2-y1)-x1/(x2-x1);
        c=Math.round(c*1000);
        c=c/1000;

    }
    public Line (double a1, double b1, double c1, double a2, double b2, double c2){
        x=(a1*c2-a2*c1)/(a2*b1-a1*b2);
        y=(b1*x+c1)/a1;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

}
