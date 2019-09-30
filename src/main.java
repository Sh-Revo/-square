public class main {
    public static void main(String[] args) {
       // double x=0,y=0;
       // second second = new second(0,0);
        int n=8;
        int i;
        second points[]=new second[n];
        second vectors[] =new second[n];
        double multiplication[] =new double[n];
        points[0]= new second(-2,2);
        points[1]=new second(-1,5);
        points[2]=new second(3,7);
        points[3]=new second(7,4) ;
        points[4]=new second(6,4);
        points[5]=new second(4,-3) ;
        points[6]=new second(1,-2);
        points[7]=new second(-1,-1);
        for (int j = 0; j <n ; j++) {

            if (multiplication[j] < 0) {
                if (j==n-1){
                    points[j-n+1]=points[j];
                } else
                points[j + 1] = points[j];
            }
       /* for (int i = 0; i < 5; i++) {
            System.out.println(i+": "+ points[i].getX()+"   "+points[i].getY());
        }*/
            //System.out.println("Vectors: ");
        for ( i = 0; i <= n-1; i++) {
            if (i==n-1){
                vectors[i]=new second(points[i].getX()-points[i-n+1].getX(),points[i].getY()-points[i-n+1].getY());
                //System.out.println(vectors[i].getX()+" "+vectors[i].getY());
            } else {
            vectors[i]=new second(points[i+1].getX()-points[i].getX(),points[i+1].getY()-points[i].getY());
            //System.out.println(vectors[i].getX()+" "+vectors[i].getY());
            }
        }

            //System.out.println("Proiz: ");
      //  vectors[0].setX(points[1].x-points[0].x);
        for ( i = 0; i <= n-1; i++) {
            if (i == n-1) {
                multiplication[i] = vectors[i].getX() * vectors[i-n+1].getX() + vectors[i].getY() * vectors[i - n+1].getY();
                //System.out.println(multiplication[i]);
            } else {
                multiplication[i] = vectors[i + 1].getX() * vectors[i].getX() + vectors[i].getY() * vectors[i + 1].getY();
                //System.out.println(multiplication[i]);
            }
        }
        }
        for (int j = 0; j <n ; j++) {
            System.out.println("New points[" + (j+1) + "]: "+points[j].getX()+" "+points[j].getY());
        }
        /*for (int j = 0; j <n ; j++) {
            System.out.println("new vector mult: "+multiplication[j]);
        }*/

    }
}
