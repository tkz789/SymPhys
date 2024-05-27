package symphys.symphys.numerical;

import java.util.function.Function;

public class RungeKutta4 {
    public static State step(State state, double dt, double mass, Function<State, Wektor> forceFunction) {
        Wektor r = state.position;
        Wektor v = state.velocity;
        double t = state.t;

        Wektor k1r = v.mul(dt);
        Wektor k1v = forceFunction.apply(state).mul(dt / mass);

        Wektor rTemp = r.add(k1r.mul(0.5));
        Wektor vTemp = v.add(k1v.mul(0.5));
        Wektor k2r = vTemp.mul(dt);
        Wektor k2v = forceFunction.apply(new State(rTemp, vTemp, t + dt / 2)).mul(dt / mass);

        rTemp = r.add(k2r.mul(0.5));
        vTemp = v.add(k2v.mul(0.5));
        Wektor k3r = vTemp.mul(dt);
        Wektor k3v = forceFunction.apply(new State(rTemp, vTemp, t + dt / 2)).mul(dt / mass);

        rTemp = r.add(k3r);
        vTemp = v.add(k3v);
        Wektor k4r = vTemp.mul(dt);
        Wektor k4v = forceFunction.apply(new State(rTemp, vTemp, t + dt)).mul(dt / mass);

        Wektor newR = r.add(k1r.add(k2r.mul(2)).add(k3r.mul(2)).add(k4r).mul(1.0 / 6.0));
        Wektor newV = v.add(k1v.add(k2v.mul(2)).add(k3v.mul(2)).add(k4v).mul(1.0 / 6.0));
        double newT = t + dt;

        return new State(newR, newV, newT);
    }
}
