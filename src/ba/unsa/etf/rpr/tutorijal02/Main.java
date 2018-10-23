package ba.unsa.etf.rpr.tutorijal02;

public class Main {

    public static void main(String[] args) {
        System.out.println("Nema ništa u mainu, pokrenite testove!");
        Interval i1 =new Interval(1.2, 2.5, true,true);
        Interval  i2= new Interval(1.2, 2.5, true, true);
        System.out.println(i1.equals(i2));
    }
}
