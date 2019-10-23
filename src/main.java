public class main implements Varible{
    static Point validPoint[]=new Point[n];
    static AdditPoint[] additPoint;
    static Point[] allPoint;
    static Position[] position;
    static Line[] lines;
    static Line[] linesFar;
    static Line[] linesNear;

    static checkCross checkCross;
    static int crossCheckI[];
    static int crossCheckJ[];
    static Segment linesToSegmentFar[];
    static Segment linesToSegmentNear[];
    static Segment crossingSegment[];
    static Position[] distanceFar;
    static Position[] distanceNear;
    static Position[] distanceFarNear;
    static Point oldPoint, newPoint;
    static Position newDistanceNear;

    static Area[] trapArea;
    static Area massMaxTrapArea;
    static int sizeCross=0;

    public static void main(String[] args) {



        points[0]= new Point(-2.0,2.0);
        points[1]=new Point(-1.0,5.0);
        points[2]=new Point(3.0,7.0);
        points[3]=new Point(5.0,4.0) ;
        points[4]=new Point(6.0,2.0);
        points[5]=new Point(5.0,-1.0) ;
        points[6]=new Point(1.0,-3.0);
        points[7]=new Point(-1.0,-1.0);
        for (int j = 0; j < n; j++) {
            System.out.println("Old points[" + (j+1) + "]: "+points[j].getX()+" "+points[j].getY());
        }
        checking();
        foundNewPoints();
        foundDistanse();
        createAllLines();
        sort();
        writeMas();
        sortNearPoint();
        sortFarPoint();
        createMainLine();
        createLinesNear();
        checkCrossline();
        searchSegment();
        createDistance();
        foundArea();
        show();

    }
        static void checking(){
            int nullPoint=0;
            for (int i = 0; i <= n-1; i++) {
                if (i==n-1){
                    vectors[i]=new Point(points[i].getX()-points[i-n+1].getX(),points[i].getY()-points[i-n+1].getY());
                } else {
                    vectors[i]=new Point(points[i+1].getX()-points[i].getX(),points[i+1].getY()-points[i].getY());
                }

            }

            for (int i = 0; i <= n-1; i++) {
                if (i == n-1) {
                    multiplication[i] = vectors[i].getX() * vectors[i-n+1].getX() + vectors[i].getY() * vectors[i - n+1].getY();
                } else {
                    multiplication[i] = vectors[i + 1].getX() * vectors[i].getX() + vectors[i].getY() * vectors[i + 1].getY();
                }
            }
            for (int l = 0; l < multiplication.length; l++) {
                if (multiplication[l] < 0) {
                    points[l]=null;
                    nullPoint++;
                    validPoint=new Point[points.length-nullPoint];
                    System.arraycopy(points,0,validPoint,0,points.length-nullPoint);
                }
            }


        }
        static void createAllLines(){
            lines=new Line[allPoint.length];
            for (int i = 0; i <lines.length ; i++) {
                if (i==lines.length-1){
                    lines[i]=new Line(allPoint[i].getX(),allPoint[i-(lines.length-1)].getX(),allPoint[i].getY(),allPoint[i-(lines.length-1)].getY());
                } else{
                lines[i] =new Line(allPoint[i].getX(),allPoint[i+1].getX(),allPoint[i].getY(),allPoint[i+1].getY());}
            }
        }
        static void foundNewPoints(){
            additPoint = new AdditPoint[validPoint.length];
            allPoint = new Point[validPoint.length*2];
            for (int j = 0; j <validPoint.length ; j++) {
                if (j==validPoint.length-1) {
                    additPoint[j]=new AdditPoint(((validPoint[j].getX()+validPoint[j-(validPoint.length-1)].getX())/2),((validPoint[j].getY()+validPoint[j-(validPoint.length-1)].getY())/2));
                }
                else {
                    additPoint[j] = new AdditPoint(((validPoint[j].getX() + validPoint[j + 1].getX()) / 2), ((validPoint[j].getY() + validPoint[j + 1].getY()) / 2));

                }
            }
            for (int j = 0; j <allPoint.length ; j++) {
                allPoint[j]= j%2==0? validPoint[j/2]:additPoint[(j-1)/2];
            }
        }

        static void foundDistanse(){
            position = new Position[allPoint.length];
            for (int i = 0; i <position.length; i++) {
                position[i]=new Position(foreignPoint,allPoint[i]);
            }

        }
        static void sort(){
            int left=0;
            int right=position.length-1;
            while (left<=right){
                for (int i = right; i >left ; i--) {
                    if (position[i - 1].getDistanse() > position[i].getDistanse()){
                        Position buff=position[i];
                        position[i]=position[i-1];
                        position[i-1]=buff;
                    }
                } left++;
                for (int i = left; i <right ; i++) {
                    if (position[i].getDistanse() > position[i + 1].getDistanse()){
                        Position buff=position[i];
                        position[i]=position[i+1];
                        position[i+1]=buff;
                    }
                }right--;
            }

        }

        static void writeMas(){
            int g=0;
            for (int i = 0; i <position.length ; i++) {
                if (i<=massNear.length-1) {
                    massNear[i]= position[i].getPoint();
                }
                else if (i>=position.length-massDistant.length){
                    massDistant[g++]= position[i].getPoint();
                }
            }
        }

        static void createMainLine(){
            linesFar = new Line[massDistant.length];
            for (int i = 0; i <linesFar.length ; i++) {
                linesFar[i]= new Line(foreignPoint.getX(),massDistant[i].getX(),foreignPoint.getY(),massDistant[i].getY());
            }
        }

        static void createLinesNear(){
            linesNear = new Line[massNear.length];
            for (int i = 0; i <linesNear.length-1 ; i++) {
                linesNear[i]= new Line(massNear[i].getX(),massNear[i+1].getX(),massNear[i].getY(),massNear[i+1].getY());
            }
        }



        static void sortNearPoint(){
            int k[]=new int[massNear.length];
            for (int i = 0; i <massNear.length ; i++) {
                for (int j = 0; j <allPoint.length ; j++) {
                    if (massNear[i].getX()==allPoint[j].getX()&&massNear[i].getY()==allPoint[j].getY()){
                        k[i]=j;
                    }
                }
            }
            boolean sorted = false;
            int temp;
            while(!sorted) {
                sorted = true;
                for (int i = 0; i < k.length - 1; i++) {
                    if (k[i] > k[i+1]) {
                        temp = k[i];
                        k[i] = k[i+1];
                        k[i+1] = temp;
                        sorted = false;
                    }
                }
            }
            for (int i = 0; i <k.length ; i++) {
                massNear[i]=allPoint[k[i]];
            }
        }

        static void sortFarPoint(){
            int k[]=new int[massDistant.length];
            for (int i = 0; i <massDistant.length ; i++) {
                for (int j = 0; j <allPoint.length ; j++) {
                    if (massDistant[i].getX()==allPoint[j].getX()&&massDistant[i].getY()==allPoint[j].getY()){
                        k[i]=j;
                    }
                }
            }
            boolean sorted = false;
            int temp;
            while(!sorted) {
                sorted = true;
                for (int i = 0; i < k.length - 1; i++) {
                    if (k[i] > k[i+1]) {
                        temp = k[i];
                        k[i] = k[i+1];
                        k[i+1] = temp;
                        sorted = false;
                    }
                }
            }
            for (int i = 0; i <k.length ; i++) {
                massDistant[i]=allPoint[k[i]];
            }
        }

        static void checkCrossline(){


            for (int i = 0; i <massDistant.length ; i++) {
                for (int j = 0; j <massNear.length-1 ; j++) {
                    checkCross = new checkCross(foreignPoint, massDistant[i], massNear[j], massNear[j + 1]);
                        if (checkCross.isFlag() == true)
                            sizeCross++;
                    }
                }
            crossCheckI = new int[sizeCross];
            crossCheckJ = new int[sizeCross];
            for (int i = 0; i <massDistant.length ; i++) {
                for (int j = 0; j <massNear.length-1 ; j++) {

                        checkCross = new checkCross(foreignPoint, massDistant[i], massNear[j], massNear[j + 1]);
                        if (checkCross.isFlag() == true) {
                                 crossCheckI[i] = i;
                                 crossCheckJ[i] = j;
                }
                    }
                }
        }
        static void searchSegment(){
            linesToSegmentFar = new Segment[crossCheckI.length];
            linesToSegmentNear = new Segment[crossCheckJ.length];
            crossingSegment = new Segment[sizeCross];
            for (int i = 0; i <linesToSegmentFar.length ; i++) {
                linesToSegmentFar[i] = new Segment(linesFar[crossCheckI[i]].getA(),linesFar[crossCheckI[i]].getB(),linesFar[crossCheckI[i]].getC());
            }
            for (int i = 0; i <linesToSegmentNear.length ; i++) {
                linesToSegmentNear[i] = new Segment(linesNear[crossCheckJ[i]].getA(),linesNear[crossCheckJ[i]].getB(),linesNear[crossCheckJ[i]].getC());
            }
            for (int i = 0; i <crossingSegment.length ; i++) {
                crossingSegment[i] = new Segment(linesToSegmentFar[i].getA(),linesToSegmentFar[i].getB(),linesToSegmentNear[i].getA(),linesToSegmentNear[i].getB());
            }
        }
        static void createDistance(){
            distanceFar = new Position[massDistant.length - 1];
            distanceNear = new Position[massNear.length - 1];
            distanceFarNear = new Position[crossingSegment.length];

            for (int i = 0; i <distanceFar.length-1 ; i++) {
                distanceFar[i] = new Position(massDistant[crossCheckI[i]],massDistant[crossCheckI[i+1]]);
            }
            for (int i = 0; i <distanceNear.length-1 ; i++) {
                distanceNear[i] = new Position(massNear[crossCheckJ[i]],massNear[crossCheckJ[i]+1]);
            }
            for (int i = 0; i <distanceFarNear.length ; i++) {
                distanceFarNear[i] = new Position(massDistant[crossCheckI[i]],crossingSegment[i]);
            }
        }

        static void foundArea(){
            double min,max;
            double maxArea=0;
            double k;
            Point point1 = new Point(0.0,0.0);
            Point point2 = new Point(0.0,0.0);
            Point point3 = new Point(0.0,0.0);
            Point point4 = new Point(0.0,0.0);
            trapArea = new Area[distanceFarNear.length];
            //massMaxTrapArea = new Area[distanceFarNear.length];
            Position[] newDistanceFar = new Position[distanceFarNear.length];
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < newDistanceFar.length ; j++) {
                    newDistanceFar[j] = new Position(massDistant[crossCheckI[i]],massDistant[crossCheckI[j]]);
                }

            }
            for (int i = 0; i <distanceFarNear.length ; i++) {
                for (int j = 0; j < distanceFarNear.length; j++) {


                    if (distanceFarNear[i].getDistanse() < distanceFarNear[j].getDistanse()) {
                        min = distanceFarNear[i].getDistanse();
                        max = distanceFarNear[j].getDistanse();
                        oldPoint = new Point(crossingSegment[i].getX(), crossingSegment[i].getY());
                        k = Math.round((min / max) * 1000);
                        k = k / 1000;
                        newPoint = new Point(massDistant[crossCheckI[j]].getX() + (crossingSegment[j].getX() - massDistant[crossCheckI[j]].getX()) * k,
                                massDistant[crossCheckI[j]].getY() + (crossingSegment[j].getY() - massDistant[crossCheckI[j]].getY()) * k);
                        newDistanceNear = new Position(oldPoint, newPoint);
                        trapArea[j] = new Area(newDistanceFar[j].getDistanse(), newDistanceNear, min);
                        if (maxArea<trapArea[j].getTrapArea()){
                            maxArea= trapArea[j].getTrapArea();
                            point1 = new Point(massDistant[crossCheckI[i]].getX(),massDistant[crossCheckI[i]].getY());
                            point2 = new Point(massDistant[crossCheckI[j]].getX(),massDistant[crossCheckI[j]].getY());
                            point3 = new Point(oldPoint.getX(),oldPoint.getY());
                            point4 = new Point(newPoint.getX(),newPoint.getY());
                        }

                    } else {
                        min = distanceFarNear[j].getDistanse();
                        max = distanceFarNear[i].getDistanse();
                        oldPoint = new Point(crossingSegment[j].getX(), crossingSegment[j].getY());
                        k = Math.round((min / max) * 1000);
                        k = k / 1000;
                        newPoint = new Point(massDistant[crossCheckI[i]].getX() + (crossingSegment[i].getX() - massDistant[crossCheckI[i]].getX()) * k,
                                massDistant[crossCheckI[i]].getY() + (crossingSegment[i].getY() - massDistant[crossCheckI[i]].getY()) * k);
                        newDistanceNear = new Position(newPoint, oldPoint);
                        trapArea[j] = new Area(newDistanceFar[j].getDistanse(), newDistanceNear, min);
                        if (maxArea<trapArea[j].getTrapArea()){
                            maxArea= trapArea[j].getTrapArea();
                            point1 = new Point(massDistant[crossCheckI[i]].getX(),massDistant[crossCheckI[i]].getY());
                            point2 = new Point(massDistant[crossCheckI[j]].getX(),massDistant[crossCheckI[j]].getY());
                            point3 = new Point(oldPoint.getX(),oldPoint.getY());
                            point4 = new Point(newPoint.getX(),newPoint.getY());
                        }
                    }

                }
                massMaxTrapArea = new Area(maxArea,point1,point2,point3,point4);

            }
            //for (int i = 0; i <massMaxTrapArea.length ; i++) {
                //System.out.println("New Area:"+massMaxTrapArea.getMax());
            //}


        }

        static void show(){

            System.out.println("\nТочки при которых Фигура выпуклая\n");
            for (int i = 0; i <allPoint.length ; i++) {
                System.out.println("All point:"+allPoint[i].getX()+" "+allPoint[i].getY());
            }
            System.out.println();
            System.out.println("New Area:"+massMaxTrapArea.getMax()+"\n");
            System.out.println("Points Area:"+"\nPoint1:"+massMaxTrapArea.getPoint1().getX()+"  "+massMaxTrapArea.getPoint1().getY()+"\nPoint2:"+massMaxTrapArea.getPoint2().getX()+"  "+massMaxTrapArea.getPoint2().getY()+
                    "\nPoint3:"+massMaxTrapArea.getPoint3().getX()+"  "+massMaxTrapArea.getPoint3().getY()+"\nPoint4:"+massMaxTrapArea.getPoint4().getX()+"  "+massMaxTrapArea.getPoint4().getY());






    }

}
