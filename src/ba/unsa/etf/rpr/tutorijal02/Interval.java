package ba.unsa.etf.rpr.tutorijal02;
public class Interval {
    private double pocetna;
    private double krajnja;
    private boolean pripada1;
    private boolean pripada2;

    public Interval(double pocetna, double krajnja, boolean pripada1, boolean pripada2) throws IllegalArgumentException {
        if (pocetna > krajnja) throw new IllegalArgumentException("Pocetna tacka, veca od krajnje!");
        this.setPocetna(pocetna);
        this.setKrajnja(krajnja);
        this.setPripada1(pripada1); //da li poc.tacka pripada intervalu, ili ne
        this.setPripada2(pripada2); // da li krajnja tacka pripada intervalu, ili ne
    }
    public static boolean provjeraIntervala(Interval i1) throws  IllegalArgumentException{
        if (i1.getPocetna() > i1.getKrajnja()) throw new IllegalArgumentException("Pocetna tacka, veca od krajnje!");
        return true;
    }
    public Interval() {
        this.setPocetna(0);
        this.setKrajnja(0);
        this.setPripada1(false);
        this.setPripada2(false);
    }

    public boolean isNull() {
        if (getPocetna() == 0 && getKrajnja() == 0 && isPripada1() == false && isPripada2() == false) return true;
        return false;
    }

    public boolean isIn(double vrijednost) {
        if ((this.pripada1 == true && vrijednost >= pocetna) || (this.pripada1 == false && vrijednost > pocetna)) {
            if ((this.pripada2 == false && vrijednost < krajnja) || (this.pripada2 == true && vrijednost <= krajnja))
                return true;
        }
        return false;
    }
    public static Interval intersect(Interval i1, Interval i2) {
        Interval i = new Interval();
        if (equals(i1,i2)==true) return i1;
        if(provjeraIntervala(i1)==false || provjeraIntervala(i2)==false) return i;
        if (i1.getPocetna() > i2.getPocetna()) {
            i.setPocetna(i1.getPocetna());
            i.setPripada1(false);
            if ( i2.getKrajnja() < i1.getKrajnja()){
                i.setKrajnja(i2.getKrajnja());
                i.setPripada2(false);
            }else if(GraniceKraj(i1, i2)==true && i2.getKrajnja()<= i1.getKrajnja()){
                i.setKrajnja(i2.getKrajnja());
                i.setPripada2(true);
            }
        }
        else if (i1.getPocetna()<=i2.getPocetna()) {
            i.setPocetna(i2.getPocetna());
            if(GranicePoc(i1,i2)==true)i.setPripada1(true);
            else i.setPripada1(false);
            if (i2.getKrajnja() >= i1.getKrajnja()) {
                i.setKrajnja(i1.getKrajnja());
                if(GraniceKraj(i1,i2)==true) i.setPripada2(true);
                else i.setPripada2(false);
            }
            else if(i2.getKrajnja()<i1.getKrajnja()){
                i.setKrajnja(i2.getKrajnja());
                i.setPripada2(true);
            }
        }
        else  i= i1.intersect(i2);
        return i;
    }
    public Interval intersect(Interval i1){
        Interval i = new Interval();
        if (equals(i1,this)==true) return i1;
        if(provjeraIntervala(i1)==false || provjeraIntervala(this)==false) return i;
        Interval temp=new Interval();
        if (i1.getPocetna() >=this.getPocetna()) {
            i.setPocetna(i1.getPocetna());
            if(GranicePoc(i1,this)==true)i.setPripada1(true);
            else i.setPripada1(false);
            if ( this.getKrajnja() <= i1.getKrajnja()){
                i.setKrajnja(this.getKrajnja());
                if(GraniceKraj(i1,this)==true)i.setPripada2(true);
                else i.setPripada2(false);
            }else if( this.getKrajnja()> i1.getKrajnja()){
                i.setKrajnja(i1.getKrajnja());
                if(GraniceKraj(i1,this)==true)i.setPripada2(true);
                else i.setPripada2(false);
            }
        }
        else if (i1.getPocetna()<=this.getPocetna()) {
            i.setPocetna(this.getPocetna());
            if(GranicePoc(i1,this)==true)i.setPripada1(true);
            else i.setPripada1(false);
            if (this.getKrajnja() >= i1.getKrajnja()) {
                i.setKrajnja(i1.getKrajnja());
                if(GraniceKraj(i1,this)==true) i.setPripada2(true);
                else i.setPripada2(false);
            }
            else if(this.getKrajnja()<i1.getKrajnja()){
                i.setKrajnja(this.getKrajnja());
                i.setPripada2(true);
            }
        }
        return i;
    }

    public boolean equals (Interval i1){
        if(i1.isPripada1()==this.isPripada1()&& i1.isPripada2()==this.isPripada2())
            if( i1.getPocetna()==this.getPocetna()&& i1.getKrajnja()==this.getKrajnja())return true;
        return false;
    }

    public static boolean equals(Interval i1, Interval i2){
        if(i1.isPripada1()==i2.isPripada1()&& i2.isPripada2()==i1.isPripada2())
            if(i1.getPocetna()==i2.getPocetna()&& i1.getKrajnja()==i2.getKrajnja())return true;
        return false;
    }
    public static boolean GranicePoc(Interval i1, Interval i2){
        if(i1.isPripada1()==true && i2.isPripada1()== true )return true;
        return false;
    }
    public static boolean GraniceKraj(Interval i1 , Interval i2){
        if(i2.isPripada2()==true && i1.isPripada2()==true) return true;
        return false;
    }
    @Override
    public String toString(){
        String pocetna="(", krajnja=")";
        if (isNull()== true) return "()";
        if(isPripada1()==true) pocetna="[";
        if(isPripada2()==true)krajnja="]";
        return pocetna+ getPocetna() +","+ getKrajnja() +krajnja;
    }

    public double getPocetna() {
        return pocetna;
    }

    public void setPocetna(double pocetna) {
        this.pocetna = pocetna;
    }

    public double getKrajnja() {
        return krajnja;
    }

    public void setKrajnja(double krajnja) {
        this.krajnja = krajnja;
    }

    public boolean isPripada1() {
        return pripada1;
    }

    public void setPripada1(boolean pripada1) {
        this.pripada1 = pripada1;
    }

    public boolean isPripada2() {
        return pripada2;
    }

    public void setPripada2(boolean pripada2) {
        this.pripada2 = pripada2;
    }
}
