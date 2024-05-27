package symphys.symphys.GivenForce;

import symphys.symphys.numerical.RungeKutta4;
import symphys.symphys.numerical.State;
import symphys.symphys.numerical.Wektor;

import java.util.function.Function;


public class PositionCalculator {

    State state;
    double mass;
    Function<State, Wektor> force;
    public PositionCalculator(Function<State, Wektor> force, Wektor v0, Wektor x0, double time, double mass){
        this.force = force;
        this.mass = mass;
        state = new State(x0, v0, time);
    }
    private Double last = null;
    public Wektor getForce(Wektor x, Wektor v, Double t){
        return force.apply(new State(x, v, t));
    }
    public Wektor getNextPosition(long l) {
        double time = l / 1000_000.;
        if (last == null){
            last = time;
            return state.position;
        }
        double dt = time - last;
        last = time;
        state = RungeKutta4.step(state, dt, mass, force);
        return state.position;
    }
}
