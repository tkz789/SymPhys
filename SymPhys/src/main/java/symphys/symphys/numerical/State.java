package symphys.symphys.numerical;

public class State {
    public Wektor position;
    public Wektor velocity;
    public double t;

    public State(Wektor position, Wektor velocity, double t) {
        this.position = position;
        this.velocity = velocity;
        this.t = t;
    }
}