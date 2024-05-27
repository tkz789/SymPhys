package symphys.symphys.fields.sim;

import symphys.symphys.numerical.Wektor;

public class Simulation {
    boolean gravity_on=false, electrostatics_on=true;
    double t=0;
    public double dt=0.03;
    public Body[] bodies;
    public BodyState[] states;

    Simulation() {}

    public Simulation(int n) {
        bodies = new Body[n];
        states = new BodyState[n];
    }

    public void step() {
        t += dt;
        BodyState[] newStates = new BodyState[states.length];
        for (int i=0; i!=states.length; ++i) {
            newStates[i] = states[i].copy();
            Wektor force = new Wektor();
            if (gravity_on) force = force.add(newStates[i].calcForce(states, Physics::calcGravityForce));
            if (electrostatics_on) force = force.add(newStates[i].calcForce(states, Physics::calcCoulombForce));
            newStates[i].applyForce(force, dt);
        }
        states = newStates;
    }

    public void dumpPositions() {
        System.out.println("----\nt="+t);
        for (int i=0; i<states.length; ++i) {
            System.out.println(i+": "+states[i].position);
        }
    }
}
