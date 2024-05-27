package symphys.symphys.fields.sim;

public class Body {
    public double mass, charge;
    public boolean movableX=true, movableY=true;

    @Override
    public String toString() {
        return "[Body m="+mass+" q="+charge+" movable=("+movableX+", "+movableY+")]";
    }
}
