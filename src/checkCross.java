public class checkCross {

    private boolean flag=false;

    public checkCross(boolean flag) {
        this.flag = flag;
    }

    public checkCross(Point start1, Point end1, Point start2, Point end2) {

        Point dir1=new Point(end1.getX() - start1.getX(),end1.getY() - start1.getY());
        Point dir2=new Point(end2.getX() - start2.getX(),end2.getY() - start2.getY());
        double a1=-dir1.getY();
        double b1=dir1.getX();
        double d1=-(a1*start1.getX()+b1*start1.getY());

        double a2=-dir2.getY();
        double b2=dir2.getX();
        double d2=-(a2*start2.getX()+b2*start2.getY());

        double seg1_line2_start = a2*start1.getX() + b2*start1.getY() + d2;
        double seg1_line2_end = a2*end1.getX() + b2* end1.getY() + d2;

        double seg2_line1_start = a1*start2.getX() + b1*start2.getY() + d1;
        double seg2_line1_end = a1*end2.getX() + b1*end2.getY() + d1;

        //если концы одного отрезка имеют один знак, значит он в одной полуплоскости и пересечения нет.
        if (seg1_line2_start * seg1_line2_end >= 0 || seg2_line1_start * seg2_line1_end >= 0){
            //System.out.println("Нет пересечния");
            flag=false;
        } else {
            //sizeCross=sizeCross+1;
            flag=true;
        }


        double u = seg1_line2_start / (seg1_line2_start - seg1_line2_end);
        //double out_intersection =  start1 + u*dir1;


    }

    public boolean isFlag() {
        return flag;
    }

}
