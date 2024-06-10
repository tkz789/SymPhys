package symphys.symphys.pendulum;

import symphys.symphys.SimulationModel;
import symphys.symphys.numerical.State;
import symphys.symphys.numerical.Wektor;

public class PendulumModel implements SimulationModel {
    PendulumCalculator calculator;
    State bob;

    PendulumModel(PendulumCalculator calculator) {
        this.calculator = calculator;
        bob=new State(calculator.position(0), new Wektor(), 0);
    }
    public void step(double dt){
        dt*=10;
        Wektor przed = calculator.position(bob.t+dt), po=calculator.position(bob.t);
        bob.position=calculator.position(bob.t+dt);
        bob.t+=dt;
    }
}
